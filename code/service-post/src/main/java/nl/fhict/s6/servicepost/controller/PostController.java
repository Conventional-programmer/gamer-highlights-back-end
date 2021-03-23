package nl.fhict.s6.servicepost.controller;

import nl.fhict.s6.libraryrest.controller.BaseController;
import nl.fhict.s6.servicepost.context.CommentHttpContext;
import nl.fhict.s6.servicepost.converters.PostDaoConverter;
import nl.fhict.s6.servicepost.datamodels.PostDao;
import nl.fhict.s6.servicepost.dto.PostDto;
import nl.fhict.s6.servicepost.service.CommentService;
import nl.fhict.s6.servicepost.service.LikeService;
import nl.fhict.s6.servicepost.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/post")
public class PostController extends BaseController<PostDao,PostDto> {
    private PostService postService;
    private PostDaoConverter postDaoConverter;
    private LikeService likeService;
    private CommentService commentService;
    @Autowired
    public PostController(PostService postService,PostDaoConverter postDaoConverter) {
        super(postService,postDaoConverter);
        this.postDaoConverter = postDaoConverter;
        this.postService = postService;
        commentService = new CommentService(new CommentHttpContext("localhost",8080,"comment"));
    }
    @Override
    public ResponseEntity<PostDto> getEntityById(@PathVariable("id") Long id)
    {
        PostDao postDao = postService.findById(id);
        PostDto postDto = postDaoConverter.objectDaoToObject(postDao);
        postDto.setCommentDtos(commentService.getCommentDtos(postDao.getId()));
        return new ResponseEntity(postDto, HttpStatus.OK);
    }
}
