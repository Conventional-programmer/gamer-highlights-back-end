package nl.fhict.s6.serviceimage.config.generation;

import nl.fhict.s6.serviceimage.datamodels.ImageMongoDao;
import nl.fhict.s6.serviceimage.dto.ContentType;

import java.util.ArrayList;
import java.util.List;

public class ImageMongoGeneration {
    public List<ImageMongoDao> generateImages()
    {
        List<ImageMongoDao> images = new ArrayList<>();
        images.add(new ImageMongoDao(null,1L,"profilepic", ContentType.PROFILE));
        return images;
    }
}
