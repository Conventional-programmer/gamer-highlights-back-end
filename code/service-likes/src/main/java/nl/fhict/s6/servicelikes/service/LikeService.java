package nl.fhict.s6.servicelikes.service;

import nl.fhict.s6.servicelikes.datamodels.LikeDao;
import nl.fhict.s6.servicelikes.repository.LikeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LikeService {
    private LikeRepository likeRepository;

    public LikeService(LikeRepository likeRepository) {
        this.likeRepository = likeRepository;
    }
    public List<LikeDao> getLikesByPostId(Long postId)
    {
        return likeRepository.getAllByPostId(postId);
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
