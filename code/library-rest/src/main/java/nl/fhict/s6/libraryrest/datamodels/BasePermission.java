package nl.fhict.s6.libraryrest.datamodels;

import java.util.List;

public class BasePermission implements Permission{
    private Long userId;
    private String subject;
    private List<String> roles;

    public BasePermission(Long userId, String subject, List<String> roles) {
        this.userId = userId;
        this.subject = subject;
        this.roles = roles;
    }

    @Override
    public Long getUserId() {
        return userId;
    }

    @Override
    public String getSubject() {
        return subject;
    }

    @Override
    public List<String> getRoles() {
        return roles;
    }
}
