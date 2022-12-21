package ma.enova.rth.converter;

import ma.enova.rth.common.util.DateUtil;
import ma.enova.rth.domain.core.PrescriptionRadiotherapie;
import ma.enova.rth.domain.historique.HistPrescriptionRadiotherapie;
import ma.enova.rth.dto.PrescriptionRadiotherapieDto;
import ma.enova.rth.common.ddd.converter.AbstractConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class PrescriptionRadiotherapieConverter extends AbstractConverter<PrescriptionRadiotherapie, PrescriptionRadiotherapieDto, HistPrescriptionRadiotherapie> {


    @Autowired
    private FrequenceRadiotherapieConverter frequenceRadiotherapieConverter;
    @Autowired
    private ModaliteRadiotherapieConverter modaliteRadiotherapieConverter;
    @Autowired
    private OrganeConverter organeConverter;
    @Autowired
    private PatientConverter patientConverter;
    @Autowired
    private PersonnelConverter personnelConverter;
    @Autowired
    private ProtocoleInclusionConverter protocoleInclusionConverter;
    @Autowired
    private ViseeConverter viseeConverter;

    public PrescriptionRadiotherapieConverter() {
        super(PrescriptionRadiotherapie.class, PrescriptionRadiotherapieDto.class, HistPrescriptionRadiotherapie.class);
    }

    @Override
    public PrescriptionRadiotherapie toItem(PrescriptionRadiotherapieDto dto) {
        PrescriptionRadiotherapie item = new PrescriptionRadiotherapie();
        if (dto != null) {
            item.setDatePrescription(DateUtil.stringToDateTime(dto.getDatePrescription()));
            item.setFractionnement(dto.getFractionnement());
            item.setDateSouhaiteDebutTraitement(DateUtil.stringToDateTime(dto.getDateSouhaiteDebutTraitement()));
            item.setObservation(dto.getObservation());
            item.setFrequenceRadiotherapie(frequenceRadiotherapieConverter.getById(dto.getFrequenceRadiotherapie()));
            item.setModaliteRadiotherapie(modaliteRadiotherapieConverter.getById(dto.getModaliteRadiotherapie()));
            item.setOrgane(organeConverter.getById(dto.getOrgane()));
            item.setPatient(patientConverter.getById(dto.getPatient()));
            item.setMedecinPrescripteur(personnelConverter.getById(dto.getMedecinPrescripteur()));
            item.setProtocoleInclusion(protocoleInclusionConverter.getById(dto.getProtocoleInclusion()));
            item.setVisee(viseeConverter.getById(dto.getVisee()));
            convertEtablissement(item, dto);

        }
        return item;
    }

    @Override
    public PrescriptionRadiotherapieDto toDto(PrescriptionRadiotherapie item) {
        PrescriptionRadiotherapieDto dto = new PrescriptionRadiotherapieDto();
        if (item != null) {
            dto = new PrescriptionRadiotherapieDto(item.getId());
            dto.setLabel(item.getLabel());
            dto.setDatePrescription(DateUtil.dateTimeToString(item.getDatePrescription()));
            dto.setFractionnement(item.getFractionnement());
            dto.setDateSouhaiteDebutTraitement(DateUtil.dateTimeToString(item.getDateSouhaiteDebutTraitement()));
            dto.setObservation(item.getObservation());

            copyToDto(item, dto);

            dto.setFrequenceRadiotherapie(frequenceRadiotherapieConverter.getById(item.getFrequenceRadiotherapie()));
            dto.setModaliteRadiotherapie(modaliteRadiotherapieConverter.getById(item.getModaliteRadiotherapie()));
            dto.setOrgane(organeConverter.getById(item.getOrgane()));
            dto.setPatient(patientConverter.getById(item.getPatient()));
            dto.setMedecinPrescripteur(personnelConverter.getById(item.getMedecinPrescripteur()));
            dto.setProtocoleInclusion(protocoleInclusionConverter.getById(item.getProtocoleInclusion()));
            dto.setVisee(viseeConverter.getById(item.getVisee()));
            convertEtablissement(dto, item);

        }

        return dto;
    }


}
