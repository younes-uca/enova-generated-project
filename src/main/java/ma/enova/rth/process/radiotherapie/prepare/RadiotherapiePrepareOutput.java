package ma.enova.rth.process.radiotherapie.prepare;

import ma.enova.rth.dto.*;

import java.time.LocalDateTime;
import java.util.List;

public class RadiotherapiePrepareOutput {

    private LocalDateTime datePrescription;
    private Integer fractionnement = 0;
    private LocalDateTime dateSouhaiteDebutTraitement;
    private String observation;
    private ProtocoleInclusionDto protocoleInclusion;
    private ViseeDto visee;
    private PersonnelDto medecinPrescripteur;
    private PatientDto patient;
    private OrganeDto organe;
    private ModaliteRadiotherapieDto modaliteRadiotherapie;
    private FrequenceRadiotherapieDto frequenceRadiotherapie;
    private EtablissementDto etablissement;

    private List<SeanceRadiotherapieDto> seanceRadiotherapies;

    public List<SeanceRadiotherapieDto> getSeanceRadiotherapies() {
        return seanceRadiotherapies;
    }

    public void setSeanceRadiotherapies(List<SeanceRadiotherapieDto> seanceRadiotherapies) {
        this.seanceRadiotherapies = seanceRadiotherapies;
    }

    public LocalDateTime getDatePrescription() {
        return datePrescription;
    }

    public void setDatePrescription(LocalDateTime datePrescription) {
        this.datePrescription = datePrescription;
    }

    public Integer getFractionnement() {
        return fractionnement;
    }

    public void setFractionnement(Integer fractionnement) {
        this.fractionnement = fractionnement;
    }

    public LocalDateTime getDateSouhaiteDebutTraitement() {
        return dateSouhaiteDebutTraitement;
    }

    public void setDateSouhaiteDebutTraitement(LocalDateTime dateSouhaiteDebutTraitement) {
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
