package ma.enova.rth.converter;

import ma.enova.rth.domain.core.Visee;
import ma.enova.rth.domain.historique.HistVisee;
import ma.enova.rth.dto.ViseeDto;
import org.springframework.stereotype.Component;


@Component
public class ViseeConverter extends AbstractConverter<Visee, ViseeDto, HistVisee> {


    public ViseeConverter() {
        super(Visee.class, ViseeDto.class, HistVisee.class);
    }

    public Visee toItem(ViseeDto dto) {
        Visee item = null;
        if (dto != null) {
            item = new Visee(dto.getId());
            item.setCode(dto.getCode());
            item.setLibelle(dto.getLibelle());
            item.setActif(dto.isActif());
            item.setHl7(dto.getHl7());
            item.setOrdre(dto.getOrdre());
            convertEtablissement(item, dto);

        }
        return item;
    }


    public ViseeDto toDto(Visee item) {
        ViseeDto dto = null;
        if (item != null) {
            dto = new ViseeDto(item.getId());
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