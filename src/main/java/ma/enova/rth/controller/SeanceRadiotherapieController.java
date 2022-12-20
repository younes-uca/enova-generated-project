package ma.enova.rth.controller;

import ma.enova.rth.common.bean.AuditEntityDto;
import ma.enova.rth.common.bean.BaseController;
import ma.enova.rth.common.bean.BusinessDto;
import ma.enova.rth.common.bean.PaginatedList;
import ma.enova.rth.common.util.StringUtil;
import ma.enova.rth.converter.SeanceRadiotherapieConverter;
import ma.enova.rth.dao.criteria.core.SeanceRadiotherapieCriteria;
import ma.enova.rth.dao.criteria.core.PrescriptionRadiotherapieCriteria;
import ma.enova.rth.dao.criteria.core.SeanceRadiotherapieCriteria;
import ma.enova.rth.dao.criteria.history.HistSeanceRadiotherapieCriteria;
import ma.enova.rth.dao.criteria.history.HistSeanceRadiotherapieCriteria;
import ma.enova.rth.domain.core.SeanceRadiotherapie;
import ma.enova.rth.domain.historique.HistSeanceRadiotherapie;
import ma.enova.rth.dto.SeanceRadiotherapieDto;
import ma.enova.rth.dto.SeanceRadiotherapieDto;
import ma.enova.rth.service.facade.ISeanceRadiotherapieService;
import ma.enova.rth.service.facade.IPrescriptionRadiotherapieService;
import ma.enova.rth.service.facade.ISeanceRadiotherapieService;
import ma.enova.rth.service.impl.SeanceRadiotherapieServiceImpl;
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
@RequestMapping("/api/seance-radiotherapie/")
public class SeanceRadiotherapieController extends AbstractController<SeanceRadiotherapie, SeanceRadiotherapieDto, HistSeanceRadiotherapie, SeanceRadiotherapieCriteria, HistSeanceRadiotherapieCriteria, ISeanceRadiotherapieService, SeanceRadiotherapieConverter> {

	public SeanceRadiotherapieController(ISeanceRadiotherapieService service, SeanceRadiotherapieConverter abstractConverter) {
		super(service, abstractConverter);
	}

	@GetMapping("id/{id}")
	public ResponseEntity<SeanceRadiotherapieDto> findById(@PathVariable("id") Long id, String[] includes, String[] excludes) throws Exception {
		return super.findById(id, includes, excludes);
	}

	@PostMapping("")
	public ResponseEntity<Long> save(@RequestBody SeanceRadiotherapieDto dto) throws Exception {
		return super.save(dto);
	}

	@PutMapping("")
	public ResponseEntity<SeanceRadiotherapieDto> update(@RequestBody SeanceRadiotherapieDto dto) throws Exception {
		return super.update(dto);
	}

	@DeleteMapping("")
	public ResponseEntity<Void> delete(@RequestBody List<SeanceRadiotherapieDto> listToDelete) throws Exception {
		return super.delete(listToDelete);
	}


	@PostMapping("find-by-criteria/")
	public ResponseEntity<List<SeanceRadiotherapieDto>> findByCriteria(@RequestBody SeanceRadiotherapieCriteria criteria) throws Exception {
		return super.findMultipleByCriteria(criteria);
	}

	@PostMapping("find-paginated-by-criteria/")
	public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody SeanceRadiotherapieCriteria criteria) throws Exception {
		return super.findPaginatedByCriteria(criteria);
	}

	@PostMapping("export/")
	public @ResponseBody ResponseEntity<InputStreamResource> export(@RequestBody SeanceRadiotherapieCriteria criteria) throws Exception {
		return super.export(criteria);
	}

	@PostMapping("data-size-by-criteria")
	public @ResponseBody ResponseEntity<Integer> getDataSize(@RequestBody SeanceRadiotherapieCriteria criteria) throws Exception {
		return super.getDataSize(criteria);
	}


	@GetMapping("history/id/{id}")
	public ResponseEntity<AuditEntityDto> findHistoryById(@PathVariable("id") Long id) throws Exception {
		return super.findHistoryById(id);
	}

	@PostMapping("history-paginated-by-criteria/")
	public @ResponseBody ResponseEntity<PaginatedList> findHistoryPaginatedByCriteria(@RequestBody HistSeanceRadiotherapieCriteria criteria) throws Exception {
		return super.findHistoryPaginatedByCriteria(criteria);
	}

	@PostMapping("export-history/")
	public @ResponseBody ResponseEntity<InputStreamResource> exportHistory(@RequestBody HistSeanceRadiotherapieCriteria criteria) throws Exception {
		return super.exportHistory(criteria);
	}

	@PostMapping("getHistSeanceRadiotherapiesDataSize")
	public @ResponseBody ResponseEntity<Integer> getHistoryDataSize(@RequestBody HistSeanceRadiotherapieCriteria criteria) throws Exception {
		return super.getHistoryDataSize(criteria);
	}



}