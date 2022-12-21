package ma.enova.rth.ws;

import ma.enova.rth.common.bean.AuditEntityDto;
import ma.enova.rth.common.bean.BaseController;
import ma.enova.rth.common.bean.PaginatedList;
import ma.enova.rth.dao.criteria.core.CategorieRoleCriteria;
import ma.enova.rth.dao.criteria.history.HistCategorieRoleCriteria;
import ma.enova.rth.dto.CategorieRoleDto;
import ma.enova.rth.service.facade.ICategorieRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Manager controller : CategorieRole
 *
 * @author JAF
 * @version 1.2
 */

@RestController
public class CategorieRoleController extends BaseController {


    /**
     * Services metiers.
     */
    @Autowired
    private ICategorieRoleService categorieRoleService;

    @GetMapping("/api/categorieRole/{id}")
    @PreAuthorize("hasRole('ROLE_READ_CATEGORIEROLE')")
    public ResponseEntity<CategorieRoleDto> getCategorieRoleById(@PathVariable("id") Long id, String[] includes, String[] excludes) throws Exception {

        return null;


    }

    @PostMapping("/api/categorieRole")
    @PreAuthorize("hasRole('ROLE_CREATE_CATEGORIEROLE')")
    public ResponseEntity<Long> addCategorieRole(@RequestBody CategorieRoleDto categorieRole) throws Exception {

        return null;

    }

    @PutMapping("/api/categorieRole")
    @PreAuthorize("hasRole('ROLE_UPDATE_CATEGORIEROLE')")
    public ResponseEntity<CategorieRoleDto> updateCategorieRole(@RequestBody CategorieRoleDto categorieRole) throws Exception {
        return null;


    }

    @DeleteMapping("/api/categorieRole/delete")
    @PreAuthorize("hasRole('ROLE_DELETE_CATEGORIEROLE')")
    public ResponseEntity<Void> deleteCategorieRole(@RequestBody List<CategorieRoleDto> categorieRoleList) throws Exception {

        return null;


    }


    @PostMapping("/api/categorieRole/listByCriteria")

    public @ResponseBody
    ResponseEntity<List<CategorieRoleDto>> getCategorieRolesByCriteria(@RequestBody CategorieRoleCriteria categorieRoleCriteria) throws Exception {

        return null;


    }

    @PostMapping("/api/categorieRole/paginatedListByCriteria")
    @PreAuthorize("hasRole('ROLE_READ_CATEGORIEROLE')")
    public @ResponseBody
    ResponseEntity<PaginatedList> paginatedListCategorieRoles(@RequestBody CategorieRoleCriteria categorieRoleCriteria) throws Exception {

        return null;


    }

    @PostMapping("/api/categorieRole/exportCategorieRoles")

    public @ResponseBody ResponseEntity<InputStreamResource> exportCategorieRoles(@RequestBody CategorieRoleCriteria categorieRoleCriteria) throws Exception {

        return null;


    }

    @PostMapping("/api/categorieRole/getCategorieRolesDataSize")

    public @ResponseBody ResponseEntity<Integer> getCategorieRoleDataSize(@RequestBody CategorieRoleCriteria categorieRoleCriteria) throws Exception {
        return null;


    }


    @GetMapping("/api/categorieRole/histCategorieRole/{id}")
    @PreAuthorize("hasRole('ROLE_HIST_CATEGORIEROLE')")
    public ResponseEntity<AuditEntityDto> getHistCategorieRoleById(@PathVariable("id") Long id) throws Exception {
        return null;


    }

    @PostMapping("/api/categorieRole/paginatedListHistByCriteria")
    @PreAuthorize("hasRole('ROLE_HIST_CATEGORIEROLE')")
    public @ResponseBody ResponseEntity<PaginatedList> paginatedListHistCategorieRoles(@RequestBody HistCategorieRoleCriteria histCategorieRoleCriteria) throws Exception {

        return null;


    }

    @PostMapping("/api/categorieRole/exportCategorieRolesHist")

    public @ResponseBody ResponseEntity<InputStreamResource> exportCategorieRolesHist(@RequestBody HistCategorieRoleCriteria histCategorieRoleCriteria) throws Exception {

        return null;


    }

    @PostMapping("/api/categorieRole/getHistCategorieRolesDataSize")

    public @ResponseBody ResponseEntity<Integer> getHistCategorieRoleDataSize(@RequestBody HistCategorieRoleCriteria histCategorieRoleCriteria) throws Exception {
        return null;


    }


}