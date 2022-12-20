package ma.enova.rth.converter;

import ma.enova.rth.common.util.DateUtil;
import ma.enova.rth.domain.core.SeanceRadiotherapie;
import ma.enova.rth.domain.historique.HistSeanceRadiotherapie;
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
            convertEtablissement(item, dto);
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
            convertEtablissement(dto, item);
        }
        return dto;

    }

}