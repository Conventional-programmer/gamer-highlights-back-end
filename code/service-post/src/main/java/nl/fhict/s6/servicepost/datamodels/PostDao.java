package nl.fhict.s6.servicepost.datamodels;

import nl.fhict.s6.libraryrest.datamodels.EntityDao;

import javax.persistence.*;
import java.util.List;

@Entity(name = "Post")
@Table(name = "post")
@Inheritance(strategy = InheritanceType.JOINED)
public class PostDao extends EntityDao {
    @Column(name = "userId")
    Long userId;
    @Column(name = "imageUrl")
    private String imageUrl;
    @Column(name = "description")
    private String description;

    public PostDao() {
    }

    public PostDao(Long id, Long userId, String imageUrl, String description) {
        super(id);
        this.userId = userId;
        this.imageUrl = imageUrl;
        this.description = description;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
