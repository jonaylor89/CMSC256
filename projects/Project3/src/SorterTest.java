import static org.junit.Assert.*;

import org.junit.Test;
import java.util.Arrays;

public class SorterTest {

	@Test
    public void testEmpty() {

        int[] initial = new int[]{};

        int[] array = Sorter.sortIntArray(initial);

        assertTrue(Arrays.equals(array, new int[]{}));

    }

    @Test
    public void testSort() {

        int[] initial = new int[]{3, 2, 1};

        int[] array = Sorter.sortIntArray(initial);

        assertTrue(Arrays.equals(array, new int[]{1, 2, 3}));

    }

    @Test
    public void testRandom() {

        int[] initial = new int[]{3, 2, 1, 5, 2, 6, 1};

        int[] array = Sorter.sortIntArray(initial);

        assertTrue(Arrays.equals(array, new int[]{1, 1, 2, 2, 3, 5, 6}));

    }

    @Test
    public void testDuplicate() {

        int[] initial = new int[]{1, 2, 3, 4, 3, 6, 3, 5, 10};

        int[] array = Sorter.sortIntArray(initial);

        assertTrue(Arrays.equals(array, new int[]{1, 2, 3, 3, 3, 4, 5, 6, 10}));

    }

    @Test(expected = IllegalArgumentException.class)
    public void testNull() {

        int[] initial = null;

        int[] array = Sorter.sortIntArray(initial);

    }

    @Test(expected = IllegalArgumentException.class)
    public void testNegative() {
        int[] initial = new int[]{-1, -2, -3, -4, -3, -6, -3, -5, -10};

        int[] array = Sorter.sortIntArray(initial);
    }

}
