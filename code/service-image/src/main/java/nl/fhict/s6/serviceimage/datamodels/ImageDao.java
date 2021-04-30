package nl.fhict.s6.serviceimage.datamodels;

import nl.fhict.s6.libraryrest.datamodels.EntityDao;
import nl.fhict.s6.serviceimage.dto.ContentType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "entity")
@Entity(name = "image")
public class ImageDao extends EntityDao {
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "name")
    private String name;
    @Column(name = "content_type")
    private ContentType contentType;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public ContentType getContentType() {
        return contentType;
    }

    public void setContentType(ContentType contentType) {
        this.contentType = contentType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
