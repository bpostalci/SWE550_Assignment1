import java.util.*;

/**
 * @author Yavuz Koroglu
 */
public class RunningAverage {
    private Double currentAverage;
    private Integer populationSize;

    /**
     * Default Constructor.
     */
    public RunningAverage() {
        this.currentAverage = 0.0;
        this.populationSize = 0;
    }

    /**
     * Explicit Value Constructor.
     */
    public RunningAverage(double lastAverage, int lastPopulationSize) {
        if (lastPopulationSize < 0) {
            throw new IllegalArgumentException("lastPopulationSize cannot be a negative number");
        }
        if (lastAverage < 0) {
            throw new IllegalArgumentException("lastAverage cannot be a negative number");
        }
        this.currentAverage = lastAverage;
        this.populationSize = lastPopulationSize;
    }

    /**
     * Copy Constructor.
     */
    public RunningAverage(RunningAverage lastAverage) {
        this.currentAverage = lastAverage.currentAverage;
        this.populationSize = lastAverage.populationSize;
    }

    /**
     * Getter for currentAverage
     */
    public Double getCurrentAverage() {
        return currentAverage;
    }

    /**
     * Getter for populationSize
     */
    public Integer getPopulationSize() {
        return populationSize;
    }

    /**
     * Adds elements to the population and returns the new average.
     */
    /**
     * corrected null pointer exception when given list is null or empty
     */
    public Double addElements(List<Double> addedPopulation) {
        if (addedPopulation == null || addedPopulation.isEmpty()) {
            return this.currentAverage;
        }
        double sum = this.currentAverage * this.populationSize;
        for (double element : addedPopulation) {
            sum += element;
            this.populationSize++;
        }
        this.currentAverage = sum / this.populationSize;
        return this.currentAverage;
    }

    /**
     * Removes elements to the population and returns the new average.
     */
    /**
     * corrected null pointer exception and return result when given list is null or empty
     */
    public Double removeElements(List<Double> removedPopulation) {
        if (removedPopulation == null || removedPopulation.isEmpty()) {
            return this.currentAverage;
        }
        double sum = this.currentAverage * this.populationSize;
        for (double element : removedPopulation) {
            sum -= element;
            this.populationSize--;
        }
        this.currentAverage = sum / this.populationSize;
        return this.currentAverage;
    }

    /**
     * Combines two running averages and returns a new running average
     */
    /**
     * corrected lastAverage calculation fault
     */
    static public RunningAverage combine(final RunningAverage avg1, final RunningAverage avg2) {
        return new RunningAverage
                (
                        (avg1.getCurrentAverage() * avg1.getPopulationSize() + avg2.getCurrentAverage() * avg2.getPopulationSize())
                                / (avg1.getPopulationSize() + avg2.getPopulationSize()),
                        avg1.getPopulationSize() + avg2.getPopulationSize()
                );
    }
}