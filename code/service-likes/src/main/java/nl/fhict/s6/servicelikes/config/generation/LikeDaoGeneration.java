package nl.fhict.s6.servicelikes.config.generation;

import nl.fhict.s6.servicelikes.datamodels.LikeDao;
import nl.fhict.s6.servicelikes.datamodels.UserDao;

import java.util.ArrayList;
import java.util.List;

public class LikeDaoGeneration {
    public List<LikeDao> generateLikeDaos(List<UserDao> userDaos)
    {
        List<LikeDao> likeDaos = new ArrayList<>();
        likeDaos.add(new LikeDao(1L,userDaos.get(0)));
        return likeDaos;
    }
}
