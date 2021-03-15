package nl.fhict.s6.servicelikes.datamodels;

import javax.persistence.*;

@Entity(name = "Likes")
@Table(name = "likes")
@Inheritance(strategy = InheritanceType.JOINED)
public class LikeDao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "postId")
    public Long postId;
    @Column(name = "likes")
    public Integer likes;

    public LikeDao() {
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }
}
