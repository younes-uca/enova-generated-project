package ma.enova.rth.domain.core;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import ma.enova.rth.common.bean.AuditBusinessObject;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

import javax.persistence.*;


/**
 * Utilisateurs
 *
 * @author JAF
 * @version 1.2
 */

@Entity
@Table(name = "utilisateur_")
@GenericGenerator(name = "utilisateurSequenceGenerator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {@Parameter(name = "sequence_name", value = "utilisateur_seq"), @Parameter(name = "increment_size", value = "1")})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Utilisateur extends AuditBusinessObject {

    /**
     * Fields.
     */

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

    /**
     * etablissement
     */
    private Etablissement etablissement;

    /**
     * profil
     */
    private Profil profil;


    /**
     * Constructeur par défaut.
     */
    public Utilisateur() {
        super();
    }

    /**
     * Constructeur.
     *
     * @param id clé primaire
     */
    public Utilisateur(Long id) {
        super(id);
    }

    /**
     * Methods.
     */

    @Column(length = 255)
    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Column(length = 255)
    public String getPrenom() {
        return this.prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    @Column(length = 255)
    public String getCin() {
        return this.cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    @Type(type = "text")
    public String getAdresse() {
        return this.adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    @Column(length = 255)
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(length = 255)
    public String getTelephone() {
        return this.telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Column(length = 255)
    public String getMobile() {
        return this.mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Column(columnDefinition = "boolean default false")
    @Type(type = "boolean")
    public boolean isExpertise() {
        return this.expertise;
    }

    public void setExpertise(boolean expertise) {
        this.expertise = expertise;
    }

    @Column(length = 255)
    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @JsonIgnore
    @Type(type = "text")
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(columnDefinition = "boolean default false")
    @Type(type = "boolean")
    public boolean isEnabled() {
        return this.enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Transient
    @Type(type = "boolean")
    public boolean isResetPassword() {
        return this.resetPassword;
    }

    public void setResetPassword(boolean resetPassword) {
        this.resetPassword = resetPassword;
    }

    @Transient
    public String getOldPassword() {
        return this.oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    @Transient
    public String getNewPassword() {
        return this.newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "etablissement_id", updatable = false)
    public Etablissement getEtablissement() {
        return this.etablissement;
    }

    public void setEtablissement(Etablissement etablissement) {
        this.etablissement = etablissement;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profil_id")
    public Profil getProfil() {
        return this.profil;
    }

    public void setProfil(Profil profil) {
        this.profil = profil;
    }


    @Id
    @Column(name = "id_utilisateur")
    @GeneratedValue(generator = "utilisateurSequenceGenerator")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Transient
    public String getLabel() {
        if (nom != null && !nom.isEmpty() && prenom != null && !prenom.isEmpty())
            label = nom + " " + prenom + " (" + username + ")";
        else
            label = username;
        return label;
    }


}