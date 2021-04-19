package nl.fhict.s6.servicepost.repository;

import nl.fhict.s6.servicepost.datamodels.PostDao;
import nl.fhict.s6.servicepost.datamodels.UserDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<PostDao,Long> {
    List<PostDao> getAllByUserId(Long userId);
}
