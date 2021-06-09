package nl.fhict.s6.servicecomment.datamodels;

import nl.fhict.s6.libraryrest.datamodels.EntityDao;

import javax.persistence.*;

@Entity(name = "Comment")
@Table(name = "comment")
public class CommentDao extends EntityDao {
    @Column(name = "postId")
    private Long postId;
    @ManyToOne
    private UserDao userDao;
    @Column(name = "comment")
    private String comment;

    public CommentDao() {
    }

    public CommentDao(Long id, Long postId, UserDao userDao, String comment) {
        super(id);
        this.postId = postId;
        this.userDao = userDao;
        this.comment = comment;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
