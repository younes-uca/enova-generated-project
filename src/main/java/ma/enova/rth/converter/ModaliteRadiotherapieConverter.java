package ma.enova.rth.converter;

import ma.enova.rth.domain.core.ModaliteRadiotherapie;
import ma.enova.rth.domain.historique.HistModaliteRadiotherapie;
import ma.enova.rth.dto.ModaliteRadiotherapieDto;
import ma.enova.rth.common.ddd.converter.AbstractConverter;
import org.springframework.stereotype.Component;


@Component
public class ModaliteRadiotherapieConverter extends AbstractConverter<ModaliteRadiotherapie, ModaliteRadiotherapieDto, HistModaliteRadiotherapie> {


    public ModaliteRadiotherapieConverter() {
        super(ModaliteRadiotherapie.class, ModaliteRadiotherapieDto.class, HistModaliteRadiotherapie.class);
    }

    public ModaliteRadiotherapie toItem(ModaliteRadiotherapieDto dto) {
        ModaliteRadiotherapie item = null;
        if (dto != null) {
            item = new ModaliteRadiotherapie(dto.getId());
            item.setCode(dto.getCode());
            item.setLibelle(dto.getLibelle());
            item.setActif(dto.isActif());
            item.setHl7(dto.getHl7());
            item.setOrdre(dto.getOrdre());
            convertEtablissement(item, dto);

        }
        return item;
    }

    public ModaliteRadiotherapieDto toDto(ModaliteRadiotherapie item) {
        ModaliteRadiotherapieDto dto = null;
        if (item != null) {
            dto = new ModaliteRadiotherapieDto(item.getId());
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