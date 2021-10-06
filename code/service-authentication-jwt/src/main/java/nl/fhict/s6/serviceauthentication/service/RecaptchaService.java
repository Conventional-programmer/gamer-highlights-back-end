package nl.fhict.s6.serviceauthentication.service;

import com.fasterxml.jackson.annotation.JsonAlias;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.security.Timestamp;


public class RecaptchaService {
    private String url = "https://www.google.com/recaptcha/api/siteverify";
    @Value("${google.server.key}")
    private String serverToken;
    private RestTemplate restTemplate;

    public RecaptchaService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    public boolean validateRecaptchaClientToken(String clientToken) {
        ResponseEntity<RecaptchaResponse> recaptchaResponseEntity = restTemplate.exchange(url, HttpMethod.POST,new HttpEntity<>(new RecaptchaParameters(serverToken,clientToken)),RecaptchaResponse.class);
        if(recaptchaResponseEntity.getStatusCodeValue()>=200 && recaptchaResponseEntity.getStatusCodeValue() <=300) {
            return recaptchaResponseEntity.getBody().success;
        }
        return false;
    }
    private class RecaptchaParameters {
        String secret;
        String response;

        public RecaptchaParameters(String secret, String response) {
            this.secret = secret;
            this.response = response;
        }
    }
    private class RecaptchaResponse
    {
        private Boolean success;
        private Timestamp challenge_ts;
        private String hostname;
        @JsonAlias("error-codes")
        private Integer[] errorCodes;

        public Boolean getSuccess() {
            return success;
        }

        public void setSuccess(Boolean success) {
            this.success = success;
        }

        public Timestamp getChallenge_ts() {
            return challenge_ts;
        }

        public void setChallenge_ts(Timestamp challenge_ts) {
            this.challenge_ts = challenge_ts;
        }

        public String getHostname() {
            return hostname;
        }

        public void setHostname(String hostname) {
            this.hostname = hostname;
        }

        public Integer[] getErrorCodes() {
            return errorCodes;
        }

        public void setErrorCodes(Integer[] errorCodes) {
            this.errorCodes = errorCodes;
        }
    }
}
