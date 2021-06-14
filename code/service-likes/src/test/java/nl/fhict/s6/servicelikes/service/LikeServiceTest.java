package nl.fhict.s6.servicelikes.service;

import nl.fhict.s6.servicelikes.config.generation.UserDaoGeneration;
import nl.fhict.s6.servicelikes.datamodels.LikeDao;
import nl.fhict.s6.servicelikes.dto.LikeDto;
import nl.fhict.s6.servicelikes.repository.LikeRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.event.annotation.BeforeTestMethod;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
public class LikeServiceTest {
    @MockBean
    private LikeRepository likeRepository;
    @Autowired
    private LikeService likeService;

    @BeforeEach
    public void setUp() throws Exception {
        LikeDao likeDao = new LikeDao();
        likeDao.setPostId(1L);
        likeDao.setUserDao(new UserDaoGeneration().generateUserDaos().get(0));
        List<LikeDao> likeDaos = new ArrayList<>();
        likeDaos.add(likeDao);
        Mockito.when(likeRepository.getAllByPostId(1L)).thenReturn(likeDaos);
    }

    @Test
    void getLikesByPostId() {
        List<LikeDao> likes = likeService.getLikesByPostId(1L);
        assertEquals(1L,likes.get(0).getPostId());
    }

    @Test
    void getLikeDaoByPostId() {
        List<LikeDao> likeDao = likeService.getLikesByPostId(1L);
        assertEquals(1L,likeDao.get(0).getPostId());
        assertEquals(1L,likeDao.get(0).getUserDao().getId());
        assertEquals("bert",likeDao.get(0).getUserDao().getUsername());
    }

    @Test
    void update() {
    }
}
