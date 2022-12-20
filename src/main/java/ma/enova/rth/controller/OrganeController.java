package ma.enova.rth.controller;

import ma.enova.rth.common.bean.AuditEntityDto;
import ma.enova.rth.common.bean.BaseController;
import ma.enova.rth.common.bean.PaginatedList;
import ma.enova.rth.common.util.StringUtil;
import ma.enova.rth.common.util.StringUtil;
import ma.enova.rth.dao.criteria.core.OrganeCriteria;
import ma.enova.rth.dao.criteria.history.HistOrganeCriteria;
import ma.enova.rth.domain.core.Organe;
import ma.enova.rth.dto.OrganeDto;
import ma.enova.rth.service.facade.IOrganeService;
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
 * Manager controller : Organe
 * @author JAF
 * @version 1.2
 */
 
@RestController
public class OrganeController extends BaseController {


/**
	* Services metiers.
*/
	@Autowired
	private IOrganeService organeService;

	@GetMapping("/api/organe/{id}")
	@PreAuthorize("hasRole('ROLE_READ_ORGANE')")
	public ResponseEntity<OrganeDto> getOrganeById(@PathVariable("id") Long id, String[] includes, String[] excludes) throws Exception {

		OrganeDto organe = organeService.getOrganeById(id);

		if (StringUtil.isNoEmpty(includes) || StringUtil.isNoEmpty(excludes))
			organe = new OrganeDto().mappedCustomDto(organe, includes, excludes);

		return new ResponseEntity<OrganeDto>(organe, HttpStatus.OK);

	}
	
	@PostMapping("/api/organe")
	@PreAuthorize("hasRole('ROLE_CREATE_ORGANE')")
	public ResponseEntity<Long> addOrgane(@RequestBody OrganeDto organe) throws Exception {

		organe = organeService.createOrgane(organe);
		
		return new ResponseEntity<Long>(organe.getId(), HttpStatus.CREATED);

	}

 	@PutMapping("/api/organe")	
	@PreAuthorize("hasRole('ROLE_UPDATE_ORGANE')")
	public ResponseEntity<OrganeDto> updateOrgane(@RequestBody OrganeDto organe) throws Exception {

		if (organe.getId() == null)
			return new ResponseEntity<OrganeDto>(HttpStatus.CONFLICT);

		organe = organeService.updateOrgane(organe);

		return new ResponseEntity<OrganeDto>(organe, HttpStatus.OK);

	}
	
	@DeleteMapping("/api/organe/delete")
	@PreAuthorize("hasRole('ROLE_DELETE_ORGANE')")
	public ResponseEntity<Void> deleteOrgane(@RequestBody List<OrganeDto> organeList) throws Exception {

		if (organeList == null || organeList.isEmpty())
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
			
		organeService.deleteOrgane(organeList);
		return new ResponseEntity<Void>(HttpStatus.OK);

	}

	@GetMapping("/api/orange/")
	public List<Organe> findAll(){
		return organeService.findAll();
	}

	@PostMapping("/api/organe/listByCriteria")	
		
	public @ResponseBody
	ResponseEntity<List<OrganeDto>> getOrganesByCriteria(@RequestBody OrganeCriteria organeCriteria) throws Exception {

		List<OrganeDto> list = organeService.findOrganesByCriteria(organeCriteria);

		if (StringUtil.isNoEmpty(organeCriteria.getIncludes()) || StringUtil.isNoEmpty(organeCriteria.getExcludes()));
			list = CollectionUtils.emptyIfNull(list).stream().map(organe -> new OrganeDto().mappedCustomDto(organe, organeCriteria.getIncludes(), organeCriteria.getExcludes())).collect(Collectors.toList());

		if (list == null || list.isEmpty())
			return new ResponseEntity<List<OrganeDto>>(HttpStatus.NO_CONTENT);

		return new ResponseEntity<List<OrganeDto>>(list, HttpStatus.OK);

	}
	
	@PostMapping("/api/organe/paginatedListByCriteria")		
	@PreAuthorize("hasRole('ROLE_READ_ORGANE')")
	public @ResponseBody
	ResponseEntity<PaginatedList> paginatedListOrganes(@RequestBody OrganeCriteria organeCriteria) throws Exception {

		List<OrganeDto> list = organeService.paginatedListOrganes(organeCriteria,organeCriteria.getPage(),organeCriteria.getMaxResults(), organeCriteria.getSortOrder(), organeCriteria.getSortField());

		if (StringUtil.isNoEmpty(organeCriteria.getIncludes()) || StringUtil.isNoEmpty(organeCriteria.getExcludes()));
			list = CollectionUtils.emptyIfNull(list).stream().map(organe -> new OrganeDto().mappedCustomDto(organe, organeCriteria.getIncludes(), organeCriteria.getExcludes())).collect(Collectors.toList());

		PaginatedList paginatedList=new PaginatedList();
		paginatedList.setList(list);
		if (list != null && !list.isEmpty()) {
			int dateSize = organeService.getOrganeDataSize(organeCriteria);
			paginatedList.setDataSize(dateSize);
		}
		
		return new ResponseEntity<PaginatedList>(paginatedList, HttpStatus.OK);

	}
	
	@PostMapping("/api/organe/exportOrganes")		
	
	public @ResponseBody ResponseEntity<InputStreamResource> exportOrganes(@RequestBody OrganeCriteria organeCriteria) throws Exception {

		if (organeCriteria.getExportModel() == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		organeCriteria.setMaxResults(null);
		List<OrganeDto> list = organeService.findOrganesByCriteria(organeCriteria);
		organeCriteria.getExportModel().setList(list);
		return getExportedFileResource(organeCriteria.getExportModel());
	
	}

	@PostMapping("/api/organe/getOrganesDataSize")	
		
	public @ResponseBody ResponseEntity<Integer> getOrganeDataSize(@RequestBody OrganeCriteria organeCriteria) throws Exception {

		int count = organeService.getOrganeDataSize(organeCriteria);

		return new ResponseEntity<Integer>(count, HttpStatus.OK);

	}
	


	@GetMapping("/api/organe/histOrgane/{id}")	
	@PreAuthorize("hasRole('ROLE_HIST_ORGANE')")
	public ResponseEntity<AuditEntityDto> getHistOrganeById(@PathVariable("id") Long id) throws Exception {

		AuditEntityDto histOrgane = organeService.getHistOrganeById(id);

		return new ResponseEntity<AuditEntityDto>(histOrgane, HttpStatus.OK);

	}
	
	@PostMapping("/api/organe/paginatedListHistByCriteria")	
	@PreAuthorize("hasRole('ROLE_HIST_ORGANE')")
	public @ResponseBody ResponseEntity<PaginatedList> paginatedListHistOrganes(@RequestBody HistOrganeCriteria histOrganeCriteria) throws Exception {

		List<AuditEntityDto> list = organeService.paginatedListHistOrganes(histOrganeCriteria,histOrganeCriteria.getPage(), histOrganeCriteria.getMaxResults(), histOrganeCriteria.getSortOrder(), histOrganeCriteria.getSortField());

		PaginatedList paginatedList=new PaginatedList();
		paginatedList.setList(list);
		if (list != null && !list.isEmpty()) {
			int dateSize = organeService.getHistOrganeDataSize(histOrganeCriteria);
			paginatedList.setDataSize(dateSize);
		}	

		return new ResponseEntity<PaginatedList>(paginatedList, HttpStatus.OK);

	}

	@PostMapping("/api/organe/exportOrganesHist")		
	
	public @ResponseBody ResponseEntity<InputStreamResource> exportOrganesHist(@RequestBody HistOrganeCriteria histOrganeCriteria) throws Exception {

		if (histOrganeCriteria.getExportModel() == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		histOrganeCriteria.setMaxResults(null);
		List<AuditEntityDto> list = organeService.findOrganesHistByCriteria(histOrganeCriteria);
		histOrganeCriteria.getExportModel().setList(list);
		return getExportedFileResource(histOrganeCriteria.getExportModel());
	
	}

	@PostMapping("/api/organe/getHistOrganesDataSize")		
	
	public @ResponseBody ResponseEntity<Integer> getHistOrganeDataSize(@RequestBody HistOrganeCriteria histOrganeCriteria) throws Exception {

		int count = organeService.getHistOrganeDataSize(histOrganeCriteria);

		return new ResponseEntity<Integer>(count, HttpStatus.OK);

	}
	

}