package nl.fhict.s6.servicepost.service;

import nl.fhict.s6.servicepost.context.LikeHttpContext;
import nl.fhict.s6.servicepost.dto.LikeDto;

import java.util.List;

public class LikeService  extends HttpBaseService<LikeDto,Long>{
    private LikeHttpContext likeHttpContext;
    public LikeService(LikeHttpContext likeHttpContext) {
        super(likeHttpContext);
        this.likeHttpContext = likeHttpContext;
    }
    public List<LikeDto> getAllLikesByPostId(Long postId)
    {
        return likeHttpContext.getCommentsByPostId(postId);
    }
}
