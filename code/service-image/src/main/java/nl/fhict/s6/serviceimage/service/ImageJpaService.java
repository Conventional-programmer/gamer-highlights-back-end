package nl.fhict.s6.serviceimage.service;


import nl.fhict.s6.libraryrest.service.CrudService;
import nl.fhict.s6.serviceimage.datamodels.ImageJpaDao;
import nl.fhict.s6.serviceimage.dto.ContentType;
import nl.fhict.s6.serviceimage.repository.ImageJpaRepository;
import org.springframework.stereotype.Service;

@Service
public class ImageJpaService extends CrudService<ImageJpaDao> implements ImageService<ImageJpaDao> {
    private ImageJpaRepository imageRepository;
    public ImageJpaService(ImageJpaRepository imageRepository) {
        super(imageRepository);
        this.imageRepository = imageRepository;
    }
    public ImageJpaDao getImageDaoByContentTypeAndName(ContentType contentType, String name)
    {
        return  imageRepository.findByContentTypeAndName(contentType,name);
    }
}
