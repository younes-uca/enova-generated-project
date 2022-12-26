package ma.enova.rth.process.radiotherapie.save;

import ma.enova.rth.zynerator.process.AbstractProcessImpl;
import ma.enova.rth.zynerator.process.Result;
import ma.enova.rth.zynerator.util.ListUtil;
import ma.enova.rth.dto.PatientDto;
import ma.enova.rth.dto.PrescriptionRadiotherapieDto;
import ma.enova.rth.dto.SeanceRadiotherapieDto;
import ma.enova.rth.process.radiotherapie.util.RadiotherapieValidator;
import ma.enova.rth.service.facade.IFrequenceRadiotherapieService;
import ma.enova.rth.service.facade.IPrescriptionRadiotherapieService;
import ma.enova.rth.service.facade.ISeanceRadiotherapieService;

import java.util.List;

public class RadiotherapieSaveProcessImpl extends AbstractProcessImpl<RadiotherapieSaveInput, RadiotherapieSaveOutput> implements RadiotherapieSaveProcess {

    @Override
    public void init(RadiotherapieSaveInput input, RadiotherapieSaveOutput output) {
        if (input.getFrequenceRadiotherapie() != null) {
            try {
                input.setFrequenceRadiotherapie(frequenceRadiotherapieService.findById(input.getFrequenceRadiotherapie().getId()));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void validate(RadiotherapieSaveInput input, RadiotherapieSaveOutput output, Result<RadiotherapieSaveInput, RadiotherapieSaveOutput> result) {
        RadiotherapieValidator.validateDateTraitement(input.getDateSouhaiteDebutTraitement(), result);
        RadiotherapieValidator.validateFrequenceRadio(input.getFrequenceRadiotherapie(), result);
        RadiotherapieValidator.validateFraction(input.getFractionnement(), result);
        //   validatePatient(input.getPatient(), result);
        validateSceance(input.getSeanceRadiotherapies(), result);
    }

    @Override
    public void run(RadiotherapieSaveInput input, RadiotherapieSaveOutput output, Result<RadiotherapieSaveInput, RadiotherapieSaveOutput> result) {
        try {
            PrescriptionRadiotherapieDto dto = constructDto(input, PrescriptionRadiotherapieDto.class);
            service.create(dto);
            for (SeanceRadiotherapieDto seanceRadiotherapy : input.getSeanceRadiotherapies()) {
                seanceRadiotherapy.setPrescriptionRadiotherapie(dto);
                seanceRadiotherapieService.create(seanceRadiotherapy);
            }
            result.addInfoMessage("success.radiotherapie.save.radiotherapie.saved");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    private void validatePatient(PatientDto patient, Result result) {
        if (patient == null || patient.getIpp() == null) {
            result.addErrorMessage("error.radiotherapie.save.patient.ipp.obligatoire");
        }
    }


    private void validateSceance(List<SeanceRadiotherapieDto> seanceRadiotherapies, Result result) {
        if (ListUtil.isEmpty(seanceRadiotherapies)) {
            result.addErrorMessage("error.radiotherapie.save.seanceRadiotherapies.obligatoire");
        }
    }

    private ISeanceRadiotherapieService seanceRadiotherapieService;
    private IPrescriptionRadiotherapieService service;
    private IFrequenceRadiotherapieService frequenceRadiotherapieService;

    public RadiotherapieSaveProcessImpl(IPrescriptionRadiotherapieService service, ISeanceRadiotherapieService seanceRadiotherapieService, IFrequenceRadiotherapieService frequenceRadiotherapieService) {
        this.seanceRadiotherapieService = seanceRadiotherapieService;
        this.service = service;
        this.frequenceRadiotherapieService = frequenceRadiotherapieService;
    }

}
