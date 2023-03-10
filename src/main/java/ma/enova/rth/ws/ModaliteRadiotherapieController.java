package ma.enova.rth.ws;

import ma.enova.rth.zynerator.dto.AuditEntityDto;
import ma.enova.rth.zynerator.util.PaginatedList;
import ma.enova.rth.zynerator.controller.AbstractController;
import ma.enova.rth.converter.ModaliteRadiotherapieConverter;
import ma.enova.rth.dao.criteria.core.ModaliteRadiotherapieCriteria;
import ma.enova.rth.dao.criteria.history.HistModaliteRadiotherapieCriteria;
import ma.enova.rth.bean.core.ModaliteRadiotherapie;
import ma.enova.rth.bean.historique.HistModaliteRadiotherapie;
import ma.enova.rth.dto.ModaliteRadiotherapieDto;
import ma.enova.rth.service.facade.IModaliteRadiotherapieService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Manager controller : ModaliteRadiotherapie
 *
 * @author JAF
 * @version 1.2
 */

@RestController
@RequestMapping("/api/modalite-radiotherapie/")
public class ModaliteRadiotherapieController extends AbstractController<ModaliteRadiotherapie, ModaliteRadiotherapieDto, HistModaliteRadiotherapie, ModaliteRadiotherapieCriteria, HistModaliteRadiotherapieCriteria, IModaliteRadiotherapieService, ModaliteRadiotherapieConverter> {

    public ModaliteRadiotherapieController(IModaliteRadiotherapieService service, ModaliteRadiotherapieConverter abstractConverter) {
        super(service, abstractConverter);
    }

    @GetMapping("id/{id}")
    public ResponseEntity<ModaliteRadiotherapieDto> findById(@PathVariable("id") Long id, String[] includes, String[] excludes) throws Exception {
        return super.findById(id, includes, excludes);
    }

    @PostMapping("")
    public ResponseEntity<Long> save(@RequestBody ModaliteRadiotherapieDto dto) throws Exception {
        return super.save(dto);
    }


    @GetMapping("")
    public ResponseEntity<List<ModaliteRadiotherapieDto>> findAll() throws Exception {
        return super.findAll();
    }

    @PutMapping("")
    public ResponseEntity<ModaliteRadiotherapieDto> update(@RequestBody ModaliteRadiotherapieDto dto) throws Exception {
        return super.update(dto);
    }

    @DeleteMapping("")
    public ResponseEntity<Void> delete(@RequestBody List<ModaliteRadiotherapieDto> listToDelete) throws Exception {
        return super.delete(listToDelete);
    }


    @PostMapping("find-by-criteria/")
    public ResponseEntity<List<ModaliteRadiotherapieDto>> findByCriteria(@RequestBody ModaliteRadiotherapieCriteria criteria) throws Exception {
        return super.findMultipleByCriteria(criteria);
    }

    @PostMapping("find-paginated-by-criteria/")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody ModaliteRadiotherapieCriteria criteria) throws Exception {
        return super.findPaginatedByCriteria(criteria);
    }

    @PostMapping("export/")
    public @ResponseBody ResponseEntity<InputStreamResource> export(@RequestBody ModaliteRadiotherapieCriteria criteria) throws Exception {
        return super.export(criteria);
    }

    @PostMapping("data-size-by-criteria")
    public @ResponseBody ResponseEntity<Integer> getDataSize(@RequestBody ModaliteRadiotherapieCriteria criteria) throws Exception {
        return super.getDataSize(criteria);
    }


    @GetMapping("history/id/{id}")
    public ResponseEntity<AuditEntityDto> findHistoryById(@PathVariable("id") Long id) throws Exception {
        return super.findHistoryById(id);
    }

    @PostMapping("history-paginated-by-criteria/")
    public @ResponseBody ResponseEntity<PaginatedList> findHistoryPaginatedByCriteria(@RequestBody HistModaliteRadiotherapieCriteria criteria) throws Exception {
        return super.findHistoryPaginatedByCriteria(criteria);
    }

    @PostMapping("export-history/")
    public @ResponseBody ResponseEntity<InputStreamResource> exportHistory(@RequestBody HistModaliteRadiotherapieCriteria criteria) throws Exception {
        return super.exportHistory(criteria);
    }

    @PostMapping("getHistModaliteRadiotherapiesDataSize")
    public @ResponseBody ResponseEntity<Integer> getHistoryDataSize(@RequestBody HistModaliteRadiotherapieCriteria criteria) throws Exception {
        return super.getHistoryDataSize(criteria);
    }


}