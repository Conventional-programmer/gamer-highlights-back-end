package nl.fhict.s6.servicecomment.config.generation;

import nl.fhict.s6.servicecomment.datamodels.UserDao;

import java.util.ArrayList;
import java.util.List;

public class UserDaoGeneration {
    public List<UserDao> generateUserDaos()
    {
        List<UserDao> userDaos = new ArrayList<>();
        userDaos.add(new UserDao(1L,"bert"));
        userDaos.add(new UserDao(2L,"gert"));
        return userDaos;
    }
}
