package com.jesztak.movieservice.service;

import com.jesztak.movieservice.model.Movie;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.net.UnknownHostException;
import java.util.Arrays;

@Service
public class OMDBService {

    @Value("${omdb.key}")
    private String apikey;
    private String URI = "http://www.omdbapi.com/?apikey=";
    private String titleParamKey = "&t=";

    public Movie findMovieByTitle(String titleSearchValue) throws RestClientException {

        Movie movie = new Movie();

        String searchURI = URI + apikey + titleParamKey + titleSearchValue;
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> result = restTemplate.exchange(searchURI, HttpMethod.GET, entity, String.class);

        JSONObject jsonObject = new JSONObject(result.getBody());
        movie.setTitle(jsonObject.getString("Title"));
        movie.setDirector(jsonObject.getString("Director"));
        movie.setActors(jsonObject.getString("Actors"));
        movie.setYear(jsonObject.getInt("Year"));

        return movie;
    }

}
