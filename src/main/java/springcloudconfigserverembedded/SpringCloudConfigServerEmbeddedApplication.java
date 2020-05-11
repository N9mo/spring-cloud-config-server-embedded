package springcloudconfigserverembedded;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import springcloudconfigserverembedded.service.PrintService;

import javax.annotation.PostConstruct;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


/**
 * Main application class
 *
 * @author Yury Zuzansky
 * @since 1.0
 */
@SpringBootApplication
@RefreshScope
public class SpringCloudConfigServerEmbeddedApplication {

	@Autowired
	ScheduledExecutorService scheduledExecutorService;
	@Autowired
	PrintService beatService;

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudConfigServerEmbeddedApplication.class, args);
	}

	@PostConstruct
	void startDemo() {
		Runnable pinger = () -> { beatService.printProperties(); };
		scheduledExecutorService.scheduleAtFixedRate(pinger, 1, 1, TimeUnit.SECONDS);
	}
}
