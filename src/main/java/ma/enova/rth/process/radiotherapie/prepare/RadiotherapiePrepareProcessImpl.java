package ma.enova.rth.process.radiotherapie.prepare;

import ma.enova.rth.zynerator.process.AbstractProcessImpl;
import ma.enova.rth.zynerator.process.Result;
import ma.enova.rth.zynerator.util.DateUtil;
import ma.enova.rth.dto.FrequenceRadiotherapieDto;
import ma.enova.rth.dto.SeanceRadiotherapieDto;
import ma.enova.rth.process.radiotherapie.util.RadiotherapieValidator;
import ma.enova.rth.service.facade.IFrequenceRadiotherapieService;
import ma.enova.rth.service.facade.IPrescriptionRadiotherapieService;

import java.util.ArrayList;

public class RadiotherapiePrepareProcessImpl extends AbstractProcessImpl<RadiotherapiePrepareInput, RadiotherapiePrepareOutput> implements RadiotherapiePrepareProcess {

    @Override
    public void init(RadiotherapiePrepareInput input, RadiotherapiePrepareOutput output) {
        output.setSeanceRadiotherapies(new ArrayList<>());
        if (input.getFrequenceRadiotherapie() != null) {
            try {
                input.setFrequenceRadiotherapie(frequenceRadiotherapieService.findById(input.getFrequenceRadiotherapie().getId()));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void validate(RadiotherapiePrepareInput input, RadiotherapiePrepareOutput output, Result<RadiotherapiePrepareInput, RadiotherapiePrepareOutput> result) {
        RadiotherapieValidator.validateDateTraitement(input.getDateSouhaiteDebutTraitement(), result);
        RadiotherapieValidator.validateFrequenceRadio(input.getFrequenceRadiotherapie(), result);
        RadiotherapieValidator.validateFraction(input.getFractionnement(), result);
    }

    @Override
    public void run(RadiotherapiePrepareInput input, RadiotherapiePrepareOutput output, Result<RadiotherapiePrepareInput, RadiotherapiePrepareOutput> result) {
        FrequenceRadiotherapieDto frequenceRadiotherapie = input.getFrequenceRadiotherapie();
        for (int i = 0; i < input.getFractionnement(); i++) {
            SeanceRadiotherapieDto seanceRadiotherapieDto = new SeanceRadiotherapieDto();
            seanceRadiotherapieDto.setDateDebut(DateUtil.addFrequence(input.getDateSouhaiteDebutTraitement(), i, frequenceRadiotherapie.getCode()));
            output.getSeanceRadiotherapies().add(seanceRadiotherapieDto);
        }
        result.addInfoMessage("success.radiotherapie.prepare.sceance.created");
    }
    private IPrescriptionRadiotherapieService service;

    private IFrequenceRadiotherapieService frequenceRadiotherapieService;
    public RadiotherapiePrepareProcessImpl(IPrescriptionRadiotherapieService service, IFrequenceRadiotherapieService frequenceRadiotherapieService) {
        this.service = service;
        this.frequenceRadiotherapieService = frequenceRadiotherapieService;
    }

}
