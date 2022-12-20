package ma.enova.rth.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import ma.enova.rth.common.bean.AuditBaseDto;
import ma.enova.rth.common.bean.Log;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PersonnelDto extends AuditBaseDto {


    /**
     * Nom
     */
    private String nom;

    /**
     * Prénom
     */
    private String prenom;

    /**
     * CIN
     */
    private String cin;

    /**
     * Adresse
     */
    private String adresse;

    /**
     * E-mail
     */
    private String email;

    /**
     * Téléphone
     */
    private String telephone;

    /**
     * Mobile
     */
    private String mobile;

    /**
     * Expertise
     */
    private boolean expertise = false;

    /**
     * Etablissement
     */
    private EtablissementDto etablissement;


    /**
     * Constructeur par défaut.
     */
    public PersonnelDto() {
        super();
    }

    /**
     * Constructeur.
     *
     * @param id clé primaire
     */
    public PersonnelDto(Long id) {
        super(id);
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
    public String getCin() {
        return this.cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    @Log
    public String getAdresse() {
        return this.adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    @Log
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Log
    public String getTelephone() {
        return this.telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Log
    public String getMobile() {
        return this.mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Log
    public boolean isExpertise() {
        return this.expertise;
    }

    public void setExpertise(boolean expertise) {
        this.expertise = expertise;
    }

    @Log
    public EtablissementDto getEtablissement() {
        return this.etablissement;
    }

    public void setEtablissement(EtablissementDto etablissement) {
        this.etablissement = etablissement;
    }

}
