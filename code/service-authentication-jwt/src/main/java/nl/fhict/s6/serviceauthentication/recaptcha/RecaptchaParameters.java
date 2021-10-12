package nl.fhict.s6.serviceauthentication.recaptcha;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

public class RecaptchaParameters {
    private String secret;
    private String response;

    public RecaptchaParameters() {
    }

    public RecaptchaParameters(String secret, String response) {
        this.secret = secret;
        this.response = response;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
    public MultiValueMap getForm()
    {
        MultiValueMap<String, Object> parts = new LinkedMultiValueMap<>();
        parts.add("secret", secret);
        parts.add("response",response);
        return parts;
    }
}
