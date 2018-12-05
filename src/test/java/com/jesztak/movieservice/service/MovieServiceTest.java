package com.jesztak.movieservice.service;

import com.jesztak.movieservice.model.Movie;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MovieServiceTest {

    @Mock
    OMDBService omdbService;

    @Mock
    YouTubeService youTubeService;

    @InjectMocks
    MovieService movieService = new MovieService();

    @Test
    public void findMovieTrailerByTitleTest() {
        Movie omdbMovieDetails = Movie.builder()
                            .actors("Glen Hansard, Markéta Irglová, Hugh Walsh, Gerard Hendrick")
                            .director("John Carney")
                            .writer("John Carney")
                            .year("2007")
                            .genre("Drama, Music, Romance")
                            .imdbRating("7.9")
                            .runtime("86 min")
                            .plot("A modern-day musical about a busker and an immigrant and their eventful week in Dublin, as they write, rehearse and record songs that tell their love story.")
                            .build();

        Movie outputMovie = omdbMovieDetails;
        outputMovie.setTrailerSource("https://www.youtube.com/embed/FWJIylZ8VyM?playlist=FWJIylZ8VyM&loop=1");

        when(omdbService.findMovieByTitle("Once"))
                .thenReturn(omdbMovieDetails);

        when(youTubeService.findMovieTrailerInfo("Once"))
                .thenReturn("https://www.youtube.com/embed/FWJIylZ8VyM?playlist=FWJIylZ8VyM&loop=1");

        Assert.assertEquals(outputMovie, movieService.findMovieTrailerByTitle("Once"));

    }

}