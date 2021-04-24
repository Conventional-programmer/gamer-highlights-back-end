package nl.fhict.s6.servicepost.context;

import nl.fhict.s6.servicepost.dto.CommentDto;
import nl.fhict.s6.servicepost.dto.LikeDto;
import org.springframework.http.HttpMethod;

import java.util.Arrays;
import java.util.List;


public class LikeHttpContext extends BaseHttpContext<LikeDto,Long>{
    public LikeHttpContext(String serverIp, Integer port, String baseUrl) {
        super(serverIp, port, baseUrl);
    }
    public List<LikeDto> getCommentsByPostId(Long  postId)
    {
        return Arrays.asList(super.restTemplate.exchange(String.format("%s/post/%d",baseUrl,postId), HttpMethod.GET,null,LikeDto[].class).getBody());
    }
}
