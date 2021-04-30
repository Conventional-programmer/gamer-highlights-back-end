package nl.fhict.s6.serviceimage.service;


import nl.fhict.s6.libraryrest.service.CrudService;
import nl.fhict.s6.serviceimage.datamodels.ImageDao;
import nl.fhict.s6.serviceimage.dto.ContentType;
import nl.fhict.s6.serviceimage.repository.ImageRepository;

public class ImageService extends CrudService<ImageDao> {
    private ImageRepository imageRepository;
    public ImageService(ImageRepository imageRepository) {
        super(imageRepository);
        this.imageRepository = imageRepository;
    }
    public ImageDao getImageDaoByContentTypeAndName(ContentType contentType, String name)
    {

    }
}
