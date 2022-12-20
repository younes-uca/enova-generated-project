package ma.enova.rth.service.impl;

import ma.enova.rth.common.bean.AuditEntityDto;
import ma.enova.rth.common.enumeration.ACTION_TYPE;
import ma.enova.rth.common.exception.EntityNotFoundException;
import ma.enova.rth.common.util.Utils;
import ma.enova.rth.dao.criteria.core.PersonnelCriteria;
import ma.enova.rth.dao.criteria.history.HistPersonnelCriteria;
import ma.enova.rth.dao.facade.core.IPersonnelRepository;
import ma.enova.rth.dao.facade.history.IHistPersonnelRepository;
import ma.enova.rth.dao.specifications.core.PersonnelSpecification;
import ma.enova.rth.dao.specifications.history.HistPersonnelSpecification;
import ma.enova.rth.domain.core.Personnel;
import ma.enova.rth.domain.historique.HistPersonnel;
import ma.enova.rth.dto.EtablissementDto;
import ma.enova.rth.dto.PersonnelDto;
import ma.enova.rth.security.UtilisateurDetailsImpl;
import ma.enova.rth.service.facade.IPersonnelService;
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
 * Implementation du service Ipersonnel
 * @author JAF
 * @version 1.2
 */

@Service(value = "personnelService")
public class PersonnelServiceImpl implements IPersonnelService {

	@Autowired
	private IPersonnelRepository personnelRepository;
	
	@Autowired
	private IHistPersonnelRepository histPersonnelRepository;



	/**
	 * createPersonnel.
	 * 
	 * @param personnel
	 * @return Personnel
	 * @throws Exception
	 */
	 
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public PersonnelDto createPersonnel(PersonnelDto personnelDto) throws Exception {

		UtilisateurDetailsImpl currentUser = (UtilisateurDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (currentUser.getEtablissement() != null)
			personnelDto.setEtablissement(new EtablissementDto(currentUser.getEtablissement().getId()));	

		Personnel personnel = new Personnel();
		personnel = personnelDto.convertToEntity(personnel, personnelDto);
		Personnel newPersonnel = personnelRepository.save(personnel);
		personnelDto.setId(newPersonnel.getId());		



			
		return personnelDto;
	}
	
	/**
	 * updatePersonnel.
	 * 
	 * @param personnel
	 * @return Personnel
	 * @throws Exception
	 */
	 
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)	 	
	public PersonnelDto updatePersonnel(PersonnelDto personnelDto) throws Exception {

		UtilisateurDetailsImpl currentUser = (UtilisateurDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		savePersonnelAuditData(personnelDto, ACTION_TYPE.UPDATE);

		Personnel personnel = personnelRepository.findById(personnelDto.getId()).orElseThrow(() -> new EntityNotFoundException("errors.notFound", new String[] { Personnel.class.getSimpleName(), personnelDto.getId().toString() }));
		personnel = personnelDto.convertToEntity(personnel, personnelDto);
		personnelRepository.saveAndFlush(personnel);
		
		return personnelDto;
	}
	
	/**
	 * getPersonnelById.
	 * 
	 * @param personnelId
	 * @return Personnel
	 * @throws Exception
	 */
	public PersonnelDto getPersonnelById(Long personnelId) throws Exception {

		Personnel personnel = personnelRepository.findById(personnelId).orElseThrow(() -> new EntityNotFoundException("errors.notFound", new String[] { Personnel.class.getSimpleName(), personnelId.toString() }));

		return  new PersonnelDto(personnel, true, 0);

	}
	

	/**
	 * findPersonnelsByCriteria.
	 * 
	 * @param personnelCriteria
	 * @return List<Personnel>
	 * @throws Exception
	 */	
	public List<PersonnelDto> findPersonnelsByCriteria(PersonnelCriteria personnelCriteria) throws Exception {
					 
		UtilisateurDetailsImpl currentUser = (UtilisateurDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		personnelCriteria.setEtablissementId(currentUser.getEtablissement() != null ? currentUser.getEtablissement().getId() : null);		
		
		Specification<Personnel> specification = new PersonnelSpecification(personnelCriteria);
		
		if (personnelCriteria.isPeagable()) {
			Pageable pageable = PageRequest.of(0, personnelCriteria.getMaxResults());
			return personnelRepository.findAll(specification, pageable).getContent().stream().map(personnel -> new PersonnelDto(personnel)).collect(Collectors.toList());

		} else {
			return personnelRepository.findAll(specification).stream().map(personnel -> new PersonnelDto(personnel)).collect(Collectors.toList());
		}
		
	}
	
	/**
	 * findPersonnelByCriteria.
	 * 
	 * @param personnelCriteria
	 * @return Personnel 
	 * @throws Exception
	 */
	
	public PersonnelDto findPersonnelByCriteria(PersonnelCriteria  personnelCriteria) throws Exception {

		UtilisateurDetailsImpl currentUser = (UtilisateurDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		personnelCriteria.setEtablissementId(currentUser.getEtablissement() != null ? currentUser.getEtablissement().getId() : null);		
		
		Specification<Personnel> specification = new PersonnelSpecification(personnelCriteria);
		
		Personnel personnel = personnelRepository.findOne(specification).orElse(null);
		
		PersonnelDto personnelDto = null;
		if (personnel != null) {
			personnelDto = new PersonnelDto();
			return new PersonnelDto(personnel, true);
		}

		return personnelDto;
	}

	/**
	 * paginatedListPersonnels.
	 * 
	 * @param personnelCriteria
	 * @param page
	 * @param pageSize
	 * @param order
	 * @param sortField
	 * @return List<Personnel>
	 * @throws Exception
	 */
	 
	public List<PersonnelDto> paginatedListPersonnels(PersonnelCriteria personnelCriteria,int page,int pageSize, String order, String sortField) throws Exception {

		UtilisateurDetailsImpl currentUser = (UtilisateurDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		personnelCriteria.setEtablissementId(currentUser.getEtablissement() != null ? currentUser.getEtablissement().getId() : null);		
		
		Specification<Personnel> specification = new PersonnelSpecification(personnelCriteria);
		order = order != null && !order.isEmpty() ? order : "desc";
		sortField = sortField != null && !sortField.isEmpty() ? sortField : "id";
		Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);

		return personnelRepository.findAll(specification, pageable).getContent().stream().map(personnel -> new PersonnelDto(personnel)).collect(Collectors.toList());
	}
	
	/**
	 * getPersonnelDataSize.
	 * 
	 * @param personnelCriteria
	 * @return int
	 * @throws Exception
	 */
	public int getPersonnelDataSize(PersonnelCriteria personnelCriteria) throws Exception {
	
		UtilisateurDetailsImpl currentUser = (UtilisateurDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		personnelCriteria.setEtablissementId(currentUser.getEtablissement() != null ? currentUser.getEtablissement().getId() : null);		
		
		Specification<Personnel> specification = new PersonnelSpecification(personnelCriteria, true);
		
		return ((Long)  personnelRepository.count(specification)).intValue();
		
	}
	
	/**
	 * deletePersonnel.
	 * 
	 * @param personnelList
	 * @throws Exception
	 */
	 
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)	 
	public void deletePersonnel(List<PersonnelDto> personnelList) throws Exception {

		UtilisateurDetailsImpl currentUser = (UtilisateurDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (personnelList != null)
			for (PersonnelDto personnelDto : personnelList) {
					Personnel toBeDeleted = personnelRepository.findById(personnelDto.getId()).orElseThrow(() -> new EntityNotFoundException("errors.notFound", new String[] { Personnel.class.getSimpleName(), personnelDto.getId().toString() }));

					personnelRepository.delete(toBeDeleted);

					HistPersonnel histPersonnel = new HistPersonnel();
					histPersonnel.setActionType(ACTION_TYPE.DELETE.name());
					histPersonnel.setObjectName("personnel");
					histPersonnel.setObjectId(personnelDto.getId());
					String personnelValue =  new ObjectMapper().writeValueAsString(personnelDto);
					histPersonnel.setData(personnelValue);
					histPersonnel.setUserId(currentUser.getId());
					histPersonnel.setUsername(currentUser.getUsername());
					histPersonnel.setDate(LocalDateTime.now());
					histPersonnelRepository.save(histPersonnel);					
			}
	}
	
	

	
	private void savePersonnelAuditData(PersonnelDto personnel, ACTION_TYPE action) throws Exception {

		PersonnelDto oldPersonnel = getPersonnelById(personnel.getId());
		if (Utils.compareObjectsDiff(personnel, oldPersonnel)) {

			UtilisateurDetailsImpl currentUser = (UtilisateurDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			HistPersonnel histPersonnel = new HistPersonnel();
			histPersonnel.setActionType(action.name());
			histPersonnel.setObjectName("personnel");
			histPersonnel.setObjectId(personnel.getId());
			histPersonnel.setUserId(currentUser.getId());
			histPersonnel.setUsername(currentUser.getUsername());
			String personnelValue =  new ObjectMapper().writeValueAsString(personnel);
			histPersonnel.setData(personnelValue);
			histPersonnel.setDate(LocalDateTime.now());
			histPersonnelRepository.save(histPersonnel);
		}
	}	
	
	/**
	 * getHistPersonnelById.
	 * 
	 * @param histPersonnelId
	 * @return AuditEntityDto
	 * @throws Exception
	 */
	public AuditEntityDto getHistPersonnelById(Long histPersonnelId) throws Exception {

		HistPersonnel histPersonnel = histPersonnelRepository.findById(histPersonnelId).orElseThrow(() -> new EntityNotFoundException("errors.notFound", new String[] { HistPersonnel.class.getSimpleName(), histPersonnelId.toString() }));

			return  new AuditEntityDto(histPersonnel);

	}
	

	/**
	 * paginatedListHistPersonnels.
	 * 
	 * @param histPersonnelCriteria
	 * @param page
	 * @param pageSize
	 * @param order
	 * @param sortField
	 * @return List<HistPersonnel>
	 * @throws Exception
	 */
	 
	public List<AuditEntityDto> paginatedListHistPersonnels(HistPersonnelCriteria histPersonnelCriteria,int page,int pageSize, String order, String sortField) throws Exception {

		Specification<HistPersonnel> specification = new HistPersonnelSpecification(histPersonnelCriteria);
		order = order != null && !order.isEmpty() ? order : "desc";
		sortField = sortField != null && !sortField.isEmpty() ? sortField : "id";
		Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);

		return histPersonnelRepository.findAll(specification, pageable).getContent().stream().map(personnel -> new AuditEntityDto(personnel)).collect(Collectors.toList());
	}

	/**
	 * findPersonnelsHistByCriteria.
	 * 
	 * @param HistPersonnelCriteria
	 * @return List<HistPersonnel>
	 * @throws Exception
	 */	
	public List<AuditEntityDto> findPersonnelsHistByCriteria(HistPersonnelCriteria histPersonnelCriteria) throws Exception {
					 
		Specification<HistPersonnel> specification = new HistPersonnelSpecification(histPersonnelCriteria);
		
		if (histPersonnelCriteria.isPeagable()) {
			Pageable pageable = PageRequest.of(0, histPersonnelCriteria.getMaxResults());
			return histPersonnelRepository.findAll(specification, pageable).getContent().stream().map(personnel -> new AuditEntityDto(personnel)).collect(Collectors.toList());

		} else {
			return histPersonnelRepository.findAll(specification).stream().map(personnel -> new AuditEntityDto(personnel)).collect(Collectors.toList());
		}
		
	}

	/**
	 * getHistPersonnelDataSize.
	 * 
	 * @param histPersonnelCriteria
	 * @return int
	 * @throws Exception
	 */
	public int getHistPersonnelDataSize(HistPersonnelCriteria histPersonnelCriteria) throws Exception {
	
		Specification<HistPersonnel> specification = new HistPersonnelSpecification(histPersonnelCriteria, true);
		
		return ((Long)  histPersonnelRepository.count(specification)).intValue();
		
	}	

}