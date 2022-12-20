package ma.enova.rth.controller;

import ma.enova.rth.common.bean.AuditEntityDto;
import ma.enova.rth.common.bean.BaseController;
import ma.enova.rth.common.bean.PaginatedList;
import ma.enova.rth.common.util.StringUtil;
import ma.enova.rth.dao.criteria.core.ViseeCriteria;
import ma.enova.rth.dao.criteria.history.HistViseeCriteria;
import ma.enova.rth.dto.ViseeDto;
import ma.enova.rth.service.facade.IViseeService;
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
 * Manager controller : Visee
 * @author JAF
 * @version 1.2
 */
 
@RestController
public class ViseeController extends BaseController {


/**
	* Services metiers.
*/
	@Autowired
	private IViseeService viseeService;

	@GetMapping("/api/visee/{id}")
	@PreAuthorize("hasRole('ROLE_READ_VISEE')")
	public ResponseEntity<ViseeDto> getViseeById(@PathVariable("id") Long id, String[] includes, String[] excludes) throws Exception {

		ViseeDto visee = viseeService.getViseeById(id);

		if (StringUtil.isNoEmpty(includes) || StringUtil.isNoEmpty(excludes))
			visee = new ViseeDto().mappedCustomDto(visee, includes, excludes);

		return new ResponseEntity<ViseeDto>(visee, HttpStatus.OK);

	}
	
	@PostMapping("/api/visee")
	@PreAuthorize("hasRole('ROLE_CREATE_VISEE')")
	public ResponseEntity<Long> addVisee(@RequestBody ViseeDto visee) throws Exception {

		visee = viseeService.createVisee(visee);
		
		return new ResponseEntity<Long>(visee.getId(), HttpStatus.CREATED);

	}

 	@PutMapping("/api/visee")	
	@PreAuthorize("hasRole('ROLE_UPDATE_VISEE')")
	public ResponseEntity<ViseeDto> updateVisee(@RequestBody ViseeDto visee) throws Exception {

		if (visee.getId() == null)
			return new ResponseEntity<ViseeDto>(HttpStatus.CONFLICT);

		visee = viseeService.updateVisee(visee);

		return new ResponseEntity<ViseeDto>(visee, HttpStatus.OK);

	}
	
	@DeleteMapping("/api/visee/delete")
	@PreAuthorize("hasRole('ROLE_DELETE_VISEE')")
	public ResponseEntity<Void> deleteVisee(@RequestBody List<ViseeDto> viseeList) throws Exception {

		if (viseeList == null || viseeList.isEmpty())
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
			
		viseeService.deleteVisee(viseeList);
		return new ResponseEntity<Void>(HttpStatus.OK);

	}



	@PostMapping("/api/visee/listByCriteria")	
		
	public @ResponseBody
	ResponseEntity<List<ViseeDto>> getViseesByCriteria(@RequestBody ViseeCriteria viseeCriteria) throws Exception {
		List<ViseeDto> list = viseeService.findViseesByCriteria(viseeCriteria);
		if (StringUtil.isNoEmpty(viseeCriteria.getIncludes()) || StringUtil.isNoEmpty(viseeCriteria.getExcludes()));
			list = CollectionUtils.emptyIfNull(list).stream().map(visee -> new ViseeDto().mappedCustomDto(visee, viseeCriteria.getIncludes(), viseeCriteria.getExcludes())).collect(Collectors.toList());

		if (list == null || list.isEmpty())
			return new ResponseEntity<List<ViseeDto>>(HttpStatus.NO_CONTENT);

		return new ResponseEntity<List<ViseeDto>>(list, HttpStatus.OK);

	}
	
	@PostMapping("/api/visee/paginatedListByCriteria")		
	@PreAuthorize("hasRole('ROLE_READ_VISEE')")
	public @ResponseBody
	ResponseEntity<PaginatedList> paginatedListVisees(@RequestBody ViseeCriteria viseeCriteria) throws Exception {

		List<ViseeDto> list = viseeService.paginatedListVisees(viseeCriteria,viseeCriteria.getPage(),viseeCriteria.getMaxResults(), viseeCriteria.getSortOrder(), viseeCriteria.getSortField());

		if (StringUtil.isNoEmpty(viseeCriteria.getIncludes()) || StringUtil.isNoEmpty(viseeCriteria.getExcludes()));
			list = CollectionUtils.emptyIfNull(list).stream().map(visee -> new ViseeDto().mappedCustomDto(visee, viseeCriteria.getIncludes(), viseeCriteria.getExcludes())).collect(Collectors.toList());

		PaginatedList paginatedList=new PaginatedList();
		paginatedList.setList(list);
		if (list != null && !list.isEmpty()) {
			int dateSize = viseeService.getViseeDataSize(viseeCriteria);
			paginatedList.setDataSize(dateSize);
		}
		
		return new ResponseEntity<PaginatedList>(paginatedList, HttpStatus.OK);

	}
	
	@PostMapping("/api/visee/exportVisees")		
	
	public @ResponseBody ResponseEntity<InputStreamResource> exportVisees(@RequestBody ViseeCriteria viseeCriteria) throws Exception {

		if (viseeCriteria.getExportModel() == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		viseeCriteria.setMaxResults(null);
		List<ViseeDto> list = viseeService.findViseesByCriteria(viseeCriteria);
		viseeCriteria.getExportModel().setList(list);
		return getExportedFileResource(viseeCriteria.getExportModel());
	
	}

	@PostMapping("/api/visee/getViseesDataSize")	
		
	public @ResponseBody ResponseEntity<Integer> getViseeDataSize(@RequestBody ViseeCriteria viseeCriteria) throws Exception {

		int count = viseeService.getViseeDataSize(viseeCriteria);

		return new ResponseEntity<Integer>(count, HttpStatus.OK);

	}
	


	@GetMapping("/api/visee/histVisee/{id}")	
	@PreAuthorize("hasRole('ROLE_HIST_VISEE')")
	public ResponseEntity<AuditEntityDto> getHistViseeById(@PathVariable("id") Long id) throws Exception {

		AuditEntityDto histVisee = viseeService.getHistViseeById(id);

		return new ResponseEntity<AuditEntityDto>(histVisee, HttpStatus.OK);

	}
	
	@PostMapping("/api/visee/paginatedListHistByCriteria")	
	@PreAuthorize("hasRole('ROLE_HIST_VISEE')")
	public @ResponseBody ResponseEntity<PaginatedList> paginatedListHistVisees(@RequestBody HistViseeCriteria histViseeCriteria) throws Exception {

		List<AuditEntityDto> list = viseeService.paginatedListHistVisees(histViseeCriteria,histViseeCriteria.getPage(), histViseeCriteria.getMaxResults(), histViseeCriteria.getSortOrder(), histViseeCriteria.getSortField());

		PaginatedList paginatedList=new PaginatedList();
		paginatedList.setList(list);
		if (list != null && !list.isEmpty()) {
			int dateSize = viseeService.getHistViseeDataSize(histViseeCriteria);
			paginatedList.setDataSize(dateSize);
		}	

		return new ResponseEntity<PaginatedList>(paginatedList, HttpStatus.OK);

	}

	@PostMapping("/api/visee/exportViseesHist")		
	
	public @ResponseBody ResponseEntity<InputStreamResource> exportViseesHist(@RequestBody HistViseeCriteria histViseeCriteria) throws Exception {

		if (histViseeCriteria.getExportModel() == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		histViseeCriteria.setMaxResults(null);
		List<AuditEntityDto> list = viseeService.findViseesHistByCriteria(histViseeCriteria);
		histViseeCriteria.getExportModel().setList(list);
		return getExportedFileResource(histViseeCriteria.getExportModel());
	
	}

	@PostMapping("/api/visee/getHistViseesDataSize")		
	
	public @ResponseBody ResponseEntity<Integer> getHistViseeDataSize(@RequestBody HistViseeCriteria histViseeCriteria) throws Exception {

		int count = viseeService.getHistViseeDataSize(histViseeCriteria);

		return new ResponseEntity<Integer>(count, HttpStatus.OK);

	}
	

}