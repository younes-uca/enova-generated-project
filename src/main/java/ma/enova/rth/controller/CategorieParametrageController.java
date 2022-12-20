package ma.enova.rth.controller;

import ma.enova.rth.common.bean.BaseController;
import ma.enova.rth.common.bean.PaginatedList;
import ma.enova.rth.dao.criteria.core.CategorieParametrageCriteria;
import ma.enova.rth.dto.CategorieParametrageDto;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Manager controller : CategorieParametrage
 *
 * @author JAF
 * @version 1.2
 */

@RestController
public class CategorieParametrageController extends BaseController {


    /**
     * Services metiers.
     */


    @GetMapping("/api/categorieParametrage/{id}")
    @PreAuthorize("hasRole('ROLE_READ_CATEGORIEPARAMETRAGE')")
    public ResponseEntity<CategorieParametrageDto> getCategorieParametrageById(@PathVariable("id") Long id, String[] includes, String[] excludes) throws Exception {
        return null;
    }

    @PostMapping("/api/categorieParametrage")
    @PreAuthorize("hasRole('ROLE_CREATE_CATEGORIEPARAMETRAGE')")
    public ResponseEntity<Long> addCategorieParametrage(@RequestBody CategorieParametrageDto categorieParametrage) throws Exception {
        return null;

    }

    @PutMapping("/api/categorieParametrage")
    @PreAuthorize("hasRole('ROLE_UPDATE_CATEGORIEPARAMETRAGE')")
    public ResponseEntity<CategorieParametrageDto> updateCategorieParametrage(@RequestBody CategorieParametrageDto categorieParametrage) throws Exception {
        return null;


    }

    @DeleteMapping("/api/categorieParametrage/delete")
    @PreAuthorize("hasRole('ROLE_DELETE_CATEGORIEPARAMETRAGE')")
    public ResponseEntity<Void> deleteCategorieParametrage(@RequestBody List<CategorieParametrageDto> categorieParametrageList) throws Exception {
        return null;


    }


    @PostMapping("/api/categorieParametrage/listByCriteria")

    public @ResponseBody
    ResponseEntity<List<CategorieParametrageDto>> getCategorieParametragesByCriteria(@RequestBody CategorieParametrageCriteria categorieParametrageCriteria) throws Exception {
        return null;


    }

    @PostMapping("/api/categorieParametrage/paginatedListByCriteria")
    @PreAuthorize("hasRole('ROLE_READ_CATEGORIEPARAMETRAGE')")
    public @ResponseBody
    ResponseEntity<PaginatedList> paginatedListCategorieParametrages(@RequestBody CategorieParametrageCriteria categorieParametrageCriteria) throws Exception {

        return null;


    }

    @PostMapping("/api/categorieParametrage/exportCategorieParametrages")

    public @ResponseBody ResponseEntity<InputStreamResource> exportCategorieParametrages(@RequestBody CategorieParametrageCriteria categorieParametrageCriteria) throws Exception {

        return null;


    }

    @PostMapping("/api/categorieParametrage/getCategorieParametragesDataSize")

    public @ResponseBody ResponseEntity<Integer> getCategorieParametrageDataSize(@RequestBody CategorieParametrageCriteria categorieParametrageCriteria) throws Exception {

        return null;


    }


}