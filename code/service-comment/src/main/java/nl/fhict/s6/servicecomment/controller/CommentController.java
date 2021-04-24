package nl.fhict.s6.servicecomment.controller;

import nl.fhict.s6.libraryrest.controller.BaseController;
import nl.fhict.s6.servicecomment.converters.CommentDaoConverter;
import nl.fhict.s6.servicecomment.datamodels.CommentDao;
import nl.fhict.s6.servicecomment.dto.CommentDto;
import nl.fhict.s6.servicecomment.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/comment")
public class CommentController extends BaseController<CommentDao, CommentDto> {
    private CommentService commentService;
    private CommentDaoConverter commentDaoConverter;
    public CommentController(CommentService commentService, CommentDaoConverter commentDaoConverter) {
        super(commentService, commentDaoConverter);
        this.commentService = commentService;
        this.commentDaoConverter = commentDaoConverter;
    }
    @RequestMapping("/post/{id}")
    public ResponseEntity<List<CommentDto>> getCommentsByUserId(@PathVariable("id")Long postId)
    {
        List<CommentDao> commentsFound = commentService.getAllCommentsByPostId(postId);
        return new ResponseEntity<>(commentDaoConverter.objectDaosToObjects(commentsFound), HttpStatus.OK);
    }
    @RequestMapping("")
    public ResponseEntity<List<CommentDto>> getCommentsByCommentIds(@RequestParam("ids")List<Long> commentIds)
    {
        List<CommentDao> commentsFound = commentService.getAllCommentsByCommentIds(commentIds);
        return new ResponseEntity<>(commentDaoConverter.objectDaosToObjects(commentsFound), HttpStatus.OK);
    }
}
