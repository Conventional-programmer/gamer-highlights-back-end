package nl.fhict.s6.servicelikes.service;

import nl.fhict.s6.libraryrest.service.CrudService;
import nl.fhict.s6.servicelikes.datamodels.UserDao;
import nl.fhict.s6.servicelikes.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService extends CrudService<UserDao> {
    private UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        super(userRepository);
        this.userRepository = userRepository;
    }
}
