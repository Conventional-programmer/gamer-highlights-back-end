package nl.fhict.s6.serviceauthentication;

import nl.fhict.s6.serviceauthentication.config.generation.RoleDaoGeneration;
import nl.fhict.s6.serviceauthentication.config.generation.UserDaoGeneration;
import nl.fhict.s6.serviceauthentication.datamodels.RoleDao;
import nl.fhict.s6.serviceauthentication.datamodels.UserDao;
import nl.fhict.s6.serviceauthentication.repository.RoleRepository;
import nl.fhict.s6.serviceauthentication.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class ServiceAuthenticationApplication {
	private Environment environment;

	public ServiceAuthenticationApplication(Environment environment) {
		this.environment = environment;
	}

	public static void main(String[] args) {
		SpringApplication.run(ServiceAuthenticationApplication.class, args);
	}

	@Bean
	@Profile("dev")
	public CommandLineRunner demo(UserRepository userRepository, RoleRepository roleRepository)
	{
		if (Arrays.asList(environment.getActiveProfiles()).contains("dev")) {
			return args -> {
				List<UserDao> userDaos = new UserDaoGeneration().generateUserDaos();
				userRepository.saveAll(userDaos);
				List<RoleDao> roleDaos = new RoleDaoGeneration().generateRoles();
				roleRepository.saveAll(roleDaos);
			};
		}
		else {
			return null;
		}
	}

}
