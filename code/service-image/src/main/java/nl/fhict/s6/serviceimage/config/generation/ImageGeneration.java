package nl.fhict.s6.serviceimage.config.generation;

import nl.fhict.s6.serviceimage.datamodels.ImageJpaDao;
import nl.fhict.s6.serviceimage.dto.ContentType;

import java.util.ArrayList;
import java.util.List;

public class ImageGeneration {
    public List<ImageJpaDao> generateImages()
    {
        List<ImageJpaDao> images = new ArrayList<>();
        images.add(new ImageJpaDao(1L,1L,"profile-pic.jpg", ContentType.PROFILE));
        return images;
    }
}
