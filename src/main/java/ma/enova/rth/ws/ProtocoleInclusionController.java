package ma.enova.rth.ws;

import ma.enova.rth.bean.core.ProtocoleInclusion;
import ma.enova.rth.bean.historique.HistProtocoleInclusion;
import ma.enova.rth.converter.ProtocoleInclusionConverter;
import ma.enova.rth.dao.criteria.core.ProtocoleInclusionCriteria;
import ma.enova.rth.dao.criteria.history.HistProtocoleInclusionCriteria;
import ma.enova.rth.dto.ProtocoleInclusionDto;
import ma.enova.rth.service.facade.IProtocoleInclusionService;
import ma.enova.rth.zynerator.controller.AbstractController;
import ma.enova.rth.zynerator.dto.AuditEntityDto;
import ma.enova.rth.zynerator.util.PaginatedList;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/protocole-inclusion/")
public class ProtocoleInclusionController extends AbstractController<ProtocoleInclusion, ProtocoleInclusionDto, HistProtocoleInclusion, ProtocoleInclusionCriteria, HistProtocoleInclusionCriteria, IProtocoleInclusionService, ProtocoleInclusionConverter> {

    public ProtocoleInclusionController(IProtocoleInclusionService service, ProtocoleInclusionConverter abstractConverter) {
        super(service, abstractConverter);
    }

    @GetMapping("id/{id}")
    public ResponseEntity<ProtocoleInclusionDto> findById(@PathVariable("id") Long id, String[] includes, String[] excludes) throws Exception {
        return super.findById(id, includes, excludes);
    }

    @GetMapping("")
    public ResponseEntity<List<ProtocoleInclusionDto>> findAll() throws Exception {
        return super.findAll();
    }

    @PostMapping("")
    public ResponseEntity<Long> save(@RequestBody ProtocoleInclusionDto dto) throws Exception {
        return super.save(dto);
    }

    @PutMapping("")
    public ResponseEntity<ProtocoleInclusionDto> update(@RequestBody ProtocoleInclusionDto dto) throws Exception {
        return super.update(dto);
    }

    @DeleteMapping("")
    public ResponseEntity<Void> delete(@RequestBody List<ProtocoleInclusionDto> listToDelete) throws Exception {
        return super.delete(listToDelete);
    }


    @PostMapping("find-by-criteria/")
    public ResponseEntity<List<ProtocoleInclusionDto>> findByCriteria(@RequestBody ProtocoleInclusionCriteria criteria) throws Exception {
        return super.findMultipleByCriteria(criteria);
    }


    @PostMapping("find-paginated-by-criteria/")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody ProtocoleInclusionCriteria criteria) throws Exception {
        return super.findPaginatedByCriteria(criteria);
    }

    @PostMapping("export/")
    public @ResponseBody ResponseEntity<InputStreamResource> export(@RequestBody ProtocoleInclusionCriteria criteria) throws Exception {
        return super.export(criteria);
    }

    @PostMapping("data-size-by-criteria")
    public @ResponseBody ResponseEntity<Integer> getDataSize(@RequestBody ProtocoleInclusionCriteria criteria) throws Exception {
        return super.getDataSize(criteria);
    }


    @GetMapping("history/id/{id}")
    public ResponseEntity<AuditEntityDto> findHistoryById(@PathVariable("id") Long id) throws Exception {
        return super.findHistoryById(id);
    }

    @PostMapping("history-paginated-by-criteria/")
    public @ResponseBody ResponseEntity<PaginatedList> findHistoryPaginatedByCriteria(@RequestBody HistProtocoleInclusionCriteria criteria) throws Exception {
        return super.findHistoryPaginatedByCriteria(criteria);
    }

    @PostMapping("export-history/")
    public @ResponseBody ResponseEntity<InputStreamResource> exportHistory(@RequestBody HistProtocoleInclusionCriteria criteria) throws Exception {
        return super.exportHistory(criteria);
    }

    @PostMapping("getHistProtocoleInclusionsDataSize")
    public @ResponseBody ResponseEntity<Integer> getHistoryDataSize(@RequestBody HistProtocoleInclusionCriteria criteria) throws Exception {
        return super.getHistoryDataSize(criteria);
    }


}