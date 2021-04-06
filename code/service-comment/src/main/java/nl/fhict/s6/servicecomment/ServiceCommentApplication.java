package nl.fhict.s6.servicecomment;

import nl.fhict.s6.servicecomment.config.generation.CommentDaoGeneration;
import nl.fhict.s6.servicecomment.config.generation.UserDaoGeneration;
import nl.fhict.s6.servicecomment.datamodels.UserDao;
import nl.fhict.s6.servicecomment.repository.CommentRepository;
import nl.fhict.s6.servicecomment.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class ServiceCommentApplication {
	private Environment environment;

	public ServiceCommentApplication(Environment environment) {
		this.environment = environment;
	}

	public static void main(String[] args) {
		SpringApplication.run(ServiceCommentApplication.class, args);
	}

	@Bean
	@Profile("dev")
	public CommandLineRunner demo(CommentRepository commentRepository, UserRepository userRepository)
	{
		if (Arrays.asList(environment.getActiveProfiles()).contains("dev")) {
			return (args) -> {
				List<UserDao> userDaos = new UserDaoGeneration().generateUserDaos();
				userRepository.saveAll(userDaos);
				commentRepository.saveAll(new CommentDaoGeneration().generateCommentDaos(userDaos));
			};
		}
		else {
			return null;
		}
	}

}
