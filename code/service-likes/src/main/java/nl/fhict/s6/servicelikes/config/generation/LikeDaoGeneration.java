package nl.fhict.s6.servicelikes.config.generation;

import nl.fhict.s6.servicelikes.datamodels.LikeDao;

import java.util.ArrayList;
import java.util.List;

public class LikeDaoGeneration {
    public List<LikeDao> generateLikeDaos()
    {
        List<LikeDao> likeDaos = new ArrayList<>();
        likeDaos.add(new LikeDao(1L,1533));
        return likeDaos;
    }
}
