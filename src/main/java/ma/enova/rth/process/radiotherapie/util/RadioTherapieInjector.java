package ma.enova.rth.process.radiotherapie.util;

import ma.enova.rth.process.radiotherapie.prepare.RadiotherapiePrepareProcess;
import ma.enova.rth.process.radiotherapie.prepare.RadiotherapiePrepareProcessImpl;
import ma.enova.rth.process.radiotherapie.save.RadiotherapieSaveProcess;
import ma.enova.rth.process.radiotherapie.save.RadiotherapieSaveProcessImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RadioTherapieInjector {

    @Bean
    public RadiotherapiePrepareProcess radiotherapiePrepareProcess() {
        return new RadiotherapiePrepareProcessImpl();
    }

    @Bean
    public RadiotherapieSaveProcess radiotherapieSaveProcess() {
        return new RadiotherapieSaveProcessImpl();
    }
}
