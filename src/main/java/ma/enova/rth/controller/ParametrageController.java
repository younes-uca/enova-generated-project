package ma.enova.rth.controller;

import ma.enova.rth.common.bean.AuditEntityDto;
import ma.enova.rth.common.bean.BaseController;
import ma.enova.rth.common.bean.BusinessDto;
import ma.enova.rth.common.bean.PaginatedList;
import ma.enova.rth.common.enumeration.TYPE_VALEUR;
import ma.enova.rth.common.util.StringUtil;
import ma.enova.rth.dao.criteria.core.ParametrageCriteria;
import ma.enova.rth.dao.criteria.history.HistParametrageCriteria;
import ma.enova.rth.dto.ParametrageDto;
import ma.enova.rth.service.facade.IParametrageService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Manager controller : Parametrage
 *
 * @author JAF
 * @version 1.2
 */

@RestController
public class ParametrageController extends BaseController {


    /**
     * Services metiers.
     */
    @Autowired
    private IParametrageService parametrageService;


    @GetMapping("/api/parametrage/{id}")
    @PreAuthorize("hasRole('ROLE_READ_PARAMETRAGE')")
    public ResponseEntity<ParametrageDto> getParametrageById(@PathVariable("id") Long id, String[] includes, String[] excludes) throws Exception {

        ParametrageDto parametrage = parametrageService.getParametrageById(id);

        if (StringUtil.isNoEmpty(includes) || StringUtil.isNoEmpty(excludes))
            parametrage = new ParametrageDto().mappedCustomDto(parametrage, includes, excludes);

        return new ResponseEntity<ParametrageDto>(parametrage, HttpStatus.OK);

    }

    @PostMapping("/api/parametrage")
    @PreAuthorize("hasRole('ROLE_CREATE_PARAMETRAGE')")
    public ResponseEntity<Long> addParametrage(@RequestBody ParametrageDto parametrage) throws Exception {

        parametrage = parametrageService.createParametrage(parametrage);

        return new ResponseEntity<Long>(parametrage.getId(), HttpStatus.CREATED);

    }

    @PutMapping("/api/parametrage")
    @PreAuthorize("hasRole('ROLE_UPDATE_PARAMETRAGE')")
    public ResponseEntity<ParametrageDto> updateParametrage(@RequestBody ParametrageDto parametrage) throws Exception {

        if (parametrage.getId() == null)
            return new ResponseEntity<ParametrageDto>(HttpStatus.CONFLICT);

        parametrage = parametrageService.updateParametrage(parametrage);

        return new ResponseEntity<ParametrageDto>(parametrage, HttpStatus.OK);

    }

    @DeleteMapping("/api/parametrage/delete")
    @PreAuthorize("hasRole('ROLE_DELETE_PARAMETRAGE')")
    public ResponseEntity<Void> deleteParametrage(@RequestBody List<ParametrageDto> parametrageList) throws Exception {

        if (parametrageList == null || parametrageList.isEmpty())
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);

        parametrageService.deleteParametrage(parametrageList);
        return new ResponseEntity<Void>(HttpStatus.OK);

    }


    @PostMapping("/api/parametrage/listByCriteria")

    public @ResponseBody
    ResponseEntity<List<ParametrageDto>> getParametragesByCriteria(@RequestBody ParametrageCriteria parametrageCriteria) throws Exception {

        List<ParametrageDto> list = parametrageService.findParametragesByCriteria(parametrageCriteria);

        if (StringUtil.isNoEmpty(parametrageCriteria.getIncludes()) || StringUtil.isNoEmpty(parametrageCriteria.getExcludes()))
            ;
        list = CollectionUtils.emptyIfNull(list).stream().map(parametrage -> new ParametrageDto().mappedCustomDto(parametrage, parametrageCriteria.getIncludes(), parametrageCriteria.getExcludes())).collect(Collectors.toList());

        if (list == null || list.isEmpty())
            return new ResponseEntity<List<ParametrageDto>>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<List<ParametrageDto>>(list, HttpStatus.OK);

    }

    @PostMapping("/api/parametrage/paginatedListByCriteria")
    @PreAuthorize("hasRole('ROLE_READ_PARAMETRAGE')")
    public @ResponseBody
    ResponseEntity<PaginatedList> paginatedListParametrages(@RequestBody ParametrageCriteria parametrageCriteria) throws Exception {

        List<ParametrageDto> list = parametrageService.paginatedListParametrages(parametrageCriteria, parametrageCriteria.getPage(), parametrageCriteria.getMaxResults(), parametrageCriteria.getSortOrder(), parametrageCriteria.getSortField());

        if (StringUtil.isNoEmpty(parametrageCriteria.getIncludes()) || StringUtil.isNoEmpty(parametrageCriteria.getExcludes()))
            ;
        list = CollectionUtils.emptyIfNull(list).stream().map(parametrage -> new ParametrageDto().mappedCustomDto(parametrage, parametrageCriteria.getIncludes(), parametrageCriteria.getExcludes())).collect(Collectors.toList());

        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(list);
        if (list != null && !list.isEmpty()) {
            int dateSize = parametrageService.getParametrageDataSize(parametrageCriteria);
            paginatedList.setDataSize(dateSize);
        }

        return new ResponseEntity<PaginatedList>(paginatedList, HttpStatus.OK);

    }

    @PostMapping("/api/parametrage/exportParametrages")

    public @ResponseBody ResponseEntity<InputStreamResource> exportParametrages(@RequestBody ParametrageCriteria parametrageCriteria) throws Exception {

        if (parametrageCriteria.getExportModel() == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        parametrageCriteria.setMaxResults(null);
        List<ParametrageDto> list = parametrageService.findParametragesByCriteria(parametrageCriteria);
        parametrageCriteria.getExportModel().setList(list);
        return getExportedFileResource(parametrageCriteria.getExportModel());

    }

    @PostMapping("/api/parametrage/getParametragesDataSize")

    public @ResponseBody ResponseEntity<Integer> getParametrageDataSize(@RequestBody ParametrageCriteria parametrageCriteria) throws Exception {

        int count = parametrageService.getParametrageDataSize(parametrageCriteria);

        return new ResponseEntity<Integer>(count, HttpStatus.OK);

    }

    @GetMapping("/api/parametrage/getTypeValeurList")
    @PreAuthorize("hasAnyRole('ROLE_READ_PARAMETRAGE','ROLE_CREATE_PARAMETRAGE','ROLE_UPDATE_PARAMETRAGE')")
    public ResponseEntity<List<TYPE_VALEUR>> getTypeValeurList() throws Exception {

        List<TYPE_VALEUR> typeValeurList = Arrays.asList(TYPE_VALEUR.values());

        return new ResponseEntity<List<TYPE_VALEUR>>(typeValeurList, HttpStatus.OK);
    }


    @GetMapping("/api/parametrage/getCategorieRoleList")
    @PreAuthorize("hasAnyRole('ROLE_READ_PARAMETRAGE','ROLE_CREATE_PARAMETRAGE','ROLE_UPDATE_PARAMETRAGE')")
    public @ResponseBody
    ResponseEntity<List<BusinessDto>> getCategorieRoleList() throws Exception {

        return null;


    }


    @GetMapping("/api/parametrage/histParametrage/{id}")
    @PreAuthorize("hasRole('ROLE_HIST_PARAMETRAGE')")
    public ResponseEntity<AuditEntityDto> getHistParametrageById(@PathVariable("id") Long id) throws Exception {

        AuditEntityDto histParametrage = parametrageService.getHistParametrageById(id);

        return new ResponseEntity<AuditEntityDto>(histParametrage, HttpStatus.OK);

    }

    @PostMapping("/api/parametrage/paginatedListHistByCriteria")
    @PreAuthorize("hasRole('ROLE_HIST_PARAMETRAGE')")
    public @ResponseBody ResponseEntity<PaginatedList> paginatedListHistParametrages(@RequestBody HistParametrageCriteria histParametrageCriteria) throws Exception {

        List<AuditEntityDto> list = parametrageService.paginatedListHistParametrages(histParametrageCriteria, histParametrageCriteria.getPage(), histParametrageCriteria.getMaxResults(), histParametrageCriteria.getSortOrder(), histParametrageCriteria.getSortField());

        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(list);
        if (list != null && !list.isEmpty()) {
            int dateSize = parametrageService.getHistParametrageDataSize(histParametrageCriteria);
            paginatedList.setDataSize(dateSize);
        }

        return new ResponseEntity<PaginatedList>(paginatedList, HttpStatus.OK);

    }

    @PostMapping("/api/parametrage/exportParametragesHist")

    public @ResponseBody ResponseEntity<InputStreamResource> exportParametragesHist(@RequestBody HistParametrageCriteria histParametrageCriteria) throws Exception {

        if (histParametrageCriteria.getExportModel() == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        histParametrageCriteria.setMaxResults(null);
        List<AuditEntityDto> list = parametrageService.findParametragesHistByCriteria(histParametrageCriteria);
        histParametrageCriteria.getExportModel().setList(list);
        return getExportedFileResource(histParametrageCriteria.getExportModel());

    }

    @PostMapping("/api/parametrage/getHistParametragesDataSize")

    public @ResponseBody ResponseEntity<Integer> getHistParametrageDataSize(@RequestBody HistParametrageCriteria histParametrageCriteria) throws Exception {

        int count = parametrageService.getHistParametrageDataSize(histParametrageCriteria);

        return new ResponseEntity<Integer>(count, HttpStatus.OK);

    }


}