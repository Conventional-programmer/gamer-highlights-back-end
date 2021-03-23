package nl.fhict.s6.servicelikes.datamodels;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity(name = "Likes")
@Table(name = "likes")
public class LikeDao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    @NotNull
    private Long postId;
    @Column(name = "likes")
    private Integer likes;

    public LikeDao() {
    }

    public LikeDao(Long postId, Integer likes) {
        this.postId = postId;
        this.likes = likes;
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
