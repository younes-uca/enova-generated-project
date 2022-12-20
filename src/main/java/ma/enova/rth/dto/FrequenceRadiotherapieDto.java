package ma.enova.rth.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import ma.enova.rth.common.bean.AuditBaseDto;
import ma.enova.rth.common.bean.Log;
import ma.enova.rth.common.util.Utils;
import ma.enova.rth.domain.core.Etablissement;
import ma.enova.rth.domain.core.FrequenceRadiotherapie;
import ma.enova.rth.common.util.DateUtil;
import ma.enova.rth.common.util.RefelexivityUtil;
import ma.enova.rth.common.util.StringUtil;
import ma.enova.rth.common.util.Utils;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FrequenceRadiotherapieDto extends AuditBaseDto {


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
     * Etablissement
     */
    private EtablissementDto etablissement;


    /**
     * Constructeur par défaut.
     */
    public FrequenceRadiotherapieDto() {
        super();
    }

    /**
     * Constructeur.
     *
     * @param id clé primaire
     */
    public FrequenceRadiotherapieDto(Long id) {
        super(id);
    }

    public FrequenceRadiotherapieDto(FrequenceRadiotherapie frequenceRadiotherapie, boolean collections, int level) {
        super();
        convertToDto(this, frequenceRadiotherapie, collections, level);
    }

    public FrequenceRadiotherapieDto(FrequenceRadiotherapie frequenceRadiotherapie, boolean collections) {
        super();
        convertToDto(this, frequenceRadiotherapie, collections, 0);
    }

    public FrequenceRadiotherapieDto(FrequenceRadiotherapie frequenceRadiotherapie) {
        super();
        convertToDto(this, frequenceRadiotherapie, false, 0);
    }

    public FrequenceRadiotherapie convertIdToEntity(FrequenceRadiotherapie frequenceRadiotherapie, FrequenceRadiotherapieDto frequenceRadiotherapieDto) {

        frequenceRadiotherapie.setId(frequenceRadiotherapieDto.getId());

        return frequenceRadiotherapie;
    }

    public FrequenceRadiotherapieDto convertIdToDto(FrequenceRadiotherapieDto frequenceRadiotherapieDto, FrequenceRadiotherapie frequenceRadiotherapie) {

        frequenceRadiotherapieDto.setId(frequenceRadiotherapie.getId());

        return frequenceRadiotherapieDto;
    }

    public FrequenceRadiotherapie convertToEntity(FrequenceRadiotherapie frequenceRadiotherapie, FrequenceRadiotherapieDto frequenceRadiotherapieDto) {

        if (frequenceRadiotherapie != null) {
            frequenceRadiotherapie = convertIdToEntity(frequenceRadiotherapie, frequenceRadiotherapieDto);
            frequenceRadiotherapie.setCode(frequenceRadiotherapieDto.getCode());
            frequenceRadiotherapie.setLibelle(frequenceRadiotherapieDto.getLibelle());
            frequenceRadiotherapie.setDescription(frequenceRadiotherapieDto.getDescription());
            frequenceRadiotherapie.setActif(frequenceRadiotherapieDto.isActif());
            frequenceRadiotherapie.setHl7(frequenceRadiotherapieDto.getHl7());
            frequenceRadiotherapie.setOrdre(frequenceRadiotherapieDto.getOrdre());
            if (frequenceRadiotherapieDto.getEtablissement() != null && frequenceRadiotherapieDto.getEtablissement().getId() != null)
                frequenceRadiotherapie.setEtablissement(new Etablissement(frequenceRadiotherapieDto.getEtablissement().getId()));

        }

        return frequenceRadiotherapie;
    }

    public FrequenceRadiotherapieDto mappedCustomDto(FrequenceRadiotherapieDto frequenceRadiotherapieDto, String[] includes, String[] excludes) {

        if (StringUtil.isNoEmpty(includes) || StringUtil.isNoEmpty(excludes)) {
            if (StringUtil.isNoEmpty(includes))
                RefelexivityUtil.copyIncludesProperties(frequenceRadiotherapieDto, this, includes);
            if (StringUtil.isNoEmpty(excludes))
                RefelexivityUtil.copyExcludesProperties(frequenceRadiotherapieDto, this, excludes);

            this.id = frequenceRadiotherapieDto.getId();

            return this;
        }

        return frequenceRadiotherapieDto;
    }

    public FrequenceRadiotherapieDto convertToDto(FrequenceRadiotherapieDto frequenceRadiotherapieDto, FrequenceRadiotherapie frequenceRadiotherapie, boolean collections, int level) {

        level++;
        if (frequenceRadiotherapieDto != null && level <= maxLevel) {
            frequenceRadiotherapieDto = convertIdToDto(frequenceRadiotherapieDto, frequenceRadiotherapie);
            frequenceRadiotherapieDto.setLabel(frequenceRadiotherapie.getLabel());
            frequenceRadiotherapieDto.setCode(frequenceRadiotherapie.getCode());
            frequenceRadiotherapieDto.setLibelle(frequenceRadiotherapie.getLibelle());
            frequenceRadiotherapieDto.setDescription(frequenceRadiotherapie.getDescription());
            frequenceRadiotherapieDto.setActif(frequenceRadiotherapie.isActif());
            frequenceRadiotherapieDto.setHl7(frequenceRadiotherapie.getHl7());
            frequenceRadiotherapieDto.setOrdre(frequenceRadiotherapie.getOrdre());

            frequenceRadiotherapieDto.setCreatedBy(frequenceRadiotherapie.getCreatedBy());
            frequenceRadiotherapieDto.setCreatedOn(DateUtil.dateTimeToString(frequenceRadiotherapie.getCreatedOn()));
            frequenceRadiotherapieDto.setUpdatedBy(frequenceRadiotherapie.getUpdatedBy());
            frequenceRadiotherapieDto.setUpdatedOn(DateUtil.dateTimeToString(frequenceRadiotherapie.getUpdatedOn()));

            frequenceRadiotherapieDto.setEtablissement(frequenceRadiotherapie.getEtablissement() != null ? new EtablissementDto(frequenceRadiotherapie.getEtablissement(), false, level) : null);
            if (collections) {
            }

        }

        return frequenceRadiotherapieDto;
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

    @Log
    public EtablissementDto getEtablissement() {
        return this.etablissement;
    }

    public void setEtablissement(EtablissementDto etablissement) {
        this.etablissement = etablissement;
    }

}
