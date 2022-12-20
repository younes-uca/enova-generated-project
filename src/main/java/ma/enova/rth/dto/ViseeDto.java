package ma.enova.rth.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import ma.enova.rth.common.bean.AuditBaseDto;
import ma.enova.rth.common.bean.Log;

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
