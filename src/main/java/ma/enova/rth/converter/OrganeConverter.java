package ma.enova.rth.converter;

import ma.enova.rth.zynerator.converter.AbstractConverter;
import ma.enova.rth.bean.core.Organe;
import ma.enova.rth.bean.historique.HistOrgane;
import ma.enova.rth.dto.OrganeDto;
import org.springframework.stereotype.Component;


@Component
public class OrganeConverter extends AbstractConverter<Organe, OrganeDto, HistOrgane> {


    public OrganeConverter() {
        super(Organe.class, OrganeDto.class, HistOrgane.class);
    }

    public Organe toItem(OrganeDto dto) {
        Organe item = null;
        if (dto != null) {
            item = new Organe(dto.getId());
            item.setCode(dto.getCode());
            item.setLibelle(dto.getLibelle());
            convertEtablissement(item, dto);

        }
        return item;
    }

    public OrganeDto toDto(Organe item) {
        OrganeDto dto = null;
        if (item != null) {
            dto = new OrganeDto(item.getId());
            dto.setLabel(item.getLabel());
            dto.setCode(item.getCode());
            dto.setLibelle(item.getLibelle());
            copyToDto(item, dto);
            convertEtablissement(dto, item);
        }
        return dto;

    }

}