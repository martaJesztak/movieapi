package com.jesztak.movieservice.service;

import com.jesztak.movieservice.model.Movie;
import com.jesztak.movieservice.model.OmdbMovieShort;
import com.jesztak.movieservice.model.OmdbSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class OMDBService {

    @Value("${omdb.key}")
    private String apikey;

    @Value("${omdb.uri}")
    private String URI;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    HttpEntity<String> httpEntity;


    public Movie findMovieByTitle(String title) throws RestClientException {
        String titleParamKey ="&t=";
        String searchURI = URI + apikey + titleParamKey + title;
        ResponseEntity<Movie> result = restTemplate.exchange(searchURI, HttpMethod.GET, httpEntity, Movie.class);

        return result.getBody();
    }

    public String[] searchMovieTitles (String title) throws RestClientException{
        String searchParamKey ="&s=";
        String searchURI = URI + apikey + searchParamKey + title;
        ResponseEntity<OmdbSearch> result = restTemplate.exchange(searchURI, HttpMethod.GET, httpEntity, OmdbSearch.class);

        OmdbMovieShort[] movies = result.getBody().getMovies();

        if (movies!=null) {
            String[] movieTitles = new String[movies.length];

            for (int i=0; i < movies.length; i++) {
                movieTitles[i] = movies[i].getTitle();
            }

            return movieTitles;
        } else {
            return null;
        }
    }
}
