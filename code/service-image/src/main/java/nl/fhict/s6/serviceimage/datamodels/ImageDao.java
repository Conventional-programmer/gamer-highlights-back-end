package nl.fhict.s6.serviceimage.datamodels;

import nl.fhict.s6.serviceimage.dto.ContentType;

public interface ImageDao {
    Long getUserId();
    void setUserId(Long userId);
    ContentType getContentType();
    void setContentType(ContentType contentType);
    String getName();
    void setName(String name);
}
