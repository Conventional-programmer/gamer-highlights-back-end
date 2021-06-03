package nl.fhict.s6.serviceimage.service;

import nl.fhict.s6.serviceimage.datamodels.ImageMongoDao;
import nl.fhict.s6.serviceimage.repository.ImageMongoRepository;
import org.springframework.stereotype.Service;

@Service
public class ImageMongoService implements ImageService<ImageMongoDao> {
    private ImageMongoRepository imageMongoRepository;
    public ImageMongoDao update(ImageMongoDao imageMongoDao){
        return imageMongoRepository.save(imageMongoDao);
    }
}
