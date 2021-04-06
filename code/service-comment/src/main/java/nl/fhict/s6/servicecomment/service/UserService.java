package nl.fhict.s6.servicecomment.service;

import nl.fhict.s6.libraryrest.service.CrudService;
import nl.fhict.s6.servicecomment.datamodels.UserDao;
import nl.fhict.s6.servicecomment.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService extends CrudService<UserDao> {
    private UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository) {
        super(userRepository);
        this.userRepository = userRepository;
    }
}
