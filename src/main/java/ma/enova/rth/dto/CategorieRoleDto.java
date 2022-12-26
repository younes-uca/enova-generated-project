package ma.enova.rth.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import ma.enova.rth.zynerator.dto.AuditBaseDto;
import ma.enova.rth.zynerator.audit.Log;
import ma.enova.rth.zynerator.util.DateUtil;
import ma.enova.rth.zynerator.util.RefelexivityUtil;
import ma.enova.rth.zynerator.util.StringUtil;
import ma.enova.rth.bean.core.CategorieRole;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategorieRoleDto extends AuditBaseDto {


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
     * Actif
     */
    private boolean actif = false;

    /**
     * Hl7
     */
    private String hl7;

    /**
     * Ordre
     */
    private Long ordre;


    /**
     * Constructeur par défaut.
     */
    public CategorieRoleDto() {
        super();
    }

    /**
     * Constructeur.
     *
     * @param id clé primaire
     */
    public CategorieRoleDto(Long id) {
        super(id);
    }

    public CategorieRoleDto(CategorieRole categorieRole, boolean collections, int level) {
        super();
        convertToDto(this, categorieRole, collections, level);
    }

    public CategorieRoleDto(CategorieRole categorieRole, boolean collections) {
        super();
        convertToDto(this, categorieRole, collections, 0);
    }

    public CategorieRoleDto(CategorieRole categorieRole) {
        super();
        convertToDto(this, categorieRole, false, 0);
    }

    public CategorieRole convertIdToEntity(CategorieRole categorieRole, CategorieRoleDto categorieRoleDto) {

        categorieRole.setId(categorieRoleDto.getId());

        return categorieRole;
    }

    public CategorieRoleDto convertIdToDto(CategorieRoleDto categorieRoleDto, CategorieRole categorieRole) {

        categorieRoleDto.setId(categorieRole.getId());

        return categorieRoleDto;
    }

    public CategorieRole convertToEntity(CategorieRole categorieRole, CategorieRoleDto categorieRoleDto) {

        if (categorieRole != null) {
            categorieRole = convertIdToEntity(categorieRole, categorieRoleDto);
            categorieRole.setCode(categorieRoleDto.getCode());
            categorieRole.setLibelle(categorieRoleDto.getLibelle());
            categorieRole.setDescription(categorieRoleDto.getDescription());
            categorieRole.setActif(categorieRoleDto.isActif());
            categorieRole.setHl7(categorieRoleDto.getHl7());
            categorieRole.setOrdre(categorieRoleDto.getOrdre());

        }

        return categorieRole;
    }

    public CategorieRoleDto mappedCustomDto(CategorieRoleDto categorieRoleDto, String[] includes, String[] excludes) {

        if (StringUtil.isNoEmpty(includes) || StringUtil.isNoEmpty(excludes)) {
            if (StringUtil.isNoEmpty(includes))
                RefelexivityUtil.copyIncludesProperties(categorieRoleDto, this, includes);
            if (StringUtil.isNoEmpty(excludes))
                RefelexivityUtil.copyExcludesProperties(categorieRoleDto, this, excludes);

            this.id = categorieRoleDto.getId();

            return this;
        }

        return categorieRoleDto;
    }

    public CategorieRoleDto convertToDto(CategorieRoleDto categorieRoleDto, CategorieRole categorieRole, boolean collections, int level) {

        level++;
        if (categorieRoleDto != null && level <= maxLevel) {
            categorieRoleDto = convertIdToDto(categorieRoleDto, categorieRole);
            categorieRoleDto.setLabel(categorieRole.getLabel());
            categorieRoleDto.setCode(categorieRole.getCode());
            categorieRoleDto.setLibelle(categorieRole.getLibelle());
            categorieRoleDto.setDescription(categorieRole.getDescription());
            categorieRoleDto.setActif(categorieRole.isActif());
            categorieRoleDto.setHl7(categorieRole.getHl7());
            categorieRoleDto.setOrdre(categorieRole.getOrdre());

            categorieRoleDto.setCreatedBy(categorieRole.getCreatedBy());
            categorieRoleDto.setCreatedOn(DateUtil.dateTimeToString(categorieRole.getCreatedOn()));
            categorieRoleDto.setUpdatedBy(categorieRole.getUpdatedBy());
            categorieRoleDto.setUpdatedOn(DateUtil.dateTimeToString(categorieRole.getUpdatedOn()));

            if (collections) {
            }

        }

        return categorieRoleDto;
    }

    @Log
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
    public boolean isActif() {
        return this.actif;
    }

    public void setActif(boolean actif) {
        this.actif = actif;
    }

    @Log
    public String getHl7() {
        return this.hl7;
    }

    public void setHl7(String hl7) {
        this.hl7 = hl7;
    }

    @Log
    public Long getOrdre() {
        return this.ordre;
    }

    public void setOrdre(Long ordre) {
        this.ordre = ordre;
    }


}
