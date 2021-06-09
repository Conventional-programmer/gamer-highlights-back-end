package nl.fhict.s6.serviceimage.config;

import nl.fhict.s6.serviceimage.config.converters.ByteArrayMediaConverter;
import nl.fhict.s6.serviceimage.config.converters.StringContentTypeConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@EnableWebMvc
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new StringContentTypeConverter());
    }
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(new ByteArrayMediaConverter());
        converters.add(new MappingJackson2HttpMessageConverter());
    }
}
