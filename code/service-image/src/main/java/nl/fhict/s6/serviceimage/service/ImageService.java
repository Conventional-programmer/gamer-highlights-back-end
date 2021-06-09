package nl.fhict.s6.serviceimage.service;

import nl.fhict.s6.libraryrest.exception.NoObjectById;
import nl.fhict.s6.serviceimage.datamodels.ImageDao;
import nl.fhict.s6.serviceimage.datamodels.ImageJpaDao;
import nl.fhict.s6.serviceimage.dto.ContentType;

public interface ImageService<I extends ImageDao> {
    I update(I imageDao) throws NoObjectById;
    I findByContentTypeAndName(ContentType contentType, String name);
    I save (I imageDao);
}
