package nl.fhict.s6.serviceauthentication.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import nl.fhict.s6.serviceauthentication.event.UserCreatedEvent;
import nl.fhict.s6.serviceauthentication.recaptcha.RecaptchaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class RecaptchaServiceTest {
    @Autowired
    RecaptchaService recaptchaService;
    @Test
    void validateRecaptchaClientToken() throws JsonProcessingException {
        Boolean clientToken = recaptchaService.validateRecaptchaClientToken("bert");
        assertFalse(clientToken);
    }
}
