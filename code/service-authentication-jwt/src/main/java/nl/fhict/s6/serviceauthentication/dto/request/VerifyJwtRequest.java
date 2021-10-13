package nl.fhict.s6.serviceauthentication.dto.request;

public class VerifyJwtRequest {
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
