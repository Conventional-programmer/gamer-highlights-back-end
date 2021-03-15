package nl.fhict.s6.servicelikes.service;

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
            return likeDao.get().likes;
        }
        return 0;
    }
}
