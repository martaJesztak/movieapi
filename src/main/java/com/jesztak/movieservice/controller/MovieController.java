package com.jesztak.movieservice.controller;

import com.jesztak.movieservice.model.Movie;
import com.jesztak.movieservice.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class MovieController {

    @Autowired
    MovieService movieService;

    @RequestMapping(value="/movie", method=GET, params = "title")
    public Movie findMovie(@RequestParam("title") String title) {
        Movie movie = movieService.findMovieByTitle(title);
        return movieService.findMovieByTitle(title);
    }
}
