package ma.enova.rth.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import ma.enova.rth.common.bean.AuditBaseDto;
import ma.enova.rth.common.bean.Log;
import ma.enova.rth.common.util.Utils;
import ma.enova.rth.domain.core.Etablissement;
import ma.enova.rth.domain.core.Visee;
import ma.enova.rth.common.util.DateUtil;
import ma.enova.rth.common.util.RefelexivityUtil;
import ma.enova.rth.common.util.StringUtil;
import ma.enova.rth.common.util.Utils;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ViseeDto extends AuditBaseDto {


    /**
     * Code
     */
    private String code;

    /**
     * Description
     */
    private String description;

    /**
     * Libellé
     */
    private String libelle;

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
     * Etablissement
     */
    private EtablissementDto etablissement;


    /**
     * Constructeur par défaut.
     */
    public ViseeDto() {
        super();
    }

    /**
     * Constructeur.
     *
     * @param id clé primaire
     */
    public ViseeDto(Long id) {
        super(id);
    }

    public ViseeDto(Visee visee, boolean collections, int level) {
        super();
        convertToDto(this, visee, collections, level);
    }

    public ViseeDto(Visee visee, boolean collections) {
        super();
        convertToDto(this, visee, collections, 0);
    }

    public ViseeDto(Visee visee) {
        super();
        convertToDto(this, visee, false, 0);
    }

    public Visee convertIdToEntity(Visee visee, ViseeDto viseeDto) {

        visee.setId(viseeDto.getId());

        return visee;
    }

    public ViseeDto convertIdToDto(ViseeDto viseeDto, Visee visee) {

        viseeDto.setId(visee.getId());

        return viseeDto;
    }

    public Visee convertToEntity(Visee visee, ViseeDto viseeDto) {

        if (visee != null) {
            visee = convertIdToEntity(visee, viseeDto);
            visee.setCode(viseeDto.getCode());
            visee.setDescription(viseeDto.getDescription());
            visee.setLibelle(viseeDto.getLibelle());
            visee.setActif(viseeDto.isActif());
            visee.setHl7(viseeDto.getHl7());
            visee.setOrdre(viseeDto.getOrdre());
            if (viseeDto.getEtablissement() != null && viseeDto.getEtablissement().getId() != null)
                visee.setEtablissement(new Etablissement(viseeDto.getEtablissement().getId()));

        }

        return visee;
    }

    public ViseeDto mappedCustomDto(ViseeDto viseeDto, String[] includes, String[] excludes) {

        if (StringUtil.isNoEmpty(includes) || StringUtil.isNoEmpty(excludes)) {
            if (StringUtil.isNoEmpty(includes))
                RefelexivityUtil.copyIncludesProperties(viseeDto, this, includes);
            if (StringUtil.isNoEmpty(excludes))
                RefelexivityUtil.copyExcludesProperties(viseeDto, this, excludes);

            this.id = viseeDto.getId();

            return this;
        }

        return viseeDto;
    }

    public ViseeDto convertToDto(ViseeDto viseeDto, Visee visee, boolean collections, int level) {

        level++;
        if (viseeDto != null && level <= maxLevel) {
            viseeDto = convertIdToDto(viseeDto, visee);
            viseeDto.setLabel(visee.getLabel());
            viseeDto.setCode(visee.getCode());
            viseeDto.setDescription(visee.getDescription());
            viseeDto.setLibelle(visee.getLibelle());
            viseeDto.setActif(visee.isActif());
            viseeDto.setHl7(visee.getHl7());
            viseeDto.setOrdre(visee.getOrdre());

            viseeDto.setCreatedBy(visee.getCreatedBy());
            viseeDto.setCreatedOn(DateUtil.dateTimeToString(visee.getCreatedOn()));
            viseeDto.setUpdatedBy(visee.getUpdatedBy());
            viseeDto.setUpdatedOn(DateUtil.dateTimeToString(visee.getUpdatedOn()));

            viseeDto.setEtablissement(visee.getEtablissement() != null ? new EtablissementDto(visee.getEtablissement(), false, level) : null);
            if (collections) {
            }

        }

        return viseeDto;
    }

    @Log
    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Log
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Log
    public String getLibelle() {
        return this.libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
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

    @Log
    public EtablissementDto getEtablissement() {
        return this.etablissement;
    }

    public void setEtablissement(EtablissementDto etablissement) {
        this.etablissement = etablissement;
    }

}
