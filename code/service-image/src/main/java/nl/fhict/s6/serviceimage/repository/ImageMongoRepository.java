package nl.fhict.s6.serviceimage.repository;

import nl.fhict.s6.serviceimage.datamodels.ImageJpaDao;
import nl.fhict.s6.serviceimage.datamodels.ImageMongoDao;
import nl.fhict.s6.serviceimage.dto.ContentType;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageMongoRepository extends MongoRepository<ImageMongoDao,String>{
    ImageMongoDao findByContentTypeAndName(ContentType contentType, String name);
}
