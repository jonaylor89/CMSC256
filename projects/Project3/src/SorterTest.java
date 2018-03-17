/*****************************************************
 * John Naylor
 * CMSC 256 Section 2
 * Project 3 Sorter
 * Test method to sort integer arrays using a counter
 ***************************************************/

import static org.junit.Assert.*;

import org.junit.Test;
import java.util.Arrays;

/**************************
 * JUnit test cases
 *************************/
public class SorterTest {

    /***************************
     * Test empty array
     **************************/
	@Test
    public void testEmpty() {

        int[] initial = new int[]{};

        int[] array = Sorter.sortIntArray(initial);

        assertTrue(Arrays.equals(array, new int[]{}));

    }

    /**************************
     * Test backwards array
     *************************/
    @Test
    public void testSort() {

        int[] initial = new int[]{3, 2, 1};

        int[] array = Sorter.sortIntArray(initial);

        assertTrue(Arrays.equals(array, new int[]{1, 2, 3}));

    }

    /***********************
     * Test random order
     **********************/
    @Test
    public void testRandom() {

        int[] initial = new int[]{3, 2, 1, 5, 2, 6, 1};

        int[] array = Sorter.sortIntArray(initial);

        assertTrue(Arrays.equals(array, new int[]{1, 1, 2, 2, 3, 5, 6}));

    }

    /***********************
     * Test duplicate items
     **********************/
    @Test
    public void testDuplicate() {

        int[] initial = new int[]{1, 2, 3, 4, 3, 6, 3, 5, 10};

        int[] array = Sorter.sortIntArray(initial);

        assertTrue(Arrays.equals(array, new int[]{1, 2, 3, 3, 3, 4, 5, 6, 10}));

    }

    /***********************
     * Test null array
     **********************/
    @Test(expected = IllegalArgumentException.class)
    public void testNull() {

        int[] initial = null;

        int[] array = Sorter.sortIntArray(initial);

    }

    /**************************
     * Test negative numbers
     *************************/
    @Test(expected = IllegalArgumentException.class)
    public void testNegative() {
        int[] initial = new int[]{-1, -2, -3, -4, -3, -6, -3, -5, -10};

        int[] array = Sorter.sortIntArray(initial);
    }

}
