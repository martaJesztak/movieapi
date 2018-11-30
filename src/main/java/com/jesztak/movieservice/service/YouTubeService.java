package com.jesztak.movieservice.service;

import com.jesztak.movieservice.model.Movie;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
public class YouTubeService {

    @Value("${youtube.key}")
    private String apikey;


    public void addMovieTrailerInfo(Movie movie) throws RestClientException {
        String URI = "https://www.googleapis.com/youtube/v3/search?key=%s&part=%s&maxresults=%s&q=%s";
        String partValue = "snippet";
        int maxresult = 1;
        String searchValue = movie.getTitle() + "-movie-trailer";
        String searchURI = String.format(URI,apikey,partValue,maxresult,searchValue);

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> result = restTemplate.exchange(searchURI, HttpMethod.GET, entity, String.class);
        JSONObject jsonObject = new JSONObject(result.getBody());

        String videoId = jsonObject.getJSONArray("items")
                                    .getJSONObject(0)
                                    .getJSONObject("id")
                                    .getString("videoId");

        String trailerSource = "https://www.youtube.com/embed/%s?playlist=%s&loop=1";
        trailerSource = String.format(trailerSource, videoId, videoId);
        movie.setTrailerSource(trailerSource);
    }
}
