package com.bms.service;

import com.bms.model.City;
import com.bms.model.Movie;
import com.bms.model.Show;
import com.bms.repository.MovieRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MovieService {
    private final MovieRepository movieRepository;
    private final ShowService showService;

    public MovieService(MovieRepository movieRepository, ShowService showService) {
        this.movieRepository = movieRepository;
        this.showService = showService;
    }

    public void addMovie(Movie movie) {
        movieRepository.addMovie(movie);
    }

    public Movie getMovie(String movieId) {
        return movieRepository.getMovie(movieId);
    }

    public void removeMovie(String movieId) {
        movieRepository.removeMovie(movieId);
    }

    public Set<Movie> showMoviesInCity(City city) {
        List<Show> allShows = showService.getAllShows();
        Set<Movie> moviesInCity = new HashSet<>();
        
        for (Show show : allShows) {
            if (show.getScreen().getTheatre().getCity().getId().equals(city.getId())) {
                moviesInCity.add(show.getMovie());
            }
        }
        return moviesInCity;
    }
}
