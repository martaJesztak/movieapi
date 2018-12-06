package com.jesztak.movieservice.service;

import com.jesztak.movieservice.model.Movie;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MovieServiceTest {

    @Mock
    private OMDBService omdbService;

    @Mock
    private YouTubeService youTubeService;

    @InjectMocks
    private MovieService movieService = new MovieService();

    private Movie movieWithOmdbDetails;
    private Movie movieWithAllDetails;

    public void buildMovieWithOmdbDetails(){
        this.movieWithOmdbDetails = Movie.builder()
                .title("Test title")
                .actors("Test Actor")
                .director("Test Director")
                .writer("Test Writer")
                .year("Test Year")
                .genre("Test Genre")
                .imdbRating("Test Rating")
                .runtime("Test runtime")
                .plot("Test plot")
                .build();
    }

    public void buildMovieWithAllDeatils(){
        this.movieWithAllDetails = Movie.builder()
                .title("Test title")
                .actors("Test Actor")
                .director("Test Director")
                .writer("Test Writer")
                .year("Test Year")
                .genre("Test Genre")
                .imdbRating("Test Rating")
                .runtime("Test runtime")
                .plot("Test plot")
                .trailerSource("Test source")
                .build();
    }

    @Test
    public void findMovieDetailsByTitle_returnMovie() {

        buildMovieWithAllDeatils();
        buildMovieWithOmdbDetails();

        when(omdbService.findMovieByTitle("Test title"))
                .thenReturn(movieWithOmdbDetails);
        when(youTubeService.findMovieTrailerInfo("Test title"))
                .thenReturn("Test source");

        Assert.assertEquals(movieWithAllDetails, movieService.findMovieDeatailsByTitle("Test title"));

    }

    @Test
    public void findMoviesByTitle_noMovieTitlesFound_ReturnNull() {
        when(omdbService.searchMovieTitles("test"))
                .thenReturn(null);

        Assert.assertNull(movieService.findMoviesByTitle("test"));
    }

    @Test
    public void findMoviesByTitle_oneMovieTitleFound_ReturnMovieList() {
        buildMovieWithOmdbDetails();
        buildMovieWithAllDeatils();
        String[] movieTitles = {"Test title"};

        when(omdbService.searchMovieTitles("Test input"))
                .thenReturn(movieTitles);
        when(omdbService.findMovieByTitle("Test title"))
                .thenReturn(movieWithOmdbDetails);
        when(youTubeService.findMovieTrailerInfo("Test title"))
                .thenReturn("Test source");

        List<Movie> movies = new ArrayList<>();
        movies.add(movieWithAllDetails);

        Assert.assertEquals(movies, movieService.findMoviesByTitle("Test input"));
    }
}