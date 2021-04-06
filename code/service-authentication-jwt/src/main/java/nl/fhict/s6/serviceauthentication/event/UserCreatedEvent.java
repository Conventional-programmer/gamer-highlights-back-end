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

}
