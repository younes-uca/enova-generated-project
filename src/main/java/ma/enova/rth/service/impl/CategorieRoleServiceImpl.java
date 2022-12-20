package ma.enova.rth.service.impl;

import ma.enova.rth.common.bean.AuditEntityDto;
import ma.enova.rth.common.enumeration.ACTION_TYPE;
import ma.enova.rth.common.exception.EntityNotFoundException;
import ma.enova.rth.common.util.Utils;
import ma.enova.rth.dao.criteria.core.CategorieRoleCriteria;
import ma.enova.rth.dao.criteria.history.HistCategorieRoleCriteria;
import ma.enova.rth.dao.facade.core.ICategorieRoleRepository;
import ma.enova.rth.dao.facade.history.IHistCategorieRoleRepository;
import ma.enova.rth.dao.specifications.core.CategorieRoleSpecification;
import ma.enova.rth.dao.specifications.history.HistCategorieRoleSpecification;
import ma.enova.rth.domain.core.CategorieRole;
import ma.enova.rth.domain.historique.HistCategorieRole;
import ma.enova.rth.dto.CategorieRoleDto;
import ma.enova.rth.security.UtilisateurDetailsImpl;
import ma.enova.rth.service.facade.ICategorieRoleService;
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
 * Implementation du service IcategorieRole
 * @author JAF
 * @version 1.2
 */

@Service(value = "categorieRoleService")
public class CategorieRoleServiceImpl implements ICategorieRoleService {

	@Autowired
	private ICategorieRoleRepository categorieRoleRepository;
	
	@Autowired
	private IHistCategorieRoleRepository histCategorieRoleRepository;



	/**
	 * createCategorieRole.
	 * 
	 * @return CategorieRole
	 * @throws Exception
	 */
	 
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public CategorieRoleDto createCategorieRole(CategorieRoleDto categorieRoleDto) throws Exception {


		CategorieRole categorieRole = new CategorieRole();
		categorieRole = categorieRoleDto.convertToEntity(categorieRole, categorieRoleDto);
		CategorieRole newCategorieRole = categorieRoleRepository.save(categorieRole);
		categorieRoleDto.setId(newCategorieRole.getId());		



			
		return categorieRoleDto;
	}
	
	/**
	 * updateCategorieRole.
	 * 
	 * @return CategorieRole
	 * @throws Exception
	 */
	 
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)	 	
	public CategorieRoleDto updateCategorieRole(CategorieRoleDto categorieRoleDto) throws Exception {


		saveCategorieRoleAuditData(categorieRoleDto, ACTION_TYPE.UPDATE);

		CategorieRole categorieRole = categorieRoleRepository.findById(categorieRoleDto.getId()).orElseThrow(() -> new EntityNotFoundException("errors.notFound", new String[] { CategorieRole.class.getSimpleName(), categorieRoleDto.getId().toString() }));
		categorieRole = categorieRoleDto.convertToEntity(categorieRole, categorieRoleDto);
		categorieRoleRepository.saveAndFlush(categorieRole);
		
		return categorieRoleDto;
	}
	
	/**
	 * getCategorieRoleById.
	 * 
	 * @param categorieRoleId
	 * @return CategorieRole
	 * @throws Exception
	 */
	public CategorieRoleDto getCategorieRoleById(Long categorieRoleId) throws Exception {

		CategorieRole categorieRole = categorieRoleRepository.findById(categorieRoleId).orElseThrow(() -> new EntityNotFoundException("errors.notFound", new String[] { CategorieRole.class.getSimpleName(), categorieRoleId.toString() }));

		return  new CategorieRoleDto(categorieRole, true, 0);

	}
	

	/**
	 * findCategorieRolesByCriteria.
	 * 
	 * @param categorieRoleCriteria
	 * @return List<CategorieRole>
	 * @throws Exception
	 */	
	public List<CategorieRoleDto> findCategorieRolesByCriteria(CategorieRoleCriteria categorieRoleCriteria) throws Exception {
					 
		Specification<CategorieRole> specification = new CategorieRoleSpecification(categorieRoleCriteria);
		
		if (categorieRoleCriteria.isPeagable()) {
			Pageable pageable = PageRequest.of(0, categorieRoleCriteria.getMaxResults());
			return categorieRoleRepository.findAll(specification, pageable).getContent().stream().map(categorieRole -> new CategorieRoleDto(categorieRole)).collect(Collectors.toList());

		} else {
			return categorieRoleRepository.findAll(specification).stream().map(categorieRole -> new CategorieRoleDto(categorieRole)).collect(Collectors.toList());
		}
		
	}
	
	/**
	 * findCategorieRoleByCriteria.
	 * 
	 * @param categorieRoleCriteria
	 * @return CategorieRole 
	 * @throws Exception
	 */
	
	public CategorieRoleDto findCategorieRoleByCriteria(CategorieRoleCriteria  categorieRoleCriteria) throws Exception {

		Specification<CategorieRole> specification = new CategorieRoleSpecification(categorieRoleCriteria);
		
		CategorieRole categorieRole = categorieRoleRepository.findOne(specification).orElse(null);
		
		CategorieRoleDto categorieRoleDto = null;
		if (categorieRole != null) {
			categorieRoleDto = new CategorieRoleDto();
			return new CategorieRoleDto(categorieRole, true);
		}

		return categorieRoleDto;
	}

	/**
	 * paginatedListCategorieRoles.
	 * 
	 * @param categorieRoleCriteria
	 * @param page
	 * @param pageSize
	 * @param order
	 * @param sortField
	 * @return List<CategorieRole>
	 * @throws Exception
	 */
	 
	public List<CategorieRoleDto> paginatedListCategorieRoles(CategorieRoleCriteria categorieRoleCriteria,int page,int pageSize, String order, String sortField) throws Exception {

		Specification<CategorieRole> specification = new CategorieRoleSpecification(categorieRoleCriteria);
		order = order != null && !order.isEmpty() ? order : "desc";
		sortField = sortField != null && !sortField.isEmpty() ? sortField : "id";
		Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);

		return categorieRoleRepository.findAll(specification, pageable).getContent().stream().map(categorieRole -> new CategorieRoleDto(categorieRole)).collect(Collectors.toList());
	}
	
	/**
	 * getCategorieRoleDataSize.
	 * 
	 * @param categorieRoleCriteria
	 * @return int
	 * @throws Exception
	 */
	public int getCategorieRoleDataSize(CategorieRoleCriteria categorieRoleCriteria) throws Exception {
	
		Specification<CategorieRole> specification = new CategorieRoleSpecification(categorieRoleCriteria, true);
		
		return ((Long)  categorieRoleRepository.count(specification)).intValue();
		
	}
	
	/**
	 * deleteCategorieRole.
	 * 
	 * @param categorieRoleList
	 * @throws Exception
	 */
	 
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)	 
	public void deleteCategorieRole(List<CategorieRoleDto> categorieRoleList) throws Exception {

		UtilisateurDetailsImpl currentUser = (UtilisateurDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (categorieRoleList != null)
			for (CategorieRoleDto categorieRoleDto : categorieRoleList) {
					CategorieRole toBeDeleted = categorieRoleRepository.findById(categorieRoleDto.getId()).orElseThrow(() -> new EntityNotFoundException("errors.notFound", new String[] { CategorieRole.class.getSimpleName(), categorieRoleDto.getId().toString() }));

					categorieRoleRepository.delete(toBeDeleted);

					HistCategorieRole histCategorieRole = new HistCategorieRole();
					histCategorieRole.setActionType(ACTION_TYPE.DELETE.name());
					histCategorieRole.setObjectName("categorieRole");
					histCategorieRole.setObjectId(categorieRoleDto.getId());
					String categorieRoleValue =  new ObjectMapper().writeValueAsString(categorieRoleDto);
					histCategorieRole.setData(categorieRoleValue);
					histCategorieRole.setUserId(currentUser.getId());
					histCategorieRole.setUsername(currentUser.getUsername());
					histCategorieRole.setDate(LocalDateTime.now());
					histCategorieRoleRepository.save(histCategorieRole);					
			}
	}
	
	

	
	private void saveCategorieRoleAuditData(CategorieRoleDto categorieRole, ACTION_TYPE action) throws Exception {

		CategorieRoleDto oldCategorieRole = getCategorieRoleById(categorieRole.getId());
		if (Utils.compareObjectsDiff(categorieRole, oldCategorieRole)) {

			UtilisateurDetailsImpl currentUser = (UtilisateurDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			HistCategorieRole histCategorieRole = new HistCategorieRole();
			histCategorieRole.setActionType(action.name());
			histCategorieRole.setObjectName("categorieRole");
			histCategorieRole.setObjectId(categorieRole.getId());
			histCategorieRole.setUserId(currentUser.getId());
			histCategorieRole.setUsername(currentUser.getUsername());
			String categorieRoleValue =  new ObjectMapper().writeValueAsString(categorieRole);
			histCategorieRole.setData(categorieRoleValue);
			histCategorieRole.setDate(LocalDateTime.now());
			histCategorieRoleRepository.save(histCategorieRole);
		}
	}	
	
	/**
	 * getHistCategorieRoleById.
	 * 
	 * @param histCategorieRoleId
	 * @return AuditEntityDto
	 * @throws Exception
	 */
	public AuditEntityDto getHistCategorieRoleById(Long histCategorieRoleId) throws Exception {

		HistCategorieRole histCategorieRole = histCategorieRoleRepository.findById(histCategorieRoleId).orElseThrow(() -> new EntityNotFoundException("errors.notFound", new String[] { HistCategorieRole.class.getSimpleName(), histCategorieRoleId.toString() }));

			return  new AuditEntityDto(histCategorieRole);

	}
	

	/**
	 * paginatedListHistCategorieRoles.
	 * 
	 * @param histCategorieRoleCriteria
	 * @param page
	 * @param pageSize
	 * @param order
	 * @param sortField
	 * @return List<HistCategorieRole>
	 * @throws Exception
	 */
	 
	public List<AuditEntityDto> paginatedListHistCategorieRoles(HistCategorieRoleCriteria histCategorieRoleCriteria,int page,int pageSize, String order, String sortField) throws Exception {

		Specification<HistCategorieRole> specification = new HistCategorieRoleSpecification(histCategorieRoleCriteria);
		order = order != null && !order.isEmpty() ? order : "desc";
		sortField = sortField != null && !sortField.isEmpty() ? sortField : "id";
		Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);

		return histCategorieRoleRepository.findAll(specification, pageable).getContent().stream().map(categorieRole -> new AuditEntityDto(categorieRole)).collect(Collectors.toList());
	}

	/**
	 * findCategorieRolesHistByCriteria.
	 * 
	 * @return List<HistCategorieRole>
	 * @throws Exception
	 */	
	public List<AuditEntityDto> findCategorieRolesHistByCriteria(HistCategorieRoleCriteria histCategorieRoleCriteria) throws Exception {
					 
		Specification<HistCategorieRole> specification = new HistCategorieRoleSpecification(histCategorieRoleCriteria);
		
		if (histCategorieRoleCriteria.isPeagable()) {
			Pageable pageable = PageRequest.of(0, histCategorieRoleCriteria.getMaxResults());
			return histCategorieRoleRepository.findAll(specification, pageable).getContent().stream().map(categorieRole -> new AuditEntityDto(categorieRole)).collect(Collectors.toList());

		} else {
			return histCategorieRoleRepository.findAll(specification).stream().map(categorieRole -> new AuditEntityDto(categorieRole)).collect(Collectors.toList());
		}
		
	}

	/**
	 * getHistCategorieRoleDataSize.
	 * 
	 * @param histCategorieRoleCriteria
	 * @return int
	 * @throws Exception
	 */
	public int getHistCategorieRoleDataSize(HistCategorieRoleCriteria histCategorieRoleCriteria) throws Exception {
	
		Specification<HistCategorieRole> specification = new HistCategorieRoleSpecification(histCategorieRoleCriteria, true);
		
		return ((Long)  histCategorieRoleRepository.count(specification)).intValue();
		
	}

	@Override
	public CategorieRoleDto create(CategorieRoleDto dto) throws Exception {
		return null;
	}

	@Override
	public CategorieRoleDto update(CategorieRoleDto dto) throws Exception {
		return null;
	}

	@Override
	public CategorieRoleDto findById(Long id) throws Exception {
		return null;
	}

	@Override
	public List<CategorieRoleDto> findMultipleByCriteria(CategorieRoleCriteria critera) throws Exception {
		return null;
	}

	@Override
	public List<CategorieRoleDto> findPaginatedByCriteria(CategorieRoleCriteria critera, int page, int pageSize, String order, String sortField) throws Exception {
		return null;
	}

	@Override
	public int getDataSize(CategorieRoleCriteria criteria) throws Exception {
		return 0;
	}

	@Override
	public void delete(List<CategorieRoleDto> categorieRoleDtos) throws Exception {

	}


	@Override
	public AuditEntityDto findHistoryById(Long id) throws Exception {
		return null;
	}

	@Override
	public List<AuditEntityDto> findHistoryByCriteria(HistCategorieRoleCriteria histCategorieRoleCriteria) throws Exception {
		return null;
	}

	@Override
	public List<AuditEntityDto> findHistoryPaginatedByCriteria(HistCategorieRoleCriteria histCategorieRoleCriteria, int page, int pageSize, String order, String sortField) throws Exception {
		return null;
	}

	@Override
	public int getHistoryDataSize(HistCategorieRoleCriteria histCategorieRoleCriteria) throws Exception {
		return 0;
	}
}