package nl.fhict.s6.serviceimage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class ServiceImageApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceImageApplication.class, args);
	}



}
