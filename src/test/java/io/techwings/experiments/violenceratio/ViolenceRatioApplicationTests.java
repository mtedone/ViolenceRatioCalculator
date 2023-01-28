package io.techwings.experiments.violenceratio;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class ViolenceRatioApplicationTests {

    double OPTIMAL_VIOLENCE_RATIO = 0;
    double WORST_VIOLENCE_RATIO = 1;

    @Test
    void optimalValuesShouldLeadToNoViolence() {
        long availableResources = Long.MAX_VALUE;
        long people = Long.MIN_VALUE;
        double averageExpectation=Double.MIN_VALUE;
        double willingness = Double.MAX_VALUE;
        double ratio = getRatio(availableResources, people, averageExpectation, willingness);
        assertTrue(ratio == ViolenceRatioCalculator.VIOLENCE_LOWEST_RATIO);
    }

    private static double getRatio(long availableResources, long people, double averageExpectation, double willingness) {
        ViolenceRatioCalculator calculator = getCalculator(availableResources, people, averageExpectation, willingness);
        double ratio = calculator.getRatio();
        return ratio;
    }

    private static ViolenceRatioCalculator getCalculator(long availableResources,
                                                         long people,
                                                         double averageExpectation,
                                                         double willingness) {
        ViolenceRatioCalculator calculator = new ViolenceRatioCalculator.Builder()
                .withAvailableResources(availableResources)
                .withPeople(people)
                .withAverageExpectation(averageExpectation)
                .withWillingness(willingness)
                .build();
        return calculator;
    }


}
