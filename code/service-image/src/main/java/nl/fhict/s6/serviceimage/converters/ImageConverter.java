package nl.fhict.s6.serviceimage.converters;

import nl.fhict.s6.serviceimage.datamodels.ImageDao;
import nl.fhict.s6.serviceimage.dto.ImageDto;

public interface ImageConverter<I extends ImageDao> {
    I convertDtoToImageDao(ImageDto imageDto,String name,Long id);
}
