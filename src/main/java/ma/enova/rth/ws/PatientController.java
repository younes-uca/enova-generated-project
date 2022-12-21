package ma.enova.rth.ws;

import ma.enova.rth.common.bean.AuditEntityDto;
import ma.enova.rth.common.bean.PaginatedList;
import ma.enova.rth.converter.PatientConverter;
import ma.enova.rth.dao.criteria.core.PatientCriteria;
import ma.enova.rth.dao.criteria.history.HistPatientCriteria;
import ma.enova.rth.domain.core.Patient;
import ma.enova.rth.domain.historique.HistPatient;
import ma.enova.rth.dto.PatientDto;
import ma.enova.rth.common.ddd.controller.AbstractController;
import ma.enova.rth.service.facade.IPatientService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/patient/")
public class PatientController extends AbstractController<Patient, PatientDto, HistPatient, PatientCriteria, HistPatientCriteria, IPatientService, PatientConverter> {

    public PatientController(IPatientService service, PatientConverter abstractConverter) {
        super(service, abstractConverter);
    }

    @GetMapping("id/{id}")
    public ResponseEntity<PatientDto> findById(@PathVariable("id") Long id, String[] includes, String[] excludes) throws Exception {
        return super.findById(id, includes, excludes);
    }

    @PostMapping("")
    public ResponseEntity<Long> save(@RequestBody PatientDto dto) throws Exception {
        return super.save(dto);
    }

    @PutMapping("")
    public ResponseEntity<PatientDto> update(@RequestBody PatientDto dto) throws Exception {
        return super.update(dto);
    }

    @DeleteMapping("")
    public ResponseEntity<Void> delete(@RequestBody List<PatientDto> listToDelete) throws Exception {
        return super.delete(listToDelete);
    }


    @PostMapping("find-by-criteria/")
    public ResponseEntity<List<PatientDto>> findByCriteria(@RequestBody PatientCriteria criteria) throws Exception {
        return super.findMultipleByCriteria(criteria);
    }

    @PostMapping("find-paginated-by-criteria/")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody PatientCriteria criteria) throws Exception {
        return super.findPaginatedByCriteria(criteria);
    }

    @PostMapping("export/")
    public @ResponseBody ResponseEntity<InputStreamResource> export(@RequestBody PatientCriteria criteria) throws Exception {
        return super.export(criteria);
    }

    @PostMapping("data-size-by-criteria")
    public @ResponseBody ResponseEntity<Integer> getDataSize(@RequestBody PatientCriteria criteria) throws Exception {
        return super.getDataSize(criteria);
    }


    @GetMapping("history/id/{id}")
    public ResponseEntity<AuditEntityDto> findHistoryById(@PathVariable("id") Long id) throws Exception {
        return super.findHistoryById(id);
    }

    @PostMapping("history-paginated-by-criteria/")
    public @ResponseBody ResponseEntity<PaginatedList> findHistoryPaginatedByCriteria(@RequestBody HistPatientCriteria criteria) throws Exception {
        return super.findHistoryPaginatedByCriteria(criteria);
    }

    @PostMapping("export-history/")
    public @ResponseBody ResponseEntity<InputStreamResource> exportHistory(@RequestBody HistPatientCriteria criteria) throws Exception {
        return super.exportHistory(criteria);
    }

    @PostMapping("getHistPatientsDataSize")
    public @ResponseBody ResponseEntity<Integer> getHistoryDataSize(@RequestBody HistPatientCriteria criteria) throws Exception {
        return super.getHistoryDataSize(criteria);
    }


}