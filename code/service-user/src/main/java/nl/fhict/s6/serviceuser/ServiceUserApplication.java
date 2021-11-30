package nl.fhict.s6.serviceuser;

import nl.fhict.s6.libraryrest.authentication.http.SecurityLoader;
import nl.fhict.s6.serviceuser.config.generation.UserGeneration;
import nl.fhict.s6.serviceuser.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;

import java.util.Arrays;

@SpringBootApplication
@Import({SecurityLoader.class})
public class ServiceUserApplication {

	private Environment environment;

	public ServiceUserApplication(Environment environment) {
		this.environment = environment;
	}

	public static void main(String[] args) {
		SpringApplication.run(ServiceUserApplication.class, args);
	}

	@Bean
	@Profile("dev")
	public CommandLineRunner demo(UserRepository userRepository)
	{
		if (Arrays.asList(environment.getActiveProfiles()).contains("dev")) {
			return (args) -> {
				userRepository.saveAll(new UserGeneration().generateUsers());
			};
		}
		else {
			return null;
		}
	}
}
