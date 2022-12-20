package ma.enova.rth.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import ma.enova.rth.common.bean.AuditBaseDto;
import ma.enova.rth.common.bean.Log;
import ma.enova.rth.common.util.Utils;
import ma.enova.rth.domain.core.Etablissement;
import ma.enova.rth.domain.core.PrescriptionRadiotherapie;
import ma.enova.rth.domain.core.SeanceRadiotherapie;
import ma.enova.rth.common.util.DateUtil;
import ma.enova.rth.common.util.RefelexivityUtil;
import ma.enova.rth.common.util.StringUtil;
import ma.enova.rth.common.util.Utils;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class SeanceRadiotherapieDto extends AuditBaseDto {


    /**
     * Date début
     */
    private String dateDebut;

    /**
     * Date de fin
     */
    private String dateFin;

    /**
     * MarquePresence
     */
    private boolean marquePresence = false;

    /**
     * prescriptionRadiotherapie
     */
    private PrescriptionRadiotherapieDto prescriptionRadiotherapie;

    /**
     * Etablissement
     */
    private EtablissementDto etablissement;


    /**
     * Constructeur par défaut.
     */
    public SeanceRadiotherapieDto() {
        super();
    }

    /**
     * Constructeur.
     *
     * @param id clé primaire
     */
    public SeanceRadiotherapieDto(Long id) {
        super(id);
    }

    @Log
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    public String getDateDebut() {
        return this.dateDebut;
    }

    public void setDateDebut(String dateDebut) {
        this.dateDebut = dateDebut;
    }

    @Log
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    public String getDateFin() {
        return this.dateFin;
    }

    public void setDateFin(String dateFin) {
        this.dateFin = dateFin;
    }

    @Log
    public boolean isMarquePresence() {
        return this.marquePresence;
    }

    public void setMarquePresence(boolean marquePresence) {
        this.marquePresence = marquePresence;
    }

    @Log
    public PrescriptionRadiotherapieDto getPrescriptionRadiotherapie() {
        return this.prescriptionRadiotherapie;
    }

    public void setPrescriptionRadiotherapie(PrescriptionRadiotherapieDto prescriptionRadiotherapie) {
        this.prescriptionRadiotherapie = prescriptionRadiotherapie;
    }

    @Log
    public EtablissementDto getEtablissement() {
        return this.etablissement;
    }

    public void setEtablissement(EtablissementDto etablissement) {
        this.etablissement = etablissement;
    }

}
