package ma.enova.rth.converter;

import ma.enova.rth.domain.core.FrequenceRadiotherapie;
import ma.enova.rth.domain.historique.HistFrequenceRadiotherapie;
import ma.enova.rth.dto.FrequenceRadiotherapieDto;
import ma.enova.rth.common.ddd.converter.AbstractConverter;
import org.springframework.stereotype.Component;


@Component
public class FrequenceRadiotherapieConverter extends AbstractConverter<FrequenceRadiotherapie, FrequenceRadiotherapieDto, HistFrequenceRadiotherapie> {


    public FrequenceRadiotherapieConverter() {
        super(FrequenceRadiotherapie.class, FrequenceRadiotherapieDto.class, HistFrequenceRadiotherapie.class);
    }

    public FrequenceRadiotherapie toItem(FrequenceRadiotherapieDto dto) {
        FrequenceRadiotherapie item = null;
        if (dto != null) {
            item = new FrequenceRadiotherapie(dto.getId());
            item.setCode(dto.getCode());
            item.setLibelle(dto.getLibelle());
            item.setActif(dto.isActif());
            item.setHl7(dto.getHl7());
            item.setOrdre(dto.getOrdre());
            convertEtablissement(item, dto);

        }
        return item;
    }

    public FrequenceRadiotherapieDto toDto(FrequenceRadiotherapie item) {
        FrequenceRadiotherapieDto dto = null;
        if (item != null) {
            dto = new FrequenceRadiotherapieDto(item.getId());
            dto.setLabel(item.getLabel());
            dto.setCode(item.getCode());
            dto.setLibelle(item.getLibelle());
            dto.setActif(item.isActif());
            dto.setHl7(item.getHl7());
            dto.setOrdre(item.getOrdre());
            copyToDto(item, dto);
            convertEtablissement(dto, item);

        }
        return dto;

    }

}