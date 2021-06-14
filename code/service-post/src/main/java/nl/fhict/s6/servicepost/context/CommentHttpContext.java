package nl.fhict.s6.servicepost.context;

import nl.fhict.s6.servicepost.dto.CommentDto;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

public class CommentHttpContext extends BaseHttpContext<CommentDto,Long> {

    public CommentHttpContext(String serverIp,Integer port, String baseUrl) {
        super(serverIp,port,baseUrl);
    }
    /*public List<CommentDto> getCommentDtosByCommentIds(List<Long> commentIds)
    {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("%s?ids=",super.baseUrl));
        Integer i;
        Integer maxSize = commentIds.size()-1;
        for (i =0; i<maxSize; i++) {
            stringBuilder.append(commentIds.get(i));
            stringBuilder.append(',');
        }
        stringBuilder.append(commentIds.get(i));
        return Arrays.asList(super.restTemplate.exchange(stringBuilder.toString(), HttpMethod.GET,null,CommentDto[].class).getBody());
    }*/
    public List<CommentDto> getCommentsByPostId(Long  postId)
    {
        return Arrays.asList(super.restTemplate.exchange(String.format("%s/post/%d",baseUrl,postId), HttpMethod.GET,null,CommentDto[].class).getBody());
    }
}
