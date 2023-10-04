package com.gridnine.testing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FlightsFilter implements Checker {

    private final Map<String, Checker> checkers = new HashMap<>();

    public void addChecker(String name, Checker checker) {
        checkers.put(name, checker);
    }

    public void filter(String name, List<Flight> flights) {
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

    @Override
    public List<Flight> check(List<Flight> flights) {
        if (checkers.isEmpty()) {
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
