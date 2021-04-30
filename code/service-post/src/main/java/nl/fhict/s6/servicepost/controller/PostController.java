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
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin(origins = "localhost:80", maxAge = 3600)
@RequestMapping("/post")
public class  PostController extends BaseController<PostDao,PostDto> {
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
        postDto.setLikes(likeService.getAllLikesByPostId(postDao.getId()));
        return new ResponseEntity(postDto, HttpStatus.OK);
    }
    @GetMapping("/user/{id}")
    public ResponseEntity<List<PostDto>> getEntityByUserId(@PathVariable("id") Long id)
    {
        List<PostDao> postDaos = postService.getPostsByUserId(id);
        List<PostDto> postDtos = postDaoConverter.objectDaosToObjects(postDaos);
        for (PostDto postDto: postDtos) {
            postDto.setCommentDtos(commentService.getCommentDtos(postDto.getId()));
        }
        return new ResponseEntity(postDtos, HttpStatus.OK);
    }
}
