import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class AllTests {

    /**
     * corrected initializing populationSize as 1
     */
    @Test
    public void initial_population_size_and_current_average_is_zero_test() {
        RunningAverage runningAverage = new RunningAverage();
        assertEquals(0, (int) runningAverage.getPopulationSize());
        assertEquals(0, runningAverage.getCurrentAverage(), 0.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void population_size_can_never_be_negative_test() {
        new RunningAverage(1, -10);
    }

    @Test
    public void addElements_not_change_contents_of_the_given_list_test() {
        RunningAverage runningAverage = new RunningAverage();

        // creates unmodifiable list of doubles
        List<Double> elements = List.of(1.0, 2.0, 3.0);
        try {
            runningAverage.addElements(elements);
        } catch (UnsupportedOperationException e) {
            fail("runningAverage.addElements method must not modify the given argument");
        }
    }

    @Test
    public void removeElements_not_change_contents_of_the_given_list_test() {
        RunningAverage runningAverage = new RunningAverage();

        // creates unmodifiable list of doubles
        List<Double> elements = List.of(1.0, 2.0, 3.0);
        try {
            runningAverage.removeElements(elements);
        } catch (UnsupportedOperationException e) {
            fail("runningAverage.removeElements method must not modify the given argument");
        }
    }

    @Test
    public void addElements_must_return_the_new_average_test() {
        RunningAverage runningAverage = new RunningAverage(10.0, 2);
        Double newAverage = runningAverage.addElements(List.of(10.0, 20.0));

        assertNotEquals(newAverage, null);
        assertEquals(12.5, newAverage, 0.0);
    }

    @Test
    public void removeElements_must_return_the_new_average_test() {
        RunningAverage runningAverage = new RunningAverage(12.5, 4);
        Double newAverage = runningAverage.removeElements(List.of(10.0, 20.0));

        assertNotEquals(newAverage, null);
        assertEquals(10.0, newAverage, 0.0);
    }

    @Test
    public void addElements_must_return_the_current_average_if_the_given_list_is_empty_test() {
        RunningAverage runningAverage = new RunningAverage(10.0, 2);
        runningAverage.addElements(List.of(10.0, 20.0));

        Double result = runningAverage.addElements(Collections.emptyList());

        assertNotEquals(result, null);
        assertEquals(result, runningAverage.getCurrentAverage());
    }

    /**
     * corrected null pointer exception when given list is null
     */
    @Test
    public void addElements_must_return_the_current_average_if_the_given_list_is_null_test() {
        RunningAverage runningAverage = new RunningAverage(10.0, 2);
        runningAverage.addElements(List.of(10.0, 20.0));

        Double result = runningAverage.addElements(null);

        assertNotEquals(result, null);
        assertEquals(result, runningAverage.getCurrentAverage());
    }

    /**
     * corrected returning 0.0 instead of currentAverage when given list is empty
     */
    @Test
    public void removeElements_must_return_the_current_average_if_the_given_list_is_empty_test() {
        RunningAverage runningAverage = new RunningAverage(12.5, 4);
        runningAverage.removeElements(List.of(10.0, 20.0));

        Double result = runningAverage.removeElements(Collections.emptyList());

        assertNotEquals(result, null);
        assertEquals(result, runningAverage.getCurrentAverage());
    }

    /**
     * corrected null pointer exception and return result when given list is null
     */
    @Test
    public void removeElements_must_return_the_current_average_if_the_given_list_is_null_test() {
        RunningAverage runningAverage = new RunningAverage(12.5, 4);
        runningAverage.removeElements(List.of(10.0, 20.0));

        Double result = runningAverage.removeElements(null);

        assertNotEquals(result, null);
        assertEquals(result, runningAverage.getCurrentAverage());
    }

    @Test
    public void addElements_must_update_the_population_size_test() {
        RunningAverage runningAverage = new RunningAverage(10.0, 2);
        runningAverage.addElements(List.of(10.0, 20.0));
        Integer expectedPopulationSize = 4;
        assertEquals(runningAverage.getPopulationSize(), expectedPopulationSize);
    }

    @Test
    public void removeElements_must_update_the_population_size_test() {
        RunningAverage runningAverage = new RunningAverage(12.5, 4);
        runningAverage.removeElements(List.of(10.0, 20.0));

        Integer expectedPopulationSize = 2;
        assertEquals(runningAverage.getPopulationSize(), expectedPopulationSize);
    }

    /**
     *  corrected lastAverage calculation fault
     */
    @Test
    public void combine_test() {
        RunningAverage runningAverage1 = new RunningAverage(12.5, 4);
        RunningAverage runningAverage2 = new RunningAverage(10.0, 2);

        RunningAverage result = RunningAverage.combine(runningAverage1, runningAverage2);
        assertNotEquals(result, null);
        Integer expectedPopulationSize = 6;
        assertEquals(result.getPopulationSize(), expectedPopulationSize);

        assertEquals(11.6666, result.getCurrentAverage(), 0.01);
    }
}
