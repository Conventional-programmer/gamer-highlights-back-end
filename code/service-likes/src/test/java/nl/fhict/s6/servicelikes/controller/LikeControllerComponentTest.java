package nl.fhict.s6.servicelikes.controller;

import nl.fhict.s6.servicelikes.dto.LikeDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("dev")
public class LikeControllerComponentTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void checkIfLikesAreReturned() throws Exception {
        String url = String.format("http://localhost:%d/game-highlights/api/like/post/%d",port,1L);
        Integer likes = 1533;
        ResponseEntity<LikeDto> likeResponse = this.restTemplate.exchange(url, HttpMethod.GET,null, LikeDto.class);
        assertTrue(likeResponse.getStatusCodeValue()>=200 && likeResponse.getStatusCodeValue() <=300);
        assertThat(likeResponse.getBody().getPostId()).isEqualTo(1L);
        assertThat(likeResponse.getBody().getUserDto().getUsername()).isEqualTo("bert");
    }
}
