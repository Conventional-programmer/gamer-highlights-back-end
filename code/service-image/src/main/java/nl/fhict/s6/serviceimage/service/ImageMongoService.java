package nl.fhict.s6.serviceimage.service;

import nl.fhict.s6.serviceimage.datamodels.ImageJpaDao;
import nl.fhict.s6.serviceimage.datamodels.ImageMongoDao;
import nl.fhict.s6.serviceimage.dto.ContentType;
import nl.fhict.s6.serviceimage.repository.ImageMongoRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;



@Service
@Profile("prod")
public class ImageMongoService implements ImageService<ImageMongoDao> {
    private ImageMongoRepository imageMongoRepository;

    public ImageMongoService(ImageMongoRepository imageMongoRepository) {
        this.imageMongoRepository = imageMongoRepository;
    }

    public ImageMongoDao update(ImageMongoDao imageMongoDao){
        return imageMongoRepository.save(imageMongoDao);
    }

    @Override
    public ImageMongoDao findByContentTypeAndName(ContentType contentType, String name) {
        return imageMongoRepository.findByContentTypeAndName(contentType,name);
    }

    @Override
    public ImageMongoDao save(ImageMongoDao imageDao) {
        return imageMongoRepository.save(imageDao);
    }
}
