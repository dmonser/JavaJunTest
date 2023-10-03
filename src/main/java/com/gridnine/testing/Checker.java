package com.gridnine.testing;

import java.util.List;

@FunctionalInterface
public interface Checker {
    void check(List<Segment> segments);
}
