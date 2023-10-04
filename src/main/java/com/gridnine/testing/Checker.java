package com.gridnine.testing;

import java.util.List;

@FunctionalInterface
public interface Checker {
    List<Flight> check(List<Flight> flights);
}
