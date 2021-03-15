package nl.fhict.s6.servicepost.config.generation;

import nl.fhict.s6.servicepost.datamodels.PostDao;

import java.util.ArrayList;
import java.util.List;

public class PostGeneration {
    public List<PostDao> generatePostDaos()
    {
        List<PostDao> postDaos = new ArrayList<>();
        postDaos.add(new PostDao(1L,"www.google.nl","bert",50));
        return postDaos;
    }
}
