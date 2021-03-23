package nl.fhict.s6.servicelikes.service;

import nl.fhict.s6.libraryrest.service.CrudService;
import nl.fhict.s6.servicelikes.datamodels.LikeDao;
import nl.fhict.s6.servicelikes.repository.LikeRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LikeService {
    private LikeRepository likeRepository;

    public LikeService(LikeRepository likeRepository) {
        this.likeRepository = likeRepository;
    }
    public Integer getLikesByPostId(Long postId)
    {
        Optional<LikeDao> likeDao = likeRepository.findById(postId);
        if(likeDao.isPresent())
        {
            return likeDao.get().getLikes();
        }
        return 0;
    }
    public LikeDao getLikeDaoByPostId(Long postId)
    {
        Optional<LikeDao> likeDao = likeRepository.findById(postId);
        if(likeDao.isPresent())
        {
            return likeDao.get();
        }
        return null;
    }
    public void update(LikeDao likeDao)
    {
        if(likeRepository.existsById(likeDao.getPostId()))
        likeRepository.save(likeDao);
    }
    public void save(LikeDao likeDao)
    {
        if(!likeRepository.existsById(likeDao.getPostId()))
        likeRepository.save(likeDao);
    }
}
