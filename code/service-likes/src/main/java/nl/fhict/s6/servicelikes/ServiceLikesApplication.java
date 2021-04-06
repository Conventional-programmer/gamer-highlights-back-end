package nl.fhict.s6.servicelikes;

import nl.fhict.s6.servicelikes.config.generation.LikeDaoGeneration;
import nl.fhict.s6.servicelikes.repository.LikeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;

import java.util.Arrays;

@SpringBootApplication
public class ServiceLikesApplication {

	private Environment environment;

	public ServiceLikesApplication(Environment environment) {
		this.environment = environment;
	}

	public static void main(String[] args) {
		SpringApplication.run(ServiceLikesApplication.class, args);
	}

	@Bean
	@Profile("dev")
	public CommandLineRunner demo(LikeRepository likeRepository)
	{
		if (Arrays.asList(environment.getActiveProfiles()).contains("dev")) {
			return (args) -> {
				likeRepository.saveAll(new LikeDaoGeneration().generateLikeDaos());
			};
		}
		else {
			return null;
		}
	}
}
