package nl.fhict.s6.servicelikes.datamodels;

import nl.fhict.s6.libraryrest.datamodels.EntityDao;

import javax.persistence.*;

@Entity(name = "Likes")
@Table(name = "likes")
public class LikeDao extends EntityDao {
    @Column(name = "post_id")
    private Long postId;
    @ManyToOne
    private UserDao userDao;

    public LikeDao() {
    }

    public LikeDao(Long postId, UserDao userDao) {
        this.postId = postId;
        this.userDao = userDao;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
