package nl.fhict.s6.servicepost.service;

import nl.fhict.s6.servicepost.context.CommentHttpContext;
import nl.fhict.s6.servicepost.dto.CommentDto;

import java.util.List;

public class CommentService extends HttpBaseService<CommentDto,Long> {
    private CommentHttpContext commentHttpContext;

    public CommentService(CommentHttpContext commentHttpContext) {
        super(commentHttpContext);
        this.commentHttpContext = commentHttpContext;
    }
    public List<CommentDto> getCommentDtos(Long postId)
    {
        return commentHttpContext.getCommentsByPostId(postId);
    }
}
