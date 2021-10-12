package nl.fhict.s6.serviceauthentication.recaptcha;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.security.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class RecaptchaService {
    private String url = "https://www.google.com/recaptcha/api/siteverify";
    @Value("${google.server.key}")
    private String serverToken;
    private RestTemplate restTemplate;

    public RecaptchaService() {
        this.restTemplate = restTemplate = new RestTemplate();
        List<HttpMessageConverter<?>> converters = new ArrayList<>();
        converters.add(new MappingJackson2HttpMessageConverter());
        converters.add(new FormHttpMessageConverter());
        restTemplate.setMessageConverters(converters);
    }
    public boolean validateRecaptchaClientToken(String clientToken) throws JsonProcessingException {
        HttpHeaders jsonHeaders = new HttpHeaders();
        jsonHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        RecaptchaParameters recaptchaParameters = new RecaptchaParameters(serverToken,clientToken);
        HttpEntity parameter = new HttpEntity<>(recaptchaParameters.getForm(),jsonHeaders);
        ResponseEntity<RecaptchaResponse> recaptchaResponseEntity = restTemplate.exchange(url, HttpMethod.POST,parameter,RecaptchaResponse.class);
        if(recaptchaResponseEntity.getStatusCodeValue()>=200 && recaptchaResponseEntity.getStatusCodeValue() <=300) {
            String[] errorCodes = recaptchaResponseEntity.getBody().getErrorCodes();
            if(errorCodes != null && errorCodes.length > 0)
            {
                System.out.println(errorCodes[0]);
            }
            return recaptchaResponseEntity.getBody().getSuccess();
        }
        System.out.println("Bad response");
        return false;
    }
}
