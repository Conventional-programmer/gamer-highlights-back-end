package nl.fhict.s6.serviceauthentication.service;

import nl.fhict.s6.libraryrest.service.CrudService;
import nl.fhict.s6.serviceauthentication.datamodels.UserDao;
import nl.fhict.s6.serviceauthentication.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public UserDao save(UserDao userDao)
    {
        return userRepository.save(userDao);
    }

    public boolean checkUserExistsByUserId(Long userId)
    {
        return userRepository.existsById(userId);
    }

    public boolean checkUserExistByUsername(String username)
    {
        return userRepository.existsByUsername(username);
    }

    public boolean checkUserExistByEmail(String email)
    {
        return userRepository.existsByEmail(email);
    }
}
