package nl.fhict.s6.servicepost.context;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.ParameterizedType;

public class BaseHttpContext<T,Id> {
    public RestTemplate restTemplate;
    public String baseUrl;
    private final Class<T> persistentClass;

    public BaseHttpContext(String serverIp,Integer port, String baseUrl) {
        this.restTemplate = new RestTemplate();
        this.baseUrl = String.format("http://%s:%d/%s",serverIp,port,baseUrl);
        persistentClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass())
                .getActualTypeArguments()[0];
    }
    public T post(T object)
    {
       return restTemplate.exchange(baseUrl, HttpMethod.POST,new HttpEntity<>(object),persistentClass).getBody();
    }

    public T getDtoById(Id id)
    {
        String url = baseUrl + id;
        T dto = restTemplate.getForObject(url,persistentClass);
        return dto;
    }
}
