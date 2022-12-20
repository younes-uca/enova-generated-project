package ma.enova.rth.controller;

import ma.enova.rth.common.bean.*;
import ma.enova.rth.common.util.DtoUtil;
import ma.enova.rth.common.util.StringUtil;
import ma.enova.rth.common.util.StringUtil;
import ma.enova.rth.dao.criteria.core.ProfilCriteria;
import ma.enova.rth.dao.criteria.core.RoleCriteria;
import ma.enova.rth.dao.criteria.history.HistProfilCriteria;
import ma.enova.rth.dto.ProfilDto;
import ma.enova.rth.dto.RoleDto;
import ma.enova.rth.service.facade.IProfilService;
import ma.enova.rth.service.facade.IRoleService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Manager controller : Profil
 * @author JAF
 * @version 1.2
 */
 
@RestController
public class ProfilController extends BaseController {


/**
	* Services metiers.
*/
	@Autowired
	private IProfilService profilService;
	@Autowired	
	private IRoleService roleService;

	@GetMapping("/api/profil/{id}")
	@PreAuthorize("hasRole('ROLE_READ_PROFIL')")
	public ResponseEntity<ProfilDto> getProfilById(@PathVariable("id") Long id, String[] includes, String[] excludes) throws Exception {

		ProfilDto profil = profilService.getProfilById(id);

		if (StringUtil.isNoEmpty(includes) || StringUtil.isNoEmpty(excludes))
			profil = new ProfilDto().mappedCustomDto(profil, includes, excludes);

		return new ResponseEntity<ProfilDto>(profil, HttpStatus.OK);

	}
	
	@PostMapping("/api/profil")
	@PreAuthorize("hasRole('ROLE_CREATE_PROFIL')")
	public ResponseEntity<Long> addProfil(@RequestBody ProfilDto profil) throws Exception {

		profil = profilService.createProfil(profil);
		
		return new ResponseEntity<Long>(profil.getId(), HttpStatus.CREATED);

	}

 	@PutMapping("/api/profil")	
	@PreAuthorize("hasRole('ROLE_UPDATE_PROFIL')")
	public ResponseEntity<ProfilDto> updateProfil(@RequestBody ProfilDto profil) throws Exception {

		if (profil.getId() == null)
			return new ResponseEntity<ProfilDto>(HttpStatus.CONFLICT);

		profil = profilService.updateProfil(profil);

		return new ResponseEntity<ProfilDto>(profil, HttpStatus.OK);

	}
	
	@DeleteMapping("/api/profil/delete")
	@PreAuthorize("hasRole('ROLE_DELETE_PROFIL')")
	public ResponseEntity<Void> deleteProfil(@RequestBody List<ProfilDto> profilList) throws Exception {

		if (profilList == null || profilList.isEmpty())
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
			
		profilService.deleteProfil(profilList);
		return new ResponseEntity<Void>(HttpStatus.OK);

	}



	@PostMapping("/api/profil/listByCriteria")	
		
	public @ResponseBody
	ResponseEntity<List<ProfilDto>> getProfilsByCriteria(@RequestBody ProfilCriteria profilCriteria) throws Exception {

		List<ProfilDto> list = profilService.findProfilsByCriteria(profilCriteria);

		if (StringUtil.isNoEmpty(profilCriteria.getIncludes()) || StringUtil.isNoEmpty(profilCriteria.getExcludes()));
			list = CollectionUtils.emptyIfNull(list).stream().map(profil -> new ProfilDto().mappedCustomDto(profil, profilCriteria.getIncludes(), profilCriteria.getExcludes())).collect(Collectors.toList());

		if (list == null || list.isEmpty())
			return new ResponseEntity<List<ProfilDto>>(HttpStatus.NO_CONTENT);

		return new ResponseEntity<List<ProfilDto>>(list, HttpStatus.OK);

	}
	
	@PostMapping("/api/profil/paginatedListByCriteria")		
	@PreAuthorize("hasRole('ROLE_READ_PROFIL')")
	public @ResponseBody
	ResponseEntity<PaginatedList> paginatedListProfils(@RequestBody ProfilCriteria profilCriteria) throws Exception {

		List<ProfilDto> list = profilService.paginatedListProfils(profilCriteria,profilCriteria.getPage(),profilCriteria.getMaxResults(), profilCriteria.getSortOrder(), profilCriteria.getSortField());

		if (StringUtil.isNoEmpty(profilCriteria.getIncludes()) || StringUtil.isNoEmpty(profilCriteria.getExcludes()));
			list = CollectionUtils.emptyIfNull(list).stream().map(profil -> new ProfilDto().mappedCustomDto(profil, profilCriteria.getIncludes(), profilCriteria.getExcludes())).collect(Collectors.toList());

		PaginatedList paginatedList=new PaginatedList();
		paginatedList.setList(list);
		if (list != null && !list.isEmpty()) {
			int dateSize = profilService.getProfilDataSize(profilCriteria);
			paginatedList.setDataSize(dateSize);
		}
		
		return new ResponseEntity<PaginatedList>(paginatedList, HttpStatus.OK);

	}
	
	@PostMapping("/api/profil/exportProfils")		
	
	public @ResponseBody ResponseEntity<InputStreamResource> exportProfils(@RequestBody ProfilCriteria profilCriteria) throws Exception {

		if (profilCriteria.getExportModel() == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		profilCriteria.setMaxResults(null);
		List<ProfilDto> list = profilService.findProfilsByCriteria(profilCriteria);
		profilCriteria.getExportModel().setList(list);
		return getExportedFileResource(profilCriteria.getExportModel());
	
	}

	@PostMapping("/api/profil/getProfilsDataSize")	
		
	public @ResponseBody ResponseEntity<Integer> getProfilDataSize(@RequestBody ProfilCriteria profilCriteria) throws Exception {

		int count = profilService.getProfilDataSize(profilCriteria);

		return new ResponseEntity<Integer>(count, HttpStatus.OK);

	}

	@GetMapping("/api/profil/getRolesProfilList/{id}")
	@PreAuthorize("hasAnyRole('ROLE_READ_PROFIL','ROLE_UPDATE_PROFIL')")
	public ResponseEntity<List<RoleDto>> getRolesProfil(@PathVariable("id") Long id) throws Exception {

		ProfilDto profil = profilService.getProfilById(id);
		
		if (profil.getRolesList() != null && !profil.getRolesList().isEmpty()) {
			RoleCriteria rolesCriteria = new RoleCriteria();
			rolesCriteria.setIdsIn(DtoUtil.getIdsListDto(profil.getRolesList()));
			rolesCriteria.setOrderByAsc(new String[] { "libelle" });
			List<RoleDto> list = roleService.findRolesByCriteria(rolesCriteria);

			if (list == null || list.isEmpty())
				return new ResponseEntity<List<RoleDto>>(HttpStatus.NO_CONTENT);

			return new ResponseEntity<List<RoleDto>>(list, HttpStatus.OK);

		} else {
			return new ResponseEntity<List<RoleDto>>(HttpStatus.NO_CONTENT);
		}

	}
	

	@GetMapping("/api/profil/getRolesList")	
	@PreAuthorize("hasAnyRole('ROLE_READ_PROFIL','ROLE_CREATE_PROFIL','ROLE_UPDATE_PROFIL')")	
	public @ResponseBody
	ResponseEntity<List<BusinessDto>> getRolesList() throws Exception {

		RoleCriteria rolesCriteria = new RoleCriteria();
		rolesCriteria.setOrderByAsc(new String[] { "libelle" });
		List<BusinessDto> list = CollectionUtils.emptyIfNull(roleService.findRolesByCriteria(rolesCriteria)).stream().map(roles -> new BusinessDto(roles.getId(), roles.getLabel())).collect(Collectors.toList());		

		if (list == null || list.isEmpty())
			return new ResponseEntity<List<BusinessDto>>(HttpStatus.NO_CONTENT);

		return new ResponseEntity<List<BusinessDto>>(list, HttpStatus.OK);

	}


	@GetMapping("/api/profil/histProfil/{id}")	
	@PreAuthorize("hasRole('ROLE_HIST_PROFIL')")
	public ResponseEntity<AuditEntityDto> getHistProfilById(@PathVariable("id") Long id) throws Exception {

		AuditEntityDto histProfil = profilService.getHistProfilById(id);

		return new ResponseEntity<AuditEntityDto>(histProfil, HttpStatus.OK);

	}
	
	@PostMapping("/api/profil/paginatedListHistByCriteria")	
	@PreAuthorize("hasRole('ROLE_HIST_PROFIL')")
	public @ResponseBody ResponseEntity<PaginatedList> paginatedListHistProfils(@RequestBody HistProfilCriteria histProfilCriteria) throws Exception {

		List<AuditEntityDto> list = profilService.paginatedListHistProfils(histProfilCriteria,histProfilCriteria.getPage(), histProfilCriteria.getMaxResults(), histProfilCriteria.getSortOrder(), histProfilCriteria.getSortField());

		PaginatedList paginatedList=new PaginatedList();
		paginatedList.setList(list);
		if (list != null && !list.isEmpty()) {
			int dateSize = profilService.getHistProfilDataSize(histProfilCriteria);
			paginatedList.setDataSize(dateSize);
		}	

		return new ResponseEntity<PaginatedList>(paginatedList, HttpStatus.OK);

	}

	@PostMapping("/api/profil/exportProfilsHist")		
	
	public @ResponseBody ResponseEntity<InputStreamResource> exportProfilsHist(@RequestBody HistProfilCriteria histProfilCriteria) throws Exception {

		if (histProfilCriteria.getExportModel() == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		histProfilCriteria.setMaxResults(null);
		List<AuditEntityDto> list = profilService.findProfilsHistByCriteria(histProfilCriteria);
		histProfilCriteria.getExportModel().setList(list);
		return getExportedFileResource(histProfilCriteria.getExportModel());
	
	}

	@PostMapping("/api/profil/getHistProfilsDataSize")		
	
	public @ResponseBody ResponseEntity<Integer> getHistProfilDataSize(@RequestBody HistProfilCriteria histProfilCriteria) throws Exception {

		int count = profilService.getHistProfilDataSize(histProfilCriteria);

		return new ResponseEntity<Integer>(count, HttpStatus.OK);

	}
	
	@GetMapping("/api/profil/getRolesCategorieByDomaine/{id}")		
	@PreAuthorize("hasAnyRole('ROLE_READ_PROFIL','ROLE_CREATE_PROFIL','ROLE_UPDATE_PROFIL')")
	public ResponseEntity<List<TreeModel>> getRolesCategorieByDomaine(@PathVariable("id") Long id) throws Exception {

		List<TreeModel> list = profilService.getRolesCategorieByDomaine(id);

		if (list == null || list.isEmpty())
			return new ResponseEntity<List<TreeModel>>(HttpStatus.NO_CONTENT);

		return new ResponseEntity<List<TreeModel>>(list, HttpStatus.OK);

	}


}