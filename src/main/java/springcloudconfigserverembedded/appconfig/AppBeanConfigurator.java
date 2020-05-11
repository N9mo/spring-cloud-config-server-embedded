package springcloudconfigserverembedded.appconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * Spring bean configuration class
 */
@Configuration
public class AppBeanConfigurator {


    /**
     * The Service for running tasks after some predefined delay and/or periodically.
     * @return
     */
    @Bean
    public ScheduledExecutorService scheduledExecutorService(){
        return Executors.newScheduledThreadPool(10);
    }
}