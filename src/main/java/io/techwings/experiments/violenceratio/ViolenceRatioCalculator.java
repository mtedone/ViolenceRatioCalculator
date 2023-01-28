package io.techwings.experiments.violenceratio;

public class ViolenceRatioCalculator {

    public static final double VIOLENCE_LOWEST_RATIO = 0;
    public static final double VIOLENCE_HIGHEST_RATIO = 1;
    private double availableResources;
    private long people;
    private double averageExpectation;
    private double willingness;


    private ViolenceRatioCalculator() {}

    public double getRatio() {
        return perfectConditions() ? VIOLENCE_LOWEST_RATIO :
                worseConditions() ? VIOLENCE_HIGHEST_RATIO :
                calculateActualRatio();
    }

    private double calculateActualRatio() {
        double ratio = 0;
        if (availableResources == 0) {
            return VIOLENCE_HIGHEST_RATIO;
        } else {
            ratio = (people * averageExpectation) / (availableResources * willingness);
            return ratio / Double.MAX_VALUE;
        }

    }

    private boolean worseConditions() {
        return availableResources == 0 &&
                people == Long.MAX_VALUE &&
                averageExpectation == Double.MAX_VALUE &&
                willingness == 0;
    }

    private boolean perfectConditions() {
        return availableResources == Long.MAX_VALUE &&
                people == 0 &&
                averageExpectation == 0 &&
                willingness == Double.MAX_VALUE;
    }

    public double getAvailableResources() {
        return this.availableResources;
    }

    public double getAverageExpectation() {
        return this.averageExpectation;
    }

    public double getWillingness() {
        return willingness;
    }

    public static class Builder {
        private double availableResources;
        private long people;
        private double averageExpectation;
        private double willingness;

        private ViolenceRatioCalculator calculator;

        public Builder() {
            calculator = new ViolenceRatioCalculator();
            this.availableResources = 0;
            this.people = 0;
            this.averageExpectation = 0;
            this.willingness = 0;
        }

        public Builder withAvailableResources(double availableResources) {
            this.availableResources = availableResources > 1 ? 1 :
            availableResources < 0 ? 0 : availableResources;
            return this;
        }

        public Builder withPeople(long people) {
            this.people = people;
            return this;
        }

        public Builder withAverageExpectation(double averageExpectation) {
            this.averageExpectation = averageExpectation > 1 ? 1 :
                    averageExpectation < 0 ? 0 : averageExpectation;
            return this;
        }

        public Builder withWillingness(double willingness) {
            this.willingness = willingness > 1 ? 1 :
                    willingness < 0 ? 0 : willingness;
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
