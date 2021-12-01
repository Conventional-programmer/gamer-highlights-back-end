package nl.fhict.s6.servicepost.service;

import nl.fhict.s6.libraryrest.authentication.http.exception.PermissionDenied;
import nl.fhict.s6.libraryrest.datamodels.Permission;
import nl.fhict.s6.libraryrest.exception.NoObjectById;
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
    public List<PostDao> getPostsByUserId(Long userId,Permission permission) {
       return postRepository.getAllByUserId(userId);
    }
    @Override
    public PostDao update(PostDao postDao, Permission permission) throws PermissionDenied, NoObjectById {
        if(!postRepository.existsByIdAndUserId(postDao.getUserId(), permission.getUserId()))
        {
            throw new PermissionDenied("Not authorized");
        }
        return super.update(postDao,permission);
    }
    @Override
    public PostDao save(PostDao postDao,Permission permission) throws PermissionDenied {
        if(postDao.getUserId() == null || !postDao.getUserId().equals(permission.getUserId()))
        {
            throw new PermissionDenied("Not authorized");
        }
        return super.save(postDao,permission);
    }
}
