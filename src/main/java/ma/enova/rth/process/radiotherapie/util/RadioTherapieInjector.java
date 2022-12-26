package ma.enova.rth.process.radiotherapie.util;

import ma.enova.rth.process.radiotherapie.prepare.RadiotherapiePrepareProcess;
import ma.enova.rth.process.radiotherapie.prepare.RadiotherapiePrepareProcessImpl;
import ma.enova.rth.process.radiotherapie.save.RadiotherapieSaveProcess;
import ma.enova.rth.process.radiotherapie.save.RadiotherapieSaveProcessImpl;
import ma.enova.rth.service.facade.IFrequenceRadiotherapieService;
import ma.enova.rth.service.facade.IPrescriptionRadiotherapieService;
import ma.enova.rth.service.facade.ISeanceRadiotherapieService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RadioTherapieInjector {

    @Bean
    public RadiotherapiePrepareProcess radiotherapiePrepareProcess(IPrescriptionRadiotherapieService service,IFrequenceRadiotherapieService frequenceRadiotherapieService) {
        return new RadiotherapiePrepareProcessImpl(service, frequenceRadiotherapieService);
    }

    @Bean
    public RadiotherapieSaveProcess radiotherapieSaveProcess(IPrescriptionRadiotherapieService service, ISeanceRadiotherapieService seanceRadiotherapieService, IFrequenceRadiotherapieService frequenceRadiotherapieService) {
        return new RadiotherapieSaveProcessImpl(service, seanceRadiotherapieService, frequenceRadiotherapieService);
    }
}
