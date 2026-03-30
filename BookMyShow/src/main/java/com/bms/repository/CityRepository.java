package com.bms.repository;

import com.bms.model.City;
import java.util.HashMap;
import java.util.Map;

public class CityRepository {
    private final Map<String, City> cities = new HashMap<>();

    public void addCity(City city) {
        cities.put(city.getId(), city);
    }

    public City getCity(String cityId) {
        return cities.get(cityId);
    }
}
