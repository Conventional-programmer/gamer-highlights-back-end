package nl.fhict.s6.servicecomment.repository;

import nl.fhict.s6.servicecomment.datamodels.CommentDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<CommentDao,Long> {
    List<CommentDao> findAllByPostId(Long postId);
}
