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
    @ElementCollection
    @Column(name = "likes")
    private List<Long> likes;
    @ElementCollection
    @Column(name = "comment")
    private List<Long> commentIds;

    public PostDao() {
    }

    public PostDao(Long id, Long userId, String imageUrl, String description, List<Long> likes) {
        super(id);
        this.userId = userId;
        this.imageUrl = imageUrl;
        this.description = description;
        this.likes = likes;
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

    public List<Long> getLikes() {
        return likes;
    }

    public void setLikes(List<Long> likes) {
        this.likes = likes;
    }
}
