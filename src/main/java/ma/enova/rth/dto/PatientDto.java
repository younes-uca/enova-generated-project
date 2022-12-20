package ma.enova.rth.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import ma.enova.rth.common.bean.AuditBaseDto;
import ma.enova.rth.common.bean.Log;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PatientDto extends AuditBaseDto {


    /**
     * Ipp
     */
    private Long ipp;

    /**
     * Nom
     */
    private String nom;

    /**
     * Prénom
     */
    private String prenom;

    /**
     * Sexe
     */
    private String sexe;

    /**
     * DateNaissance
     */
    private String dateNaissance;

    /**
     * Etablissement
     */
    private EtablissementDto etablissement;


    /**
     * Constructeur par défaut.
     */
    public PatientDto() {
        super();
    }

    /**
     * Constructeur.
     *
     * @param id clé primaire
     */
    public PatientDto(Long id) {
        super(id);
    }

    @Log
    public Long getIpp() {
        return this.ipp;
    }

    public void setIpp(Long ipp) {
        this.ipp = ipp;
    }

    @Log
    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Log
    public String getPrenom() {
        return this.prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    @Log
    public String getSexe() {
        return this.sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    @Log
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    public String getDateNaissance() {
        return this.dateNaissance;
    }

    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    @Log
    public EtablissementDto getEtablissement() {
        return this.etablissement;
    }

    public void setEtablissement(EtablissementDto etablissement) {
        this.etablissement = etablissement;
    }

}
