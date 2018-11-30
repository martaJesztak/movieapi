package com.jesztak.movieservice.controller;

import com.jesztak.movieservice.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {
    @Autowired
    MovieService movieService;

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @PostMapping("/")
    public String searchMovieTrailer(Model model, @RequestParam("search") String searchPhrase){
        model.addAttribute("movie",movieService.findMovieTrailerByTitle(searchPhrase));
        return "index";
    }
}
