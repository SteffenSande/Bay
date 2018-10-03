package rest;

import org.glassfish.jersey.logging.LoggingFeature;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;
import java.util.logging.Level;
import java.util.logging.Logger;

@ApplicationPath("rest")
public class JaxRsConfiguration extends ResourceConfig {

    public JaxRsConfiguration() {
        super();
        packages(RestService.class.getPackage().getName());
        register(new LoggingFeature(Logger.getLogger(LoggingFeature.DEFAULT_LOGGER_NAME), Level.INFO, LoggingFeature.Verbosity.PAYLOAD_ANY, 8192));
        register(DebugMapper.class);
    }
}
