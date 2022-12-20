package ma.enova.rth.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import ma.enova.rth.common.bean.BaseDto;
import ma.enova.rth.common.bean.Log;
import ma.enova.rth.common.util.RefelexivityUtil;
import ma.enova.rth.common.util.StringUtil;
import ma.enova.rth.domain.core.CategorieRole;
import ma.enova.rth.domain.core.Role;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class RoleDto extends BaseDto {


    /**
     * Libellé
     */
    private String libelle;

    /**
     * Description
     */
    private String description;

    /**
     * Domaine
     */
    private Integer domaine = 1;

    /**
     * categorieRole
     */
    private CategorieRoleDto categorieRole;


    /**
     * Constructeur par défaut.
     */
    public RoleDto() {
        super();
    }

    /**
     * Constructeur.
     *
     * @param id clé primaire
     */
    public RoleDto(Long id) {
        super(id);
    }

    public RoleDto(Role role, boolean collections, int level) {
        super();
        convertToDto(this, role, collections, level);
    }

    public RoleDto(Role role, boolean collections) {
        super();
        convertToDto(this, role, collections, 0);
    }

    public RoleDto(Role role) {
        super();
        convertToDto(this, role, false, 0);
    }

    public Role convertIdToEntity(Role role, RoleDto roleDto) {

        role.setId(roleDto.getId());

        return role;
    }

    public RoleDto convertIdToDto(RoleDto roleDto, Role role) {

        roleDto.setId(role.getId());

        return roleDto;
    }

    public Role convertToEntity(Role role, RoleDto roleDto) {

        if (role != null) {
            role = convertIdToEntity(role, roleDto);
            role.setLibelle(roleDto.getLibelle());
            role.setDescription(roleDto.getDescription());
            role.setDomaine(roleDto.getDomaine());
            role.setCategorieRole(roleDto.getCategorieRole() != null ? new CategorieRole(roleDto.getCategorieRole().getId()) : null);

        }

        return role;
    }

    public RoleDto mappedCustomDto(RoleDto roleDto, String[] includes, String[] excludes) {

        if (StringUtil.isNoEmpty(includes) || StringUtil.isNoEmpty(excludes)) {
            if (StringUtil.isNoEmpty(includes))
                RefelexivityUtil.copyIncludesProperties(roleDto, this, includes);
            if (StringUtil.isNoEmpty(excludes))
                RefelexivityUtil.copyExcludesProperties(roleDto, this, excludes);

            this.id = roleDto.getId();

            return this;
        }

        return roleDto;
    }

    public RoleDto convertToDto(RoleDto roleDto, Role role, boolean collections, int level) {

        level++;
        if (roleDto != null && level <= maxLevel) {
            roleDto = convertIdToDto(roleDto, role);
            roleDto.setLabel(role.getLabel());
            roleDto.setLibelle(role.getLibelle());
            roleDto.setDescription(role.getDescription());
            roleDto.setDomaine(role.getDomaine());
            roleDto.setCategorieRole(role.getCategorieRole() != null ? new CategorieRoleDto(role.getCategorieRole(), false, level) : null);
            if (collections) {
            }

        }

        return roleDto;
    }

    @Log
    public String getLibelle() {
        return this.libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    @Log
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Log
    public Integer getDomaine() {
        return this.domaine;
    }

    public void setDomaine(Integer domaine) {
        this.domaine = domaine;
    }

    @Log
    public CategorieRoleDto getCategorieRole() {
        return this.categorieRole;
    }

    public void setCategorieRole(CategorieRoleDto categorieRole) {
        this.categorieRole = categorieRole;
    }

}
