package ma.enova.rth.service.impl;

import ma.enova.rth.common.bean.AuditEntityDto;
import ma.enova.rth.common.enumeration.ACTION_TYPE;
import ma.enova.rth.common.exception.EntityNotFoundException;
import ma.enova.rth.common.util.Utils;
import ma.enova.rth.dao.criteria.core.ModaliteRadiotherapieCriteria;
import ma.enova.rth.dao.criteria.history.HistModaliteRadiotherapieCriteria;
import ma.enova.rth.dao.facade.core.IModaliteRadiotherapieRepository;
import ma.enova.rth.dao.facade.history.IHistModaliteRadiotherapieRepository;
import ma.enova.rth.dao.specifications.core.ModaliteRadiotherapieSpecification;
import ma.enova.rth.dao.specifications.history.HistModaliteRadiotherapieSpecification;
import ma.enova.rth.domain.core.ModaliteRadiotherapie;
import ma.enova.rth.domain.historique.HistModaliteRadiotherapie;
import ma.enova.rth.dto.EtablissementDto;
import ma.enova.rth.dto.ModaliteRadiotherapieDto;
import ma.enova.rth.security.UtilisateurDetailsImpl;
import ma.enova.rth.service.facade.IModaliteRadiotherapieService;
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
 * Implementation du service ImodaliteRadiotherapie
 * @author JAF
 * @version 1.2
 */

@Service(value = "modaliteRadiotherapieService")
public class ModaliteRadiotherapieServiceImpl implements IModaliteRadiotherapieService {

	@Autowired
	private IModaliteRadiotherapieRepository modaliteRadiotherapieRepository;
	
	@Autowired
	private IHistModaliteRadiotherapieRepository histModaliteRadiotherapieRepository;



	/**
	 * createModaliteRadiotherapie.
	 * 
	 * @param modaliteRadiotherapie
	 * @return ModaliteRadiotherapie
	 * @throws Exception
	 */
	 
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public ModaliteRadiotherapieDto createModaliteRadiotherapie(ModaliteRadiotherapieDto modaliteRadiotherapieDto) throws Exception {

		UtilisateurDetailsImpl currentUser = (UtilisateurDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (currentUser.getEtablissement() != null)
			modaliteRadiotherapieDto.setEtablissement(new EtablissementDto(currentUser.getEtablissement().getId()));	

		ModaliteRadiotherapie modaliteRadiotherapie = new ModaliteRadiotherapie();
		modaliteRadiotherapie = modaliteRadiotherapieDto.convertToEntity(modaliteRadiotherapie, modaliteRadiotherapieDto);
		ModaliteRadiotherapie newModaliteRadiotherapie = modaliteRadiotherapieRepository.save(modaliteRadiotherapie);
		modaliteRadiotherapieDto.setId(newModaliteRadiotherapie.getId());		



			
		return modaliteRadiotherapieDto;
	}
	
	/**
	 * updateModaliteRadiotherapie.
	 * 
	 * @param modaliteRadiotherapie
	 * @return ModaliteRadiotherapie
	 * @throws Exception
	 */
	 
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)	 	
	public ModaliteRadiotherapieDto updateModaliteRadiotherapie(ModaliteRadiotherapieDto modaliteRadiotherapieDto) throws Exception {

		UtilisateurDetailsImpl currentUser = (UtilisateurDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		saveModaliteRadiotherapieAuditData(modaliteRadiotherapieDto, ACTION_TYPE.UPDATE);

		ModaliteRadiotherapie modaliteRadiotherapie = modaliteRadiotherapieRepository.findById(modaliteRadiotherapieDto.getId()).orElseThrow(() -> new EntityNotFoundException("errors.notFound", new String[] { ModaliteRadiotherapie.class.getSimpleName(), modaliteRadiotherapieDto.getId().toString() }));
		modaliteRadiotherapie = modaliteRadiotherapieDto.convertToEntity(modaliteRadiotherapie, modaliteRadiotherapieDto);
		modaliteRadiotherapieRepository.saveAndFlush(modaliteRadiotherapie);
		
		return modaliteRadiotherapieDto;
	}
	
	/**
	 * getModaliteRadiotherapieById.
	 * 
	 * @param modaliteRadiotherapieId
	 * @return ModaliteRadiotherapie
	 * @throws Exception
	 */
	public ModaliteRadiotherapieDto getModaliteRadiotherapieById(Long modaliteRadiotherapieId) throws Exception {

		ModaliteRadiotherapie modaliteRadiotherapie = modaliteRadiotherapieRepository.findById(modaliteRadiotherapieId).orElseThrow(() -> new EntityNotFoundException("errors.notFound", new String[] { ModaliteRadiotherapie.class.getSimpleName(), modaliteRadiotherapieId.toString() }));

		return  new ModaliteRadiotherapieDto(modaliteRadiotherapie, true, 0);

	}
	

	/**
	 * findModaliteRadiotherapiesByCriteria.
	 * 
	 * @param modaliteRadiotherapieCriteria
	 * @return List<ModaliteRadiotherapie>
	 * @throws Exception
	 */	
	public List<ModaliteRadiotherapieDto> findModaliteRadiotherapiesByCriteria(ModaliteRadiotherapieCriteria modaliteRadiotherapieCriteria) throws Exception {
					 
		UtilisateurDetailsImpl currentUser = (UtilisateurDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		modaliteRadiotherapieCriteria.setEtablissementId(currentUser.getEtablissement() != null ? currentUser.getEtablissement().getId() : null);		
		
		Specification<ModaliteRadiotherapie> specification = new ModaliteRadiotherapieSpecification(modaliteRadiotherapieCriteria);
		
		if (modaliteRadiotherapieCriteria.isPeagable()) {
			Pageable pageable = PageRequest.of(0, modaliteRadiotherapieCriteria.getMaxResults());
			return modaliteRadiotherapieRepository.findAll(specification, pageable).getContent().stream().map(modaliteRadiotherapie -> new ModaliteRadiotherapieDto(modaliteRadiotherapie)).collect(Collectors.toList());

		} else {
			return modaliteRadiotherapieRepository.findAll(specification).stream().map(modaliteRadiotherapie -> new ModaliteRadiotherapieDto(modaliteRadiotherapie)).collect(Collectors.toList());
		}
		
	}
	
	/**
	 * findModaliteRadiotherapieByCriteria.
	 * 
	 * @param modaliteRadiotherapieCriteria
	 * @return ModaliteRadiotherapie 
	 * @throws Exception
	 */
	
	public ModaliteRadiotherapieDto findModaliteRadiotherapieByCriteria(ModaliteRadiotherapieCriteria  modaliteRadiotherapieCriteria) throws Exception {

		UtilisateurDetailsImpl currentUser = (UtilisateurDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		modaliteRadiotherapieCriteria.setEtablissementId(currentUser.getEtablissement() != null ? currentUser.getEtablissement().getId() : null);		
		
		Specification<ModaliteRadiotherapie> specification = new ModaliteRadiotherapieSpecification(modaliteRadiotherapieCriteria);
		
		ModaliteRadiotherapie modaliteRadiotherapie = modaliteRadiotherapieRepository.findOne(specification).orElse(null);
		
		ModaliteRadiotherapieDto modaliteRadiotherapieDto = null;
		if (modaliteRadiotherapie != null) {
			modaliteRadiotherapieDto = new ModaliteRadiotherapieDto();
			return new ModaliteRadiotherapieDto(modaliteRadiotherapie, true);
		}

		return modaliteRadiotherapieDto;
	}

	/**
	 * paginatedListModaliteRadiotherapies.
	 * 
	 * @param modaliteRadiotherapieCriteria
	 * @param page
	 * @param pageSize
	 * @param order
	 * @param sortField
	 * @return List<ModaliteRadiotherapie>
	 * @throws Exception
	 */
	 
	public List<ModaliteRadiotherapieDto> paginatedListModaliteRadiotherapies(ModaliteRadiotherapieCriteria modaliteRadiotherapieCriteria,int page,int pageSize, String order, String sortField) throws Exception {

		UtilisateurDetailsImpl currentUser = (UtilisateurDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		modaliteRadiotherapieCriteria.setEtablissementId(currentUser.getEtablissement() != null ? currentUser.getEtablissement().getId() : null);		
		
		Specification<ModaliteRadiotherapie> specification = new ModaliteRadiotherapieSpecification(modaliteRadiotherapieCriteria);
		order = order != null && !order.isEmpty() ? order : "desc";
		sortField = sortField != null && !sortField.isEmpty() ? sortField : "id";
		Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);

		return modaliteRadiotherapieRepository.findAll(specification, pageable).getContent().stream().map(modaliteRadiotherapie -> new ModaliteRadiotherapieDto(modaliteRadiotherapie)).collect(Collectors.toList());
	}
	
	/**
	 * getModaliteRadiotherapieDataSize.
	 * 
	 * @param modaliteRadiotherapieCriteria
	 * @return int
	 * @throws Exception
	 */
	public int getModaliteRadiotherapieDataSize(ModaliteRadiotherapieCriteria modaliteRadiotherapieCriteria) throws Exception {
	
		UtilisateurDetailsImpl currentUser = (UtilisateurDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		modaliteRadiotherapieCriteria.setEtablissementId(currentUser.getEtablissement() != null ? currentUser.getEtablissement().getId() : null);		
		
		Specification<ModaliteRadiotherapie> specification = new ModaliteRadiotherapieSpecification(modaliteRadiotherapieCriteria, true);
		
		return ((Long)  modaliteRadiotherapieRepository.count(specification)).intValue();
		
	}
	
	/**
	 * deleteModaliteRadiotherapie.
	 * 
	 * @param modaliteRadiotherapieList
	 * @throws Exception
	 */
	 
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)	 
	public void deleteModaliteRadiotherapie(List<ModaliteRadiotherapieDto> modaliteRadiotherapieList) throws Exception {

		UtilisateurDetailsImpl currentUser = (UtilisateurDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (modaliteRadiotherapieList != null)
			for (ModaliteRadiotherapieDto modaliteRadiotherapieDto : modaliteRadiotherapieList) {
					ModaliteRadiotherapie toBeDeleted = modaliteRadiotherapieRepository.findById(modaliteRadiotherapieDto.getId()).orElseThrow(() -> new EntityNotFoundException("errors.notFound", new String[] { ModaliteRadiotherapie.class.getSimpleName(), modaliteRadiotherapieDto.getId().toString() }));

					modaliteRadiotherapieRepository.delete(toBeDeleted);

					HistModaliteRadiotherapie histModaliteRadiotherapie = new HistModaliteRadiotherapie();
					histModaliteRadiotherapie.setActionType(ACTION_TYPE.DELETE.name());
					histModaliteRadiotherapie.setObjectName("modaliteRadiotherapie");
					histModaliteRadiotherapie.setObjectId(modaliteRadiotherapieDto.getId());
					String modaliteRadiotherapieValue =  new ObjectMapper().writeValueAsString(modaliteRadiotherapieDto);
					histModaliteRadiotherapie.setData(modaliteRadiotherapieValue);
					histModaliteRadiotherapie.setUserId(currentUser.getId());
					histModaliteRadiotherapie.setUsername(currentUser.getUsername());
					histModaliteRadiotherapie.setDate(LocalDateTime.now());
					histModaliteRadiotherapieRepository.save(histModaliteRadiotherapie);					
			}
	}
	
	

	
	private void saveModaliteRadiotherapieAuditData(ModaliteRadiotherapieDto modaliteRadiotherapie, ACTION_TYPE action) throws Exception {

		ModaliteRadiotherapieDto oldModaliteRadiotherapie = getModaliteRadiotherapieById(modaliteRadiotherapie.getId());
		if (Utils.compareObjectsDiff(modaliteRadiotherapie, oldModaliteRadiotherapie)) {

			UtilisateurDetailsImpl currentUser = (UtilisateurDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			HistModaliteRadiotherapie histModaliteRadiotherapie = new HistModaliteRadiotherapie();
			histModaliteRadiotherapie.setActionType(action.name());
			histModaliteRadiotherapie.setObjectName("modaliteRadiotherapie");
			histModaliteRadiotherapie.setObjectId(modaliteRadiotherapie.getId());
			histModaliteRadiotherapie.setUserId(currentUser.getId());
			histModaliteRadiotherapie.setUsername(currentUser.getUsername());
			String modaliteRadiotherapieValue =  new ObjectMapper().writeValueAsString(modaliteRadiotherapie);
			histModaliteRadiotherapie.setData(modaliteRadiotherapieValue);
			histModaliteRadiotherapie.setDate(LocalDateTime.now());
			histModaliteRadiotherapieRepository.save(histModaliteRadiotherapie);
		}
	}	
	
	/**
	 * getHistModaliteRadiotherapieById.
	 * 
	 * @param histModaliteRadiotherapieId
	 * @return AuditEntityDto
	 * @throws Exception
	 */
	public AuditEntityDto getHistModaliteRadiotherapieById(Long histModaliteRadiotherapieId) throws Exception {

		HistModaliteRadiotherapie histModaliteRadiotherapie = histModaliteRadiotherapieRepository.findById(histModaliteRadiotherapieId).orElseThrow(() -> new EntityNotFoundException("errors.notFound", new String[] { HistModaliteRadiotherapie.class.getSimpleName(), histModaliteRadiotherapieId.toString() }));

			return  new AuditEntityDto(histModaliteRadiotherapie);

	}
	

	/**
	 * paginatedListHistModaliteRadiotherapies.
	 * 
	 * @param histModaliteRadiotherapieCriteria
	 * @param page
	 * @param pageSize
	 * @param order
	 * @param sortField
	 * @return List<HistModaliteRadiotherapie>
	 * @throws Exception
	 */
	 
	public List<AuditEntityDto> paginatedListHistModaliteRadiotherapies(HistModaliteRadiotherapieCriteria histModaliteRadiotherapieCriteria,int page,int pageSize, String order, String sortField) throws Exception {

		Specification<HistModaliteRadiotherapie> specification = new HistModaliteRadiotherapieSpecification(histModaliteRadiotherapieCriteria);
		order = order != null && !order.isEmpty() ? order : "desc";
		sortField = sortField != null && !sortField.isEmpty() ? sortField : "id";
		Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);

		return histModaliteRadiotherapieRepository.findAll(specification, pageable).getContent().stream().map(modaliteRadiotherapie -> new AuditEntityDto(modaliteRadiotherapie)).collect(Collectors.toList());
	}

	/**
	 * findModaliteRadiotherapiesHistByCriteria.
	 * 
	 * @param HistModaliteRadiotherapieCriteria
	 * @return List<HistModaliteRadiotherapie>
	 * @throws Exception
	 */	
	public List<AuditEntityDto> findModaliteRadiotherapiesHistByCriteria(HistModaliteRadiotherapieCriteria histModaliteRadiotherapieCriteria) throws Exception {
					 
		Specification<HistModaliteRadiotherapie> specification = new HistModaliteRadiotherapieSpecification(histModaliteRadiotherapieCriteria);
		
		if (histModaliteRadiotherapieCriteria.isPeagable()) {
			Pageable pageable = PageRequest.of(0, histModaliteRadiotherapieCriteria.getMaxResults());
			return histModaliteRadiotherapieRepository.findAll(specification, pageable).getContent().stream().map(modaliteRadiotherapie -> new AuditEntityDto(modaliteRadiotherapie)).collect(Collectors.toList());

		} else {
			return histModaliteRadiotherapieRepository.findAll(specification).stream().map(modaliteRadiotherapie -> new AuditEntityDto(modaliteRadiotherapie)).collect(Collectors.toList());
		}
		
	}

	/**
	 * getHistModaliteRadiotherapieDataSize.
	 * 
	 * @param histModaliteRadiotherapieCriteria
	 * @return int
	 * @throws Exception
	 */
	public int getHistModaliteRadiotherapieDataSize(HistModaliteRadiotherapieCriteria histModaliteRadiotherapieCriteria) throws Exception {
	
		Specification<HistModaliteRadiotherapie> specification = new HistModaliteRadiotherapieSpecification(histModaliteRadiotherapieCriteria, true);
		
		return ((Long)  histModaliteRadiotherapieRepository.count(specification)).intValue();
		
	}	

}