package nl.fhict.s6.servicecomment.service;

import nl.fhict.s6.libraryrest.service.CrudService;
import nl.fhict.s6.servicecomment.datamodels.UserDao;
import nl.fhict.s6.servicecomment.repository.UserRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public class UserService extends CrudService<UserDao> {
    private UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        super(userRepository);
    }
}
