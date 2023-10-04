package com.gridnine.testing;

import java.util.List;

public class Main {
    private static final String CHECK_BEFORE = "After";
    private static final String CHECK_AFTER = "Before";
    private static final String CHECK_TIME = "Time";
    private static final FlightsFilter FLIGHTS_FILTER = new FlightsFilter();

    public static void main(String[] args) {
        List<Flight> flights = FlightBuilder.createFlights();

        FLIGHTS_FILTER.addChecker(CHECK_BEFORE, FiltrationRules.createAfterCheck());
        FLIGHTS_FILTER.addChecker(CHECK_AFTER, FiltrationRules.createBeforeCheck());
        FLIGHTS_FILTER.addChecker(CHECK_TIME, FiltrationRules.createTimeCheck());

        List<Flight> filteredFlights = FLIGHTS_FILTER.check(flights);
        System.out.println("Filtered flights:\n" + filteredFlights + " --- " + filteredFlights.size());
    }
}