package nl.fhict.s6.serviceauthentication.dto.response;

import java.util.List;

public class VerifyTokenResponse {
    private boolean valid;
    private String id;
    private String email;
    private List<String> authorities;

    public VerifyTokenResponse(String id, String email, List<String> authorities) {
        this.id = id;
        this.email = email;
        this.authorities = authorities;
        this.valid = true;
    }

    public VerifyTokenResponse() {
        this.valid = false;
    }

    public boolean isValid() {
        return valid;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public List<String> getAuthorities() {
        return authorities;
    }

}
