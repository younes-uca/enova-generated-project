package ma.enova.rth.dao.criteria.core;

import com.fasterxml.jackson.annotation.JsonFormat;
import ma.enova.rth.zynerator.criteria.BaseCriteria;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Critère PrescriptionRadiotherapie.
 */
public class PrescriptionRadiotherapieCriteria extends BaseCriteria {

    /**
     * Fields.
     */

    private LocalDateTime datePrescription;
    private LocalDateTime datePrescriptionFrom;
    private LocalDateTime datePrescriptionTo;
    private String fractionnement;
    private LocalDateTime dateSouhaiteDebutTraitement;
    private LocalDateTime dateSouhaiteDebutTraitementFrom;
    private LocalDateTime dateSouhaiteDebutTraitementTo;
    private String observation;
    private String observationLike;
    private Long protocoleInclusionId;
    private List<Long> protocoleInclusionIdIn;
    private Long viseeId;
    private Long medecinPrescripteurId;
    private Long patientId;
    private Long organeId;
    private Long modaliteRadiotherapieId;
    private Long frequenceRadiotherapieId;
    private Long etablissementId;


    /**
     * Methods.
     */


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    public LocalDateTime getDatePrescription() {
        return this.datePrescription;
    }

    public void setDatePrescription(LocalDateTime datePrescription) {
        this.datePrescription = datePrescription;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    public LocalDateTime getDatePrescriptionTo() {
        return this.datePrescriptionTo;
    }

    public void setDatePrescriptionTo(LocalDateTime datePrescriptionTo) {
        this.datePrescriptionTo = datePrescriptionTo;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    public LocalDateTime getDatePrescriptionFrom() {
        return this.datePrescriptionFrom;
    }

    public void setDatePrescriptionFrom(LocalDateTime datePrescriptionFrom) {
        this.datePrescriptionFrom = datePrescriptionFrom;
    }

    public String getFractionnement() {
        return this.fractionnement;
    }

    public void setFractionnement(String fractionnement) {
        this.fractionnement = fractionnement;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    public LocalDateTime getDateSouhaiteDebutTraitement() {
        return this.dateSouhaiteDebutTraitement;
    }

    public void setDateSouhaiteDebutTraitement(LocalDateTime dateSouhaiteDebutTraitement) {
        this.dateSouhaiteDebutTraitement = dateSouhaiteDebutTraitement;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    public LocalDateTime getDateSouhaiteDebutTraitementTo() {
        return this.dateSouhaiteDebutTraitementTo;
    }

    public void setDateSouhaiteDebutTraitementTo(LocalDateTime dateSouhaiteDebutTraitementTo) {
        this.dateSouhaiteDebutTraitementTo = dateSouhaiteDebutTraitementTo;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    public LocalDateTime getDateSouhaiteDebutTraitementFrom() {
        return this.dateSouhaiteDebutTraitementFrom;
    }

    public void setDateSouhaiteDebutTraitementFrom(LocalDateTime dateSouhaiteDebutTraitementFrom) {
        this.dateSouhaiteDebutTraitementFrom = dateSouhaiteDebutTraitementFrom;
    }

    public String getObservationLike() {
        return this.observationLike;
    }

    public void setObservationLike(String observationLike) {
        this.observationLike = observationLike;
    }

    public String getObservation() {
        return this.observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public Long getProtocoleInclusionId() {
        return this.protocoleInclusionId;
    }

    public void setProtocoleInclusionId(Long protocoleInclusionId) {
        this.protocoleInclusionId = protocoleInclusionId;
    }

    public Long getViseeId() {
        return this.viseeId;
    }

    public void setViseeId(Long viseeId) {
        this.viseeId = viseeId;
    }

    public Long getMedecinPrescripteurId() {
        return this.medecinPrescripteurId;
    }

    public void setMedecinPrescripteurId(Long medecinPrescripteurId) {
        this.medecinPrescripteurId = medecinPrescripteurId;
    }

    public Long getPatientId() {
        return this.patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public Long getOrganeId() {
        return this.organeId;
    }

    public void setOrganeId(Long organeId) {
        this.organeId = organeId;
    }

    public Long getModaliteRadiotherapieId() {
        return this.modaliteRadiotherapieId;
    }

    public void setModaliteRadiotherapieId(Long modaliteRadiotherapieId) {
        this.modaliteRadiotherapieId = modaliteRadiotherapieId;
    }

    public Long getFrequenceRadiotherapieId() {
        return this.frequenceRadiotherapieId;
    }

    public void setFrequenceRadiotherapieId(Long frequenceRadiotherapieId) {
        this.frequenceRadiotherapieId = frequenceRadiotherapieId;
    }

    public Long getEtablissementId() {
        return this.etablissementId;
    }

    public void setEtablissementId(Long etablissementId) {
        this.etablissementId = etablissementId;
    }

    public List<Long> getProtocoleInclusionIdIn() {
        return protocoleInclusionIdIn;
    }

    public void setProtocoleInclusionIdIn(List<Long> protocoleInclusionIdIn) {
        this.protocoleInclusionIdIn = protocoleInclusionIdIn;
    }
}