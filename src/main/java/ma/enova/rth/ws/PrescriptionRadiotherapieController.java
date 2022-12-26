package ma.enova.rth.ws;

import ma.enova.rth.zynerator.dto.AuditEntityDto;
import ma.enova.rth.zynerator.util.PaginatedList;
import ma.enova.rth.zynerator.controller.AbstractController;
import ma.enova.rth.zynerator.process.Result;
import ma.enova.rth.converter.PrescriptionRadiotherapieConverter;
import ma.enova.rth.dao.criteria.core.PrescriptionRadiotherapieCriteria;
import ma.enova.rth.dao.criteria.history.HistPrescriptionRadiotherapieCriteria;
import ma.enova.rth.bean.core.PrescriptionRadiotherapie;
import ma.enova.rth.bean.historique.HistPrescriptionRadiotherapie;
import ma.enova.rth.dto.PrescriptionRadiotherapieDto;
import ma.enova.rth.process.radiotherapie.prepare.RadiotherapiePrepareInput;
import ma.enova.rth.process.radiotherapie.prepare.RadiotherapiePrepareOutput;
import ma.enova.rth.process.radiotherapie.prepare.RadiotherapiePrepareProcess;
import ma.enova.rth.process.radiotherapie.save.RadiotherapieSaveInput;
import ma.enova.rth.process.radiotherapie.save.RadiotherapieSaveOutput;
import ma.enova.rth.process.radiotherapie.save.RadiotherapieSaveProcess;
import ma.enova.rth.service.facade.IPrescriptionRadiotherapieService;
import org.springframework.beans.factory.annotation.Autowired;
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
public class PrescriptionRadiotherapieController extends AbstractController<PrescriptionRadiotherapie, PrescriptionRadiotherapieDto,
        HistPrescriptionRadiotherapie, PrescriptionRadiotherapieCriteria, HistPrescriptionRadiotherapieCriteria, IPrescriptionRadiotherapieService,
        PrescriptionRadiotherapieConverter> {

    @Autowired
    private RadiotherapiePrepareProcess radiotherapiePrepareProcess;
    @Autowired
    private RadiotherapieSaveProcess radiotherapieSaveProcess;

    public PrescriptionRadiotherapieController(IPrescriptionRadiotherapieService service, PrescriptionRadiotherapieConverter abstractConverter) {
        super(service, abstractConverter);
    }

    @PostMapping("prepare/")
    public ResponseEntity<Result<RadiotherapiePrepareInput, RadiotherapiePrepareOutput>> prepare(@RequestBody RadiotherapiePrepareInput input) {
        RadiotherapiePrepareOutput output = new RadiotherapiePrepareOutput();
        Result<RadiotherapiePrepareInput, RadiotherapiePrepareOutput> result = radiotherapiePrepareProcess.execute(input, output);
        return new ResponseEntity<>(result, result.getStatus());
    }

    @PostMapping("")
    public ResponseEntity<Result<RadiotherapieSaveInput,RadiotherapieSaveOutput>> save(@RequestBody RadiotherapieSaveInput input) throws Exception {
        RadiotherapieSaveOutput output = new RadiotherapieSaveOutput();
        Result<RadiotherapieSaveInput, RadiotherapieSaveOutput> result = radiotherapieSaveProcess.execute(input, output);
        return new ResponseEntity<>(result, result.getStatus());
    }


    @GetMapping("")
    public ResponseEntity<List<PrescriptionRadiotherapieDto>> findAll() throws Exception {
        return super.findAll();
    }

    @GetMapping("id/{id}/")
    public ResponseEntity<PrescriptionRadiotherapieDto> findById(@PathVariable("id") Long id, String[] includes, String[] excludes) throws Exception {
        return super.findById(id, includes, excludes);
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