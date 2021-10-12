package nl.fhict.s6.serviceauthentication.event;

public class UserCreatedEvent {
    Long userId;
    String username;

    public UserCreatedEvent() {
    }

    public UserCreatedEvent(Long userId, String username) {
        this.userId = userId;
        this.username = username;
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
