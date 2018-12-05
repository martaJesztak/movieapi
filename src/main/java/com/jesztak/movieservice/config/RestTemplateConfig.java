package com.jesztak.movieservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Configuration
public class RestTemplateConfig {

    @Autowired
    private HttpHeaders httpHeaders;

    @Bean
    public HttpHeaders HttpHeaders() {

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");

        return headers;
    }

    @Bean
    public HttpEntity<String> HttpEntity() {
        return new HttpEntity<String>("parameters", httpHeaders);
    }

    @Bean
    public RestTemplate Resttemplate() {
        return new RestTemplate();
    }
}
