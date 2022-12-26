package ma.enova.rth.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import ma.enova.rth.zynerator.dto.AuditBaseDto;
import ma.enova.rth.zynerator.audit.Log;
import ma.enova.rth.zynerator.util.DateUtil;
import ma.enova.rth.zynerator.util.RefelexivityUtil;
import ma.enova.rth.zynerator.util.StringUtil;
import ma.enova.rth.bean.core.Etablissement;
import ma.enova.rth.bean.core.Profil;
import ma.enova.rth.bean.core.Utilisateur;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UtilisateurDto extends AuditBaseDto {


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
     * Nom d'utilisateur
     */
    private String username;

    /**
     * Mot de passe
     */
    private String password;

    /**
     * Activer
     */
    private boolean enabled = false;

    /**
     * R�initialiser le mot de passe
     */
    private boolean resetPassword = false;

    /**
     * Ancien mot de passe
     */
    private String oldPassword;

    /**
     * Nouveau mot de passe
     */
    private String newPassword;

    // transiant
    private String nomComplet;
    /**
     * etablissement
     */
    private EtablissementDto etablissement;

    /**
     * profil
     */
    private ProfilDto profil;


    /**
     * Constructeur par défaut.
     */
    public UtilisateurDto() {
        super();
    }

    /**
     * Constructeur.
     *
     * @param id clé primaire
     */
    public UtilisateurDto(Long id) {
        super(id);
    }

    public UtilisateurDto(Utilisateur utilisateur, boolean collections, int level) {
        super();
        convertToDto(this, utilisateur, collections, level);
    }

    public UtilisateurDto(Utilisateur utilisateur, boolean collections) {
        super();
        convertToDto(this, utilisateur, collections, 0);
    }

    public UtilisateurDto(Utilisateur utilisateur) {
        super();
        convertToDto(this, utilisateur, false, 0);
    }

    public Utilisateur convertIdToEntity(Utilisateur utilisateur, UtilisateurDto utilisateurDto) {

        utilisateur.setId(utilisateurDto.getId());

        return utilisateur;
    }

    public UtilisateurDto convertIdToDto(UtilisateurDto utilisateurDto, Utilisateur utilisateur) {

        utilisateurDto.setId(utilisateur.getId());

        return utilisateurDto;
    }

    public Utilisateur convertToEntity(Utilisateur utilisateur, UtilisateurDto utilisateurDto) {

        if (utilisateur != null) {
            utilisateur = convertIdToEntity(utilisateur, utilisateurDto);
            utilisateur.setNom(utilisateurDto.getNom());
            utilisateur.setPrenom(utilisateurDto.getPrenom());
            utilisateur.setCin(utilisateurDto.getCin());
            utilisateur.setAdresse(utilisateurDto.getAdresse());
            utilisateur.setEmail(utilisateurDto.getEmail());
            utilisateur.setTelephone(utilisateurDto.getTelephone());
            utilisateur.setMobile(utilisateurDto.getMobile());
            utilisateur.setExpertise(utilisateurDto.isExpertise());
            utilisateur.setUsername(utilisateurDto.getUsername());
            utilisateur.setEnabled(utilisateurDto.isEnabled());
            if (utilisateurDto.getEtablissement() != null && utilisateurDto.getEtablissement().getId() != null)
                utilisateur.setEtablissement(new Etablissement(utilisateurDto.getEtablissement().getId()));
            utilisateur.setProfil(utilisateurDto.getProfil() != null ? new Profil(utilisateurDto.getProfil().getId()) : null);

        }

        return utilisateur;
    }

    public UtilisateurDto mappedCustomDto(UtilisateurDto utilisateurDto, String[] includes, String[] excludes) {

        if (StringUtil.isNoEmpty(includes) || StringUtil.isNoEmpty(excludes)) {
            if (StringUtil.isNoEmpty(includes))
                RefelexivityUtil.copyIncludesProperties(utilisateurDto, this, includes);
            if (StringUtil.isNoEmpty(excludes))
                RefelexivityUtil.copyExcludesProperties(utilisateurDto, this, excludes);

            this.id = utilisateurDto.getId();

            return this;
        }

        return utilisateurDto;
    }

    public UtilisateurDto convertToDto(UtilisateurDto utilisateurDto, Utilisateur utilisateur, boolean collections, int level) {

        level++;
        if (utilisateurDto != null && level <= maxLevel) {
            utilisateurDto = convertIdToDto(utilisateurDto, utilisateur);
            utilisateurDto.setLabel(utilisateur.getLabel());
            utilisateurDto.setNom(utilisateur.getNom());
            utilisateurDto.setPrenom(utilisateur.getPrenom());
            utilisateurDto.setCin(utilisateur.getCin());
            utilisateurDto.setAdresse(utilisateur.getAdresse());
            utilisateurDto.setEmail(utilisateur.getEmail());
            utilisateurDto.setTelephone(utilisateur.getTelephone());
            utilisateurDto.setMobile(utilisateur.getMobile());
            utilisateurDto.setExpertise(utilisateur.isExpertise());
            utilisateurDto.setUsername(utilisateur.getUsername());
            utilisateurDto.setEnabled(utilisateur.isEnabled());

            utilisateurDto.setCreatedBy(utilisateur.getCreatedBy());
            utilisateurDto.setCreatedOn(DateUtil.dateTimeToString(utilisateur.getCreatedOn()));
            utilisateurDto.setUpdatedBy(utilisateur.getUpdatedBy());
            utilisateurDto.setUpdatedOn(DateUtil.dateTimeToString(utilisateur.getUpdatedOn()));

            utilisateurDto.setEtablissement(utilisateur.getEtablissement() != null ? new EtablissementDto(utilisateur.getEtablissement(), false, level) : null);
            utilisateurDto.setProfil(utilisateur.getProfil() != null ? new ProfilDto(utilisateur.getProfil(), false, level) : null);
            if (collections) {
            }

        }

        return utilisateurDto;
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
    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Log
    @JsonIgnore
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Log
    public boolean isEnabled() {
        return this.enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isResetPassword() {
        return this.resetPassword;
    }

    public void setResetPassword(boolean resetPassword) {
        this.resetPassword = resetPassword;
    }

    public String getOldPassword() {
        return this.oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return this.newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    @JsonProperty(access = Access.READ_ONLY)
    public String getNomComplet() {
        if (nom != null && !nom.isEmpty() && prenom != null && !prenom.isEmpty())
            nomComplet = nom + " " + prenom + " (" + username + ")";
        else
            nomComplet = username;
        return nomComplet;
    }

    @Log
    public EtablissementDto getEtablissement() {
        return this.etablissement;
    }

    public void setEtablissement(EtablissementDto etablissement) {
        this.etablissement = etablissement;
    }

    @Log
    public ProfilDto getProfil() {
        return this.profil;
    }

    public void setProfil(ProfilDto profil) {
        this.profil = profil;
    }

}
