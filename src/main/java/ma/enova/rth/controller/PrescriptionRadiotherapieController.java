package ma.enova.rth.controller;

import ma.enova.rth.common.bean.AuditEntityDto;
import ma.enova.rth.common.bean.PaginatedList;
import ma.enova.rth.converter.PrescriptionRadiotherapieConverter;
import ma.enova.rth.dao.criteria.core.PrescriptionRadiotherapieCriteria;
import ma.enova.rth.dao.criteria.history.HistPrescriptionRadiotherapieCriteria;
import ma.enova.rth.domain.core.PrescriptionRadiotherapie;
import ma.enova.rth.domain.historique.HistPrescriptionRadiotherapie;
import ma.enova.rth.dto.PrescriptionRadiotherapieDto;
import ma.enova.rth.service.facade.IPrescriptionRadiotherapieService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
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
@RequestMapping("/api/prescription-rdiotherapie")
public class PrescriptionRadiotherapieController extends AbstractController<PrescriptionRadiotherapie, PrescriptionRadiotherapieDto, HistPrescriptionRadiotherapie, PrescriptionRadiotherapieCriteria, HistPrescriptionRadiotherapieCriteria, IPrescriptionRadiotherapieService, PrescriptionRadiotherapieConverter> {

    public PrescriptionRadiotherapieController(IPrescriptionRadiotherapieService service, PrescriptionRadiotherapieConverter abstractConverter) {
        super(service, abstractConverter);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<PrescriptionRadiotherapieDto> findById(@PathVariable("id") Long id, String[] includes, String[] excludes) throws Exception {
        return super.findById(id, includes, excludes);

    }

    @PostMapping("/")
    public ResponseEntity<Long> save(@RequestBody PrescriptionRadiotherapieDto dto) throws Exception {
        return super.save(dto);
    }


    @PutMapping("/")
    public ResponseEntity<PrescriptionRadiotherapieDto> update(@RequestBody PrescriptionRadiotherapieDto dto) throws Exception {
        return super.update(dto);
    }

    @DeleteMapping("delete")
    public ResponseEntity<Void> delete(@RequestBody List<PrescriptionRadiotherapieDto> listToDelete) throws Exception {
        return super.delete(listToDelete);
    }


    @PostMapping("listByCriteria")
    public ResponseEntity<List<PrescriptionRadiotherapieDto>> findByCriteria(@RequestBody PrescriptionRadiotherapieCriteria criteria) throws Exception {
        return super.findMultipleByCriteria(criteria);
    }

    @PostMapping("paginatedListByCriteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody PrescriptionRadiotherapieCriteria criteria) throws Exception {
        return super.findPaginatedByCriteria(criteria);
    }

    @PostMapping("exportPrescriptionRadiotherapies")
    public ResponseEntity<InputStreamResource> export(@RequestBody PrescriptionRadiotherapieCriteria criteria) throws Exception {
        return super.export(criteria);

    }

    @PostMapping("getPrescriptionRadiotherapiesDataSize")
    public @ResponseBody ResponseEntity<Integer> getDataSize(@RequestBody PrescriptionRadiotherapieCriteria criteria) throws Exception {
        return super.getDataSize(criteria);

    }


    @GetMapping("histPrescriptionRadiotherapie/{id}")
    public ResponseEntity<AuditEntityDto> findHistoryById(@PathVariable("id") Long id) throws Exception {
        return super.findHistoryById(id);
    }

    @PostMapping("paginatedListHistByCriteria")
    public @ResponseBody ResponseEntity<PaginatedList> paginatedListHistPrescriptionRadiotherapies(@RequestBody HistPrescriptionRadiotherapieCriteria histPrescriptionRadiotherapieCriteria) throws Exception {

        List<AuditEntityDto> list = prescriptionRadiotherapieService.findHistoryPaginatedByCriteria(histPrescriptionRadiotherapieCriteria, histPrescriptionRadiotherapieCriteria.getPage(), histPrescriptionRadiotherapieCriteria.getMaxResults(), histPrescriptionRadiotherapieCriteria.getSortOrder(), histPrescriptionRadiotherapieCriteria.getSortField());

        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(list);
        if (list != null && !list.isEmpty()) {
            int dateSize = prescriptionRadiotherapieService.getHistoryDataSize(histPrescriptionRadiotherapieCriteria);
            paginatedList.setDataSize(dateSize);
        }

        return new ResponseEntity<PaginatedList>(paginatedList, HttpStatus.OK);

    }

    @PostMapping("exportPrescriptionRadiotherapiesHist")

    public @ResponseBody ResponseEntity<InputStreamResource> exportPrescriptionRadiotherapiesHist(@RequestBody HistPrescriptionRadiotherapieCriteria histPrescriptionRadiotherapieCriteria) throws Exception {

        if (histPrescriptionRadiotherapieCriteria.getExportModel() == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        histPrescriptionRadiotherapieCriteria.setMaxResults(null);
//		List<AuditEntityDto> list = prescriptionRadiotherapieService.findHistoryPaginatedByCriteria(histPrescriptionRadiotherapieCriteria);
//		histPrescriptionRadiotherapieCriteria.getExportModel().setList(list);
//		return getExportedFileResource(histPrescriptionRadiotherapieCriteria.getExportModel());
        return null;//TODO correct this bug

    }

    @PostMapping("getHistPrescriptionRadiotherapiesDataSize")

    public @ResponseBody ResponseEntity<Integer> getHistPrescriptionRadiotherapieDataSize(@RequestBody HistPrescriptionRadiotherapieCriteria histPrescriptionRadiotherapieCriteria) throws Exception {

        int count = prescriptionRadiotherapieService.getHistoryDataSize(histPrescriptionRadiotherapieCriteria);

        return new ResponseEntity<Integer>(count, HttpStatus.OK);

    }


}