package nl.fhict.s6.serviceimage.converters;

import nl.fhict.s6.serviceimage.datamodels.ImageJpaDao;
import nl.fhict.s6.serviceimage.datamodels.ImageMongoDao;
import nl.fhict.s6.serviceimage.dto.ImageDto;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("prod")
public class ImageMongoConverter implements ImageConverter<ImageMongoDao> {

    @Override
    public ImageMongoDao convertDtoToImageDao(ImageDto imageDto, String name, Long id) {
        ImageMongoDao imageMongoDao = new ImageMongoDao();
        imageMongoDao.setContentType(imageDto.getImageType());
        imageMongoDao.setName(name);
        imageMongoDao.setUserId(id);
        return imageMongoDao;
    }
}
