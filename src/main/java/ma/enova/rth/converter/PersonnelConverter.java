package ma.enova.rth.converter;

import ma.enova.rth.domain.core.Personnel;
import ma.enova.rth.domain.historique.HistPersonnel;
import ma.enova.rth.dto.PersonnelDto;
import org.springframework.stereotype.Component;


@Component
public class PersonnelConverter extends AbstractConverter<Personnel, PersonnelDto, HistPersonnel> {


    public PersonnelConverter() {
        super(Personnel.class, PersonnelDto.class, HistPersonnel.class);
    }

    public Personnel toItem(PersonnelDto dto) {
        Personnel item = null;
        if (dto != null) {
            item = new Personnel(dto.getId());
            dto.setLabel(item.getLabel());
            dto.setNom(item.getNom());
            dto.setPrenom(item.getPrenom());
            dto.setCin(item.getCin());
            dto.setAdresse(item.getAdresse());
            dto.setEmail(item.getEmail());
            dto.setTelephone(item.getTelephone());
            dto.setMobile(item.getMobile());
            dto.setExpertise(item.isExpertise());
           /* if (dto.getEtablissement() != null && dto.getEtablissement().getId() != null)
                item.setEtablissement(new Etablissement(dto.getEtablissement().getId()));
            */
        }
        return item;
    }

    public PersonnelDto toDto(Personnel item) {
        PersonnelDto dto = null;
        if (item != null) {
            dto = new PersonnelDto(item.getId());
            item.setNom(dto.getNom());
            item.setPrenom(dto.getPrenom());
            item.setCin(dto.getCin());
            item.setAdresse(dto.getAdresse());
            item.setEmail(dto.getEmail());
            item.setTelephone(dto.getTelephone());
            item.setMobile(dto.getMobile());
            item.setExpertise(dto.isExpertise());

            copyToDto(item, dto);
            //  dto.setEtablissement(item.getEtablissement() != null ? new EtablissementDto(item.getEtablissement(), false, level) : null);
        }
        return dto;

    }

}