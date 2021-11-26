package nl.fhict.s6.serviceimage.service;


import nl.fhict.s6.libraryrest.exception.NoObjectById;
import nl.fhict.s6.libraryrest.service.CrudService;
import nl.fhict.s6.serviceimage.datamodels.ImageJpaDao;
import nl.fhict.s6.serviceimage.dto.ContentType;
import nl.fhict.s6.serviceimage.repository.ImageJpaRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("dev")
public class ImageJpaService extends CrudService<ImageJpaDao> implements ImageService<ImageJpaDao> {
    private ImageJpaRepository imageRepository;
    public ImageJpaService(ImageJpaRepository imageRepository) {
        super(imageRepository);
        this.imageRepository = imageRepository;
    }

    @Override
    public ImageJpaDao update(ImageJpaDao imageDao) throws NoObjectById {
        if(!imageRepository.existsById(imageDao.getId()))
        {
            throw new NoObjectById("Bad request");
        }
        return imageRepository.save(imageDao);
    }

    @Override
    public ImageJpaDao findByContentTypeAndName(ContentType contentType, String name) {
        return imageRepository.findByContentTypeAndName(contentType,name);
    }

    @Override
    public ImageJpaDao save(ImageJpaDao imageDao) {
        return imageRepository.save(imageDao);
    }
}
