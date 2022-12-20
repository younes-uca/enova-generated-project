package ma.enova.rth.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import ma.enova.rth.common.bean.AuditBaseDto;
import ma.enova.rth.common.bean.Log;
import ma.enova.rth.common.util.Utils;
import ma.enova.rth.domain.core.Etablissement;
import ma.enova.rth.domain.core.ModaliteRadiotherapie;
import ma.enova.rth.common.util.DateUtil;
import ma.enova.rth.common.util.RefelexivityUtil;
import ma.enova.rth.common.util.StringUtil;
import ma.enova.rth.common.util.Utils;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ModaliteRadiotherapieDto extends AuditBaseDto {


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
    public ModaliteRadiotherapieDto() {
        super();
    }

    /**
     * Constructeur.
     *
     * @param id clé primaire
     */
    public ModaliteRadiotherapieDto(Long id) {
        super(id);
    }

    public ModaliteRadiotherapieDto(ModaliteRadiotherapie modaliteRadiotherapie, boolean collections, int level) {
        super();
        convertToDto(this, modaliteRadiotherapie, collections, level);
    }

    public ModaliteRadiotherapieDto(ModaliteRadiotherapie modaliteRadiotherapie, boolean collections) {
        super();
        convertToDto(this, modaliteRadiotherapie, collections, 0);
    }

    public ModaliteRadiotherapieDto(ModaliteRadiotherapie modaliteRadiotherapie) {
        super();
        convertToDto(this, modaliteRadiotherapie, false, 0);
    }

    public ModaliteRadiotherapie convertIdToEntity(ModaliteRadiotherapie modaliteRadiotherapie, ModaliteRadiotherapieDto modaliteRadiotherapieDto) {

        modaliteRadiotherapie.setId(modaliteRadiotherapieDto.getId());

        return modaliteRadiotherapie;
    }

    public ModaliteRadiotherapieDto convertIdToDto(ModaliteRadiotherapieDto modaliteRadiotherapieDto, ModaliteRadiotherapie modaliteRadiotherapie) {

        modaliteRadiotherapieDto.setId(modaliteRadiotherapie.getId());

        return modaliteRadiotherapieDto;
    }

    public ModaliteRadiotherapie convertToEntity(ModaliteRadiotherapie modaliteRadiotherapie, ModaliteRadiotherapieDto modaliteRadiotherapieDto) {

        if (modaliteRadiotherapie != null) {
            modaliteRadiotherapie = convertIdToEntity(modaliteRadiotherapie, modaliteRadiotherapieDto);
            modaliteRadiotherapie.setCode(modaliteRadiotherapieDto.getCode());
            modaliteRadiotherapie.setLibelle(modaliteRadiotherapieDto.getLibelle());
            modaliteRadiotherapie.setDescription(modaliteRadiotherapieDto.getDescription());
            modaliteRadiotherapie.setActif(modaliteRadiotherapieDto.isActif());
            modaliteRadiotherapie.setHl7(modaliteRadiotherapieDto.getHl7());
            modaliteRadiotherapie.setOrdre(modaliteRadiotherapieDto.getOrdre());
            if (modaliteRadiotherapieDto.getEtablissement() != null && modaliteRadiotherapieDto.getEtablissement().getId() != null)
                modaliteRadiotherapie.setEtablissement(new Etablissement(modaliteRadiotherapieDto.getEtablissement().getId()));

        }

        return modaliteRadiotherapie;
    }

    public ModaliteRadiotherapieDto mappedCustomDto(ModaliteRadiotherapieDto modaliteRadiotherapieDto, String[] includes, String[] excludes) {

        if (StringUtil.isNoEmpty(includes) || StringUtil.isNoEmpty(excludes)) {
            if (StringUtil.isNoEmpty(includes))
                RefelexivityUtil.copyIncludesProperties(modaliteRadiotherapieDto, this, includes);
            if (StringUtil.isNoEmpty(excludes))
                RefelexivityUtil.copyExcludesProperties(modaliteRadiotherapieDto, this, excludes);

            this.id = modaliteRadiotherapieDto.getId();

            return this;
        }

        return modaliteRadiotherapieDto;
    }

    public ModaliteRadiotherapieDto convertToDto(ModaliteRadiotherapieDto modaliteRadiotherapieDto, ModaliteRadiotherapie modaliteRadiotherapie, boolean collections, int level) {

        level++;
        if (modaliteRadiotherapieDto != null && level <= maxLevel) {
            modaliteRadiotherapieDto = convertIdToDto(modaliteRadiotherapieDto, modaliteRadiotherapie);
            modaliteRadiotherapieDto.setLabel(modaliteRadiotherapie.getLabel());
            modaliteRadiotherapieDto.setCode(modaliteRadiotherapie.getCode());
            modaliteRadiotherapieDto.setLibelle(modaliteRadiotherapie.getLibelle());
            modaliteRadiotherapieDto.setDescription(modaliteRadiotherapie.getDescription());
            modaliteRadiotherapieDto.setActif(modaliteRadiotherapie.isActif());
            modaliteRadiotherapieDto.setHl7(modaliteRadiotherapie.getHl7());
            modaliteRadiotherapieDto.setOrdre(modaliteRadiotherapie.getOrdre());

            modaliteRadiotherapieDto.setCreatedBy(modaliteRadiotherapie.getCreatedBy());
            modaliteRadiotherapieDto.setCreatedOn(DateUtil.dateTimeToString(modaliteRadiotherapie.getCreatedOn()));
            modaliteRadiotherapieDto.setUpdatedBy(modaliteRadiotherapie.getUpdatedBy());
            modaliteRadiotherapieDto.setUpdatedOn(DateUtil.dateTimeToString(modaliteRadiotherapie.getUpdatedOn()));

            modaliteRadiotherapieDto.setEtablissement(modaliteRadiotherapie.getEtablissement() != null ? new EtablissementDto(modaliteRadiotherapie.getEtablissement(), false, level) : null);
            if (collections) {
            }

        }

        return modaliteRadiotherapieDto;
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
