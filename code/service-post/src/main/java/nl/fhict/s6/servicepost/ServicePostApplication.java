package nl.fhict.s6.servicepost;

import nl.fhict.s6.libraryrest.authentication.http.SecurityLoader;
import nl.fhict.s6.servicepost.config.generation.PostGeneration;
import nl.fhict.s6.servicepost.repository.PostRepository;
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
public class ServicePostApplication {
	private Environment environment;

	public ServicePostApplication(Environment environment) {
		this.environment = environment;
	}

	public static void main(String[] args) {
		SpringApplication.run(ServicePostApplication.class, args);
	}

	@Bean
	@Profile("dev")
	public CommandLineRunner demo(PostRepository postRepository)
	{
		if (Arrays.asList(environment.getActiveProfiles()).contains("dev")) {
			return (args) -> {
				postRepository.saveAll(new PostGeneration().generatePostDaos());
			};
		}
		else {
			return null;
		}
	}

}
