package nl.fhict.s6.serviceimage.datamodels;

import nl.fhict.s6.serviceimage.dto.ContentType;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import javax.persistence.Column;

@Document
public class ImageMongoDao implements ImageDao {
    @MongoId
    private Long userId;
    private String name;
    private ContentType contentType;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ContentType getContentType() {
        return contentType;
    }

    public void setContentType(ContentType contentType) {
        this.contentType = contentType;
    }
}
