package com.jesztak.movieservice.service;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class YouTubeService {

    @Value("${youtube.key}")
    private String apikey;

    @Value("${youtube.uri}")
    private String URI;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    HttpEntity<String> httpEntity;

    public String findMovieTrailerInfo(String title) throws RestClientException {
        String partValue = "snippet";
        int maxresult = 1;
        String searchValue = title + "-movie-trailer";
        String searchURI = String.format(URI,apikey,partValue,maxresult,searchValue);

        ResponseEntity<String> result = restTemplate.exchange(searchURI, HttpMethod.GET, httpEntity, String.class);

        JSONObject jsonObject = new JSONObject(result.getBody());
        String videoId = jsonObject.getJSONArray("items")
                                    .getJSONObject(0)
                                    .getJSONObject("id")
                                    .getString("videoId");

        String trailerSource = "https://www.youtube.com/embed/%s?playlist=%s&loop=1";
        trailerSource = String.format(trailerSource, videoId, videoId);

        return trailerSource;
    }
}
