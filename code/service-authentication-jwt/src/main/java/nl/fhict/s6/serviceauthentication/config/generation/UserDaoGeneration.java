package nl.fhict.s6.serviceauthentication.config.generation;

import nl.fhict.s6.serviceauthentication.datamodels.UserDao;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

public class UserDaoGeneration {
    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    public List<UserDao> generateUserDaos()
    {
        List<UserDao> userDaos = new ArrayList<>();
        userDaos.add(new UserDao(1L,"bert","bert@gmail.com",passwordEncoder.encode("Gert123")));
        userDaos.add(new UserDao(2L,"gert","gert@gmail.com",passwordEncoder.encode("Bert123")));
        return userDaos;
    }
}
