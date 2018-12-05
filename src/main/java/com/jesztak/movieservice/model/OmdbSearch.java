package com.jesztak.movieservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OmdbSearch {

    @JsonProperty("Search")
    private OmdbMovieShort[] movies;

    @JsonProperty("totalResults")
    private String totalResults;

}
