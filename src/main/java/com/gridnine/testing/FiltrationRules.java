package com.gridnine.testing;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FiltrationRules {
    public static Checker createBeforeCheck() {
        return new Checker() {
            @Override
            public List<Flight> check(List<Flight> flights) {

                LocalDateTime currentTime = LocalDateTime.now();
                List<Flight> result = new ArrayList<>();

                for (Flight flight : flights) {
                    List<Segment> segments = flight.getSegments();
                    LocalDateTime departureDate = segments.get(0).getDepartureDate();
                    if (departureDate.isBefore(currentTime)) {
                        result.add(flight);
                    }
                }
                System.out.println("Flights with departure before current time:\n"
                        + result);
                return result;
            }
        };
    }

    public static Checker createAfterCheck() {
        return new Checker() {
            @Override
            public List<Flight> check(List<Flight> flights) {
                List<Flight> result = new ArrayList<>();

                for (Flight flight : flights) {
                    List<Segment> segments = flight.getSegments();
                    LocalDateTime departureDate = segments.get(0).getDepartureDate();
                    LocalDateTime arrivalDate = segments.get(0).getArrivalDate();
                    if (departureDate.isAfter(arrivalDate)) {
                        result.add(flight);
                    }
                }
                System.out.println("Flights with departure after arrival:\n"
                        + result);
                return result;
            }
        };
    }

    public static Checker createTimeCheck() {
        return new Checker() {
            @Override
            public List<Flight> check(List<Flight> flights) {
                List<Flight> result = new ArrayList<>();
                Duration correct = Duration.ofHours(2);
                for (Flight flight : flights) {
                    List<Segment> segments = flight.getSegments();
                    if (segments.size() > 1) {
                        Duration totalTime = Duration.ZERO;
                        for (int i = 0; i < segments.size() - 1; i++) {
                            LocalDateTime firstArrival = segments.get(i).getArrivalDate();
                            LocalDateTime secondDeparture = segments.get(i + 1).getDepartureDate();
                            Duration duration = Duration.between(firstArrival, secondDeparture);
                            totalTime = totalTime.plus(duration);
                        }
                        if (totalTime.toHours() > 2) {
                            result.add(flight);
                        }
                    }
                }
                System.out.println("Flights with total time on earth is more than two hours:\n" + result);
                return result;
            }
        };
    }
}
