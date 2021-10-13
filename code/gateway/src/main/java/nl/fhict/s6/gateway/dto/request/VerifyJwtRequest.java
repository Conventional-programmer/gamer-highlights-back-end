package nl.fhict.s6.gateway.dto.request;

public class VerifyJwtRequest {
    private String token;

    public VerifyJwtRequest(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
