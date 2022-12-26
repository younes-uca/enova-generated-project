package ma.enova.rth.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import ma.enova.rth.zynerator.dto.AuditBaseDto;
import ma.enova.rth.zynerator.audit.Log;
import ma.enova.rth.zynerator.util.DateUtil;
import ma.enova.rth.zynerator.util.RefelexivityUtil;
import ma.enova.rth.zynerator.util.StringUtil;
import ma.enova.rth.bean.core.Profil;
import ma.enova.rth.bean.core.Role;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProfilDto extends AuditBaseDto {


    /**
     * Code
     */
    private String code;

    /**
     * Libellé
     */
    private String libelle;

    /**
     * Description
     */
    private String description;

    /**
     * roles
     */
    private List<RoleDto> rolesList;


    /**
     * Constructeur par défaut.
     */
    public ProfilDto() {
        super();
    }

    /**
     * Constructeur.
     *
     * @param id clé primaire
     */
    public ProfilDto(Long id) {
        super(id);
    }

    public ProfilDto(Profil profil, boolean collections, int level) {
        super();
        convertToDto(this, profil, collections, level);
    }

    public ProfilDto(Profil profil, boolean collections) {
        super();
        convertToDto(this, profil, collections, 0);
    }

    public ProfilDto(Profil profil) {
        super();
        convertToDto(this, profil, false, 0);
    }

    public Profil convertIdToEntity(Profil profil, ProfilDto profilDto) {

        profil.setId(profilDto.getId());

        return profil;
    }

    public ProfilDto convertIdToDto(ProfilDto profilDto, Profil profil) {

        profilDto.setId(profil.getId());

        return profilDto;
    }

    public Profil convertToEntity(Profil profil, ProfilDto profilDto) {

        if (profil != null) {
            profil = convertIdToEntity(profil, profilDto);
            profil.setCode(profilDto.getCode());
            profil.setLibelle(profilDto.getLibelle());
            profil.setDescription(profilDto.getDescription());
            if ((profilDto.getRolesList() != null && !profilDto.getRolesList().isEmpty())) {
                profil.setRolesList(new ArrayList<Role>());
                for (RoleDto rolesDto : profilDto.getRolesList()) {
                    if (rolesDto.getId() != null)
                        profil.getRolesList().add(new Role(rolesDto.getId()));
                }
            } else {
                profil.setRolesList(null);
            }

        }

        return profil;
    }

    public ProfilDto mappedCustomDto(ProfilDto profilDto, String[] includes, String[] excludes) {

        if (StringUtil.isNoEmpty(includes) || StringUtil.isNoEmpty(excludes)) {
            if (StringUtil.isNoEmpty(includes))
                RefelexivityUtil.copyIncludesProperties(profilDto, this, includes);
            if (StringUtil.isNoEmpty(excludes))
                RefelexivityUtil.copyExcludesProperties(profilDto, this, excludes);

            this.id = profilDto.getId();

            return this;
        }

        return profilDto;
    }

    public ProfilDto convertToDto(ProfilDto profilDto, Profil profil, boolean collections, int level) {

        level++;
        if (profilDto != null && level <= maxLevel) {
            profilDto = convertIdToDto(profilDto, profil);
            profilDto.setLabel(profil.getLabel());
            profilDto.setCode(profil.getCode());
            profilDto.setLibelle(profil.getLibelle());
            profilDto.setDescription(profil.getDescription());

            profilDto.setCreatedBy(profil.getCreatedBy());
            profilDto.setCreatedOn(DateUtil.dateTimeToString(profil.getCreatedOn()));
            profilDto.setUpdatedBy(profil.getUpdatedBy());
            profilDto.setUpdatedOn(DateUtil.dateTimeToString(profil.getUpdatedOn()));

            if (collections) {
                if ((profil.getRolesList() != null && !profil.getRolesList().isEmpty())) {
                    profilDto.setRolesList(new ArrayList<RoleDto>());
                    for (Role roles : profil.getRolesList()) {
                        if (roles.getId() != null)
                            profilDto.getRolesList().add(new RoleDto(roles, false, level));
                    }
                } else {
                    profilDto.setRolesList(null);
                }
            }

        }

        return profilDto;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
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
    public List<RoleDto> getRolesList() {
        return this.rolesList;
    }

    public void setRolesList(List<RoleDto> rolesList) {
        this.rolesList = rolesList;
    }

}
