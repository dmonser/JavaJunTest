import com.gridnine.testing.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class FlightsFilterTest {


    @ParameterizedTest
    @MethodSource("addSource")
    public void testCheck(FlightsFilter sut ,List<Flight> flights, int expectedStatus) {
        System.out.println("Start check test");

        //act
        List<Flight> result = sut.check(flights);

        //assert
        Assertions.assertEquals(result.size(), expectedStatus);
    }

    public static Stream<Arguments> addSource() {
        List<Flight> flights = FlightBuilder.createFlights();
        FlightsFilter filter1 = new FlightsFilter();

        FlightsFilter filter2 = new FlightsFilter();
        filter2.addChecker("1", FiltrationRules.createBeforeCheck());
        filter2.addChecker("2", FiltrationRules.createAfterCheck());
        filter2.addChecker("3", FiltrationRules.createTimeCheck());

        return Stream.of(
                Arguments.of(filter1 ,flights, 6),
                Arguments.of(filter2, flights, 2)
        );
    }
}
