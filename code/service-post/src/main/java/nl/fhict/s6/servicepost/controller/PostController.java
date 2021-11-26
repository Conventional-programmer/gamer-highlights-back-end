package nl.fhict.s6.servicepost.controller;

import nl.fhict.s6.libraryrest.authentication.http.PermissionHttpHeader;
import nl.fhict.s6.libraryrest.authentication.http.exception.PermissionDenied;
import nl.fhict.s6.libraryrest.controller.BaseController;
import nl.fhict.s6.servicepost.context.CommentHttpContext;
import nl.fhict.s6.servicepost.context.LikeHttpContext;
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
        this.likeService = new LikeService(new LikeHttpContext("service-likes",9005,"like"));
        commentService = new CommentService(new CommentHttpContext("service-comment",9003,"comment"));
    }
    @Override
    public ResponseEntity<PostDto> getEntityById(@PathVariable("id") Long id, PermissionHttpHeader permissionHttpHeader)
    {
        try {
            PostDao postDao = postService.findById(id,permissionHttpHeader.getBasePermission());
            PostDto postDto = postDaoConverter.objectDaoToObject(postDao);
            postDto.setComments(commentService.getCommentDtos(postDao.getId()));
            postDto.setLikes(likeService.getAllLikesByPostId(postDao.getId()));
            return new ResponseEntity(postDto, HttpStatus.OK);
        } catch (PermissionDenied permissionDenied) {
            return new ResponseEntity<>(null,HttpStatus.FORBIDDEN);
        }
    }
    @GetMapping("/user/{user_id}")
    public ResponseEntity<List<PostDto>> getEntityByUserId(@PathVariable("user_id") Long id,PermissionHttpHeader permissionHttpHeader)
    {
        if(!permissionHttpHeader.getBasePermission().getUserId().equals(id))
        {
            System.out.println(permissionHttpHeader.getBasePermission().getUserId());
            System.out.println(id);
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        List<PostDao> postDaos = postService.getPostsByUserId(id,permissionHttpHeader.getBasePermission());
        List<PostDto> postDtos = postDaoConverter.objectDaosToObjects(postDaos);
        for (PostDto postDto: postDtos) {
            postDto.setComments(commentService.getCommentDtos(postDto.getId()));
            postDto.setLikes(likeService.getAllLikesByPostId(postDto.getId()));
        }
        return new ResponseEntity(postDtos, HttpStatus.OK);
    }
}
