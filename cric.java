import java.util.Random;
import java.util.Scanner;

public class CricketGame {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int totalRuns = 0;
        int balls = 6;

        System.out.println("🏏 Welcome to the One Over Cricket Game!");
        System.out.println("You have 6 balls. Try to score as many runs as possible.");
        System.out.println("Enter your shot (0 to 6):\n");

        for (int i = 1; i <= balls; i++) {
            System.out.print("Ball " + i + ": ");
            int playerShot = scanner.nextInt();

            if (playerShot < 0 || playerShot > 6) {
                System.out.println("❌ Invalid input! Please enter a number between 0 and 6.");
                i--; // retry same ball
                continue;
            }

            int bowlerBall = random.nextInt(7); // 0 to 6

            if (playerShot == bowlerBall) {
                System.out.println("💥 OUT! You played " + playerShot + " and bowler bowled " + bowlerBall);
                System.out.println("🏁 Final Score: " + totalRuns + " runs in " + (i - 1) + " balls.");
                return;
            } else {
                totalRuns += playerShot;
                System.out.println("✅ Safe! You scored " + playerShot + " run(s).");
            }
        }

        System.out.println("\n🏁 Over Completed!");
        System.out.println("🏏 Final Score: " + totalRuns + " runs in 6 balls.");
    }
}