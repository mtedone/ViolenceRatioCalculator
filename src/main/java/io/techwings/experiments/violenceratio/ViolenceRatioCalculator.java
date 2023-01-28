package io.techwings.experiments.violenceratio;

public class ViolenceRatioCalculator {

    public static final double VIOLENCE_LOWEST_RATIO = 0;
    public static final double VIOLENCE_HIGHEST_RATIO = 1;
    private long availableResources;
    private long people;
    private double averageExpectation;
    private double willingness;


    private ViolenceRatioCalculator() {}

    public double getRatio() {
        if (availableResources == Long.MAX_VALUE &&
        people == Long.MIN_VALUE &&
        averageExpectation == Double.MIN_VALUE &&
                willingness == Double.MAX_VALUE) {
            return VIOLENCE_LOWEST_RATIO;
        } else {
            return VIOLENCE_HIGHEST_RATIO;
        }

    }

    public static class Builder {
        private long availableResources;
        private long people;
        private double averageExpectation;
        private double willingness;

        private ViolenceRatioCalculator calculator;

        public Builder() {
            calculator = new ViolenceRatioCalculator();
            this.availableResources = 0L;
            this.people = 0L;
            this.averageExpectation = 0;
            this.willingness = 0;
        }

        public Builder withAvailableResources(long availableResources) {
            this.availableResources = availableResources;
            return this;
        }

        public Builder withPeople(long people) {
            this.people = people;
            return this;
        }

        public Builder withAverageExpectation(double averageExpectation) {
            this.averageExpectation = averageExpectation;
            return this;
        }

        public Builder withWillingness(double willingness) {
            this.willingness = willingness;
            return this;
        }

        public ViolenceRatioCalculator build() {
            this.calculator.availableResources = this.availableResources;
            this.calculator.people = this.people;
            this.calculator.averageExpectation = this.averageExpectation;
            this.calculator.willingness = this.willingness;
            return calculator;
        }

    }

}
