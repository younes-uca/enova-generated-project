package ma.enova.rth.controller;

import ma.enova.rth.common.bean.AuditEntityDto;
import ma.enova.rth.common.bean.BaseController;
import ma.enova.rth.common.bean.PaginatedList;
import ma.enova.rth.common.util.StringUtil;
import ma.enova.rth.dao.criteria.core.EtablissementCriteria;
import ma.enova.rth.dao.criteria.history.HistEtablissementCriteria;
import ma.enova.rth.dto.EtablissementDto;
import ma.enova.rth.service.facade.IEtablissementService;
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
 * Manager controller : Etablissement
 *
 * @author JAF
 * @version 1.2
 */

@RestController
public class EtablissementController extends BaseController {


    /**
     * Services metiers.
     */
    @Autowired
    private IEtablissementService etablissementService;

    @GetMapping("/api/etablissement/{id}")
    @PreAuthorize("hasRole('ROLE_READ_ETABLISSEMENT')")
    public ResponseEntity<EtablissementDto> getEtablissementById(@PathVariable("id") Long id, String[] includes, String[] excludes) throws Exception {

        EtablissementDto etablissement = etablissementService.getEtablissementById(id);

        if (StringUtil.isNoEmpty(includes) || StringUtil.isNoEmpty(excludes))
            etablissement = new EtablissementDto().mappedCustomDto(etablissement, includes, excludes);

        return new ResponseEntity<EtablissementDto>(etablissement, HttpStatus.OK);

    }

    @PostMapping("/api/etablissement")
    @PreAuthorize("hasRole('ROLE_CREATE_ETABLISSEMENT')")
    public ResponseEntity<Long> addEtablissement(@RequestBody EtablissementDto etablissement) throws Exception {

        etablissement = etablissementService.createEtablissement(etablissement);

        return new ResponseEntity<Long>(etablissement.getId(), HttpStatus.CREATED);

    }

    @PutMapping("/api/etablissement")
    @PreAuthorize("hasRole('ROLE_UPDATE_ETABLISSEMENT')")
    public ResponseEntity<EtablissementDto> updateEtablissement(@RequestBody EtablissementDto etablissement) throws Exception {

        if (etablissement.getId() == null)
            return new ResponseEntity<EtablissementDto>(HttpStatus.CONFLICT);

        etablissement = etablissementService.updateEtablissement(etablissement);

        return new ResponseEntity<EtablissementDto>(etablissement, HttpStatus.OK);

    }

    @DeleteMapping("/api/etablissement/delete")
    @PreAuthorize("hasRole('ROLE_DELETE_ETABLISSEMENT')")
    public ResponseEntity<Void> deleteEtablissement(@RequestBody List<EtablissementDto> etablissementList) throws Exception {

        if (etablissementList == null || etablissementList.isEmpty())
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);

        etablissementService.deleteEtablissement(etablissementList);
        return new ResponseEntity<Void>(HttpStatus.OK);

    }


    @PostMapping("/api/etablissement/listByCriteria")

    public @ResponseBody
    ResponseEntity<List<EtablissementDto>> getEtablissementsByCriteria(@RequestBody EtablissementCriteria etablissementCriteria) throws Exception {

        List<EtablissementDto> list = etablissementService.findEtablissementsByCriteria(etablissementCriteria);

        if (StringUtil.isNoEmpty(etablissementCriteria.getIncludes()) || StringUtil.isNoEmpty(etablissementCriteria.getExcludes()))
            ;
        list = CollectionUtils.emptyIfNull(list).stream().map(etablissement -> new EtablissementDto().mappedCustomDto(etablissement, etablissementCriteria.getIncludes(), etablissementCriteria.getExcludes())).collect(Collectors.toList());

        if (list == null || list.isEmpty())
            return new ResponseEntity<List<EtablissementDto>>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<List<EtablissementDto>>(list, HttpStatus.OK);

    }

    @PostMapping("/api/etablissement/paginatedListByCriteria")
    @PreAuthorize("hasRole('ROLE_READ_ETABLISSEMENT')")
    public @ResponseBody
    ResponseEntity<PaginatedList> paginatedListEtablissements(@RequestBody EtablissementCriteria etablissementCriteria) throws Exception {

        List<EtablissementDto> list = etablissementService.paginatedListEtablissements(etablissementCriteria, etablissementCriteria.getPage(), etablissementCriteria.getMaxResults(), etablissementCriteria.getSortOrder(), etablissementCriteria.getSortField());

        if (StringUtil.isNoEmpty(etablissementCriteria.getIncludes()) || StringUtil.isNoEmpty(etablissementCriteria.getExcludes()))
            ;
        list = CollectionUtils.emptyIfNull(list).stream().map(etablissement -> new EtablissementDto().mappedCustomDto(etablissement, etablissementCriteria.getIncludes(), etablissementCriteria.getExcludes())).collect(Collectors.toList());

        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(list);
        if (list != null && !list.isEmpty()) {
            int dateSize = etablissementService.getEtablissementDataSize(etablissementCriteria);
            paginatedList.setDataSize(dateSize);
        }

        return new ResponseEntity<PaginatedList>(paginatedList, HttpStatus.OK);

    }

    @PostMapping("/api/etablissement/exportEtablissements")

    public @ResponseBody ResponseEntity<InputStreamResource> exportEtablissements(@RequestBody EtablissementCriteria etablissementCriteria) throws Exception {

        if (etablissementCriteria.getExportModel() == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        etablissementCriteria.setMaxResults(null);
        List<EtablissementDto> list = etablissementService.findEtablissementsByCriteria(etablissementCriteria);
        etablissementCriteria.getExportModel().setList(list);
        return getExportedFileResource(etablissementCriteria.getExportModel());

    }

    @PostMapping("/api/etablissement/getEtablissementsDataSize")

    public @ResponseBody ResponseEntity<Integer> getEtablissementDataSize(@RequestBody EtablissementCriteria etablissementCriteria) throws Exception {

        int count = etablissementService.getEtablissementDataSize(etablissementCriteria);

        return new ResponseEntity<Integer>(count, HttpStatus.OK);

    }


    @GetMapping("/api/etablissement/histEtablissement/{id}")
    @PreAuthorize("hasRole('ROLE_HIST_ETABLISSEMENT')")
    public ResponseEntity<AuditEntityDto> getHistEtablissementById(@PathVariable("id") Long id) throws Exception {

        AuditEntityDto histEtablissement = etablissementService.getHistEtablissementById(id);

        return new ResponseEntity<AuditEntityDto>(histEtablissement, HttpStatus.OK);

    }

    @PostMapping("/api/etablissement/paginatedListHistByCriteria")
    @PreAuthorize("hasRole('ROLE_HIST_ETABLISSEMENT')")
    public @ResponseBody ResponseEntity<PaginatedList> paginatedListHistEtablissements(@RequestBody HistEtablissementCriteria histEtablissementCriteria) throws Exception {

        List<AuditEntityDto> list = etablissementService.paginatedListHistEtablissements(histEtablissementCriteria, histEtablissementCriteria.getPage(), histEtablissementCriteria.getMaxResults(), histEtablissementCriteria.getSortOrder(), histEtablissementCriteria.getSortField());

        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(list);
        if (list != null && !list.isEmpty()) {
            int dateSize = etablissementService.getHistEtablissementDataSize(histEtablissementCriteria);
            paginatedList.setDataSize(dateSize);
        }

        return new ResponseEntity<PaginatedList>(paginatedList, HttpStatus.OK);

    }

    @PostMapping("/api/etablissement/exportEtablissementsHist")

    public @ResponseBody ResponseEntity<InputStreamResource> exportEtablissementsHist(@RequestBody HistEtablissementCriteria histEtablissementCriteria) throws Exception {

        if (histEtablissementCriteria.getExportModel() == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        histEtablissementCriteria.setMaxResults(null);
        List<AuditEntityDto> list = etablissementService.findEtablissementsHistByCriteria(histEtablissementCriteria);
        histEtablissementCriteria.getExportModel().setList(list);
        return getExportedFileResource(histEtablissementCriteria.getExportModel());

    }

    @PostMapping("/api/etablissement/getHistEtablissementsDataSize")

    public @ResponseBody ResponseEntity<Integer> getHistEtablissementDataSize(@RequestBody HistEtablissementCriteria histEtablissementCriteria) throws Exception {

        int count = etablissementService.getHistEtablissementDataSize(histEtablissementCriteria);

        return new ResponseEntity<Integer>(count, HttpStatus.OK);

    }


}