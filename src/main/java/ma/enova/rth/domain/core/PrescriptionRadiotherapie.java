package ma.enova.rth.domain.core;

import com.fasterxml.jackson.annotation.JsonInclude;
import ma.enova.rth.common.bean.AuditBusinessObject;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;


/**
 * prescriptionRadiotherapie
 *
 * @author JAF
 * @version 1.2
 */

@Entity
@Table(name = "prescriptionradiotherapie_")
@GenericGenerator(name = "prescriptionRadiotherapieSequenceGenerator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {@Parameter(name = "sequence_name", value = "prescriptionRadiotherapie_seq"), @Parameter(name = "increment_size", value = "1")})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PrescriptionRadiotherapie extends AuditBusinessObject {

    /**
     * Fields.
     */

    /**
     * DatePrescription
     */
    private LocalDateTime datePrescription;

    /**
     * Fractionnement
     */
    private Integer fractionnement = 0;

    /**
     * DateSouhaiteDebutTraitement
     */
    private LocalDateTime dateSouhaiteDebutTraitement;

    /**
     * Observations
     */
    private String observation;

    /**
     * protocoleInclusion
     */
    private ProtocoleInclusion protocoleInclusion;

    /**
     * visee
     */
    private Visee visee;

    /**
     * medecinPrescripteur
     */
    private Personnel medecinPrescripteur;

    /**
     * patient
     */
    private Patient patient;

    /**
     * organe
     */
    private Organe organe;

    /**
     * modaliteRadiotherapie
     */
    private ModaliteRadiotherapie modaliteRadiotherapie;

    /**
     * frequenceRadiotherapie
     */
    private FrequenceRadiotherapie frequenceRadiotherapie;

    /**
     * Etablissement
     */
    private Etablissement etablissement;


    /**
     * Constructeur par défaut.
     */
    public PrescriptionRadiotherapie() {
        super();
    }

    /**
     * Constructeur.
     *
     * @param id clé primaire
     */
    public PrescriptionRadiotherapie(Long id) {
        super(id);
    }

    /**
     * Methods.
     */

    public LocalDateTime getDatePrescription() {
        return this.datePrescription;
    }

    public void setDatePrescription(LocalDateTime datePrescription) {
        this.datePrescription = datePrescription;
    }

    @Column(columnDefinition = "integer default 0")
    @Type(type = "integer")
    public Integer getFractionnement() {
        return this.fractionnement;
    }

    public void setFractionnement(Integer fractionnement) {
        this.fractionnement = fractionnement;
    }

    public LocalDateTime getDateSouhaiteDebutTraitement() {
        return this.dateSouhaiteDebutTraitement;
    }

    public void setDateSouhaiteDebutTraitement(LocalDateTime dateSouhaiteDebutTraitement) {
        this.dateSouhaiteDebutTraitement = dateSouhaiteDebutTraitement;
    }

    @Type(type = "text")
    public String getObservation() {
        return this.observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "protocoleInclusion_id")
    public ProtocoleInclusion getProtocoleInclusion() {
        return this.protocoleInclusion;
    }

    public void setProtocoleInclusion(ProtocoleInclusion protocoleInclusion) {
        this.protocoleInclusion = protocoleInclusion;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "visee_id")
    public Visee getVisee() {
        return this.visee;
    }

    public void setVisee(Visee visee) {
        this.visee = visee;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medecinPrescripteur_id")
    public Personnel getMedecinPrescripteur() {
        return this.medecinPrescripteur;
    }

    public void setMedecinPrescripteur(Personnel medecinPrescripteur) {
        this.medecinPrescripteur = medecinPrescripteur;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id")
    public Patient getPatient() {
        return this.patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organe_id")
    public Organe getOrgane() {
        return this.organe;
    }

    public void setOrgane(Organe organe) {
        this.organe = organe;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "modaliteRadiotherapie_id")
    public ModaliteRadiotherapie getModaliteRadiotherapie() {
        return this.modaliteRadiotherapie;
    }

    public void setModaliteRadiotherapie(ModaliteRadiotherapie modaliteRadiotherapie) {
        this.modaliteRadiotherapie = modaliteRadiotherapie;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "frequenceRadiotherapie_id")
    public FrequenceRadiotherapie getFrequenceRadiotherapie() {
        return this.frequenceRadiotherapie;
    }

    public void setFrequenceRadiotherapie(FrequenceRadiotherapie frequenceRadiotherapie) {
        this.frequenceRadiotherapie = frequenceRadiotherapie;
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
    @Column(name = "id")
    @GeneratedValue(generator = "prescriptionRadiotherapieSequenceGenerator")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Transient
    public String getLabel() {
        label = observation;
        return label;
    }


}