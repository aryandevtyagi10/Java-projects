import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        // NUMBER GUESSING GAME

        Random random = new Random();
        Scanner scanner = new Scanner(System.in);

        int guess;
        int attempts = 0;
        int min = 1;
        int max = 100;
        int randomNumber = random.nextInt(min,max + 1);

        System.out.println("Number Guessing Game!");
        System.out.printf("Enter a number between %d-%d:\n",min,max);
        do {
             System.out.print("Enter a Guess: ");
             guess = scanner.nextInt();
             attempts++;
             if (guess < randomNumber){
                 System.out.println("Too low!");
             } else if (guess> randomNumber) {
                 System.out.println("Too High!");
             }
             else {
                 System.out.println("Correct! The number was: " + randomNumber);
                 System.out.println("# of attempts "+ attempts);
             }
        } while (guess != randomNumber);

        System.out.println("Won");
        scanner.close();
    }
}