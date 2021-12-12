package nl.fhict.s6.serviceimage.controller;

import nl.fhict.s6.serviceimage.dto.ContentType;
import nl.fhict.s6.serviceimage.dto.ImageDto;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("dev")
public class ImageControllerTests {
    @LocalServerPort
    private int port;
    @Autowired
    TestRestTemplate restTemplate;

    String baseUrl = "http://localhost:%d/image/";

    @Test
    public void uploadImage() throws IOException {
        Path directoryPath = Paths.get("images/profile/profile.jpg");
        byte[] media = FileUtils.readFileToByteArray(directoryPath.toFile());
        String url = String.format(baseUrl,port);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        headers.set("User-Id",String.valueOf(1));
        // This nested HttpEntiy is important to create the correct
        // Content-Disposition entry with metadata "name" and "filename"
        MultiValueMap<String, String> fileMap = new LinkedMultiValueMap<>();
        ContentDisposition contentDisposition = ContentDisposition
                .builder("form-data")
                .name("file")
                .filename("profile.jpg")
                .build();
        fileMap.add(HttpHeaders.CONTENT_DISPOSITION, contentDisposition.toString());
        HttpEntity<byte[]> fileEntity = new HttpEntity<>(media, fileMap);
        ImageDto imageDto = new ImageDto(ContentType.PROFILE);
        HttpHeaders jsonHeaders = new HttpHeaders();
        jsonHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<ImageDto> imageEntity = new HttpEntity<>(imageDto,jsonHeaders);
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", fileEntity);
        body.add("image",imageEntity);
        HttpEntity<MultiValueMap<String, Object>> requestEntity =
                new HttpEntity<>(body, headers);;
        ResponseEntity<String> response = restTemplate.exchange(url,HttpMethod.POST,requestEntity, String.class);
        System.out.println(response.getBody());
        System.out.println(response.getStatusCode().getReasonPhrase());
        assertTrue(response.getStatusCodeValue()>=200 && response.getStatusCodeValue() <=300);
    }
    @Test
    public void getImageWithMediaType() throws IOException {
        String url = baseUrl+ "%s/%s";
        url = String.format(url,port, ContentType.PROFILE.toString().toLowerCase(),"profilepic.jpg");
        System.out.println(url);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("User-Id",String.valueOf(1));
        HttpEntity httpEntity = new HttpEntity<>(httpHeaders);
        ResponseEntity<byte[]> response = restTemplate.exchange(url,HttpMethod.GET,httpEntity, byte[].class);
        System.out.println(response.getStatusCode().getReasonPhrase());
        assertTrue(response.getStatusCodeValue()>=200 && response.getStatusCodeValue() <=300);
    }
    @Test
    public void getImageWithMediaTypeNegativeId() throws IOException {
        String url = baseUrl+ "/%s/%s";
        url = String.format(url,port, ContentType.PROFILE.toString().toLowerCase(),"profilepic");
        System.out.println(url);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("User-Id",String.valueOf(-1));
        HttpEntity httpEntity = new HttpEntity<>(httpHeaders);
        ResponseEntity<byte[]> response = restTemplate.exchange(url,HttpMethod.GET,httpEntity, byte[].class);
        System.out.println(response.getStatusCode().getReasonPhrase());
        assertTrue(response.getStatusCodeValue()== 401);
    }
}
