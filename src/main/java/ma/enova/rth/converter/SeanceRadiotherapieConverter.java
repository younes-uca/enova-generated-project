package ma.enova.rth.converter;

import ma.enova.rth.RadiotherapieApplication;
import ma.enova.rth.common.util.DateUtil;
import ma.enova.rth.common.util.Utils;
import ma.enova.rth.domain.core.Patient;
import ma.enova.rth.domain.core.PrescriptionRadiotherapie;
import ma.enova.rth.domain.core.SeanceRadiotherapie;
import ma.enova.rth.domain.historique.HistSeanceRadiotherapie;
import ma.enova.rth.dto.EtablissementDto;
import ma.enova.rth.dto.PatientDto;
import ma.enova.rth.dto.PrescriptionRadiotherapieDto;
import ma.enova.rth.dto.SeanceRadiotherapieDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class SeanceRadiotherapieConverter extends AbstractConverter<SeanceRadiotherapie, SeanceRadiotherapieDto, HistSeanceRadiotherapie> {

    @Autowired
    private PrescriptionRadiotherapieConverter prescriptionRadiotherapieConverter;

    public SeanceRadiotherapieConverter() {
        super(SeanceRadiotherapie.class, SeanceRadiotherapieDto.class, HistSeanceRadiotherapie.class);
    }

    public SeanceRadiotherapie toItem(SeanceRadiotherapieDto dto) {
        SeanceRadiotherapie item = null;
        if (dto != null) {
            item = new SeanceRadiotherapie(dto.getId());
            item.setDateDebut(DateUtil.stringToDateTime(dto.getDateDebut()));
            item.setDateFin(DateUtil.stringToDateTime(dto.getDateFin()));
            item.setMarquePresence(dto.isMarquePresence());
            item.setPrescriptionRadiotherapie(prescriptionRadiotherapieConverter.getById(dto.getPrescriptionRadiotherapie()));
	
           /* if (dto.getEtablissement() != null && dto.getEtablissement().getId() != null)
                item.setEtablissement(new Etablissement(dto.getEtablissement().getId()));
            */
        }
        return item;
    }

    public SeanceRadiotherapieDto toDto(SeanceRadiotherapie item) {
        SeanceRadiotherapieDto dto = null;
        if (item != null) {
            dto = new SeanceRadiotherapieDto(item.getId());
            dto.setLabel(item.getLabel());
            dto.setDateDebut(DateUtil.dateTimeToString(item.getDateDebut()));
            dto.setDateFin(DateUtil.dateTimeToString(item.getDateFin()));
            dto.setMarquePresence(item.isMarquePresence());
            dto.setPrescriptionRadiotherapie(prescriptionRadiotherapieConverter.getById(item.getPrescriptionRadiotherapie()));

            copyToDto(item, dto);
            //  dto.setEtablissement(item.getEtablissement() != null ? new EtablissementDto(item.getEtablissement(), false, level) : null);
        }
        return dto;

    }

}