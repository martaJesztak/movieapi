package com.jesztak.movieservice.service;

import com.jesztak.movieservice.model.Movie;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
public class MovieService {

    public final String URI = "http://www.omdbapi.com/?apikey=3d8b8656&";
    public final String titleParamKey = "&t=";

    public Movie findMovieByTitle(String titleSearchValue) {
        String searchURI = URI + titleParamKey + titleSearchValue;
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> result = restTemplate.exchange(searchURI, HttpMethod.GET, entity, String.class);

        JSONObject jsonObject = new JSONObject(result.getBody());
        Movie movie = new Movie();
        movie.setTitle(jsonObject.getString("Title"));
        movie.setDirector(jsonObject.getString("Director"));
        movie.setActors(jsonObject.getString("Actors"));
        movie.setYear(jsonObject.getInt("Year"));

        return movie;
    }

}
