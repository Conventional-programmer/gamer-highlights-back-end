package nl.fhict.s6.servicepost.dto;

import java.util.List;

public class PostDto {
    private Long id;
    private UserDto postingUser;
    private String imageUrl;
    private String description;
    private List<CommentDto> comments;
    private List<LikeDto> likes;

    public PostDto() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public UserDto getPostingUser() {
        return postingUser;
    }

    public void setPostingUser(UserDto postingUser) {
        this.postingUser = postingUser;
    }

    public List<CommentDto> getComments() {
        return comments;
    }

    public void setComments(List<CommentDto> comments) {
        this.comments = comments;
    }

    public List<LikeDto> getLikes() {
        return likes;
    }

    public void setLikes(List<LikeDto> likes) {
        this.likes = likes;
    }
}
