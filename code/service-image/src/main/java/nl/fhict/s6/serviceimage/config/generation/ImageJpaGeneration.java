package nl.fhict.s6.serviceimage.config.generation;

import nl.fhict.s6.serviceimage.datamodels.ImageJpaDao;
import nl.fhict.s6.serviceimage.dto.ContentType;

import java.util.ArrayList;
import java.util.List;

public class ImageJpaGeneration {
    public List<ImageJpaDao> generateImages()
    {
        List<ImageJpaDao> images = new ArrayList<>();
        images.add(new ImageJpaDao(1L,1L,"profilepic", ContentType.PROFILE));
        return images;
    }
}
