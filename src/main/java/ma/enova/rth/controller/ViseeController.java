package ma.enova.rth.controller;

import ma.enova.rth.common.bean.AuditEntityDto;
import ma.enova.rth.common.bean.BaseController;
import ma.enova.rth.common.bean.PaginatedList;
import ma.enova.rth.common.util.StringUtil;
import ma.enova.rth.converter.ViseeConverter;
import ma.enova.rth.dao.criteria.core.ViseeCriteria;
import ma.enova.rth.dao.criteria.core.ViseeCriteria;
import ma.enova.rth.dao.criteria.history.HistViseeCriteria;
import ma.enova.rth.dao.criteria.history.HistViseeCriteria;
import ma.enova.rth.domain.core.Visee;
import ma.enova.rth.domain.historique.HistVisee;
import ma.enova.rth.dto.ViseeDto;
import ma.enova.rth.dto.ViseeDto;
import ma.enova.rth.service.facade.IViseeService;
import ma.enova.rth.service.facade.IViseeService;
import ma.enova.rth.service.impl.ViseeServiceImpl;
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
@RequestMapping("/api/visee/")
public class ViseeController extends AbstractController<Visee, ViseeDto, HistVisee, ViseeCriteria, HistViseeCriteria, IViseeService, ViseeConverter> {

	public ViseeController(IViseeService service, ViseeConverter abstractConverter) {
		super(service, abstractConverter);
	}

	@GetMapping("id/{id}")
	public ResponseEntity<ViseeDto> findById(@PathVariable("id") Long id, String[] includes, String[] excludes) throws Exception {
		return super.findById(id, includes, excludes);
	}

	@PostMapping("")
	public ResponseEntity<Long> save(@RequestBody ViseeDto dto) throws Exception {
		return super.save(dto);
	}

	@PutMapping("")
	public ResponseEntity<ViseeDto> update(@RequestBody ViseeDto dto) throws Exception {
		return super.update(dto);
	}

	@DeleteMapping("")
	public ResponseEntity<Void> delete(@RequestBody List<ViseeDto> listToDelete) throws Exception {
		return super.delete(listToDelete);
	}


	@PostMapping("find-by-criteria/")
	public ResponseEntity<List<ViseeDto>> findByCriteria(@RequestBody ViseeCriteria criteria) throws Exception {
		return super.findMultipleByCriteria(criteria);
	}

	@PostMapping("find-paginated-by-criteria/")
	public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody ViseeCriteria criteria) throws Exception {
		return super.findPaginatedByCriteria(criteria);
	}

	@PostMapping("export/")
	public @ResponseBody ResponseEntity<InputStreamResource> export(@RequestBody ViseeCriteria criteria) throws Exception {
		return super.export(criteria);
	}

	@PostMapping("data-size-by-criteria")
	public @ResponseBody ResponseEntity<Integer> getDataSize(@RequestBody ViseeCriteria criteria) throws Exception {
		return super.getDataSize(criteria);
	}


	@GetMapping("history/id/{id}")
	public ResponseEntity<AuditEntityDto> findHistoryById(@PathVariable("id") Long id) throws Exception {
		return super.findHistoryById(id);
	}

	@PostMapping("history-paginated-by-criteria/")
	public @ResponseBody ResponseEntity<PaginatedList> findHistoryPaginatedByCriteria(@RequestBody HistViseeCriteria criteria) throws Exception {
		return super.findHistoryPaginatedByCriteria(criteria);
	}

	@PostMapping("export-history/")
	public @ResponseBody ResponseEntity<InputStreamResource> exportHistory(@RequestBody HistViseeCriteria criteria) throws Exception {
		return super.exportHistory(criteria);
	}

	@PostMapping("getHistViseesDataSize")
	public @ResponseBody ResponseEntity<Integer> getHistoryDataSize(@RequestBody HistViseeCriteria criteria) throws Exception {
		return super.getHistoryDataSize(criteria);
	}

}