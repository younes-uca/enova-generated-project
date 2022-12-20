package ma.enova.rth.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import ma.enova.rth.common.bean.AuditBaseDto;
import ma.enova.rth.common.bean.Log;
import ma.enova.rth.common.util.Utils;
import ma.enova.rth.domain.core.Etablissement;
import ma.enova.rth.domain.core.Patient;
import ma.enova.rth.common.util.DateUtil;
import ma.enova.rth.common.util.RefelexivityUtil;
import ma.enova.rth.common.util.StringUtil;
import ma.enova.rth.common.util.Utils;
import ma.enova.rth.common.util.DateUtil;
import ma.enova.rth.common.util.RefelexivityUtil;
import ma.enova.rth.common.util.StringUtil;
import ma.enova.rth.common.util.Utils;

import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PatientDto extends AuditBaseDto {


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
    private String dateNaissance;

    /**
     * Etablissement
     */
    private EtablissementDto etablissement;


    /**
     * Constructeur par défaut.
     */
    public PatientDto() {
        super();
    }

    /**
     * Constructeur.
     *
     * @param id clé primaire
     */
    public PatientDto(Long id) {
        super(id);
    }

    public PatientDto(Patient patient, boolean collections, int level) {
        super();
        convertToDto(this, patient, collections, level);
    }

    public PatientDto(Patient patient, boolean collections) {
        super();
        convertToDto(this, patient, collections, 0);
    }

    public PatientDto(Patient patient) {
        super();
        convertToDto(this, patient, false, 0);
    }

    public Patient convertIdToEntity(Patient patient, PatientDto patientDto) {

        patient.setId(patientDto.getId());

        return patient;
    }

    public PatientDto convertIdToDto(PatientDto patientDto, Patient patient) {

        patientDto.setId(patient.getId());

        return patientDto;
    }

    public Patient convertToEntity(Patient patient, PatientDto patientDto) {

        if (patient != null) {
            patient = convertIdToEntity(patient, patientDto);
            patient.setIpp(patientDto.getIpp());
            patient.setNom(patientDto.getNom());
            patient.setPrenom(patientDto.getPrenom());
            patient.setSexe(patientDto.getSexe());
            patient.setDateNaissance(DateUtil.stringToDate(patientDto.getDateNaissance()));
            if (patientDto.getEtablissement() != null && patientDto.getEtablissement().getId() != null)
                patient.setEtablissement(new Etablissement(patientDto.getEtablissement().getId()));

        }

        return patient;
    }

    public PatientDto mappedCustomDto(PatientDto patientDto, String[] includes, String[] excludes) {

        if (StringUtil.isNoEmpty(includes) || StringUtil.isNoEmpty(excludes)) {
            if (StringUtil.isNoEmpty(includes))
                RefelexivityUtil.copyIncludesProperties(patientDto, this, includes);
            if (StringUtil.isNoEmpty(excludes))
                RefelexivityUtil.copyExcludesProperties(patientDto, this, excludes);

            this.id = patientDto.getId();

            return this;
        }

        return patientDto;
    }

    public PatientDto convertToDto(PatientDto patientDto, Patient patient, boolean collections, int level) {

        level++;
        if (patientDto != null && level <= maxLevel) {
            patientDto = convertIdToDto(patientDto, patient);
            patientDto.setLabel(patient.getLabel());
            patientDto.setIpp(patient.getIpp());
            patientDto.setNom(patient.getNom());
            patientDto.setPrenom(patient.getPrenom());
            patientDto.setSexe(patient.getSexe());
            patientDto.setDateNaissance(DateUtil.dateToString(patient.getDateNaissance()));

            patientDto.setCreatedBy(patient.getCreatedBy());
            patientDto.setCreatedOn(DateUtil.dateTimeToString(patient.getCreatedOn()));
            patientDto.setUpdatedBy(patient.getUpdatedBy());
            patientDto.setUpdatedOn(DateUtil.dateTimeToString(patient.getUpdatedOn()));

            patientDto.setEtablissement(patient.getEtablissement() != null ? new EtablissementDto(patient.getEtablissement(), false, level) : null);
            if (collections) {
            }

        }

        return patientDto;
    }

    @Log
    public Long getIpp() {
        return this.ipp;
    }

    public void setIpp(Long ipp) {
        this.ipp = ipp;
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
    public String getSexe() {
        return this.sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    @Log
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    public String getDateNaissance() {
        return this.dateNaissance;
    }

    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    @Log
    public EtablissementDto getEtablissement() {
        return this.etablissement;
    }

    public void setEtablissement(EtablissementDto etablissement) {
        this.etablissement = etablissement;
    }

}
