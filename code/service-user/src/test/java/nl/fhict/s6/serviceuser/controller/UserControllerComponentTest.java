package nl.fhict.s6.serviceuser.controller;

import nl.fhict.s6.serviceuser.dto.UserDto;
import org.apache.coyote.Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("dev")
public class UserControllerComponentTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void changeUsername() throws Exception {
        UserDto userDto = new UserDto();
        userDto.setUserId(1L);
        userDto.setUsername("berend");
        Long id = 1L;
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        requestHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<UserDto> userDtoHttpEntity = new HttpEntity<>(userDto, requestHeaders);
        String url = String.format("http://localhost:%d/game-highlights/api/user/%d",port,id);
        ResponseEntity<Void> responseEntity =this.restTemplate.exchange(url, HttpMethod.PUT,userDtoHttpEntity,Void.class);
        System.out.println(responseEntity.getHeaders().toString());
        assertTrue(!responseEntity.getStatusCode().isError());
    }
}
