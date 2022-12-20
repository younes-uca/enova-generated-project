package ma.enova.rth.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import ma.enova.rth.common.bean.AuditBaseDto;
import ma.enova.rth.common.bean.Log;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PrescriptionRadiotherapieDto extends AuditBaseDto {


    /**
     * DatePrescription
     */
    private String datePrescription;

    /**
     * Fractionnement
     */
    private Integer fractionnement = 0;

    /**
     * DateSouhaiteDebutTraitement
     */
    private String dateSouhaiteDebutTraitement;

    /**
     * Observations
     */
    private String observation;

    /**
     * protocoleInclusion
     */
    private ProtocoleInclusionDto protocoleInclusion;

    /**
     * visee
     */
    private ViseeDto visee;

    /**
     * medecinPrescripteur
     */
    private PersonnelDto medecinPrescripteur;

    /**
     * patient
     */
    private PatientDto patient;

    /**
     * organe
     */
    private OrganeDto organe;

    /**
     * modaliteRadiotherapie
     */
    private ModaliteRadiotherapieDto modaliteRadiotherapie;

    /**
     * frequenceRadiotherapie
     */
    private FrequenceRadiotherapieDto frequenceRadiotherapie;

    /**
     * Etablissement
     */
    private EtablissementDto etablissement;


    /**
     * Constructeur par défaut.
     */
    public PrescriptionRadiotherapieDto() {
        super();
    }

    /**
     * Constructeur.
     *
     * @param id clé primaire
     */
    public PrescriptionRadiotherapieDto(Long id) {
        super(id);
    }

    @Log
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    public String getDatePrescription() {
        return this.datePrescription;
    }

    public void setDatePrescription(String datePrescription) {
        this.datePrescription = datePrescription;
    }

    @Log
    public Integer getFractionnement() {
        return this.fractionnement;
    }

    public void setFractionnement(Integer fractionnement) {
        this.fractionnement = fractionnement;
    }

    @Log
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    public String getDateSouhaiteDebutTraitement() {
        return this.dateSouhaiteDebutTraitement;
    }

    public void setDateSouhaiteDebutTraitement(String dateSouhaiteDebutTraitement) {
        this.dateSouhaiteDebutTraitement = dateSouhaiteDebutTraitement;
    }

    @Log
    public String getObservation() {
        return this.observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    @Log
    public ProtocoleInclusionDto getProtocoleInclusion() {
        return this.protocoleInclusion;
    }

    public void setProtocoleInclusion(ProtocoleInclusionDto protocoleInclusion) {
        this.protocoleInclusion = protocoleInclusion;
    }

    @Log
    public ViseeDto getVisee() {
        return this.visee;
    }

    public void setVisee(ViseeDto visee) {
        this.visee = visee;
    }

    @Log
    public PersonnelDto getMedecinPrescripteur() {
        return this.medecinPrescripteur;
    }

    public void setMedecinPrescripteur(PersonnelDto medecinPrescripteur) {
        this.medecinPrescripteur = medecinPrescripteur;
    }

    @Log
    public PatientDto getPatient() {
        return this.patient;
    }

    public void setPatient(PatientDto patient) {
        this.patient = patient;
    }

    @Log
    public OrganeDto getOrgane() {
        return this.organe;
    }

    public void setOrgane(OrganeDto organe) {
        this.organe = organe;
    }

    @Log
    public ModaliteRadiotherapieDto getModaliteRadiotherapie() {
        return this.modaliteRadiotherapie;
    }

    public void setModaliteRadiotherapie(ModaliteRadiotherapieDto modaliteRadiotherapie) {
        this.modaliteRadiotherapie = modaliteRadiotherapie;
    }

    @Log
    public FrequenceRadiotherapieDto getFrequenceRadiotherapie() {
        return this.frequenceRadiotherapie;
    }

    public void setFrequenceRadiotherapie(FrequenceRadiotherapieDto frequenceRadiotherapie) {
        this.frequenceRadiotherapie = frequenceRadiotherapie;
    }

    @Log
    public EtablissementDto getEtablissement() {
        return this.etablissement;
    }

    public void setEtablissement(EtablissementDto etablissement) {
        this.etablissement = etablissement;
    }

}
