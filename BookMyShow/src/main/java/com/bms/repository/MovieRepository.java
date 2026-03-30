package com.bms.repository;

import com.bms.model.Movie;
import java.util.HashMap;
import java.util.Map;

public class MovieRepository {
    private final Map<String, Movie> movies = new HashMap<>();

    public void addMovie(Movie movie) {
        movies.put(movie.getId(), movie);
    }

    public Movie getMovie(String movieId) {
        return movies.get(movieId);
    }

    public void removeMovie(String movieId) {
        movies.remove(movieId);
    }
}
