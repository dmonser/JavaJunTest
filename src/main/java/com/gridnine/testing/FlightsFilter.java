package com.gridnine.testing;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FlightsFilter {

    private final Map<String, Checker> checkers = new HashMap<>();

    private final List<Flight> flights;

    public FlightsFilter(List<Flight> flights) {
        this.flights = flights;
    }

    public List<Flight> getFlights() {
        return flights;
    }

    public void addChecker(String name, Checker checker) {
        checkers.put(name, checker);
    }
}
