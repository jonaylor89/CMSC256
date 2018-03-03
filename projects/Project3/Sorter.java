/*****************************************************
 * John Naylor
 * CMSC 256 Section 2
 * Project 3 Sorter
 * Method to sort integer arrays using a counter
 ***************************************************/

public class Sorter {

    /**********************************************************
     * Sorts integer arrays
     * @param initial Integer array needing to be sorted
     * @return The same array instance with sorted members
     ********************************************************/
    public static int[] sortIntArray(int[] initial) {

        // Find max
        int max = 0;
        for (int i = 0; i < initial.length; i++) {
            if (initial[i] > max) {
                max = initial[i];
            }
        }

        // Count how many of each element is in the array
        int[] temp = new int[max+1];
        for (int j = 0; j < initial.length; j++) {
            temp[initial[j]]++;  
        }

        // Put the elements back in the right order
        int index = 0;
        for (int k = 0; k < temp.length; k++) {
            if (temp[k] != 0) {
                for (int l = 0; l < temp[k]; l++) {
                    initial[index] = k;
                    index++;
                }
            }
        }

        return initial;
    } 

}