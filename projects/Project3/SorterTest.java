
public class SorterTest {
    public static void main(String[] args) {
        int[] array = Sorter.sortIntArray(new int[]{3, 2, 1});
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + ", ");
        }

        System.out.println();

        array = Sorter.sortIntArray(new int[]{1, 2, 3, 4, 3, 6, 3, 5, 10});
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + ", ");
        }

        System.out.println();

        array = Sorter.sortIntArray(new int[]{3, 2, 1, 5, 2, 6, 1});
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + ", ");
        }
    }
}