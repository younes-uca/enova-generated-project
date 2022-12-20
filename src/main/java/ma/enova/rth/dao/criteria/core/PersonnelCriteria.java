package ma.enova.rth.dao.criteria.core;

import ma.enova.rth.common.bean.BaseCriteria;

/**
 * Critère Personnel.
 */
public class PersonnelCriteria extends BaseCriteria {

    /**
     * Fields.
     */

    private String nom;
    private String nomLike;
    private String prenom;
    private String prenomLike;
    private String cin;
    private String cinLike;
    private String adresse;
    private String adresseLike;
    private String email;
    private String emailLike;
    private String telephone;
    private String telephoneLike;
    private String mobile;
    private String mobileLike;
    private String expertise;
    private Long etablissementId;


    /**
     * Methods.
     */

    public String getNomLike() {
        return this.nomLike;
    }

    public void setNomLike(String nomLike) {
        this.nomLike = nomLike;
    }

    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenomLike() {
        return this.prenomLike;
    }

    public void setPrenomLike(String prenomLike) {
        this.prenomLike = prenomLike;
    }

    public String getPrenom() {
        return this.prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getCinLike() {
        return this.cinLike;
    }

    public void setCinLike(String cinLike) {
        this.cinLike = cinLike;
    }

    public String getCin() {
        return this.cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getAdresseLike() {
        return this.adresseLike;
    }

    public void setAdresseLike(String adresseLike) {
        this.adresseLike = adresseLike;
    }

    public String getAdresse() {
        return this.adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getEmailLike() {
        return this.emailLike;
    }

    public void setEmailLike(String emailLike) {
        this.emailLike = emailLike;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephoneLike() {
        return this.telephoneLike;
    }

    public void setTelephoneLike(String telephoneLike) {
        this.telephoneLike = telephoneLike;
    }

    public String getTelephone() {
        return this.telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getMobileLike() {
        return this.mobileLike;
    }

    public void setMobileLike(String mobileLike) {
        this.mobileLike = mobileLike;
    }

    public String getMobile() {
        return this.mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getExpertise() {
        return this.expertise;
    }

    public void setExpertise(String expertise) {
        this.expertise = expertise;
    }

    public Long getEtablissementId() {
        return this.etablissementId;
    }

    public void setEtablissementId(Long etablissementId) {
        this.etablissementId = etablissementId;
    }


}