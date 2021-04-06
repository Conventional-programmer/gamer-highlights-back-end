package nl.fhict.s6.serviceuser.config.generation;

import nl.fhict.s6.serviceuser.datamodels.UserDao;

import java.util.ArrayList;
import java.util.List;

public class UserGeneration {
    public List<UserDao> generateUsers()
    {
        List<UserDao> userDaos = new ArrayList<>();
        userDaos.add(new UserDao(1L,"Bert@gmail.com"));
        return userDaos;
    }
}
