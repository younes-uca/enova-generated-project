package ma.enova.rth.controller;

import ma.enova.rth.common.bean.AuditEntityDto;
import ma.enova.rth.common.bean.BaseController;
import ma.enova.rth.common.bean.PaginatedList;
import ma.enova.rth.common.util.StringUtil;
import ma.enova.rth.converter.FrequenceRadiotherapieConverter;
import ma.enova.rth.converter.FrequenceRadiotherapieConverter;
import ma.enova.rth.dao.criteria.core.FrequenceRadiotherapieCriteria;
import ma.enova.rth.dao.criteria.core.FrequenceRadiotherapieCriteria;
import ma.enova.rth.dao.criteria.history.HistFrequenceRadiotherapieCriteria;
import ma.enova.rth.dao.criteria.history.HistFrequenceRadiotherapieCriteria;
import ma.enova.rth.domain.core.FrequenceRadiotherapie;
import ma.enova.rth.domain.historique.HistFrequenceRadiotherapie;
import ma.enova.rth.dto.FrequenceRadiotherapieDto;
import ma.enova.rth.dto.FrequenceRadiotherapieDto;
import ma.enova.rth.service.facade.IFrequenceRadiotherapieService;
import ma.enova.rth.service.facade.IFrequenceRadiotherapieService;
import ma.enova.rth.service.facade.IFrequenceRadiotherapieService;
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
 * Manager controller : FrequenceRadiotherapie
 *
 * @author JAF
 * @version 1.2
 */

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
    public ResponseEntity<Long> addFrequenceRadiotherapie(@RequestBody FrequenceRadiotherapieDto dto) throws Exception {
        return super.save(dto);
    }

    @PutMapping("")
    public ResponseEntity<FrequenceRadiotherapieDto> updateFrequenceRadiotherapie(@RequestBody FrequenceRadiotherapieDto dto) throws Exception {
        return super.update(dto);
    }

    @DeleteMapping("")
    public ResponseEntity<Void> deleteFrequenceRadiotherapie(@RequestBody List<FrequenceRadiotherapieDto> listToDelete) throws Exception {
        return super.delete(listToDelete);
    }


    @PostMapping("find-by-criteria/")
    public ResponseEntity<List<FrequenceRadiotherapieDto>> findByCriteria(@RequestBody FrequenceRadiotherapieCriteria criteria) throws Exception {
        return super.findMultipleByCriteria(criteria);
    }

    @PostMapping("find-paginated-by-criteria/")
    public ResponseEntity<PaginatedList> paginatedListFrequenceRadiotherapies(@RequestBody FrequenceRadiotherapieCriteria criteria) throws Exception {
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

    @PostMapping("/api/frequenceRadiotherapie/paginatedListHistByCriteria")
    @PreAuthorize("hasRole('ROLE_HIST_FREQUENCERADIOTHERAPIE')")
    public @ResponseBody ResponseEntity<PaginatedList> paginatedListHistFrequenceRadiotherapies(@RequestBody HistFrequenceRadiotherapieCriteria histFrequenceRadiotherapieCriteria) throws Exception {

        List<AuditEntityDto> list = frequenceRadiotherapieService.paginatedListHistFrequenceRadiotherapies(histFrequenceRadiotherapieCriteria, histFrequenceRadiotherapieCriteria.getPage(), histFrequenceRadiotherapieCriteria.getMaxResults(), histFrequenceRadiotherapieCriteria.getSortOrder(), histFrequenceRadiotherapieCriteria.getSortField());

        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(list);
        if (list != null && !list.isEmpty()) {
            int dateSize = frequenceRadiotherapieService.getHistFrequenceRadiotherapieDataSize(histFrequenceRadiotherapieCriteria);
            paginatedList.setDataSize(dateSize);
        }

        return new ResponseEntity<PaginatedList>(paginatedList, HttpStatus.OK);

    }

    @PostMapping("/api/frequenceRadiotherapie/exportFrequenceRadiotherapiesHist")

    public @ResponseBody ResponseEntity<InputStreamResource> exportFrequenceRadiotherapiesHist(@RequestBody HistFrequenceRadiotherapieCriteria histFrequenceRadiotherapieCriteria) throws Exception {

        if (histFrequenceRadiotherapieCriteria.getExportModel() == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        histFrequenceRadiotherapieCriteria.setMaxResults(null);
        List<AuditEntityDto> list = frequenceRadiotherapieService.findFrequenceRadiotherapiesHistByCriteria(histFrequenceRadiotherapieCriteria);
        histFrequenceRadiotherapieCriteria.getExportModel().setList(list);
        return getExportedFileResource(histFrequenceRadiotherapieCriteria.getExportModel());

    }

    @PostMapping("/api/frequenceRadiotherapie/getHistFrequenceRadiotherapiesDataSize")

    public @ResponseBody ResponseEntity<Integer> getHistFrequenceRadiotherapieDataSize(@RequestBody HistFrequenceRadiotherapieCriteria histFrequenceRadiotherapieCriteria) throws Exception {

        int count = frequenceRadiotherapieService.getHistFrequenceRadiotherapieDataSize(histFrequenceRadiotherapieCriteria);

        return new ResponseEntity<Integer>(count, HttpStatus.OK);

    }


}