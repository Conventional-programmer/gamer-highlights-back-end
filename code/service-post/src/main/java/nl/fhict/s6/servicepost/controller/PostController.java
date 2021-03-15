package nl.fhict.s6.servicepost.controller;

import nl.fhict.s6.libraryrest.controller.BaseController;
import nl.fhict.s6.servicepost.converters.PostDaoConverter;
import nl.fhict.s6.servicepost.datamodels.PostDao;
import nl.fhict.s6.servicepost.dto.PostDto;
import nl.fhict.s6.servicepost.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/post")
public class PostController extends BaseController<PostDao,PostDto> {
    private PostService postService;
    private PostDaoConverter postDaoConverter;
    @Autowired
    public PostController(PostService postService,PostDaoConverter postDaoConverter) {
        super(postService,postDaoConverter);
        this.postDaoConverter = postDaoConverter;
        this.postService = postService;
    }

}
