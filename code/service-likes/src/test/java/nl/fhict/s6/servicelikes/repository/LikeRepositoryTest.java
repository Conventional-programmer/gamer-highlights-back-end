package nl.fhict.s6.servicelikes.repository;

import nl.fhict.s6.servicelikes.datamodels.LikeDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ContextConfiguration
@Transactional
@DataJpaTest
public class LikeRepositoryTest {
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private LikeRepository likeRepository;


    @Test
    void findById() {
        LikeDao likeDao = new LikeDao();
        likeDao.setPostId(1L);
        likeDao.setLikes(500);
        entityManager.merge(likeDao);
        Optional<LikeDao> found = likeRepository.findById(1L);
        assertTrue(found.isPresent());
        assertEquals(found.get().getPostId(),1L);
        assertEquals(found.get().getLikes(),500);
        entityManager.flush();
    }
    @Test
    void save() {
        LikeDao likeDao = new LikeDao();
        likeDao.setPostId(2L);
        likeDao.setLikes(500);
        likeRepository.save(likeDao);
        LikeDao found  = entityManager.find(LikeDao.class, 2L);
        assertTrue(likeRepository.existsById(2L));

    }
    @Test
    void update() {
        LikeDao likeDao = new LikeDao();
        likeDao.setPostId(3L);
        likeDao.setLikes(500);
        likeRepository.save(likeDao);
        LikeDao found  = likeRepository.getOne(3L);
        assertEquals(likeDao.getLikes(), found.getLikes());
        likeDao.setLikes(300);
        likeRepository.save(likeDao);
        assertEquals(likeDao.getLikes(), found.getLikes());
        entityManager.flush();
    }
}
