package ma.enova.rth.controller;

import ma.enova.rth.common.bean.BaseController;
import ma.enova.rth.common.bean.BusinessDto;
import ma.enova.rth.common.bean.PaginatedList;
import ma.enova.rth.common.util.StringUtil;
import ma.enova.rth.dao.criteria.core.RoleCriteria;
import ma.enova.rth.dto.RoleDto;
import ma.enova.rth.service.facade.ICategorieRoleService;
import ma.enova.rth.service.facade.IRoleService;
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
 * Manager controller : Role
 *
 * @author JAF
 * @version 1.2
 */

@RestController
public class RoleController extends BaseController {


    /**
     * Services metiers.
     */
    @Autowired
    private IRoleService roleService;
    @Autowired
    private ICategorieRoleService categorieRoleService;

    @GetMapping("/api/role/{id}")

    public ResponseEntity<RoleDto> getRoleById(@PathVariable("id") Long id, String[] includes, String[] excludes) throws Exception {

        RoleDto role = roleService.getRoleById(id);

        if (StringUtil.isNoEmpty(includes) || StringUtil.isNoEmpty(excludes))
            role = new RoleDto().mappedCustomDto(role, includes, excludes);

        return new ResponseEntity<RoleDto>(role, HttpStatus.OK);

    }

    @PostMapping("/api/role")

    public ResponseEntity<Long> addRole(@RequestBody RoleDto role) throws Exception {

        role = roleService.createRole(role);

        return new ResponseEntity<Long>(role.getId(), HttpStatus.CREATED);

    }

    @PutMapping("/api/role")

    public ResponseEntity<RoleDto> updateRole(@RequestBody RoleDto role) throws Exception {

        if (role.getId() == null)
            return new ResponseEntity<RoleDto>(HttpStatus.CONFLICT);

        role = roleService.updateRole(role);

        return new ResponseEntity<RoleDto>(role, HttpStatus.OK);

    }


    @PostMapping("/api/role/listByCriteria")

    public @ResponseBody
    ResponseEntity<List<RoleDto>> getRolesByCriteria(@RequestBody RoleCriteria roleCriteria) throws Exception {

        List<RoleDto> list = roleService.findRolesByCriteria(roleCriteria);

        if (StringUtil.isNoEmpty(roleCriteria.getIncludes()) || StringUtil.isNoEmpty(roleCriteria.getExcludes())) ;
        list = CollectionUtils.emptyIfNull(list).stream().map(role -> new RoleDto().mappedCustomDto(role, roleCriteria.getIncludes(), roleCriteria.getExcludes())).collect(Collectors.toList());

        if (list == null || list.isEmpty())
            return new ResponseEntity<List<RoleDto>>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<List<RoleDto>>(list, HttpStatus.OK);

    }

    @PostMapping("/api/role/paginatedListByCriteria")

    public @ResponseBody
    ResponseEntity<PaginatedList> paginatedListRoles(@RequestBody RoleCriteria roleCriteria) throws Exception {

        List<RoleDto> list = roleService.paginatedListRoles(roleCriteria, roleCriteria.getPage(), roleCriteria.getMaxResults(), roleCriteria.getSortOrder(), roleCriteria.getSortField());

        if (StringUtil.isNoEmpty(roleCriteria.getIncludes()) || StringUtil.isNoEmpty(roleCriteria.getExcludes())) ;
        list = CollectionUtils.emptyIfNull(list).stream().map(role -> new RoleDto().mappedCustomDto(role, roleCriteria.getIncludes(), roleCriteria.getExcludes())).collect(Collectors.toList());

        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(list);
        if (list != null && !list.isEmpty()) {
            int dateSize = roleService.getRoleDataSize(roleCriteria);
            paginatedList.setDataSize(dateSize);
        }

        return new ResponseEntity<PaginatedList>(paginatedList, HttpStatus.OK);

    }

    @PostMapping("/api/role/exportRoles")

    public @ResponseBody ResponseEntity<InputStreamResource> exportRoles(@RequestBody RoleCriteria roleCriteria) throws Exception {

        if (roleCriteria.getExportModel() == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        roleCriteria.setMaxResults(null);
        List<RoleDto> list = roleService.findRolesByCriteria(roleCriteria);
        roleCriteria.getExportModel().setList(list);
        return getExportedFileResource(roleCriteria.getExportModel());

    }

    @PostMapping("/api/role/getRolesDataSize")

    public @ResponseBody ResponseEntity<Integer> getRoleDataSize(@RequestBody RoleCriteria roleCriteria) throws Exception {

        int count = roleService.getRoleDataSize(roleCriteria);

        return new ResponseEntity<Integer>(count, HttpStatus.OK);

    }


    @GetMapping("/api/role/getCategorieRoleList")
    @PreAuthorize("hasAnyRole('ROLE_READ_ROLE','ROLE_CREATE_ROLE','ROLE_UPDATE_ROLE')")
    public @ResponseBody
    ResponseEntity<List<BusinessDto>> getCategorieRoleList() throws Exception {

        return null;


    }


}