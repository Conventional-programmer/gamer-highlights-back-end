package nl.fhict.s6.serviceimage.config.converters;

import nl.fhict.s6.serviceimage.dto.ContentType;
import org.springframework.core.convert.converter.Converter;

public class StringContentTypeConverter implements Converter<String, ContentType> {
    @Override
    public ContentType convert(String s) {
        try
        {
            return ContentType.valueOf(s.toUpperCase());
        }
        catch (Exception exp)
        {
            return null;
        }
    }
}
