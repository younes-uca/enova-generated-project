package ma.enova.rth.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import ma.enova.rth.common.bean.AuditBaseDto;
import ma.enova.rth.common.bean.Log;
import ma.enova.rth.common.enumeration.STATUT_PROTOCOLEINCLUSION;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProtocoleInclusionDto extends AuditBaseDto {


    /**
     * Code
     */
    private String code;

    /**
     * Libellé
     */
    private String libelle;

    /**
     * Status
     */
    private STATUT_PROTOCOLEINCLUSION status;

    /**
     * Etablissement
     */
    private EtablissementDto etablissement;


    /**
     * Constructeur par défaut.
     */
    public ProtocoleInclusionDto() {
        super();
    }

    /**
     * Constructeur.
     *
     * @param id clé primaire
     */
    public ProtocoleInclusionDto(Long id) {
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
    public STATUT_PROTOCOLEINCLUSION getStatus() {
        return status;
    }

    public void setStatus(STATUT_PROTOCOLEINCLUSION status) {
        this.status = status;
    }

    @Log
    public EtablissementDto getEtablissement() {
        return this.etablissement;
    }

    public void setEtablissement(EtablissementDto etablissement) {
        this.etablissement = etablissement;
    }

}
