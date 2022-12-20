package ma.enova.rth.controller;

import ma.enova.rth.common.bean.AuditEntityDto;
import ma.enova.rth.common.bean.BaseController;
import ma.enova.rth.common.bean.PaginatedList;
import ma.enova.rth.common.util.StringUtil;
import ma.enova.rth.dao.criteria.core.PersonnelCriteria;
import ma.enova.rth.dao.criteria.history.HistPersonnelCriteria;
import ma.enova.rth.dto.PersonnelDto;
import ma.enova.rth.service.facade.IPersonnelService;
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
 * Manager controller : Personnel
 * @author JAF
 * @version 1.2
 */
 
@RestController
public class PersonnelController extends BaseController {


/**
	* Services metiers.
*/
	@Autowired
	private IPersonnelService personnelService;

	@GetMapping("/api/personnel/{id}")
	@PreAuthorize("hasRole('ROLE_READ_PERSONNEL')")
	public ResponseEntity<PersonnelDto> getPersonnelById(@PathVariable("id") Long id, String[] includes, String[] excludes) throws Exception {

		PersonnelDto personnel = personnelService.getPersonnelById(id);

		if (StringUtil.isNoEmpty(includes) || StringUtil.isNoEmpty(excludes))
			personnel = new PersonnelDto().mappedCustomDto(personnel, includes, excludes);

		return new ResponseEntity<PersonnelDto>(personnel, HttpStatus.OK);

	}
	
	@PostMapping("/api/personnel")
	@PreAuthorize("hasRole('ROLE_CREATE_PERSONNEL')")
	public ResponseEntity<Long> addPersonnel(@RequestBody PersonnelDto personnel) throws Exception {

		personnel = personnelService.createPersonnel(personnel);
		
		return new ResponseEntity<Long>(personnel.getId(), HttpStatus.CREATED);

	}

 	@PutMapping("/api/personnel")	
	@PreAuthorize("hasRole('ROLE_UPDATE_PERSONNEL')")
	public ResponseEntity<PersonnelDto> updatePersonnel(@RequestBody PersonnelDto personnel) throws Exception {

		if (personnel.getId() == null)
			return new ResponseEntity<PersonnelDto>(HttpStatus.CONFLICT);

		personnel = personnelService.updatePersonnel(personnel);

		return new ResponseEntity<PersonnelDto>(personnel, HttpStatus.OK);

	}
	
	@DeleteMapping("/api/personnel/delete")
	@PreAuthorize("hasRole('ROLE_DELETE_PERSONNEL')")
	public ResponseEntity<Void> deletePersonnel(@RequestBody List<PersonnelDto> personnelList) throws Exception {

		if (personnelList == null || personnelList.isEmpty())
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
			
		personnelService.deletePersonnel(personnelList);
		return new ResponseEntity<Void>(HttpStatus.OK);

	}



	@PostMapping("/api/personnel/listByCriteria")	
		
	public @ResponseBody
	ResponseEntity<List<PersonnelDto>> getPersonnelsByCriteria(@RequestBody PersonnelCriteria personnelCriteria) throws Exception {

		List<PersonnelDto> list = personnelService.findPersonnelsByCriteria(personnelCriteria);

		if (StringUtil.isNoEmpty(personnelCriteria.getIncludes()) || StringUtil.isNoEmpty(personnelCriteria.getExcludes()));
			list = CollectionUtils.emptyIfNull(list).stream().map(personnel -> new PersonnelDto().mappedCustomDto(personnel, personnelCriteria.getIncludes(), personnelCriteria.getExcludes())).collect(Collectors.toList());

		if (list == null || list.isEmpty())
			return new ResponseEntity<List<PersonnelDto>>(HttpStatus.NO_CONTENT);

		return new ResponseEntity<List<PersonnelDto>>(list, HttpStatus.OK);

	}
	
	@PostMapping("/api/personnel/paginatedListByCriteria")		
	@PreAuthorize("hasRole('ROLE_READ_PERSONNEL')")
	public @ResponseBody
	ResponseEntity<PaginatedList> paginatedListPersonnels(@RequestBody PersonnelCriteria personnelCriteria) throws Exception {

		List<PersonnelDto> list = personnelService.paginatedListPersonnels(personnelCriteria,personnelCriteria.getPage(),personnelCriteria.getMaxResults(), personnelCriteria.getSortOrder(), personnelCriteria.getSortField());

		if (StringUtil.isNoEmpty(personnelCriteria.getIncludes()) || StringUtil.isNoEmpty(personnelCriteria.getExcludes()));
			list = CollectionUtils.emptyIfNull(list).stream().map(personnel -> new PersonnelDto().mappedCustomDto(personnel, personnelCriteria.getIncludes(), personnelCriteria.getExcludes())).collect(Collectors.toList());

		PaginatedList paginatedList=new PaginatedList();
		paginatedList.setList(list);
		if (list != null && !list.isEmpty()) {
			int dateSize = personnelService.getPersonnelDataSize(personnelCriteria);
			paginatedList.setDataSize(dateSize);
		}
		
		return new ResponseEntity<PaginatedList>(paginatedList, HttpStatus.OK);

	}
	
	@PostMapping("/api/personnel/exportPersonnels")		
	
	public @ResponseBody ResponseEntity<InputStreamResource> exportPersonnels(@RequestBody PersonnelCriteria personnelCriteria) throws Exception {

		if (personnelCriteria.getExportModel() == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		personnelCriteria.setMaxResults(null);
		List<PersonnelDto> list = personnelService.findPersonnelsByCriteria(personnelCriteria);
		personnelCriteria.getExportModel().setList(list);
		return getExportedFileResource(personnelCriteria.getExportModel());
	
	}

	@PostMapping("/api/personnel/getPersonnelsDataSize")	
		
	public @ResponseBody ResponseEntity<Integer> getPersonnelDataSize(@RequestBody PersonnelCriteria personnelCriteria) throws Exception {

		int count = personnelService.getPersonnelDataSize(personnelCriteria);

		return new ResponseEntity<Integer>(count, HttpStatus.OK);

	}
	


	@GetMapping("/api/personnel/histPersonnel/{id}")	
	@PreAuthorize("hasRole('ROLE_HIST_PERSONNEL')")
	public ResponseEntity<AuditEntityDto> getHistPersonnelById(@PathVariable("id") Long id) throws Exception {

		AuditEntityDto histPersonnel = personnelService.getHistPersonnelById(id);

		return new ResponseEntity<AuditEntityDto>(histPersonnel, HttpStatus.OK);

	}
	
	@PostMapping("/api/personnel/paginatedListHistByCriteria")	
	@PreAuthorize("hasRole('ROLE_HIST_PERSONNEL')")
	public @ResponseBody ResponseEntity<PaginatedList> paginatedListHistPersonnels(@RequestBody HistPersonnelCriteria histPersonnelCriteria) throws Exception {

		List<AuditEntityDto> list = personnelService.paginatedListHistPersonnels(histPersonnelCriteria,histPersonnelCriteria.getPage(), histPersonnelCriteria.getMaxResults(), histPersonnelCriteria.getSortOrder(), histPersonnelCriteria.getSortField());

		PaginatedList paginatedList=new PaginatedList();
		paginatedList.setList(list);
		if (list != null && !list.isEmpty()) {
			int dateSize = personnelService.getHistPersonnelDataSize(histPersonnelCriteria);
			paginatedList.setDataSize(dateSize);
		}	

		return new ResponseEntity<PaginatedList>(paginatedList, HttpStatus.OK);

	}

	@PostMapping("/api/personnel/exportPersonnelsHist")		
	
	public @ResponseBody ResponseEntity<InputStreamResource> exportPersonnelsHist(@RequestBody HistPersonnelCriteria histPersonnelCriteria) throws Exception {

		if (histPersonnelCriteria.getExportModel() == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		histPersonnelCriteria.setMaxResults(null);
		List<AuditEntityDto> list = personnelService.findPersonnelsHistByCriteria(histPersonnelCriteria);
		histPersonnelCriteria.getExportModel().setList(list);
		return getExportedFileResource(histPersonnelCriteria.getExportModel());
	
	}

	@PostMapping("/api/personnel/getHistPersonnelsDataSize")		
	
	public @ResponseBody ResponseEntity<Integer> getHistPersonnelDataSize(@RequestBody HistPersonnelCriteria histPersonnelCriteria) throws Exception {

		int count = personnelService.getHistPersonnelDataSize(histPersonnelCriteria);

		return new ResponseEntity<Integer>(count, HttpStatus.OK);

	}
	

}