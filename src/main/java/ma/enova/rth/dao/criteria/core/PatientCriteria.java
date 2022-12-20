package ma.enova.rth.dao.criteria.core;

import com.fasterxml.jackson.annotation.JsonFormat;
import ma.enova.rth.common.bean.BaseCriteria;

import java.time.LocalDate;

/**
 * Critère Patient.
 */
public class PatientCriteria extends BaseCriteria {

    /**
     * Fields.
     */

    private String ipp;
    private String nom;
    private String nomLike;
    private String prenom;
    private String prenomLike;
    private String sexe;
    private String sexeLike;
    private LocalDate dateNaissance;
    private LocalDate dateNaissanceFrom;
    private LocalDate dateNaissanceTo;
    private Long etablissementId;


    /**
     * Methods.
     */

    public String getIpp() {
        return this.ipp;
    }

    public void setIpp(String ipp) {
        this.ipp = ipp;
    }

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

    public String getSexeLike() {
        return this.sexeLike;
    }

    public void setSexeLike(String sexeLike) {
        this.sexeLike = sexeLike;
    }

    public String getSexe() {
        return this.sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    public LocalDate getDateNaissance() {
        return this.dateNaissance;
    }

    public void setDateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    public LocalDate getDateNaissanceTo() {
        return this.dateNaissanceTo;
    }

    public void setDateNaissanceTo(LocalDate dateNaissanceTo) {
        this.dateNaissanceTo = dateNaissanceTo;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    public LocalDate getDateNaissanceFrom() {
        return this.dateNaissanceFrom;
    }

    public void setDateNaissanceFrom(LocalDate dateNaissanceFrom) {
        this.dateNaissanceFrom = dateNaissanceFrom;
    }

    public Long getEtablissementId() {
        return this.etablissementId;
    }

    public void setEtablissementId(Long etablissementId) {
        this.etablissementId = etablissementId;
    }


}