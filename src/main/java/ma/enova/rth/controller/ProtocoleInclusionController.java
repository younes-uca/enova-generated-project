package ma.enova.rth.controller;

import ma.enova.rth.common.bean.AuditEntityDto;
import ma.enova.rth.common.bean.BaseController;
import ma.enova.rth.common.bean.PaginatedList;
import ma.enova.rth.common.enumeration.STATUT_PROTOCOLEINCLUSION;
import ma.enova.rth.common.util.StringUtil;
import ma.enova.rth.dao.criteria.core.ProtocoleInclusionCriteria;
import ma.enova.rth.dao.criteria.history.HistProtocoleInclusionCriteria;
import ma.enova.rth.dto.ProtocoleInclusionDto;
import ma.enova.rth.service.facade.IProtocoleInclusionService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Manager controller : ProtocoleInclusion
 * @author JAF
 * @version 1.2
 */
 
@RestController
public class ProtocoleInclusionController extends BaseController {


/**
	* Services metiers.
*/
	@Autowired
	private IProtocoleInclusionService protocoleInclusionService;

	@GetMapping("/api/protocoleInclusion/{id}")
	@PreAuthorize("hasRole('ROLE_READ_PROTOCOLEINCLUSION')")
	public ResponseEntity<ProtocoleInclusionDto> getProtocoleInclusionById(@PathVariable("id") Long id, String[] includes, String[] excludes) throws Exception {

		ProtocoleInclusionDto protocoleInclusion = protocoleInclusionService.getProtocoleInclusionById(id);

		if (StringUtil.isNoEmpty(includes) || StringUtil.isNoEmpty(excludes))
			protocoleInclusion = new ProtocoleInclusionDto().mappedCustomDto(protocoleInclusion, includes, excludes);

		return new ResponseEntity<ProtocoleInclusionDto>(protocoleInclusion, HttpStatus.OK);

	}
	
	@PostMapping("/api/protocoleInclusion")
	@PreAuthorize("hasRole('ROLE_CREATE_PROTOCOLEINCLUSION')")
	public ResponseEntity<Long> addProtocoleInclusion(@RequestBody ProtocoleInclusionDto protocoleInclusion) throws Exception {

		protocoleInclusion = protocoleInclusionService.createProtocoleInclusion(protocoleInclusion);
		
		return new ResponseEntity<Long>(protocoleInclusion.getId(), HttpStatus.CREATED);

	}

 	@PutMapping("/api/protocoleInclusion")	
	@PreAuthorize("hasRole('ROLE_UPDATE_PROTOCOLEINCLUSION')")
	public ResponseEntity<ProtocoleInclusionDto> updateProtocoleInclusion(@RequestBody ProtocoleInclusionDto protocoleInclusion) throws Exception {

		if (protocoleInclusion.getId() == null)
			return new ResponseEntity<ProtocoleInclusionDto>(HttpStatus.CONFLICT);

		protocoleInclusion = protocoleInclusionService.updateProtocoleInclusion(protocoleInclusion);

		return new ResponseEntity<ProtocoleInclusionDto>(protocoleInclusion, HttpStatus.OK);

	}
	
	@DeleteMapping("/api/protocoleInclusion/delete")
	@PreAuthorize("hasRole('ROLE_DELETE_PROTOCOLEINCLUSION')")
	public ResponseEntity<Void> deleteProtocoleInclusion(@RequestBody List<ProtocoleInclusionDto> protocoleInclusionList) throws Exception {

		if (protocoleInclusionList == null || protocoleInclusionList.isEmpty())
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
			
		protocoleInclusionService.deleteProtocoleInclusion(protocoleInclusionList);
		return new ResponseEntity<Void>(HttpStatus.OK);

	}



	@PostMapping("/api/protocoleInclusion/listByCriteria")	
		
	public @ResponseBody
	ResponseEntity<List<ProtocoleInclusionDto>> getProtocoleInclusionsByCriteria(@RequestBody ProtocoleInclusionCriteria protocoleInclusionCriteria) throws Exception {

		List<ProtocoleInclusionDto> list = protocoleInclusionService.findProtocoleInclusionsByCriteria(protocoleInclusionCriteria);

		if (StringUtil.isNoEmpty(protocoleInclusionCriteria.getIncludes()) || StringUtil.isNoEmpty(protocoleInclusionCriteria.getExcludes()));
			list = CollectionUtils.emptyIfNull(list).stream().map(protocoleInclusion -> new ProtocoleInclusionDto().mappedCustomDto(protocoleInclusion, protocoleInclusionCriteria.getIncludes(), protocoleInclusionCriteria.getExcludes())).collect(Collectors.toList());

		if (list == null || list.isEmpty())
			return new ResponseEntity<List<ProtocoleInclusionDto>>(HttpStatus.NO_CONTENT);

		return new ResponseEntity<List<ProtocoleInclusionDto>>(list, HttpStatus.OK);

	}
	
	@PostMapping("/api/protocoleInclusion/paginatedListByCriteria")		
	@PreAuthorize("hasRole('ROLE_READ_PROTOCOLEINCLUSION')")
	public @ResponseBody
	ResponseEntity<PaginatedList> paginatedListProtocoleInclusions(@RequestBody ProtocoleInclusionCriteria protocoleInclusionCriteria) throws Exception {

		List<ProtocoleInclusionDto> list = protocoleInclusionService.paginatedListProtocoleInclusions(protocoleInclusionCriteria,protocoleInclusionCriteria.getPage(),protocoleInclusionCriteria.getMaxResults(), protocoleInclusionCriteria.getSortOrder(), protocoleInclusionCriteria.getSortField());

		if (StringUtil.isNoEmpty(protocoleInclusionCriteria.getIncludes()) || StringUtil.isNoEmpty(protocoleInclusionCriteria.getExcludes()));
			list = CollectionUtils.emptyIfNull(list).stream().map(protocoleInclusion -> new ProtocoleInclusionDto().mappedCustomDto(protocoleInclusion, protocoleInclusionCriteria.getIncludes(), protocoleInclusionCriteria.getExcludes())).collect(Collectors.toList());

		PaginatedList paginatedList=new PaginatedList();
		paginatedList.setList(list);
		if (list != null && !list.isEmpty()) {
			int dateSize = protocoleInclusionService.getProtocoleInclusionDataSize(protocoleInclusionCriteria);
			paginatedList.setDataSize(dateSize);
		}
		
		return new ResponseEntity<PaginatedList>(paginatedList, HttpStatus.OK);

	}
	
	@PostMapping("/api/protocoleInclusion/exportProtocoleInclusions")		
	
	public @ResponseBody ResponseEntity<InputStreamResource> exportProtocoleInclusions(@RequestBody ProtocoleInclusionCriteria protocoleInclusionCriteria) throws Exception {

		if (protocoleInclusionCriteria.getExportModel() == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		protocoleInclusionCriteria.setMaxResults(null);
		List<ProtocoleInclusionDto> list = protocoleInclusionService.findProtocoleInclusionsByCriteria(protocoleInclusionCriteria);
		protocoleInclusionCriteria.getExportModel().setList(list);
		return getExportedFileResource(protocoleInclusionCriteria.getExportModel());
	
	}

	@PostMapping("/api/protocoleInclusion/getProtocoleInclusionsDataSize")	
		
	public @ResponseBody ResponseEntity<Integer> getProtocoleInclusionDataSize(@RequestBody ProtocoleInclusionCriteria protocoleInclusionCriteria) throws Exception {

		int count = protocoleInclusionService.getProtocoleInclusionDataSize(protocoleInclusionCriteria);

		return new ResponseEntity<Integer>(count, HttpStatus.OK);

	}
	
	@GetMapping("/api/protocoleInclusion/getStatusList")
	@PreAuthorize("hasAnyRole('ROLE_READ_PROTOCOLEINCLUSION','ROLE_CREATE_PROTOCOLEINCLUSION','ROLE_UPDATE_PROTOCOLEINCLUSION')")
	public ResponseEntity<List<STATUT_PROTOCOLEINCLUSION>> getStatusList() throws Exception {

		List<STATUT_PROTOCOLEINCLUSION> statusList = Arrays.asList(STATUT_PROTOCOLEINCLUSION.values());

		return new ResponseEntity<List<STATUT_PROTOCOLEINCLUSION>>(statusList, HttpStatus.OK);
	}



	@GetMapping("/api/protocoleInclusion/histProtocoleInclusion/{id}")	
	@PreAuthorize("hasRole('ROLE_HIST_PROTOCOLEINCLUSION')")
	public ResponseEntity<AuditEntityDto> getHistProtocoleInclusionById(@PathVariable("id") Long id) throws Exception {

		AuditEntityDto histProtocoleInclusion = protocoleInclusionService.getHistProtocoleInclusionById(id);

		return new ResponseEntity<AuditEntityDto>(histProtocoleInclusion, HttpStatus.OK);

	}
	
	@PostMapping("/api/protocoleInclusion/paginatedListHistByCriteria")	
	@PreAuthorize("hasRole('ROLE_HIST_PROTOCOLEINCLUSION')")
	public @ResponseBody ResponseEntity<PaginatedList> paginatedListHistProtocoleInclusions(@RequestBody HistProtocoleInclusionCriteria histProtocoleInclusionCriteria) throws Exception {

		List<AuditEntityDto> list = protocoleInclusionService.paginatedListHistProtocoleInclusions(histProtocoleInclusionCriteria,histProtocoleInclusionCriteria.getPage(), histProtocoleInclusionCriteria.getMaxResults(), histProtocoleInclusionCriteria.getSortOrder(), histProtocoleInclusionCriteria.getSortField());

		PaginatedList paginatedList=new PaginatedList();
		paginatedList.setList(list);
		if (list != null && !list.isEmpty()) {
			int dateSize = protocoleInclusionService.getHistProtocoleInclusionDataSize(histProtocoleInclusionCriteria);
			paginatedList.setDataSize(dateSize);
		}	

		return new ResponseEntity<PaginatedList>(paginatedList, HttpStatus.OK);

	}

	@PostMapping("/api/protocoleInclusion/exportProtocoleInclusionsHist")		
	
	public @ResponseBody ResponseEntity<InputStreamResource> exportProtocoleInclusionsHist(@RequestBody HistProtocoleInclusionCriteria histProtocoleInclusionCriteria) throws Exception {

		if (histProtocoleInclusionCriteria.getExportModel() == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		histProtocoleInclusionCriteria.setMaxResults(null);
		List<AuditEntityDto> list = protocoleInclusionService.findProtocoleInclusionsHistByCriteria(histProtocoleInclusionCriteria);
		histProtocoleInclusionCriteria.getExportModel().setList(list);
		return getExportedFileResource(histProtocoleInclusionCriteria.getExportModel());
	
	}

	@PostMapping("/api/protocoleInclusion/getHistProtocoleInclusionsDataSize")		
	
	public @ResponseBody ResponseEntity<Integer> getHistProtocoleInclusionDataSize(@RequestBody HistProtocoleInclusionCriteria histProtocoleInclusionCriteria) throws Exception {

		int count = protocoleInclusionService.getHistProtocoleInclusionDataSize(histProtocoleInclusionCriteria);

		return new ResponseEntity<Integer>(count, HttpStatus.OK);

	}
	

}