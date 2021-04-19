package nl.fhict.s6.servicepost.service;

import nl.fhict.s6.libraryrest.service.CrudService;
import nl.fhict.s6.servicepost.datamodels.PostDao;
import nl.fhict.s6.servicepost.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService extends CrudService<PostDao> {
    private PostRepository postRepository;
    public PostService(PostRepository postRepository) {
        super(postRepository);
        this.postRepository = postRepository;
    }
    public List<PostDao> getPostsByUserId(Long userId) {
       return postRepository.getAllByUserId(userId);
    }

}
