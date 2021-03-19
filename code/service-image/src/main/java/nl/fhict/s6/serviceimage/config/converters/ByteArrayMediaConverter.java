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
        List<MediaType> list = new ArrayList<MediaType>();
        list.add(MediaType.IMAGE_JPEG);
        list.add(MediaType.IMAGE_PNG);
        list.add(MediaType.APPLICATION_OCTET_STREAM);
        return list;
    }
}