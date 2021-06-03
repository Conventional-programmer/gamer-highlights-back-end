package nl.fhict.s6.serviceimage.service;

import nl.fhict.s6.libraryrest.exception.NoObjectById;
import nl.fhict.s6.serviceimage.datamodels.ImageDao;

public interface ImageService<I extends ImageDao> {
    I update(I imageDao) throws NoObjectById;
}
