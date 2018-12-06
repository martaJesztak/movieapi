package com.jesztak.movieservice.controller;

import com.jesztak.movieservice.model.Movie;
import com.jesztak.movieservice.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class MovieRestController {

    @Autowired
    MovieService movieService;

    @RequestMapping(value="/movie", method=GET, params = "title")
    public Movie findMovie(@RequestParam("title") String title) {
        return movieService.findMovieDeatailsByTitle(title);
    }

    @RequestMapping(value="/movies", method=GET, params = "title")
    public List<Movie> findMovies(@RequestParam("title") String title) {
        return movieService.findMoviesByTitle(title);
    }


}
