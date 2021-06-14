package nl.fhict.s6.serviceimage.datamodels;

import nl.fhict.s6.serviceimage.dto.ContentType;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document(collection = "image")
public class ImageMongoDao implements ImageDao {
    @MongoId
    private String id;
    private Long userId;
    private String name;
    private ContentType contentType;

    public ImageMongoDao() {
    }

    public ImageMongoDao(String id, Long userId, String name, ContentType contentType) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.contentType = contentType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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
