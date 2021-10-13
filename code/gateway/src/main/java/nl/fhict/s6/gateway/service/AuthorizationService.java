package nl.fhict.s6.gateway.service;

import nl.fhict.s6.gateway.dto.request.VerifyJwtRequest;
import nl.fhict.s6.gateway.dto.response.VerifyTokenResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AuthorizationService {
    @Value("${authentication.service.authorization.host}")
    private String authServerHost;

    @Value("${authentication.service.authorization.port}")
    private int authServerPort;
    RestTemplate restTemplate = new RestTemplate();


    public VerifyTokenResponse getVerifyTokenResponseByToken(String token)
    {
            String url = authServerHost + ":" + authServerPort + "/auth/token/verify";
            VerifyJwtRequest verifyRequest = new VerifyJwtRequest(token);
            HttpEntity<VerifyJwtRequest> parameter = new HttpEntity<>(verifyRequest);
            ResponseEntity<VerifyTokenResponse> verifyTokenResponseEntity = restTemplate.exchange(url, HttpMethod.POST,parameter, VerifyTokenResponse.class);

//          If response is OK, set authenticated to true
            if(verifyTokenResponseEntity.getStatusCodeValue()>=200 && verifyTokenResponseEntity.getStatusCodeValue() <=300)
            {
                System.out.println("request went through");
                VerifyTokenResponse verifyTokenResponse = verifyTokenResponseEntity.getBody();
                System.out.println("id: "+ verifyTokenResponse.getId());
                return verifyTokenResponse;
            }
            else {
                System.out.println(verifyTokenResponseEntity.getStatusCode().getReasonPhrase());
                return null;
            }
    }
}
