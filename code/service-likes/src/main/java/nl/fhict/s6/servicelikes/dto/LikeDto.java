package nl.fhict.s6.servicelikes.dto;

public class LikeDto {
    private Long postId;
    private Integer likes;

    public LikeDto() {
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
