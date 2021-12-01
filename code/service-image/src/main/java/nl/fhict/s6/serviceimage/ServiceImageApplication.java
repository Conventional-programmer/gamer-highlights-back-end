package nl.fhict.s6.serviceimage;

import nl.fhict.s6.libraryrest.authentication.http.SecurityLoader;
import nl.fhict.s6.serviceimage.config.generation.ImageJpaGeneration;
import nl.fhict.s6.serviceimage.config.generation.ImageMongoGeneration;
import nl.fhict.s6.serviceimage.repository.ImageJpaRepository;
import nl.fhict.s6.serviceimage.repository.ImageMongoRepository;
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
public class ServiceImageApplication {
	private Environment environment;

	public ServiceImageApplication(Environment environment) {
		this.environment = environment;
	}

	public static void main(String[] args) {
		SpringApplication.run(ServiceImageApplication.class, args);
	}

	@Bean
	@Profile("dev")
	public CommandLineRunner dev(ImageJpaRepository imageJpaRepository)
	{
		if (Arrays.asList(environment.getActiveProfiles()).contains("dev")) {
			return (args) -> {
				imageJpaRepository.saveAll(new ImageJpaGeneration().generateImages());
			};
		}
		else {
			return null;
		}
	}
	@Bean
	@Profile("prod")
	public CommandLineRunner prod(ImageMongoRepository imageMongoRepository)
	{
		if (Arrays.asList(environment.getActiveProfiles()).contains("prod")) {
			return (args) -> {
				if(imageMongoRepository.count() <= 0)
				{
					imageMongoRepository.saveAll(new ImageMongoGeneration().generateImages());
				}
			};
		}
		else {
			return null;
		}
	}

}
