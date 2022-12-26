package ma.enova.rth.dao.criteria.core;

import com.fasterxml.jackson.annotation.JsonFormat;
import ma.enova.rth.zynerator.criteria.BaseCriteria;

import java.time.LocalDateTime;

/**
 * Critère SeanceRadiotherapie.
 */
public class SeanceRadiotherapieCriteria extends BaseCriteria {

    /**
     * Fields.
     */

    private LocalDateTime dateDebut;
    private LocalDateTime dateDebutFrom;
    private LocalDateTime dateDebutTo;
    private LocalDateTime dateFin;
    private LocalDateTime dateFinFrom;
    private LocalDateTime dateFinTo;
    private String marquePresence;
    private Long prescriptionRadiotherapieId;
    private Long etablissementId;


    /**
     * Methods.
     */


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    public LocalDateTime getDateDebut() {
        return this.dateDebut;
    }

    public void setDateDebut(LocalDateTime dateDebut) {
        this.dateDebut = dateDebut;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    public LocalDateTime getDateDebutTo() {
        return this.dateDebutTo;
    }

    public void setDateDebutTo(LocalDateTime dateDebutTo) {
        this.dateDebutTo = dateDebutTo;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    public LocalDateTime getDateDebutFrom() {
        return this.dateDebutFrom;
    }

    public void setDateDebutFrom(LocalDateTime dateDebutFrom) {
        this.dateDebutFrom = dateDebutFrom;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    public LocalDateTime getDateFin() {
        return this.dateFin;
    }

    public void setDateFin(LocalDateTime dateFin) {
        this.dateFin = dateFin;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    public LocalDateTime getDateFinTo() {
        return this.dateFinTo;
    }

    public void setDateFinTo(LocalDateTime dateFinTo) {
        this.dateFinTo = dateFinTo;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    public LocalDateTime getDateFinFrom() {
        return this.dateFinFrom;
    }

    public void setDateFinFrom(LocalDateTime dateFinFrom) {
        this.dateFinFrom = dateFinFrom;
    }

    public String getMarquePresence() {
        return this.marquePresence;
    }

    public void setMarquePresence(String marquePresence) {
        this.marquePresence = marquePresence;
    }

    public Long getPrescriptionRadiotherapieId() {
        return this.prescriptionRadiotherapieId;
    }

    public void setPrescriptionRadiotherapieId(Long prescriptionRadiotherapieId) {
        this.prescriptionRadiotherapieId = prescriptionRadiotherapieId;
    }

    public Long getEtablissementId() {
        return this.etablissementId;
    }

    public void setEtablissementId(Long etablissementId) {
        this.etablissementId = etablissementId;
    }


}