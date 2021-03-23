package nl.fhict.s6.servicelikes.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("dev")
public class LikeControllerComponentTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void checkIfLikesAreReturned() throws Exception {
        String url = String.format("http://localhost:%d/game-highlights/api/like/%d",port,1L);
        Integer likes = 1533;
        assertThat(this.restTemplate.getForObject(url,
                String.class)).contains(likes.toString());
    }
}
