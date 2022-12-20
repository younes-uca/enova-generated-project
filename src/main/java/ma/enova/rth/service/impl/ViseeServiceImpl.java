package ma.enova.rth.service.impl;

import ma.enova.rth.common.bean.AuditEntityDto;
import ma.enova.rth.common.enumeration.ACTION_TYPE;
import ma.enova.rth.common.exception.EntityNotFoundException;
import ma.enova.rth.common.util.Utils;
import ma.enova.rth.dao.criteria.core.ViseeCriteria;
import ma.enova.rth.dao.criteria.history.HistViseeCriteria;
import ma.enova.rth.dao.facade.core.IViseeRepository;
import ma.enova.rth.dao.facade.history.IHistViseeRepository;
import ma.enova.rth.dao.specifications.core.ViseeSpecification;
import ma.enova.rth.dao.specifications.history.HistViseeSpecification;
import ma.enova.rth.domain.core.Visee;
import ma.enova.rth.domain.historique.HistVisee;
import ma.enova.rth.dto.EtablissementDto;
import ma.enova.rth.dto.ViseeDto;
import ma.enova.rth.security.UtilisateurDetailsImpl;
import ma.enova.rth.service.facade.IViseeService;
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
 * Implementation du service Ivisee
 * @author JAF
 * @version 1.2
 */

@Service(value = "viseeService")
public class ViseeServiceImpl implements IViseeService {

	@Autowired
	private IViseeRepository viseeRepository;
	
	@Autowired
	private IHistViseeRepository histViseeRepository;



	/**
	 * createVisee.
	 * 
	 * @param visee
	 * @return Visee
	 * @throws Exception
	 */
	 
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public ViseeDto createVisee(ViseeDto viseeDto) throws Exception {

		UtilisateurDetailsImpl currentUser = (UtilisateurDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (currentUser.getEtablissement() != null)
			viseeDto.setEtablissement(new EtablissementDto(currentUser.getEtablissement().getId()));	

		Visee visee = new Visee();
		visee = viseeDto.convertToEntity(visee, viseeDto);
		Visee newVisee = viseeRepository.save(visee);
		viseeDto.setId(newVisee.getId());		



			
		return viseeDto;
	}
	
	/**
	 * updateVisee.
	 * 
	 * @param visee
	 * @return Visee
	 * @throws Exception
	 */
	 
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)	 	
	public ViseeDto updateVisee(ViseeDto viseeDto) throws Exception {

		UtilisateurDetailsImpl currentUser = (UtilisateurDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		saveViseeAuditData(viseeDto, ACTION_TYPE.UPDATE);

		Visee visee = viseeRepository.findById(viseeDto.getId()).orElseThrow(() -> new EntityNotFoundException("errors.notFound", new String[] { Visee.class.getSimpleName(), viseeDto.getId().toString() }));
		visee = viseeDto.convertToEntity(visee, viseeDto);
		viseeRepository.saveAndFlush(visee);
		
		return viseeDto;
	}
	
	/**
	 * getViseeById.
	 * 
	 * @param viseeId
	 * @return Visee
	 * @throws Exception
	 */
	public ViseeDto getViseeById(Long viseeId) throws Exception {

		Visee visee = viseeRepository.findById(viseeId).orElseThrow(() -> new EntityNotFoundException("errors.notFound", new String[] { Visee.class.getSimpleName(), viseeId.toString() }));

		return  new ViseeDto(visee, true, 0);

	}
	

	/**
	 * findViseesByCriteria.
	 * 
	 * @param viseeCriteria
	 * @return List<Visee>
	 * @throws Exception
	 */	
	public List<ViseeDto> findViseesByCriteria(ViseeCriteria viseeCriteria) throws Exception {
					 
		UtilisateurDetailsImpl currentUser = (UtilisateurDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		viseeCriteria.setEtablissementId(currentUser.getEtablissement() != null ? currentUser.getEtablissement().getId() : null);		
		
		Specification<Visee> specification = new ViseeSpecification(viseeCriteria);
		
		if (viseeCriteria.isPeagable()) {
			Pageable pageable = PageRequest.of(0, viseeCriteria.getMaxResults());
			return viseeRepository.findAll(specification, pageable).getContent().stream().map(visee -> new ViseeDto(visee)).collect(Collectors.toList());

		} else {
			return viseeRepository.findAll(specification).stream().map(visee -> new ViseeDto(visee)).collect(Collectors.toList());
		}
		
	}
	
	/**
	 * findViseeByCriteria.
	 * 
	 * @param viseeCriteria
	 * @return Visee 
	 * @throws Exception
	 */
	
	public ViseeDto findViseeByCriteria(ViseeCriteria  viseeCriteria) throws Exception {

		UtilisateurDetailsImpl currentUser = (UtilisateurDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		viseeCriteria.setEtablissementId(currentUser.getEtablissement() != null ? currentUser.getEtablissement().getId() : null);		
		
		Specification<Visee> specification = new ViseeSpecification(viseeCriteria);
		
		Visee visee = viseeRepository.findOne(specification).orElse(null);
		
		ViseeDto viseeDto = null;
		if (visee != null) {
			viseeDto = new ViseeDto();
			return new ViseeDto(visee, true);
		}

		return viseeDto;
	}

	/**
	 * paginatedListVisees.
	 * 
	 * @param viseeCriteria
	 * @param page
	 * @param pageSize
	 * @param order
	 * @param sortField
	 * @return List<Visee>
	 * @throws Exception
	 */
	 
	public List<ViseeDto> paginatedListVisees(ViseeCriteria viseeCriteria,int page,int pageSize, String order, String sortField) throws Exception {

		UtilisateurDetailsImpl currentUser = (UtilisateurDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		viseeCriteria.setEtablissementId(currentUser.getEtablissement() != null ? currentUser.getEtablissement().getId() : null);		
		
		Specification<Visee> specification = new ViseeSpecification(viseeCriteria);
		order = order != null && !order.isEmpty() ? order : "desc";
		sortField = sortField != null && !sortField.isEmpty() ? sortField : "id";
		Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);

		return viseeRepository.findAll(specification, pageable).getContent().stream().map(visee -> new ViseeDto(visee)).collect(Collectors.toList());
	}
	
	/**
	 * getViseeDataSize.
	 * 
	 * @param viseeCriteria
	 * @return int
	 * @throws Exception
	 */
	public int getViseeDataSize(ViseeCriteria viseeCriteria) throws Exception {
	
		UtilisateurDetailsImpl currentUser = (UtilisateurDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		viseeCriteria.setEtablissementId(currentUser.getEtablissement() != null ? currentUser.getEtablissement().getId() : null);		
		
		Specification<Visee> specification = new ViseeSpecification(viseeCriteria, true);
		
		return ((Long)  viseeRepository.count(specification)).intValue();
		
	}
	
	/**
	 * deleteVisee.
	 * 
	 * @param viseeList
	 * @throws Exception
	 */
	 
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)	 
	public void deleteVisee(List<ViseeDto> viseeList) throws Exception {

		UtilisateurDetailsImpl currentUser = (UtilisateurDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (viseeList != null)
			for (ViseeDto viseeDto : viseeList) {
					Visee toBeDeleted = viseeRepository.findById(viseeDto.getId()).orElseThrow(() -> new EntityNotFoundException("errors.notFound", new String[] { Visee.class.getSimpleName(), viseeDto.getId().toString() }));

					viseeRepository.delete(toBeDeleted);

					HistVisee histVisee = new HistVisee();
					histVisee.setActionType(ACTION_TYPE.DELETE.name());
					histVisee.setObjectName("visee");
					histVisee.setObjectId(viseeDto.getId());
					String viseeValue =  new ObjectMapper().writeValueAsString(viseeDto);
					histVisee.setData(viseeValue);
					histVisee.setUserId(currentUser.getId());
					histVisee.setUsername(currentUser.getUsername());
					histVisee.setDate(LocalDateTime.now());
					histViseeRepository.save(histVisee);					
			}
	}
	
	

	
	private void saveViseeAuditData(ViseeDto visee, ACTION_TYPE action) throws Exception {

		ViseeDto oldVisee = getViseeById(visee.getId());
		if (Utils.compareObjectsDiff(visee, oldVisee)) {

			UtilisateurDetailsImpl currentUser = (UtilisateurDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			HistVisee histVisee = new HistVisee();
			histVisee.setActionType(action.name());
			histVisee.setObjectName("visee");
			histVisee.setObjectId(visee.getId());
			histVisee.setUserId(currentUser.getId());
			histVisee.setUsername(currentUser.getUsername());
			String viseeValue =  new ObjectMapper().writeValueAsString(visee);
			histVisee.setData(viseeValue);
			histVisee.setDate(LocalDateTime.now());
			histViseeRepository.save(histVisee);
		}
	}	
	
	/**
	 * getHistViseeById.
	 * 
	 * @param histViseeId
	 * @return AuditEntityDto
	 * @throws Exception
	 */
	public AuditEntityDto getHistViseeById(Long histViseeId) throws Exception {

		HistVisee histVisee = histViseeRepository.findById(histViseeId).orElseThrow(() -> new EntityNotFoundException("errors.notFound", new String[] { HistVisee.class.getSimpleName(), histViseeId.toString() }));

			return  new AuditEntityDto(histVisee);

	}
	

	/**
	 * paginatedListHistVisees.
	 * 
	 * @param histViseeCriteria
	 * @param page
	 * @param pageSize
	 * @param order
	 * @param sortField
	 * @return List<HistVisee>
	 * @throws Exception
	 */
	 
	public List<AuditEntityDto> paginatedListHistVisees(HistViseeCriteria histViseeCriteria,int page,int pageSize, String order, String sortField) throws Exception {

		Specification<HistVisee> specification = new HistViseeSpecification(histViseeCriteria);
		order = order != null && !order.isEmpty() ? order : "desc";
		sortField = sortField != null && !sortField.isEmpty() ? sortField : "id";
		Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);

		return histViseeRepository.findAll(specification, pageable).getContent().stream().map(visee -> new AuditEntityDto(visee)).collect(Collectors.toList());
	}

	/**
	 * findViseesHistByCriteria.
	 * 
	 * @param HistViseeCriteria
	 * @return List<HistVisee>
	 * @throws Exception
	 */	
	public List<AuditEntityDto> findViseesHistByCriteria(HistViseeCriteria histViseeCriteria) throws Exception {
					 
		Specification<HistVisee> specification = new HistViseeSpecification(histViseeCriteria);
		
		if (histViseeCriteria.isPeagable()) {
			Pageable pageable = PageRequest.of(0, histViseeCriteria.getMaxResults());
			return histViseeRepository.findAll(specification, pageable).getContent().stream().map(visee -> new AuditEntityDto(visee)).collect(Collectors.toList());

		} else {
			return histViseeRepository.findAll(specification).stream().map(visee -> new AuditEntityDto(visee)).collect(Collectors.toList());
		}
		
	}

	/**
	 * getHistViseeDataSize.
	 * 
	 * @param histViseeCriteria
	 * @return int
	 * @throws Exception
	 */
	public int getHistViseeDataSize(HistViseeCriteria histViseeCriteria) throws Exception {
	
		Specification<HistVisee> specification = new HistViseeSpecification(histViseeCriteria, true);
		
		return ((Long)  histViseeRepository.count(specification)).intValue();
		
	}	

}