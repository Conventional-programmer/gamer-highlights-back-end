package nl.fhict.s6.servicelikes.repository;

import nl.fhict.s6.servicelikes.datamodels.LikeDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ContextConfiguration
@Transactional
@DirtiesContext
@DataJpaTest
@ActiveProfiles("test")
public class LikeRepositoryTest {
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private LikeRepository likeRepository;


    @Test
    void findById() {
        LikeDao likeDao = new LikeDao();
        likeDao.setLikes(500);
        LikeDao tryingToFind = entityManager.merge(likeDao);
        Optional<LikeDao> found = likeRepository.findById(tryingToFind.getPostId());
        assertTrue(found.isPresent());
        assertEquals(found.get().getPostId(),tryingToFind.getPostId());
        assertEquals(found.get().getLikes(),500);
    }
    @Test
    void save() {
        LikeDao likeDao = new LikeDao();
        likeDao.setLikes(500);
        LikeDao tryingToFind =likeRepository.save(likeDao);
        LikeDao found  = entityManager.find(LikeDao.class,tryingToFind.getPostId());
        assertNotNull(found);
        assertEquals(500,found.getLikes());

    }
    @Test
    void update() {
        LikeDao likeDao = new LikeDao();
        likeDao.setLikes(500);
        likeRepository.save(likeDao);
        LikeDao found  = likeRepository.getOne(1L);
        assertEquals(likeDao.getLikes(), found.getLikes());
        likeDao.setLikes(300);
        likeRepository.save(likeDao);
        assertEquals(likeDao.getLikes(), found.getLikes());
        entityManager.flush();
    }
}
