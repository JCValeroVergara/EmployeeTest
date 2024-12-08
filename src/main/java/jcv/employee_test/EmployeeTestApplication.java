package jcv.employee_test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication(scanBasePackages = "com.soap")
@EnableAutoConfiguration
public class EmployeeTestApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeTestApplication.class);
	public static void main(String[] args) {

		SpringApplication.run(EmployeeTestApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void logServicesStarted() {
		LOGGER.info("La aplicación Spring Boot ha iniciado correctamente.");
		LOGGER.info("Servicios REST y SOAP deberían estar funcionando ahora.");
	}

}
