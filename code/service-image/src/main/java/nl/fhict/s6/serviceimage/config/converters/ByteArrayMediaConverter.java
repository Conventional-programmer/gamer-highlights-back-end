package nl.fhict.s6.serviceimage.config.converters;

import org.springframework.http.MediaType;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;

import java.util.ArrayList;
import java.util.List;

public class ByteArrayMediaConverter extends ByteArrayHttpMessageConverter {
    public ByteArrayMediaConverter() {
        this.setSupportedMediaTypes(supportedMediaTypes());
    }
    private List<MediaType> supportedMediaTypes() {
        List<MediaType> list = new ArrayList<>();
        list.add(MediaType.IMAGE_JPEG);
        list.add(MediaType.IMAGE_PNG);
        return list;
    }
}
