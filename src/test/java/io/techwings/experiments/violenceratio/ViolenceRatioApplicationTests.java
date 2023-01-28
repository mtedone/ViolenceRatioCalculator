package io.techwings.experiments.violenceratio;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class ViolenceRatioApplicationTests {

    double OPTIMAL_VIOLENCE_RATIO = 0;
    double WORST_VIOLENCE_RATIO = 1;

    @Test
    void zeroOneFactorVariablesGreaterThanOne_GetNormalisedToOne() {
        double availableResources = 2;
        long people = 0L;
        double averageExpectation=2;
        double willingness = 2;
        ViolenceRatioCalculator calculator = getCalculator(availableResources, people, averageExpectation, willingness);
        assertTrue(calculator.getAvailableResources() == 1);
        assertTrue(calculator.getAverageExpectation() == 1);
        assertTrue(calculator.getWillingness() == 1);
    }

    @Test
    void zeroOneFactorVariablesLowerThanZero_GetNormalisedToZero() {
        double availableResources = -1;
        long people = 0L;
        double averageExpectation=-1;
        double willingness = -1;
        ViolenceRatioCalculator calculator = getCalculator(availableResources, people, averageExpectation, willingness);
        assertTrue(calculator.getAvailableResources() == 0.0);
        assertTrue(calculator.getAverageExpectation() == 0.0);
        assertTrue(calculator.getWillingness() == 0.0);
    }

    @Test
    void optimalValuesLeadsToNoViolence() {
        double availableResources = Long.MAX_VALUE;
        long people = 0L;
        double averageExpectation=0L;
        double willingness = Double.MAX_VALUE;
        double ratio = getRatio(availableResources, people, averageExpectation, willingness);
        assertTrue(ratio == ViolenceRatioCalculator.VIOLENCE_LOWEST_RATIO);
    }

    @Test
    void EgoDrivenValuesLeadsToHighestViolence() {
        double availableResources = 0;
        long people = Long.MAX_VALUE;
        double averageExpectation=Double.MAX_VALUE;
        double willingness = 0L;
        double ratio = getRatio(availableResources, people, averageExpectation, willingness);
        assertTrue(ratio == ViolenceRatioCalculator.VIOLENCE_HIGHEST_RATIO);
    }

    @Test
    void lowerResourcesLeadsToHigherViolence() {
        double availableResources = 1;
        long people = 1;
        double averageExpectation=1;
        double willingness = Double.MAX_VALUE;
        double highResourceRatio = getRatio(availableResources, people, averageExpectation, willingness);

        availableResources = 0.5;
        double lowResourceRatio = getRatio(availableResources, people, averageExpectation, willingness);
        assertTrue(lowResourceRatio > highResourceRatio);
    }

    @Test
    void higherExpectationsLeadsToHigherViolence() {
        double availableResources = Double.MAX_VALUE;
        long people = 1;
        double averageExpectation=0.1;
        double willingness = Double.MAX_VALUE;
        double lowExpectationRatio = getRatio(availableResources, people, averageExpectation, willingness);

        averageExpectation = 0.9;
        double highExpectationRatio = getRatio(availableResources, people, averageExpectation, willingness);
        assertTrue(highExpectationRatio > lowExpectationRatio);
    }

    @Test
    void lowerWillingnessLeadsToHigherViolence() {
        double availableResources = Double.MAX_VALUE;
        long people = 100;
        double averageExpectation=0.1;
        double willingness = 0.9;
        double highWillingnessRatio = getRatio(availableResources, people, averageExpectation, willingness);

        willingness = 0.1;
        double lowWillingnessRatio = getRatio(availableResources, people, averageExpectation, willingness);
        assertTrue(lowWillingnessRatio > highWillingnessRatio);
    }

    @Test
    void morePeopleLeadToHigherViolence() {
        long availableResources = Long.MAX_VALUE;
        long people = 1;
        double averageExpectation=1;
        double willingness = Double.MAX_VALUE;
        double lowPeopleRatio = getRatio(availableResources, people, averageExpectation, willingness);

        people = 100;
        double higherPeopleRatio = getRatio(availableResources, people, averageExpectation, willingness);

        assertTrue(higherPeopleRatio > lowPeopleRatio);
    }

    @Test
    void noResourcesShouldLeadToHighestViolence() {
        long availableResources = 0;
        long people = 100;
        double averageExpectation=0;
        double willingness = Double.MAX_VALUE;
        double ratio = getRatio(availableResources, people, averageExpectation, willingness);
        assertTrue(ratio == ViolenceRatioCalculator.VIOLENCE_HIGHEST_RATIO);
    }

    private static double getRatio(double availableResources, long people, double averageExpectation, double willingness) {
        ViolenceRatioCalculator calculator = getCalculator(availableResources, people, averageExpectation, willingness);
        double ratio = calculator.getRatio();
        return ratio;
    }

    private static ViolenceRatioCalculator getCalculator(double availableResources,
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
