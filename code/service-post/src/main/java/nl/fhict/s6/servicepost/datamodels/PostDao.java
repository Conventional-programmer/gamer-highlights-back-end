package nl.fhict.s6.servicepost.datamodels;

import nl.fhict.s6.libraryrest.datamodels.EntityDao;

import javax.persistence.*;

@Entity(name = "Post")
@Table(name = "post")
@Inheritance(strategy = InheritanceType.JOINED)
public class PostDao extends EntityDao {
    @Column(name = "imageUrl")
    private String imageUrl;
    @Column(name = "description")
    private String description;
    @Column(name = "likes")
    private int likes;

    public PostDao() {
    }

    public PostDao(Long id, String imageUrl, String description, int likes) {
        super(id);
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

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }
}
