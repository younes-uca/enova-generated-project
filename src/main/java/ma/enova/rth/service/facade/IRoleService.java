package ma.enova.rth.service.facade;

import ma.enova.rth.dao.criteria.core.RoleCriteria;
import ma.enova.rth.dto.RoleDto;

import java.util.List;

/**
 * Interface service role
 *
 * @author JAF
 * @version 1.2
 */
public interface IRoleService {


    /**
     * createRole.
     *
     * @param role
     * @return Role
     * @throws Exception
     */
    public RoleDto createRole(RoleDto role) throws Exception;

    /**
     * updateRole.
     *
     * @param role
     * @return Role
     * @throws Exception
     */
    public RoleDto updateRole(RoleDto role) throws Exception;


    /**
     * getRoleById.
     *
     * @param roleId
     * @return Role
     * @throws Exception
     */
    public RoleDto getRoleById(Long roleId) throws Exception;

    /**
     * findRolesByCriteria.
     *
     * @param roleCriteria
     * @return List<Role>
     * @throws Exception
     */
    public List<RoleDto> findRolesByCriteria(RoleCriteria roleCriteria) throws Exception;

    /**
     * findRoleByCriteria.
     *
     * @param roleCriteria
     * @return Role
     * @throws Exception
     */

    public RoleDto findRoleByCriteria(RoleCriteria roleCriteria) throws Exception;

    /**
     * paginatedListRoles.
     *
     * @param roleCriteria
     * @param page
     * @param pageSize
     * @param order
     * @param sortField
     * @return List<Role>
     * @throws Exception
     */
    public List<RoleDto> paginatedListRoles(RoleCriteria roleCriteria, int page, int pageSize, String order, String sortField) throws Exception;

    /**
     * getRoleDataSize.
     *
     * @param roleCriteria
     * @return int
     * @throws Exception
     */
    public int getRoleDataSize(RoleCriteria roleCriteria) throws Exception;

    /**
     * deleteRole.
     *
     * @param roleList
     * @throws Exception
     */
    public void deleteRole(List<RoleDto> roleList) throws Exception;


}
