package nl.fhict.s6.servicepost.context;

import nl.fhict.s6.servicepost.dto.CommentDto;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Tag("integration")
public class CommentHttpContextTest {
    private CommentHttpContext commentHttpContext = new CommentHttpContext("localhost",9003,"/comment");
    @Test
    void getCommentDtos() {
        List<CommentDto> commentDtoList = commentHttpContext.getCommentsByPostId(1L);
        System.out.println(commentDtoList.size());
        assertTrue(commentDtoList.size() > 0);
    }
}
