package com.jesztak.movieservice.service;

import com.jesztak.movieservice.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import java.util.ArrayList;
import java.util.List;


@Service
public class MovieService {

    @Autowired
    OMDBService omdbService;

    @Autowired
    YouTubeService youTubeService;

    public Movie findMovieDeatailsByTitle(String title) throws RestClientException {
        Movie movie = omdbService.findMovieByTitle(title);
        movie.setTrailerSource(youTubeService.findMovieTrailerInfo(movie.getTitle()));
        if (movie.getTrailerSource()!= null) {
            return movie;
        } else {
            return null;
        }
    }

    public List<Movie> findMoviesByTitle(String title) throws RestClientException {
        String[] movieTitles = omdbService.searchMovieTitles(title);
        if (movieTitles!=null) {
            List<Movie> movies = new ArrayList<>();
            for (String movieTitle: movieTitles) {
                Movie movie = findMovieDeatailsByTitle(movieTitle);
                if (movie != null) {
                    movies.add(movie);
                }
            }
            return movies;
        } else {
            return null;
        }
    }
}
