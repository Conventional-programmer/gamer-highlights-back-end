package nl.fhict.s6.servicepost.service;

import nl.fhict.s6.servicepost.context.CommentHttpContext;
import nl.fhict.s6.servicepost.dto.CommentDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService extends HttpBaseService<CommentDto,Long> {
    private CommentHttpContext commentHttpContext;

    public CommentService(CommentHttpContext commentHttpContext) {
        super(commentHttpContext);
    }
    public List<CommentDto> getCommentDtos(Long postId)
    {
        return commentHttpContext.getCommentDtos(postId);
    }
}
