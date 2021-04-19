package nl.fhict.s6.servicepost.context;

import nl.fhict.s6.servicepost.dto.CommentDto;

import java.util.Arrays;
import java.util.List;


public class CommentHttpContext extends BaseHttpContext<CommentDto,Long> {

    public CommentHttpContext(String serverIp,Integer port, String baseUrl) {
        super(serverIp,port,baseUrl);
        baseUrl = String.format("http://%s:%d/%s",serverIp,port,baseUrl);
    }
    public List<CommentDto> getCommentDtos(Long postId)
    {
        return Arrays.asList(super.restTemplate.getForEntity(super.baseUrl+"/"+postId,CommentDto[].class).getBody());
    }
    /*public CommentDto getCommentDto(Long id)
    {
        String url = new StringBuilder(baseUrl).append(id).toString();
        CommentDto commentDto = restTemplate.getForObject(url,CommentDto.class);
        return commentDto;
    }*/
}
