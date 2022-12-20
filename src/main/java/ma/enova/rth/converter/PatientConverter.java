package ma.enova.rth.converter;

import ma.enova.rth.common.util.DateUtil;
import ma.enova.rth.common.util.Utils;
import ma.enova.rth.domain.core.Patient;
import ma.enova.rth.domain.historique.HistPatient;
import ma.enova.rth.dto.PatientDto;
import org.springframework.stereotype.Component;


@Component
public class PatientConverter extends AbstractConverter<Patient, PatientDto, HistPatient> {


    public PatientConverter() {
        super(Patient.class, PatientDto.class, HistPatient.class);
    }

    public Patient toItem(PatientDto dto) {
        Patient item = null;
        if (dto != null) {
            item = new Patient(dto.getId());
            item.setIpp(dto.getIpp());
            item.setNom(dto.getNom());
            item.setPrenom(dto.getPrenom());
            item.setSexe(dto.getSexe());
            item.setDateNaissance(DateUtil.stringToDate(dto.getDateNaissance()));
           /* if (dto.getEtablissement() != null && dto.getEtablissement().getId() != null)
                item.setEtablissement(new Etablissement(dto.getEtablissement().getId()));
            */
        }
        return item;
    }

    public PatientDto toDto(Patient item) {
        PatientDto dto = null;
        if (item != null) {
            dto.setLabel(item.getLabel());
            dto.setIpp(item.getIpp());
            dto.setNom(item.getNom());
            dto.setPrenom(item.getPrenom());
            dto.setSexe(item.getSexe());
            dto.setDateNaissance(DateUtil.dateToString(item.getDateNaissance()));

            copyToDto(item, dto);
            //  dto.setEtablissement(item.getEtablissement() != null ? new EtablissementDto(item.getEtablissement(), false, level) : null);
        }
        return dto;

    }

}