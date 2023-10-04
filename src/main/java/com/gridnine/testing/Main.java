package com.gridnine.testing;

import java.util.List;

public class Main {
    private final static String CHECK_BEFORE = "After";
    private final static String CHECK_AFTER = "Before";
    private final static String CHECK_TIME = "Time";

    public static void main(String[] args) {
        List<Flight> flights = FlightBuilder.createFlights();
        FlightsFilter flightsFilter = new FlightsFilter();



        flightsFilter.addChecker(CHECK_BEFORE, FiltrationRules.createAfterCheck());
        flightsFilter.addChecker(CHECK_AFTER, FiltrationRules.createBeforeCheck());
//        flightsFilter.addChecker(CHECK_TIME, FiltrationRules.createTimeCheck());

        List<Flight> filteredFlights = flightsFilter.check(flights);
        System.out.println("Filtered flights:\n" + filteredFlights + " --- " + filteredFlights.size());

    }
}