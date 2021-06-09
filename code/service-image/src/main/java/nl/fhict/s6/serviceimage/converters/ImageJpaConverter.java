package nl.fhict.s6.serviceimage.converters;

import nl.fhict.s6.serviceimage.datamodels.ImageJpaDao;
import nl.fhict.s6.serviceimage.dto.ImageDto;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")
public class ImageJpaConverter implements ImageConverter<ImageJpaDao> {
    @Override
    public ImageJpaDao convertDtoToImageDao(ImageDto imageDto, String name, Long id) {
        ImageJpaDao imageJpaDao = new ImageJpaDao();
        imageJpaDao.setContentType(imageDto.getImageType());
        imageJpaDao.setName(name);
        imageJpaDao.setUserId(id);
        return imageJpaDao;
    }
}
