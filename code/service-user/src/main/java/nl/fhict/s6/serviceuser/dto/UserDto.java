package nl.fhict.s6.serviceuser.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserDto {
    private Long userId;
    private String username;
    private String password;

    public UserDto() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
