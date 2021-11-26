package nl.fhict.s6.servicepost.controller;

import nl.fhict.s6.servicepost.dto.CommentDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("dev")
public class PostControllerComponentTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void getEntityById() {
        String url = String.format("http://localhost:%d/post/%d",port,1L);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("user_id",Long.toString(1L));
        ResponseEntity responseEntity = restTemplate.exchange(url,HttpMethod.GET,new HttpEntity<>(httpHeaders), CommentDto.class);
        assertTrue(responseEntity.getStatusCodeValue()>=200 && responseEntity.getStatusCodeValue()<=300);
    }

    @Test
    void getEntityByUserId() {
    }
}
