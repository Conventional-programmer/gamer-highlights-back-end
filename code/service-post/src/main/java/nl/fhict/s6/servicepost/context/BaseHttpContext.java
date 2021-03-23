package nl.fhict.s6.servicepost.context;

import org.springframework.web.client.RestTemplate;

import java.lang.reflect.ParameterizedType;

public class BaseHttpContext<T,Id> {
    public RestTemplate restTemplate;
    public String baseUrl;
    private final Class<T> persistentClass;

    public BaseHttpContext(String serverIp,Integer port, String baseUrl) {
        this.restTemplate = new RestTemplate();
        baseUrl = String.format("http://%s:%d/%s",serverIp,port,baseUrl);
        persistentClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass())
                .getActualTypeArguments()[0];
    }

    public T getDtoById(Id id)
    {
        String url = new StringBuilder(baseUrl).append(id).toString();
        T dto = restTemplate.getForObject(url,persistentClass);
        return dto;
    }
    /*public List<T> getDtosById(Id id)
    {
        String url = new StringBuilder(baseUrl).append(id).toString();
    }*/
}
