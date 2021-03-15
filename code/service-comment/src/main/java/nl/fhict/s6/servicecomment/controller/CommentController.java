package nl.fhict.s6.servicecomment.controller;

import nl.fhict.s6.libraryrest.controller.BaseController;
import nl.fhict.s6.servicecomment.converters.CommentDaoConverter;
import nl.fhict.s6.servicecomment.datamodels.CommentDao;
import nl.fhict.s6.servicecomment.dto.CommentDto;
import nl.fhict.s6.servicecomment.service.CommentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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

}
