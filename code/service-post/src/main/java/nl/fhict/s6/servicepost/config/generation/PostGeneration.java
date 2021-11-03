package nl.fhict.s6.servicepost.config.generation;

import nl.fhict.s6.servicepost.datamodels.PostDao;

import java.util.ArrayList;
import java.util.List;

public class PostGeneration {
    public List<PostDao> generatePostDaos()
    {
        List<PostDao> postDaos = new ArrayList<>();
        postDaos.add(new PostDao(1L,1L,"https://localhost/image/profile/profilepic","bert"));
        postDaos.add(new PostDao(2L,2L,"https://localhost/image/profile/profilepic","gert"));
        return postDaos;
    }
}
