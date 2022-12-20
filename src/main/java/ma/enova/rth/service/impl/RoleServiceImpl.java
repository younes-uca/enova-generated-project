package ma.enova.rth.service.impl;

import ma.enova.rth.common.exception.EntityNotFoundException;
import ma.enova.rth.dao.criteria.core.RoleCriteria;
import ma.enova.rth.dao.facade.core.IRoleRepository;
import ma.enova.rth.dao.specifications.core.RoleSpecification;
import ma.enova.rth.domain.core.Role;
import ma.enova.rth.dto.RoleDto;
import ma.enova.rth.service.facade.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation du service Irole
 *
 * @author JAF
 * @version 1.2
 */

@Service(value = "roleService")
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private IRoleRepository roleRepository;


    /**
     * createRole.
     *
     * @param role
     * @return Role
     * @throws Exception
     */

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public RoleDto createRole(RoleDto roleDto) throws Exception {


        Role role = new Role();
        role = roleDto.convertToEntity(role, roleDto);
        Role newRole = roleRepository.save(role);
        roleDto.setId(newRole.getId());


        return roleDto;
    }

    /**
     * updateRole.
     *
     * @param role
     * @return Role
     * @throws Exception
     */

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public RoleDto updateRole(RoleDto roleDto) throws Exception {


        Role role = roleRepository.findById(roleDto.getId()).orElseThrow(() -> new EntityNotFoundException("errors.notFound", new String[]{Role.class.getSimpleName(), roleDto.getId().toString()}));
        role = roleDto.convertToEntity(role, roleDto);
        roleRepository.saveAndFlush(role);

        return roleDto;
    }

    /**
     * getRoleById.
     *
     * @param roleId
     * @return Role
     * @throws Exception
     */
    public RoleDto getRoleById(Long roleId) throws Exception {

        Role role = roleRepository.findById(roleId).orElseThrow(() -> new EntityNotFoundException("errors.notFound", new String[]{Role.class.getSimpleName(), roleId.toString()}));

        return new RoleDto(role, true, 0);

    }


    /**
     * findRolesByCriteria.
     *
     * @param roleCriteria
     * @return List<Role>
     * @throws Exception
     */
    public List<RoleDto> findRolesByCriteria(RoleCriteria roleCriteria) throws Exception {

        Specification<Role> specification = new RoleSpecification(roleCriteria);

        if (roleCriteria.isPeagable()) {
            Pageable pageable = PageRequest.of(0, roleCriteria.getMaxResults());
            return roleRepository.findAll(specification, pageable).getContent().stream().map(role -> new RoleDto(role)).collect(Collectors.toList());

        } else {
            return roleRepository.findAll(specification).stream().map(role -> new RoleDto(role)).collect(Collectors.toList());
        }

    }

    /**
     * findRoleByCriteria.
     *
     * @param roleCriteria
     * @return Role
     * @throws Exception
     */

    public RoleDto findRoleByCriteria(RoleCriteria roleCriteria) throws Exception {

        Specification<Role> specification = new RoleSpecification(roleCriteria);

        Role role = roleRepository.findOne(specification).orElse(null);

        RoleDto roleDto = null;
        if (role != null) {
            roleDto = new RoleDto();
            return new RoleDto(role, true);
        }

        return roleDto;
    }

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

    public List<RoleDto> paginatedListRoles(RoleCriteria roleCriteria, int page, int pageSize, String order, String sortField) throws Exception {

        Specification<Role> specification = new RoleSpecification(roleCriteria);
        order = order != null && !order.isEmpty() ? order : "desc";
        sortField = sortField != null && !sortField.isEmpty() ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);

        return roleRepository.findAll(specification, pageable).getContent().stream().map(role -> new RoleDto(role)).collect(Collectors.toList());
    }

    /**
     * getRoleDataSize.
     *
     * @param roleCriteria
     * @return int
     * @throws Exception
     */
    public int getRoleDataSize(RoleCriteria roleCriteria) throws Exception {

        Specification<Role> specification = new RoleSpecification(roleCriteria, true);

        return ((Long) roleRepository.count(specification)).intValue();

    }

    /**
     * deleteRole.
     *
     * @param roleList
     * @throws Exception
     */

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public void deleteRole(List<RoleDto> roleList) throws Exception {


        if (roleList != null)
            for (RoleDto roleDto : roleList) {
                Role toBeDeleted = roleRepository.findById(roleDto.getId()).orElseThrow(() -> new EntityNotFoundException("errors.notFound", new String[]{Role.class.getSimpleName(), roleDto.getId().toString()}));

                roleRepository.delete(toBeDeleted);

            }
    }


}