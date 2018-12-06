package com.jesztak.movieservice.controller;

import com.jesztak.movieservice.model.Movie;
import com.jesztak.movieservice.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    MovieService movieService;

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @PostMapping("/")
    public String searchMovieTrailers(Model model, @RequestParam("search") String searchPhrase){
        try {
            List<Movie> movies = movieService.findMoviesByTitle(searchPhrase);
            if (movies!=null) {
                model.addAttribute("movies", movies);
            }
            return "index";
        } catch (RestClientException e) {
            return "error";
        }
    }
}
