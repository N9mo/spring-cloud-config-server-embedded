package springcloudconfigserverembedded.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

/**
 * Demo class for printing actual properties value from remote storage.
 *
 * We can use @RefreshScope for configuration classes or beans.
 * As a result, the default scope will be refresh instead of singleton.
 * Using @RefreshScope, Spring will clear its internal cache of these components on an EnvironmentChangeEvent.
 * Then, on the next access to the bean, a new instance is created.
 *
 * @author Yury Zuzansky
 * @since 1.0
 */
@Service
@RefreshScope
public class PrintService {

    @Value("${test.string}")
    String propMessage;

    /**
     * Prints actual properties value from remote storage to console.
     */
    public void printProperties() {
        System.out.println(propMessage);
    }
}
