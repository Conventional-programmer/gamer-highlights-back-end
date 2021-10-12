package nl.fhict.s6.serviceauthentication.dto;

public class RefreshTokenResponse {
    private String response;

    public RefreshTokenResponse() {
    }

    public RefreshTokenResponse(String response) {
        this.response = response;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
