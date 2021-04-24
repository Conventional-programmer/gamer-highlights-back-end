package nl.fhict.s6.servicecomment.service;

import nl.fhict.s6.libraryrest.service.CrudService;
import nl.fhict.s6.servicecomment.datamodels.CommentDao;
import nl.fhict.s6.servicecomment.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService extends CrudService<CommentDao> {
    private CommentRepository commentRepository;
    @Autowired
    public CommentService(CommentRepository commentRepository) {
        super(commentRepository);
        this.commentRepository = commentRepository;
    }
    public List<CommentDao> getAllCommentsByCommentIds(List<Long> commentIds)
    {
        return commentRepository.findAllById(commentIds);
    }

    public List<CommentDao> getAllCommentsByPostId(Long postId) {
        return commentRepository.findAllByPostId(postId);
    }
}
