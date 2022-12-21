package ma.enova.rth.controller;

import ma.enova.rth.common.bean.AuditEntityDto;
import ma.enova.rth.common.bean.PaginatedList;
import ma.enova.rth.converter.PrescriptionRadiotherapieConverter;
import ma.enova.rth.dao.criteria.core.PrescriptionRadiotherapieCriteria;
import ma.enova.rth.dao.criteria.history.HistPrescriptionRadiotherapieCriteria;
import ma.enova.rth.domain.core.PrescriptionRadiotherapie;
import ma.enova.rth.domain.historique.HistPrescriptionRadiotherapie;
import ma.enova.rth.dto.PrescriptionRadiotherapieDto;
import ma.enova.rth.common.ddd.controller.AbstractController;
import ma.enova.rth.service.facade.IPrescriptionRadiotherapieService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Manager controller : PrescriptionRadiotherapie
 *
 * @author JAF
 * @version 1.2
 */

@RestController
@RequestMapping("/api/prescription-rdiotherapie/")
public class PrescriptionRadiotherapieController extends AbstractController<PrescriptionRadiotherapie, PrescriptionRadiotherapieDto, HistPrescriptionRadiotherapie, PrescriptionRadiotherapieCriteria, HistPrescriptionRadiotherapieCriteria, IPrescriptionRadiotherapieService, PrescriptionRadiotherapieConverter> {

    public PrescriptionRadiotherapieController(IPrescriptionRadiotherapieService service, PrescriptionRadiotherapieConverter abstractConverter) {
        super(service, abstractConverter);
    }

    @GetMapping("id/{id}/")
    public ResponseEntity<PrescriptionRadiotherapieDto> findById(@PathVariable("id") Long id, String[] includes, String[] excludes) throws Exception {
        return super.findById(id, includes, excludes);

    }

    @PostMapping("")
    public ResponseEntity<Long> save(@RequestBody PrescriptionRadiotherapieDto dto) throws Exception {
        return super.save(dto);
    }


    @PutMapping("")
    public ResponseEntity<PrescriptionRadiotherapieDto> update(@RequestBody PrescriptionRadiotherapieDto dto) throws Exception {
        return super.update(dto);
    }

    @DeleteMapping("")
    public ResponseEntity<Void> delete(@RequestBody List<PrescriptionRadiotherapieDto> listToDelete) throws Exception {
        return super.delete(listToDelete);
    }


    @PostMapping("find-by-criteria/")
    public ResponseEntity<List<PrescriptionRadiotherapieDto>> findByCriteria(@RequestBody PrescriptionRadiotherapieCriteria criteria) throws Exception {
        return super.findMultipleByCriteria(criteria);
    }

    @PostMapping("find-paginated-by-criteria/")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody PrescriptionRadiotherapieCriteria criteria) throws Exception {
        return super.findPaginatedByCriteria(criteria);
    }

    @PostMapping("export/")
    public ResponseEntity<InputStreamResource> export(@RequestBody PrescriptionRadiotherapieCriteria criteria) throws Exception {
        return super.export(criteria);

    }

    @PostMapping("data-size-by-criteria/")
    public ResponseEntity<Integer> getDataSize(@RequestBody PrescriptionRadiotherapieCriteria criteria) throws Exception {
        return super.getDataSize(criteria);

    }

    @GetMapping("history/id/{id}/")
    public ResponseEntity<AuditEntityDto> findHistoryById(@PathVariable("id") Long id) throws Exception {
        return super.findHistoryById(id);
    }

    @PostMapping("history-paginated-by-criteria/")
    public ResponseEntity<PaginatedList> findHistoryPaginatedByCriteria(@RequestBody HistPrescriptionRadiotherapieCriteria criteria) throws Exception {
        return super.findHistoryPaginatedByCriteria(criteria);
    }

    @PostMapping("export-history/")
    public ResponseEntity<InputStreamResource> exportHistory(@RequestBody HistPrescriptionRadiotherapieCriteria criteria) throws Exception {
        return super.exportHistory(criteria);
    }

    @PostMapping("getHistPrescriptionRadiotherapiesDataSize")
    public ResponseEntity<Integer> getHistoryDataSize(@RequestBody HistPrescriptionRadiotherapieCriteria criteria) throws Exception {
        return super.getHistoryDataSize(criteria);
    }


}