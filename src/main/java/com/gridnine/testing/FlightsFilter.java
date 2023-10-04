package com.gridnine.testing;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FlightsFilter {

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
}
