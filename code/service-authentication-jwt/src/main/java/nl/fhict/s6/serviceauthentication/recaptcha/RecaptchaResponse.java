package nl.fhict.s6.serviceauthentication.recaptcha;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.time.LocalDateTime;

public class RecaptchaResponse {
    private Boolean success;
    private LocalDateTime challenge_ts;
    private String hostname;
    @JsonAlias("error-codes")
    private String[] errorCodes;

    public RecaptchaResponse() {
    }


    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public LocalDateTime getChallenge_ts() {
        return challenge_ts;
    }

    public void setChallenge_ts(LocalDateTime challenge_ts) {
        this.challenge_ts = challenge_ts;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String[] getErrorCodes() {
        return errorCodes;
    }

    public void setErrorCodes(String[] errorCodes) {
        this.errorCodes = errorCodes;
    }
}
