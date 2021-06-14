package nl.fhict.s6.servicepost.context;

import nl.fhict.s6.servicepost.dto.LikeDto;
import nl.fhict.s6.servicepost.service.LikeService;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class LikeHttpContextTest {
    LikeHttpContext likeHttpContext = new LikeHttpContext("localhost",9005,"like");
    @Test
    public void getCommentsByPostId() {
        List<LikeDto> likeDtos = likeHttpContext.getCommentsByPostId(1L);
        assertTrue(likeDtos.size()>0);
    }
}
