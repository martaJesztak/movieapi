package com.jesztak.movieservice.service;

import com.jesztak.movieservice.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

    @Autowired
    OMDBService omdbService;

    @Autowired
    YouTubeService youTubeService;

    public Movie findMovieTrailerByTitle(String title) {
        Movie movie = omdbService.findMovieByTitle(title);
        youTubeService.addMovieTrailerInfo(movie);
        return movie;
    }
}
