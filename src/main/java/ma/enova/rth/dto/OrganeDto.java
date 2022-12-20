package ma.enova.rth.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import ma.enova.rth.common.bean.AuditBaseDto;
import ma.enova.rth.common.bean.Log;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrganeDto extends AuditBaseDto {


    /**
     * Code
     */
    private String code;

    /**
     * Libellé
     */
    private String libelle;

    /**
     * Etablissement
     */
    private EtablissementDto etablissement;


    /**
     * Constructeur par défaut.
     */
    public OrganeDto() {
        super();
    }

    /**
     * Constructeur.
     *
     * @param id clé primaire
     */
    public OrganeDto(Long id) {
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
    public String getLibelle() {
        return this.libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    @Log
    public EtablissementDto getEtablissement() {
        return this.etablissement;
    }

    public void setEtablissement(EtablissementDto etablissement) {
        this.etablissement = etablissement;
    }

}
