package nl.fhict.s6.servicelikes.service;

import nl.fhict.s6.servicelikes.datamodels.LikeDao;
import nl.fhict.s6.servicelikes.repository.LikeRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.event.annotation.BeforeTestMethod;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class LikeServiceTest {
    @MockBean
    private LikeRepository likeRepository;
    @Autowired
    private LikeService likeService;

    @BeforeEach
    public void setUp() throws Exception {
        LikeDao likeDao = new LikeDao();
        likeDao.setPostId(1L);
        likeDao.setLikes(300);
        Optional<LikeDao> optional = Optional.of(likeDao);
        Mockito.when(likeRepository.findById(1L)).thenReturn(optional);
    }

    @Test
    void getLikesByPostId() {
        Integer likes = likeService.getLikesByPostId(1L);
        assertEquals(300,likes);
    }

    @Test
    void getLikeDaoByPostId() {
        LikeDao likeDao = likeService.getLikeDaoByPostId(1L);
        assertEquals(1L,likeDao.getPostId());
        assertEquals(300,likeDao.getLikes());
    }

    @Test
    void update() {
    }
}
