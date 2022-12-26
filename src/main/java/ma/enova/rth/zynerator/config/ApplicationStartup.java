package ma.enova.rth.zynerator.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {

    /**
     * This event is executed as late as conceivably possible to indicate that
     * the application is ready to service requests.
     */

    @Value("${server.port}")
    private String port;

    @Value("${server.servlet.context-path}")
    private String appName;

    @Override
    public void onApplicationEvent(final ApplicationReadyEvent event) {

        System.out.println("Starting " + appName + " on port :" + port);

    }
}