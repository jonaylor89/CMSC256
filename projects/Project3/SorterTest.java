/**************************************
 * John Naylor
 * CMSC 256 Section 2
 * Project 3 Sorter
 * Test the sorter class
 ***************************************/
import org.junit.Test;
import org.junit.Assert.assertEquals;

public class SorterTest {

    @Test
    public static void testSort1() {

        int[] initial = new int[]{3, 2, 1};

        int[] array = Sorter.sortIntArray(initial);

        assertEquals("First Test", array, new int[]{1, 2, 3});

    }

    @Test
    public static void testSort2() {

        int[] initial = new int[]{3, 2, 1, 5, 2, 6, 1};

        int[] array = Sorter.sortIntArray(initial);

        assertEquals("Second Test", array, new int[]{1, 1, 2, 2, 3, 5, 6});

    }

    @Test
    public static void testSort3() {

        int[] initial = new int[]{1, 2, 3, 4, 3, 6, 3, 5, 10};

        int[] array = Sorter.sortIntArray(initial);

        assertEquals("Third Test", array, 1, 2, 3, 3, 3, 4, 5, 10);

    }
}