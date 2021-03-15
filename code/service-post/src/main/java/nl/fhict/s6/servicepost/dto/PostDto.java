package nl.fhict.s6.servicepost.dto;

import java.util.List;

public class PostDto {
    private Long id;
    private UserDto postingUser;
    private String imageUrl;
    private String description;
    private List<CommentDto> commentIds;
    private int likes;

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

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

    public List<CommentDto> getCommentIds() {
        return commentIds;
    }

    public void setCommentIds(List<CommentDto> commentIds) {
        this.commentIds = commentIds;
    }
}
