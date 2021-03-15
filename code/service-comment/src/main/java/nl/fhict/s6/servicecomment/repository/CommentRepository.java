package nl.fhict.s6.servicecomment.repository;

import nl.fhict.s6.servicecomment.datamodels.CommentDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<CommentDao,Long> {
}
