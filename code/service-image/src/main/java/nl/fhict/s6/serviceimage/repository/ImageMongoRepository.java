package nl.fhict.s6.serviceimage.repository;

import nl.fhict.s6.serviceimage.datamodels.ImageMongoDao;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ImageMongoRepository extends MongoRepository<ImageMongoDao,Long>{
}
