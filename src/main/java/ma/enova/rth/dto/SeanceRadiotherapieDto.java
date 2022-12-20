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

    public SeanceRadiotherapieDto(SeanceRadiotherapie seanceRadiotherapie, boolean collections, int level) {
        super();
        convertToDto(this, seanceRadiotherapie, collections, level);
    }

    public SeanceRadiotherapieDto(SeanceRadiotherapie seanceRadiotherapie, boolean collections) {
        super();
        convertToDto(this, seanceRadiotherapie, collections, 0);
    }

    public SeanceRadiotherapieDto(SeanceRadiotherapie seanceRadiotherapie) {
        super();
        convertToDto(this, seanceRadiotherapie, false, 0);
    }

    public SeanceRadiotherapie convertIdToEntity(SeanceRadiotherapie seanceRadiotherapie, SeanceRadiotherapieDto seanceRadiotherapieDto) {

        seanceRadiotherapie.setId(seanceRadiotherapieDto.getId());

        return seanceRadiotherapie;
    }

    public SeanceRadiotherapieDto convertIdToDto(SeanceRadiotherapieDto seanceRadiotherapieDto, SeanceRadiotherapie seanceRadiotherapie) {

        seanceRadiotherapieDto.setId(seanceRadiotherapie.getId());

        return seanceRadiotherapieDto;
    }

    public SeanceRadiotherapie convertToEntity(SeanceRadiotherapie seanceRadiotherapie, SeanceRadiotherapieDto seanceRadiotherapieDto) {

        if (seanceRadiotherapie != null) {
            seanceRadiotherapie = convertIdToEntity(seanceRadiotherapie, seanceRadiotherapieDto);
            seanceRadiotherapie.setDateDebut(DateUtil.stringToDateTime(seanceRadiotherapieDto.getDateDebut()));
            seanceRadiotherapie.setDateFin(DateUtil.stringToDateTime(seanceRadiotherapieDto.getDateFin()));
            seanceRadiotherapie.setMarquePresence(seanceRadiotherapieDto.isMarquePresence());
            seanceRadiotherapie.setPrescriptionRadiotherapie(seanceRadiotherapieDto.getPrescriptionRadiotherapie() != null ? new PrescriptionRadiotherapie(seanceRadiotherapieDto.getPrescriptionRadiotherapie().getId()) : null);
            if (seanceRadiotherapieDto.getEtablissement() != null && seanceRadiotherapieDto.getEtablissement().getId() != null)
                seanceRadiotherapie.setEtablissement(new Etablissement(seanceRadiotherapieDto.getEtablissement().getId()));

        }

        return seanceRadiotherapie;
    }

    public SeanceRadiotherapieDto mappedCustomDto(SeanceRadiotherapieDto seanceRadiotherapieDto, String[] includes, String[] excludes) {

        if (StringUtil.isNoEmpty(includes) || StringUtil.isNoEmpty(excludes)) {
            if (StringUtil.isNoEmpty(includes))
                RefelexivityUtil.copyIncludesProperties(seanceRadiotherapieDto, this, includes);
            if (StringUtil.isNoEmpty(excludes))
                RefelexivityUtil.copyExcludesProperties(seanceRadiotherapieDto, this, excludes);

            this.id = seanceRadiotherapieDto.getId();

            return this;
        }

        return seanceRadiotherapieDto;
    }

    public SeanceRadiotherapieDto convertToDto(SeanceRadiotherapieDto seanceRadiotherapieDto, SeanceRadiotherapie seanceRadiotherapie, boolean collections, int level) {

        level++;
        if (seanceRadiotherapieDto != null && level <= maxLevel) {
            seanceRadiotherapieDto = convertIdToDto(seanceRadiotherapieDto, seanceRadiotherapie);
            seanceRadiotherapieDto.setLabel(seanceRadiotherapie.getLabel());
            seanceRadiotherapieDto.setDateDebut(DateUtil.dateTimeToString(seanceRadiotherapie.getDateDebut()));
            seanceRadiotherapieDto.setDateFin(DateUtil.dateTimeToString(seanceRadiotherapie.getDateFin()));
            seanceRadiotherapieDto.setMarquePresence(seanceRadiotherapie.isMarquePresence());

            seanceRadiotherapieDto.setCreatedBy(seanceRadiotherapie.getCreatedBy());
            seanceRadiotherapieDto.setCreatedOn(DateUtil.dateTimeToString(seanceRadiotherapie.getCreatedOn()));
            seanceRadiotherapieDto.setUpdatedBy(seanceRadiotherapie.getUpdatedBy());
            seanceRadiotherapieDto.setUpdatedOn(DateUtil.dateTimeToString(seanceRadiotherapie.getUpdatedOn()));

            //	seanceRadiotherapieDto.setPrescriptionRadiotherapie(seanceRadiotherapie.getPrescriptionRadiotherapie() != null ? new PrescriptionRadiotherapieDto(seanceRadiotherapie.getPrescriptionRadiotherapie(), false, level) : null);
            seanceRadiotherapieDto.setEtablissement(seanceRadiotherapie.getEtablissement() != null ? new EtablissementDto(seanceRadiotherapie.getEtablissement(), false, level) : null);
            if (collections) {
            }

        }

        return seanceRadiotherapieDto;
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
