package nl.fhict.s6.servicepost.config.generation;

import nl.fhict.s6.servicepost.datamodels.PostDao;

import java.util.ArrayList;
import java.util.List;

public class PostGeneration {
    public List<PostDao> generatePostDaos()
    {
        List<PostDao> postDaos = new ArrayList<>();
        postDaos.add(new PostDao(1L,1L,"https://www.planetware.com/wpimages/2020/02/france-in-pictures-beautiful-places-to-photograph-eiffel-tower.jpg","bert"));
        return postDaos;
    }
}
