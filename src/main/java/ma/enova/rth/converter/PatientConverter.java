package ma.enova.rth.converter;

import ma.enova.rth.zynerator.converter.AbstractConverter;
import ma.enova.rth.zynerator.util.DateUtil;
import ma.enova.rth.bean.core.Patient;
import ma.enova.rth.bean.historique.HistPatient;
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
            convertEtablissement(item, dto);

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
            convertEtablissement(dto, item);
        }
        return dto;

    }

}