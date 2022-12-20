package ma.enova.rth.converter;

import ma.enova.rth.domain.core.ProtocoleInclusion;
import ma.enova.rth.domain.historique.HistProtocoleInclusion;
import ma.enova.rth.dto.ProtocoleInclusionDto;
import org.springframework.stereotype.Component;


@Component
public class ProtocoleInclusionConverter extends AbstractConverter<ProtocoleInclusion, ProtocoleInclusionDto, HistProtocoleInclusion> {


    public ProtocoleInclusionConverter() {
        super(ProtocoleInclusion.class, ProtocoleInclusionDto.class, HistProtocoleInclusion.class);
    }

    public ProtocoleInclusion toItem(ProtocoleInclusionDto dto) {
        ProtocoleInclusion item = null;
        if (dto != null) {
            item = new ProtocoleInclusion(dto.getId());
            item.setCode(dto.getCode());
            item.setLibelle(dto.getLibelle());
            item.setStatus(dto.getStatus());
           /* if (dto.getEtablissement() != null && dto.getEtablissement().getId() != null)
                item.setEtablissement(new Etablissement(dto.getEtablissement().getId()));
            */
        }
        return item;
    }

    public ProtocoleInclusionDto toDto(ProtocoleInclusion item) {
        ProtocoleInclusionDto dto = null;
        if (item != null) {
            dto = new ProtocoleInclusionDto(item.getId());
            dto.setLabel(item.getLabel());
            dto.setCode(item.getCode());
            dto.setLibelle(item.getLibelle());
            dto.setStatus(item.getStatus());
            copyToDto(item, dto);
            //  dto.setEtablissement(item.getEtablissement() != null ? new EtablissementDto(item.getEtablissement(), false, level) : null);
        }
        return dto;

    }

}