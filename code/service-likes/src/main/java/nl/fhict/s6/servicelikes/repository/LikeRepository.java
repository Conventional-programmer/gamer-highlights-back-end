package nl.fhict.s6.servicelikes.repository;

import nl.fhict.s6.servicelikes.datamodels.LikeDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikeRepository extends JpaRepository<LikeDao,Long> {
    List<LikeDao> getAllByPostId(Long postId);
}
