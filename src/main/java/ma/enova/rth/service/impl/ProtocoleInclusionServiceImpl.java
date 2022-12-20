package ma.enova.rth.service.impl;

import ma.enova.rth.common.bean.AuditEntityDto;
import ma.enova.rth.common.enumeration.ACTION_TYPE;
import ma.enova.rth.common.exception.EntityNotFoundException;
import ma.enova.rth.common.util.Utils;
import ma.enova.rth.dao.criteria.core.ProtocoleInclusionCriteria;
import ma.enova.rth.dao.criteria.history.HistProtocoleInclusionCriteria;
import ma.enova.rth.dao.facade.core.IProtocoleInclusionRepository;
import ma.enova.rth.dao.facade.history.IHistProtocoleInclusionRepository;
import ma.enova.rth.dao.specifications.core.ProtocoleInclusionSpecification;
import ma.enova.rth.dao.specifications.history.HistProtocoleInclusionSpecification;
import ma.enova.rth.domain.core.ProtocoleInclusion;
import ma.enova.rth.domain.historique.HistProtocoleInclusion;
import ma.enova.rth.dto.EtablissementDto;
import ma.enova.rth.dto.ProtocoleInclusionDto;
import ma.enova.rth.security.UtilisateurDetailsImpl;
import ma.enova.rth.service.facade.IProtocoleInclusionService;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
/**
 * Implementation du service IprotocoleInclusion
 * @author JAF
 * @version 1.2
 */

@Service(value = "protocoleInclusionService")
public class ProtocoleInclusionServiceImpl implements IProtocoleInclusionService {

	@Autowired
	private IProtocoleInclusionRepository protocoleInclusionRepository;
	
	@Autowired
	private IHistProtocoleInclusionRepository histProtocoleInclusionRepository;



	/**
	 * createProtocoleInclusion.
	 * 
	 * @param protocoleInclusion
	 * @return ProtocoleInclusion
	 * @throws Exception
	 */
	 
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public ProtocoleInclusionDto createProtocoleInclusion(ProtocoleInclusionDto protocoleInclusionDto) throws Exception {

		UtilisateurDetailsImpl currentUser = (UtilisateurDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (currentUser.getEtablissement() != null)
			protocoleInclusionDto.setEtablissement(new EtablissementDto(currentUser.getEtablissement().getId()));	

		ProtocoleInclusion protocoleInclusion = new ProtocoleInclusion();
		protocoleInclusion = protocoleInclusionDto.convertToEntity(protocoleInclusion, protocoleInclusionDto);
		ProtocoleInclusion newProtocoleInclusion = protocoleInclusionRepository.save(protocoleInclusion);
		protocoleInclusionDto.setId(newProtocoleInclusion.getId());		



			
		return protocoleInclusionDto;
	}
	
	/**
	 * updateProtocoleInclusion.
	 * 
	 * @param protocoleInclusion
	 * @return ProtocoleInclusion
	 * @throws Exception
	 */
	 
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)	 	
	public ProtocoleInclusionDto updateProtocoleInclusion(ProtocoleInclusionDto protocoleInclusionDto) throws Exception {

		UtilisateurDetailsImpl currentUser = (UtilisateurDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		saveProtocoleInclusionAuditData(protocoleInclusionDto, ACTION_TYPE.UPDATE);

		ProtocoleInclusion protocoleInclusion = protocoleInclusionRepository.findById(protocoleInclusionDto.getId()).orElseThrow(() -> new EntityNotFoundException("errors.notFound", new String[] { ProtocoleInclusion.class.getSimpleName(), protocoleInclusionDto.getId().toString() }));
		protocoleInclusion = protocoleInclusionDto.convertToEntity(protocoleInclusion, protocoleInclusionDto);
		protocoleInclusionRepository.saveAndFlush(protocoleInclusion);
		
		return protocoleInclusionDto;
	}
	
	/**
	 * getProtocoleInclusionById.
	 * 
	 * @param protocoleInclusionId
	 * @return ProtocoleInclusion
	 * @throws Exception
	 */
	public ProtocoleInclusionDto getProtocoleInclusionById(Long protocoleInclusionId) throws Exception {

		ProtocoleInclusion protocoleInclusion = protocoleInclusionRepository.findById(protocoleInclusionId).orElseThrow(() -> new EntityNotFoundException("errors.notFound", new String[] { ProtocoleInclusion.class.getSimpleName(), protocoleInclusionId.toString() }));

		return  new ProtocoleInclusionDto(protocoleInclusion, true, 0);

	}
	

	/**
	 * findProtocoleInclusionsByCriteria.
	 * 
	 * @param protocoleInclusionCriteria
	 * @return List<ProtocoleInclusion>
	 * @throws Exception
	 */	
	public List<ProtocoleInclusionDto> findProtocoleInclusionsByCriteria(ProtocoleInclusionCriteria protocoleInclusionCriteria) throws Exception {
					 
		UtilisateurDetailsImpl currentUser = (UtilisateurDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		protocoleInclusionCriteria.setEtablissementId(currentUser.getEtablissement() != null ? currentUser.getEtablissement().getId() : null);		
		
		Specification<ProtocoleInclusion> specification = new ProtocoleInclusionSpecification(protocoleInclusionCriteria);
		
		if (protocoleInclusionCriteria.isPeagable()) {
			Pageable pageable = PageRequest.of(0, protocoleInclusionCriteria.getMaxResults());
			return protocoleInclusionRepository.findAll(specification, pageable).getContent().stream().map(protocoleInclusion -> new ProtocoleInclusionDto(protocoleInclusion)).collect(Collectors.toList());

		} else {
			return protocoleInclusionRepository.findAll(specification).stream().map(protocoleInclusion -> new ProtocoleInclusionDto(protocoleInclusion)).collect(Collectors.toList());
		}
		
	}
	
	/**
	 * findProtocoleInclusionByCriteria.
	 * 
	 * @param protocoleInclusionCriteria
	 * @return ProtocoleInclusion 
	 * @throws Exception
	 */
	
	public ProtocoleInclusionDto findProtocoleInclusionByCriteria(ProtocoleInclusionCriteria  protocoleInclusionCriteria) throws Exception {

		UtilisateurDetailsImpl currentUser = (UtilisateurDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		protocoleInclusionCriteria.setEtablissementId(currentUser.getEtablissement() != null ? currentUser.getEtablissement().getId() : null);		
		
		Specification<ProtocoleInclusion> specification = new ProtocoleInclusionSpecification(protocoleInclusionCriteria);
		
		ProtocoleInclusion protocoleInclusion = protocoleInclusionRepository.findOne(specification).orElse(null);
		
		ProtocoleInclusionDto protocoleInclusionDto = null;
		if (protocoleInclusion != null) {
			protocoleInclusionDto = new ProtocoleInclusionDto();
			return new ProtocoleInclusionDto(protocoleInclusion, true);
		}

		return protocoleInclusionDto;
	}

	/**
	 * paginatedListProtocoleInclusions.
	 * 
	 * @param protocoleInclusionCriteria
	 * @param page
	 * @param pageSize
	 * @param order
	 * @param sortField
	 * @return List<ProtocoleInclusion>
	 * @throws Exception
	 */
	 
	public List<ProtocoleInclusionDto> paginatedListProtocoleInclusions(ProtocoleInclusionCriteria protocoleInclusionCriteria,int page,int pageSize, String order, String sortField) throws Exception {

		UtilisateurDetailsImpl currentUser = (UtilisateurDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		protocoleInclusionCriteria.setEtablissementId(currentUser.getEtablissement() != null ? currentUser.getEtablissement().getId() : null);		
		
		Specification<ProtocoleInclusion> specification = new ProtocoleInclusionSpecification(protocoleInclusionCriteria);
		order = order != null && !order.isEmpty() ? order : "desc";
		sortField = sortField != null && !sortField.isEmpty() ? sortField : "id";
		Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);

		return protocoleInclusionRepository.findAll(specification, pageable).getContent().stream().map(protocoleInclusion -> new ProtocoleInclusionDto(protocoleInclusion)).collect(Collectors.toList());
	}
	
	/**
	 * getProtocoleInclusionDataSize.
	 * 
	 * @param protocoleInclusionCriteria
	 * @return int
	 * @throws Exception
	 */
	public int getProtocoleInclusionDataSize(ProtocoleInclusionCriteria protocoleInclusionCriteria) throws Exception {
	
		UtilisateurDetailsImpl currentUser = (UtilisateurDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		protocoleInclusionCriteria.setEtablissementId(currentUser.getEtablissement() != null ? currentUser.getEtablissement().getId() : null);		
		
		Specification<ProtocoleInclusion> specification = new ProtocoleInclusionSpecification(protocoleInclusionCriteria, true);
		
		return ((Long)  protocoleInclusionRepository.count(specification)).intValue();
		
	}
	
	/**
	 * deleteProtocoleInclusion.
	 * 
	 * @param protocoleInclusionList
	 * @throws Exception
	 */
	 
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)	 
	public void deleteProtocoleInclusion(List<ProtocoleInclusionDto> protocoleInclusionList) throws Exception {

		UtilisateurDetailsImpl currentUser = (UtilisateurDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (protocoleInclusionList != null)
			for (ProtocoleInclusionDto protocoleInclusionDto : protocoleInclusionList) {
					ProtocoleInclusion toBeDeleted = protocoleInclusionRepository.findById(protocoleInclusionDto.getId()).orElseThrow(() -> new EntityNotFoundException("errors.notFound", new String[] { ProtocoleInclusion.class.getSimpleName(), protocoleInclusionDto.getId().toString() }));

					protocoleInclusionRepository.delete(toBeDeleted);

					HistProtocoleInclusion histProtocoleInclusion = new HistProtocoleInclusion();
					histProtocoleInclusion.setActionType(ACTION_TYPE.DELETE.name());
					histProtocoleInclusion.setObjectName("protocoleInclusion");
					histProtocoleInclusion.setObjectId(protocoleInclusionDto.getId());
					String protocoleInclusionValue =  new ObjectMapper().writeValueAsString(protocoleInclusionDto);
					histProtocoleInclusion.setData(protocoleInclusionValue);
					histProtocoleInclusion.setUserId(currentUser.getId());
					histProtocoleInclusion.setUsername(currentUser.getUsername());
					histProtocoleInclusion.setDate(LocalDateTime.now());
					histProtocoleInclusionRepository.save(histProtocoleInclusion);					
			}
	}
	
	

	
	private void saveProtocoleInclusionAuditData(ProtocoleInclusionDto protocoleInclusion, ACTION_TYPE action) throws Exception {

		ProtocoleInclusionDto oldProtocoleInclusion = getProtocoleInclusionById(protocoleInclusion.getId());
		if (Utils.compareObjectsDiff(protocoleInclusion, oldProtocoleInclusion)) {

			UtilisateurDetailsImpl currentUser = (UtilisateurDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			HistProtocoleInclusion histProtocoleInclusion = new HistProtocoleInclusion();
			histProtocoleInclusion.setActionType(action.name());
			histProtocoleInclusion.setObjectName("protocoleInclusion");
			histProtocoleInclusion.setObjectId(protocoleInclusion.getId());
			histProtocoleInclusion.setUserId(currentUser.getId());
			histProtocoleInclusion.setUsername(currentUser.getUsername());
			String protocoleInclusionValue =  new ObjectMapper().writeValueAsString(protocoleInclusion);
			histProtocoleInclusion.setData(protocoleInclusionValue);
			histProtocoleInclusion.setDate(LocalDateTime.now());
			histProtocoleInclusionRepository.save(histProtocoleInclusion);
		}
	}	
	
	/**
	 * getHistProtocoleInclusionById.
	 * 
	 * @param histProtocoleInclusionId
	 * @return AuditEntityDto
	 * @throws Exception
	 */
	public AuditEntityDto getHistProtocoleInclusionById(Long histProtocoleInclusionId) throws Exception {

		HistProtocoleInclusion histProtocoleInclusion = histProtocoleInclusionRepository.findById(histProtocoleInclusionId).orElseThrow(() -> new EntityNotFoundException("errors.notFound", new String[] { HistProtocoleInclusion.class.getSimpleName(), histProtocoleInclusionId.toString() }));

			return  new AuditEntityDto(histProtocoleInclusion);

	}
	

	/**
	 * paginatedListHistProtocoleInclusions.
	 * 
	 * @param histProtocoleInclusionCriteria
	 * @param page
	 * @param pageSize
	 * @param order
	 * @param sortField
	 * @return List<HistProtocoleInclusion>
	 * @throws Exception
	 */
	 
	public List<AuditEntityDto> paginatedListHistProtocoleInclusions(HistProtocoleInclusionCriteria histProtocoleInclusionCriteria,int page,int pageSize, String order, String sortField) throws Exception {

		Specification<HistProtocoleInclusion> specification = new HistProtocoleInclusionSpecification(histProtocoleInclusionCriteria);
		order = order != null && !order.isEmpty() ? order : "desc";
		sortField = sortField != null && !sortField.isEmpty() ? sortField : "id";
		Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);

		return histProtocoleInclusionRepository.findAll(specification, pageable).getContent().stream().map(protocoleInclusion -> new AuditEntityDto(protocoleInclusion)).collect(Collectors.toList());
	}

	/**
	 * findProtocoleInclusionsHistByCriteria.
	 * 
	 * @param HistProtocoleInclusionCriteria
	 * @return List<HistProtocoleInclusion>
	 * @throws Exception
	 */	
	public List<AuditEntityDto> findProtocoleInclusionsHistByCriteria(HistProtocoleInclusionCriteria histProtocoleInclusionCriteria) throws Exception {
					 
		Specification<HistProtocoleInclusion> specification = new HistProtocoleInclusionSpecification(histProtocoleInclusionCriteria);
		
		if (histProtocoleInclusionCriteria.isPeagable()) {
			Pageable pageable = PageRequest.of(0, histProtocoleInclusionCriteria.getMaxResults());
			return histProtocoleInclusionRepository.findAll(specification, pageable).getContent().stream().map(protocoleInclusion -> new AuditEntityDto(protocoleInclusion)).collect(Collectors.toList());

		} else {
			return histProtocoleInclusionRepository.findAll(specification).stream().map(protocoleInclusion -> new AuditEntityDto(protocoleInclusion)).collect(Collectors.toList());
		}
		
	}

	/**
	 * getHistProtocoleInclusionDataSize.
	 * 
	 * @param histProtocoleInclusionCriteria
	 * @return int
	 * @throws Exception
	 */
	public int getHistProtocoleInclusionDataSize(HistProtocoleInclusionCriteria histProtocoleInclusionCriteria) throws Exception {
	
		Specification<HistProtocoleInclusion> specification = new HistProtocoleInclusionSpecification(histProtocoleInclusionCriteria, true);
		
		return ((Long)  histProtocoleInclusionRepository.count(specification)).intValue();
		
	}	

}