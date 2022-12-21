package ma.enova.rth.ws;

import ma.enova.rth.common.bean.AuditEntityDto;
import ma.enova.rth.common.bean.PaginatedList;
import ma.enova.rth.converter.FrequenceRadiotherapieConverter;
import ma.enova.rth.dao.criteria.core.FrequenceRadiotherapieCriteria;
import ma.enova.rth.dao.criteria.history.HistFrequenceRadiotherapieCriteria;
import ma.enova.rth.domain.core.FrequenceRadiotherapie;
import ma.enova.rth.domain.historique.HistFrequenceRadiotherapie;
import ma.enova.rth.dto.FrequenceRadiotherapieDto;
import ma.enova.rth.common.ddd.controller.AbstractController;
import ma.enova.rth.service.facade.IFrequenceRadiotherapieService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/frequence-radiotherapie/")
public class FrequenceRadiotherapieController extends AbstractController<FrequenceRadiotherapie, FrequenceRadiotherapieDto, HistFrequenceRadiotherapie, FrequenceRadiotherapieCriteria, HistFrequenceRadiotherapieCriteria, IFrequenceRadiotherapieService, FrequenceRadiotherapieConverter> {

    public FrequenceRadiotherapieController(IFrequenceRadiotherapieService service, FrequenceRadiotherapieConverter abstractConverter) {
        super(service, abstractConverter);
    }

    @GetMapping("id/{id}")
    public ResponseEntity<FrequenceRadiotherapieDto> findById(@PathVariable("id") Long id, String[] includes, String[] excludes) throws Exception {
        return super.findById(id, includes, excludes);
    }

    @PostMapping("")
    public ResponseEntity<Long> save(@RequestBody FrequenceRadiotherapieDto dto) throws Exception {
        return super.save(dto);
    }

    @PutMapping("")
    public ResponseEntity<FrequenceRadiotherapieDto> update(@RequestBody FrequenceRadiotherapieDto dto) throws Exception {
        return super.update(dto);
    }

    @DeleteMapping("")
    public ResponseEntity<Void> delete(@RequestBody List<FrequenceRadiotherapieDto> listToDelete) throws Exception {
        return super.delete(listToDelete);
    }


    @PostMapping("find-by-criteria/")
    public ResponseEntity<List<FrequenceRadiotherapieDto>> findByCriteria(@RequestBody FrequenceRadiotherapieCriteria criteria) throws Exception {
        return super.findMultipleByCriteria(criteria);
    }

    @PostMapping("find-paginated-by-criteria/")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody FrequenceRadiotherapieCriteria criteria) throws Exception {
        return super.findPaginatedByCriteria(criteria);
    }

    @PostMapping("export/")
    public @ResponseBody ResponseEntity<InputStreamResource> export(@RequestBody FrequenceRadiotherapieCriteria criteria) throws Exception {
        return super.export(criteria);
    }

    @PostMapping("data-size-by-criteria")
    public @ResponseBody ResponseEntity<Integer> getDataSize(@RequestBody FrequenceRadiotherapieCriteria criteria) throws Exception {
        return super.getDataSize(criteria);
    }


    @GetMapping("history/id/{id}")
    public ResponseEntity<AuditEntityDto> findHistoryById(@PathVariable("id") Long id) throws Exception {
        return super.findHistoryById(id);
    }

    @PostMapping("history-paginated-by-criteria/")
    public @ResponseBody ResponseEntity<PaginatedList> findHistoryPaginatedByCriteria(@RequestBody HistFrequenceRadiotherapieCriteria criteria) throws Exception {
        return super.findHistoryPaginatedByCriteria(criteria);
    }

    @PostMapping("export-history/")
    public @ResponseBody ResponseEntity<InputStreamResource> exportHistory(@RequestBody HistFrequenceRadiotherapieCriteria criteria) throws Exception {
        return super.exportHistory(criteria);
    }

    @PostMapping("getHistFrequenceRadiotherapiesDataSize")
    public @ResponseBody ResponseEntity<Integer> getHistoryDataSize(@RequestBody HistFrequenceRadiotherapieCriteria criteria) throws Exception {
        return super.getHistoryDataSize(criteria);
    }


}