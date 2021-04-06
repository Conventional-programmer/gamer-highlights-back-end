package nl.fhict.s6.servicecomment.service;

import nl.fhict.s6.libraryrest.service.CrudService;
import nl.fhict.s6.servicecomment.datamodels.CommentDao;
import nl.fhict.s6.servicecomment.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService extends CrudService<CommentDao> {
    private CommentRepository commentRepository;
    @Autowired
    public CommentService(CommentRepository commentRepository) {
        super(commentRepository);
        this.commentRepository = commentRepository;
    }
    public void k(CommentDao commentDao)
    {
        commentRepository.save(commentDao);
    }
}
