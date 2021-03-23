package nl.fhict.s6.servicelikes.datamodels;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class LikeDaoTest {
    private LikeDao likeDao;
    @BeforeEach
    void setUp() {
        likeDao = new LikeDao();
        likeDao.setLikes(500);
        likeDao.setPostId(1L);
    }

    @Test
    void getPostId() {
         assertEquals(likeDao.getPostId(),1L);
    }

    @Test
    void setPostId() {
        likeDao.setPostId(2L);
        assertEquals(likeDao.getPostId(),2L);
    }

    @Test
    void getLikes() {
        assertEquals(likeDao.getLikes(),500);
    }

    @Test
    void setLikes() {
        likeDao.setLikes(550);
        assertEquals(likeDao.getLikes(),550);
    }

}
