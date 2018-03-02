/**
 * John Naylor
 * CMSC 256 Section 2
 */

public class Sorter {

    public static int[] sortIntArray(int[] initial) {

        int max = 0;
        for (int i = 0; i < initial.length; i++) {
            if (initial[i] > max) {
                max = initial[i];
            }
        }

        int[] temp = new int[max+1];
        for (int j = 0; j < initial.length; j++) {
            temp[initial[j]]++;  
        }

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