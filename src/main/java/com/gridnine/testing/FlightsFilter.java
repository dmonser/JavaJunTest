package com.gridnine.testing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


//This class processing user implementation of filters
 public class FlightsFilter implements Checker {

    private final Map<String, Checker> checkers = new HashMap<>();

    //Add filter in map
    public void addChecker(String name, Checker checker) {
        checkers.put(name, checker);
    }

    //Execute filter by name
    public void filtration(String name, List<Flight> flights) {
        if (checkers.containsKey(name)) {
            Checker checker = checkers.get(name);
            checker.check(flights);
        } else {
            throw new IllegalArgumentException(
                    "Unknown checker"
            );
        }
    }

    public Map<String, Checker> getCheckers() {
        return checkers;
    }

    //This method processing all custom filters added to the map, and exclude their result from test list of flight
    @Override
    public List<Flight> check(List<Flight> flights) {
        if (checkers.isEmpty()) {
            System.out.println("Filters are not set");
            return flights;
        }

        List<Flight> excludeFlights = new ArrayList<>();

        for (var entry : checkers.entrySet()) {
            Checker checker = entry.getValue();
            List<Flight> result = checker.check(flights);
            excludeFlights.addAll(result);
        }

        List<Flight> result = new ArrayList<>(flights);
        result.removeAll(excludeFlights);

        return result;
    }
}
