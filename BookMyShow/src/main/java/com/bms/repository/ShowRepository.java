package com.bms.repository;

import com.bms.model.Show;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShowRepository {
    private final Map<String, Show> shows = new HashMap<>();
    
    // For quick lookup of shows in a screen to prevent overlap
    private final Map<String, List<Show>> screenToShows = new HashMap<>();

    public void addShow(Show show) {
        shows.put(show.getId(), show);
        String screenId = show.getScreen().getId();
        screenToShows.putIfAbsent(screenId, new ArrayList<>());
        screenToShows.get(screenId).add(show);
    }

    public Show getShow(String showId) {
        return shows.get(showId);
    }
    
    public List<Show> getShowsByScreen(String screenId) {
        return screenToShows.getOrDefault(screenId, new ArrayList<>());
    }
    
    public List<Show> getAllShows() {
        return new ArrayList<>(shows.values());
    }

    public void removeShow(String showId) {
        Show show = shows.remove(showId);
        if (show != null) {
            String screenId = show.getScreen().getId();
            List<Show> screenShows = screenToShows.get(screenId);
            if (screenShows != null) {
                screenShows.remove(show);
            }
        }
    }
}
