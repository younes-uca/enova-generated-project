package ma.enova.rth.process.radiotherapie.util;

import ma.enova.rth.zynerator.process.AbstractProcessInput;
import ma.enova.rth.dto.*;

public class RadiotherapieInput extends AbstractProcessInput {

    protected String datePrescription;
    protected Integer fractionnement = 0;
    protected String dateSouhaiteDebutTraitement;
    protected String observation;
    protected ProtocoleInclusionDto protocoleInclusion;
    protected ViseeDto visee;
    protected PersonnelDto medecinPrescripteur;
    protected PatientDto patient;
    protected OrganeDto organe;
    protected ModaliteRadiotherapieDto modaliteRadiotherapie;
    protected FrequenceRadiotherapieDto frequenceRadiotherapie;
    protected EtablissementDto etablissement;

    public String getDatePrescription() {
        return datePrescription;
    }

    public void setDatePrescription(String datePrescription) {
        this.datePrescription = datePrescription;
    }

    public Integer getFractionnement() {
        return fractionnement;
    }

    public void setFractionnement(Integer fractionnement) {
        this.fractionnement = fractionnement;
    }

    public String getDateSouhaiteDebutTraitement() {
        return dateSouhaiteDebutTraitement;
    }

    public void setDateSouhaiteDebutTraitement(String dateSouhaiteDebutTraitement) {
        this.dateSouhaiteDebutTraitement = dateSouhaiteDebutTraitement;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public ProtocoleInclusionDto getProtocoleInclusion() {
        return protocoleInclusion;
    }

    public void setProtocoleInclusion(ProtocoleInclusionDto protocoleInclusion) {
        this.protocoleInclusion = protocoleInclusion;
    }

    public ViseeDto getVisee() {
        return visee;
    }

    public void setVisee(ViseeDto visee) {
        this.visee = visee;
    }

    public PersonnelDto getMedecinPrescripteur() {
        return medecinPrescripteur;
    }

    public void setMedecinPrescripteur(PersonnelDto medecinPrescripteur) {
        this.medecinPrescripteur = medecinPrescripteur;
    }

    public PatientDto getPatient() {
        return patient;
    }

    public void setPatient(PatientDto patient) {
        this.patient = patient;
    }

    public OrganeDto getOrgane() {
        return organe;
    }

    public void setOrgane(OrganeDto organe) {
        this.organe = organe;
    }

    public ModaliteRadiotherapieDto getModaliteRadiotherapie() {
        return modaliteRadiotherapie;
    }

    public void setModaliteRadiotherapie(ModaliteRadiotherapieDto modaliteRadiotherapie) {
        this.modaliteRadiotherapie = modaliteRadiotherapie;
    }

    public FrequenceRadiotherapieDto getFrequenceRadiotherapie() {
        return frequenceRadiotherapie;
    }

    public void setFrequenceRadiotherapie(FrequenceRadiotherapieDto frequenceRadiotherapie) {
        this.frequenceRadiotherapie = frequenceRadiotherapie;
    }

    public EtablissementDto getEtablissement() {
        return etablissement;
    }

    public void setEtablissement(EtablissementDto etablissement) {
        this.etablissement = etablissement;
    }
}
