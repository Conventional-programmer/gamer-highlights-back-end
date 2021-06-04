package nl.fhict.s6.serviceimage.controller;

import nl.fhict.s6.serviceimage.dto.ContentType;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("dev")
public class ImageControllerTest {
    @LocalServerPort
    private int port;
    @Autowired
    TestRestTemplate restTemplate;

    String baseUrl = "http://localhost:%d/image";

    @Test
    void getImageWithMediaType() throws IOException {
        String url = baseUrl+ "/%s/%s";
        url = String.format(url,port, ContentType.PROFILE,"profilepic");
        System.out.println(url);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("userId",String.valueOf(1));
        HttpEntity httpEntity = new HttpEntity<>(httpHeaders);
        ResponseEntity<byte[]> response = restTemplate.exchange(url,HttpMethod.GET,httpEntity, byte[].class);
        assertTrue(response.getStatusCodeValue()>=200 && response.getStatusCodeValue() <=300);
        URL imageUrl = getClass().getResource("/static/profile/profilepic.jpg");
        InputStream in = imageUrl.openStream();
        byte[] media = IOUtils.toByteArray(in);
        assertTrue(Arrays.equals(response.getBody(), media),"Wrong content");;
    }

    @Test
    void uploadImage() {
    }
}
