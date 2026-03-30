package com.bms.repository;

import com.bms.model.Theatre;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TheatreRepository {
    private final Map<String, Theatre> theatres = new HashMap<>();
    private final Map<String, List<Theatre>> cityToTheatres = new HashMap<>();

    public void addTheatre(Theatre theatre) {
        theatres.put(theatre.getId(), theatre);
        String cityId = theatre.getCity().getId();
        cityToTheatres.putIfAbsent(cityId, new ArrayList<>());
        cityToTheatres.get(cityId).add(theatre);
    }

    public Theatre getTheatre(String theatreId) {
        return theatres.get(theatreId);
    }

    public List<Theatre> getTheatresByCity(String cityId) {
        return cityToTheatres.getOrDefault(cityId, new ArrayList<>());
    }
}
