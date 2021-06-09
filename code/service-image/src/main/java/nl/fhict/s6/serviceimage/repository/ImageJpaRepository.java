package nl.fhict.s6.serviceimage.repository;

import nl.fhict.s6.serviceimage.datamodels.ImageJpaDao;
import nl.fhict.s6.serviceimage.dto.ContentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageJpaRepository extends JpaRepository<ImageJpaDao,Long>{
    ImageJpaDao findByContentTypeAndName(ContentType contentType, String name);
}
