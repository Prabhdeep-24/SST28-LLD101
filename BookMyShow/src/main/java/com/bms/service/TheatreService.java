package com.bms.service;

import com.bms.model.City;
import com.bms.model.Screen;
import com.bms.model.Theatre;
import com.bms.repository.TheatreRepository;

import java.util.List;

public class TheatreService {
    private final TheatreRepository theatreRepository;

    public TheatreService(TheatreRepository theatreRepository) {
        this.theatreRepository = theatreRepository;
    }

    public void addTheatre(Theatre theatre) {
        theatreRepository.addTheatre(theatre);
    }
    
    public void addScreenToTheatre(String theatreId, Screen screen) {
        Theatre theatre = theatreRepository.getTheatre(theatreId);
        if (theatre != null) {
            theatre.addScreen(screen);
        }
    }

    public List<Theatre> showTheatres(City city) {
        return theatreRepository.getTheatresByCity(city.getId());
    }

    public Theatre getTheatre(String theatreId) {
        return theatreRepository.getTheatre(theatreId);
    }
}
