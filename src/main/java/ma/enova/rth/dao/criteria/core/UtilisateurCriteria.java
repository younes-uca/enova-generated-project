package ma.enova.rth.dao.criteria.core;

import ma.enova.rth.common.bean.BaseCriteria;

import java.util.List;

/**
 * Critère Utilisateur.
 */
public class UtilisateurCriteria extends BaseCriteria {

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
    private String username;
    private String usernameLike;
    private String password;
    private String passwordLike;
    private String enabled = "true";
    private List<Long> enabledAndIds;
    private String resetPassword;
    private String oldPassword;
    private String oldPasswordLike;
    private String newPassword;
    private String newPasswordLike;
    private Long etablissementId;
    private Long profilId;


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

    public String getUsernameLike() {
        return this.usernameLike;
    }

    public void setUsernameLike(String usernameLike) {
        this.usernameLike = usernameLike;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordLike() {
        return this.passwordLike;
    }

    public void setPasswordLike(String passwordLike) {
        this.passwordLike = passwordLike;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEnabled() {
        return this.enabled;
    }

    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }

    public List<Long> getEnabledAndIds() {
        return enabledAndIds;
    }

    public void setEnabledAndIds(List<Long> enabledAndIds) {
        this.enabledAndIds = enabledAndIds;
    }

    public String getResetPassword() {
        return this.resetPassword;
    }

    public void setResetPassword(String resetPassword) {
        this.resetPassword = resetPassword;
    }

    public String getOldPasswordLike() {
        return this.oldPasswordLike;
    }

    public void setOldPasswordLike(String oldPasswordLike) {
        this.oldPasswordLike = oldPasswordLike;
    }

    public String getOldPassword() {
        return this.oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPasswordLike() {
        return this.newPasswordLike;
    }

    public void setNewPasswordLike(String newPasswordLike) {
        this.newPasswordLike = newPasswordLike;
    }

    public String getNewPassword() {
        return this.newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public Long getEtablissementId() {
        return this.etablissementId;
    }

    public void setEtablissementId(Long etablissementId) {
        this.etablissementId = etablissementId;
    }

    public Long getProfilId() {
        return this.profilId;
    }

    public void setProfilId(Long profilId) {
        this.profilId = profilId;
    }


}