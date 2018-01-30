
import java.util.Scanner;

public class Naylor_John_Problem01 {
    
    public static void main(String[] argv) {
        Scanner userInput = new Scanner(System.in);

        int numberOfTextbooks = userInput.nextInt();
        double max = 0;
        double min = 10000;

        for (int i = 0; i < numberOfTextbooks; i++) {
            double nextTextbook = userInput.nextDouble();
            if (nextTextbook > max) {
                max = nextTextbook; 
            }

            if (nextTextbook < min) {
                min = nextTextbook; 
            }
        }

        System.out.printf("%.2f\n", max - min);

    }
}
