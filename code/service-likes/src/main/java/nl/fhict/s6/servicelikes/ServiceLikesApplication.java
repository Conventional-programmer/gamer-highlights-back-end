package nl.fhict.s6.servicelikes;

import nl.fhict.s6.servicelikes.config.generation.LikeDaoGeneration;
import nl.fhict.s6.servicelikes.config.generation.UserDaoGeneration;
import nl.fhict.s6.servicelikes.datamodels.UserDao;
import nl.fhict.s6.servicelikes.repository.LikeRepository;
import nl.fhict.s6.servicelikes.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
	public CommandLineRunner demo(LikeRepository likeRepository, UserRepository userRepository)
	{
		if (Arrays.asList(environment.getActiveProfiles()).contains("dev")) {
			return (args) -> {
				List<UserDao> userDaos = new UserDaoGeneration().generateUserDaos();
				userRepository.saveAll(userDaos);
				likeRepository.saveAll(new LikeDaoGeneration().generateLikeDaos(userDaos));
			};
		}
		else {
			return null;
		}
	}
}
