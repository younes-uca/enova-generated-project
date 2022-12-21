package ma.enova.rth.controller;

import ma.enova.rth.common.bean.AuditEntityDto;
import ma.enova.rth.common.bean.PaginatedList;
import ma.enova.rth.converter.PersonnelConverter;
import ma.enova.rth.dao.criteria.core.PersonnelCriteria;
import ma.enova.rth.dao.criteria.history.HistPersonnelCriteria;
import ma.enova.rth.domain.core.Personnel;
import ma.enova.rth.domain.historique.HistPersonnel;
import ma.enova.rth.dto.PersonnelDto;
import ma.enova.rth.common.ddd.controller.AbstractController;
import ma.enova.rth.service.facade.IPersonnelService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/personnel/")
public class PersonnelController extends AbstractController<Personnel, PersonnelDto, HistPersonnel, PersonnelCriteria, HistPersonnelCriteria, IPersonnelService, PersonnelConverter> {

    public PersonnelController(IPersonnelService service, PersonnelConverter abstractConverter) {
        super(service, abstractConverter);
    }

    @GetMapping("id/{id}")
    public ResponseEntity<PersonnelDto> findById(@PathVariable("id") Long id, String[] includes, String[] excludes) throws Exception {
        return super.findById(id, includes, excludes);
    }

    @PostMapping("")
    public ResponseEntity<Long> save(@RequestBody PersonnelDto dto) throws Exception {
        return super.save(dto);
    }

    @PutMapping("")
    public ResponseEntity<PersonnelDto> update(@RequestBody PersonnelDto dto) throws Exception {
        return super.update(dto);
    }

    @DeleteMapping("")
    public ResponseEntity<Void> delete(@RequestBody List<PersonnelDto> listToDelete) throws Exception {
        return super.delete(listToDelete);
    }


    @PostMapping("find-by-criteria/")
    public ResponseEntity<List<PersonnelDto>> findByCriteria(@RequestBody PersonnelCriteria criteria) throws Exception {
        return super.findMultipleByCriteria(criteria);
    }

    @PostMapping("find-paginated-by-criteria/")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody PersonnelCriteria criteria) throws Exception {
        return super.findPaginatedByCriteria(criteria);
    }

    @PostMapping("export/")
    public @ResponseBody ResponseEntity<InputStreamResource> export(@RequestBody PersonnelCriteria criteria) throws Exception {
        return super.export(criteria);
    }

    @PostMapping("data-size-by-criteria")
    public @ResponseBody ResponseEntity<Integer> getDataSize(@RequestBody PersonnelCriteria criteria) throws Exception {
        return super.getDataSize(criteria);
    }


    @GetMapping("history/id/{id}")
    public ResponseEntity<AuditEntityDto> findHistoryById(@PathVariable("id") Long id) throws Exception {
        return super.findHistoryById(id);
    }

    @PostMapping("history-paginated-by-criteria/")
    public @ResponseBody ResponseEntity<PaginatedList> findHistoryPaginatedByCriteria(@RequestBody HistPersonnelCriteria criteria) throws Exception {
        return super.findHistoryPaginatedByCriteria(criteria);
    }

    @PostMapping("export-history/")
    public @ResponseBody ResponseEntity<InputStreamResource> exportHistory(@RequestBody HistPersonnelCriteria criteria) throws Exception {
        return super.exportHistory(criteria);
    }

    @PostMapping("getHistPersonnelsDataSize")
    public @ResponseBody ResponseEntity<Integer> getHistoryDataSize(@RequestBody HistPersonnelCriteria criteria) throws Exception {
        return super.getHistoryDataSize(criteria);
    }


}