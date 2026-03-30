package com.bms.service;

import com.bms.model.City;
import com.bms.model.Movie;
import com.bms.model.SeatStatus;
import com.bms.model.Show;
import com.bms.model.ShowSeat;
import com.bms.model.Theatre;
import com.bms.repository.ShowRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import java.util.concurrent.ConcurrentHashMap;

public class ShowService {
    private final ShowRepository showRepository;
    
    private final Map<String, Object> screenLocks = new ConcurrentHashMap<>();
    private final Map<String, List<ShowSeat>> showSeatsMap = new ConcurrentHashMap<>();

    public ShowService(ShowRepository showRepository) {
        this.showRepository = showRepository;
    }

    public boolean addShow(Show newShow) {
        String screenId = newShow.getScreen().getId();
        screenLocks.putIfAbsent(screenId, new Object());
        
        synchronized (screenLocks.get(screenId)) {
            List<Show> existingShows = showRepository.getShowsByScreen(screenId);
            for (Show show : existingShows) {
                if (newShow.getStartTime().isBefore(show.getEndTime()) && 
                    newShow.getEndTime().isAfter(show.getStartTime())) {
                    System.out.println("Cannot add show! Time overlap for screen " + screenId);
                    return false;
                }
            }
            showRepository.addShow(newShow);
            
            List<ShowSeat> showSeats = new ArrayList<>();
            double basePrice = 100.0; 
            newShow.getScreen().getSeats().forEach(seat -> {
                ShowSeat showSeat = new ShowSeat(newShow.getId() + "-" + seat.getId(), newShow, seat, SeatStatus.AVAILABLE, basePrice);
                showSeats.add(showSeat);
            });
            showSeatsMap.put(newShow.getId(), showSeats);
            return true;
        }
    }

    public Show getShow(String showId) {
        return showRepository.getShow(showId);
    }
    
    public void removeShow(String showId) {
        Show show = showRepository.getShow(showId);
        if (show != null) {
            String screenId = show.getScreen().getId();
            synchronized (screenLocks.get(screenId)) {
                showRepository.removeShow(showId);
                showSeatsMap.remove(showId);
            }
        }
    }
    
    public List<Show> getAllShows() {
        return showRepository.getAllShows();
    }
    
    public List<ShowSeat> getShowSeats(String showId) {
        return showSeatsMap.getOrDefault(showId, new ArrayList<>());
    }

    public Set<Theatre> getTheatresShowingMovie(Movie movie, City city) {
        List<Show> allShows = showRepository.getAllShows();
        Set<Theatre> theatres = new HashSet<>();
        for (Show show : allShows) {
            if (show.getMovie().getId().equals(movie.getId()) &&
                show.getScreen().getTheatre().getCity().getId().equals(city.getId())) {
                theatres.add(show.getScreen().getTheatre());
            }
        }
        return theatres;
    }

    public List<Show> getShowsInTheatre(Theatre theatre) {
        List<Show> allShows = showRepository.getAllShows();
        List<Show> shows = new ArrayList<>();
        for (Show show : allShows) {
            if (show.getScreen().getTheatre().getId().equals(theatre.getId())) {
                shows.add(show);
            }
        }
        return shows;
    }
}
