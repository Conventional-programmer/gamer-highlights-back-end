package nl.fhict.s6.servicelikes.repository;

import nl.fhict.s6.servicelikes.config.generation.UserDaoGeneration;
import nl.fhict.s6.servicelikes.datamodels.LikeDao;
import nl.fhict.s6.servicelikes.datamodels.UserDao;
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
@ActiveProfiles("dev")
public class LikeRepositoryTest {
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private LikeRepository likeRepository;


    @Test
    void findById() {
        LikeDao likeDao = new LikeDao();
        likeDao.setUserDao(new UserDaoGeneration().generateUserDaos().get(0));
        LikeDao tryingToFind = entityManager.merge(likeDao);
        Optional<LikeDao> found = likeRepository.findById(tryingToFind.getId());
        assertTrue(found.isPresent());
        assertEquals(found.get().getPostId(),tryingToFind.getPostId());
        assertEquals(found.get().getUserDao().getId(),1L);
    }
    @Test
    void save() {
        LikeDao likeDao = new LikeDao();
        likeDao.setUserDao(new UserDaoGeneration().generateUserDaos().get(0));
        LikeDao tryingToFind =likeRepository.save(likeDao);
        LikeDao found  = entityManager.find(LikeDao.class,tryingToFind.getId());
        assertNotNull(found);
        assertEquals("bert",found.getUserDao().getUsername());

    }
    @Test
    void update() {
        LikeDao likeDao = new LikeDao();
        likeDao.setPostId(1L);
        likeDao.setUserDao(new UserDaoGeneration().generateUserDaos().get(0));
        LikeDao saved = likeRepository.save(likeDao);
        LikeDao found  = likeRepository.getOne(saved.getId());
        assertEquals(likeDao.getUserDao().getId(), found.getUserDao().getId());
        likeDao.setUserDao(new UserDaoGeneration().generateUserDaos().get(1));
        likeRepository.save(likeDao);
        assertEquals(likeDao.getUserDao().getId(), found.getUserDao().getId());
        entityManager.flush();
    }
}
