package ma.enova.rth.controller;

import ma.enova.rth.common.bean.AuditEntityDto;
import ma.enova.rth.common.bean.BaseController;
import ma.enova.rth.common.bean.PaginatedList;
import ma.enova.rth.common.util.StringUtil;
import ma.enova.rth.common.util.StringUtil;
import ma.enova.rth.converter.OrganeConverter;
import ma.enova.rth.dao.criteria.core.OrganeCriteria;
import ma.enova.rth.dao.criteria.core.OrganeCriteria;
import ma.enova.rth.dao.criteria.history.HistOrganeCriteria;
import ma.enova.rth.dao.criteria.history.HistOrganeCriteria;
import ma.enova.rth.domain.core.Organe;
import ma.enova.rth.domain.core.Organe;
import ma.enova.rth.domain.historique.HistOrgane;
import ma.enova.rth.dto.OrganeDto;
import ma.enova.rth.dto.OrganeDto;
import ma.enova.rth.service.facade.IOrganeService;
import ma.enova.rth.service.facade.IOrganeService;
import ma.enova.rth.service.impl.OrganeServiceImpl;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

 
@RestController
@RequestMapping("/api/organe/")
public class OrganeController extends AbstractController<Organe, OrganeDto, HistOrgane, OrganeCriteria, HistOrganeCriteria, IOrganeService, OrganeConverter> {

	@Autowired
	OrganeServiceImpl organeService;
	public OrganeController(IOrganeService service, OrganeConverter abstractConverter) {
		super(service, abstractConverter);
	}

	@GetMapping("id/{id}")
	public ResponseEntity<OrganeDto> findById(@PathVariable("id") Long id, String[] includes, String[] excludes) throws Exception {
		return super.findById(id, includes, excludes);
	}

	@PostMapping("")
	public ResponseEntity<Long> save(@RequestBody OrganeDto dto) throws Exception {
		return super.save(dto);
	}

	@PutMapping("")
	public ResponseEntity<OrganeDto> update(@RequestBody OrganeDto dto) throws Exception {
		return super.update(dto);
	}

	@DeleteMapping("")
	public ResponseEntity<Void> delete(@RequestBody List<OrganeDto> listToDelete) throws Exception {
		return super.delete(listToDelete);
	}


	@PostMapping("find-by-criteria/")
	public ResponseEntity<List<OrganeDto>> findByCriteria(@RequestBody OrganeCriteria criteria) throws Exception {
		return super.findMultipleByCriteria(criteria);
	}

	@PostMapping("find-paginated-by-criteria/")
	public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody OrganeCriteria criteria) throws Exception {
		return super.findPaginatedByCriteria(criteria);
	}

	@PostMapping("export/")
	public @ResponseBody ResponseEntity<InputStreamResource> export(@RequestBody OrganeCriteria criteria) throws Exception {
		return super.export(criteria);
	}

	@PostMapping("data-size-by-criteria")
	public @ResponseBody ResponseEntity<Integer> getDataSize(@RequestBody OrganeCriteria criteria) throws Exception {
		return super.getDataSize(criteria);
	}


	@GetMapping("history/id/{id}")
	public ResponseEntity<AuditEntityDto> findHistoryById(@PathVariable("id") Long id) throws Exception {
		return super.findHistoryById(id);
	}

	@PostMapping("history-paginated-by-criteria/")
	public @ResponseBody ResponseEntity<PaginatedList> findHistoryPaginatedByCriteria(@RequestBody HistOrganeCriteria criteria) throws Exception {
		return super.findHistoryPaginatedByCriteria(criteria);
	}

	@PostMapping("export-history/")
	public @ResponseBody ResponseEntity<InputStreamResource> exportHistory(@RequestBody HistOrganeCriteria criteria) throws Exception {
		return super.exportHistory(criteria);
	}

	@PostMapping("getHistOrganesDataSize")
	public @ResponseBody ResponseEntity<Integer> getHistoryDataSize(@RequestBody HistOrganeCriteria criteria) throws Exception {
		return super.getHistoryDataSize(criteria);
	}

	@GetMapping("")
	public List<Organe> findAll(){
		return organeService.findAll();
	}

}