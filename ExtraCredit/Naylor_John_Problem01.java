
import java.util.Scanner;

public class Naylor_John_Problem01 {
    
    public static void main(String[] argv) {
        Scanner userInput = new Scanner(System.in);

        int numberOfTextbooks = userInput.nextInt();
        int max = 0;
        int min = 10000;

        for (int i = 0; i < numberOfTextbooks; i++) {
            int nextTextbook = userInput.nextInt();
            if (nextTextbook > max) {
                max = nextTextbook; 
            }

            if (nextTextbook < min) {
                min = nextTextbook; 
            }
        }

        System.out.println(max - min);

    }
}
