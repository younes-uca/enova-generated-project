package ma.enova.rth.domain.core;

import com.fasterxml.jackson.annotation.JsonInclude;
import ma.enova.rth.common.bean.AuditBusinessObject;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.time.LocalDate;


/**
 * patient
 *
 * @author JAF
 * @version 1.2
 */

@Entity
@Table(name = "patient_")
@GenericGenerator(name = "patientSequenceGenerator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {@Parameter(name = "sequence_name", value = "patient_seq"), @Parameter(name = "increment_size", value = "1")})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Patient extends AuditBusinessObject {

    /**
     * Fields.
     */

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
    private LocalDate dateNaissance;

    /**
     * Etablissement
     */
    private Etablissement etablissement;


    /**
     * Constructeur par défaut.
     */
    public Patient() {
        super();
    }

    /**
     * Constructeur.
     *
     * @param id clé primaire
     */
    public Patient(Long id) {
        super(id);
    }

    /**
     * Methods.
     */

    public Long getIpp() {
        return this.ipp;
    }

    public void setIpp(Long ipp) {
        this.ipp = ipp;
    }

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
    public String getSexe() {
        return this.sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public LocalDate getDateNaissance() {
        return this.dateNaissance;
    }

    public void setDateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "etablissement_id", updatable = false)
    public Etablissement getEtablissement() {
        return this.etablissement;
    }

    public void setEtablissement(Etablissement etablissement) {
        this.etablissement = etablissement;
    }


    @Id
    @Column(name = "id_patient")
    @GeneratedValue(generator = "patientSequenceGenerator")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Transient
    public String getLabel() {
        label = nom;
        return label;
    }


}