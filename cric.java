import java.util.*;

public class CricketGameUpscaled {

    static Scanner scanner = new Scanner(System.in);
    static Random random = new Random();

    public static void main(String[] args) {
        System.out.println("🏏 Welcome to Ultimate Console Cricket Game!");

        System.out.print("Enter your name: ");
        String playerName = scanner.nextLine();

        System.out.print("Choose number of overs (1–5): ");
        int overs = scanner.nextInt();
        while (overs < 1 || overs > 5) {
            System.out.print("❌ Invalid! Enter overs between 1 to 5: ");
            overs = scanner.nextInt();
        }

        System.out.println("Select Difficulty:\n1. Easy\n2. Medium\n3. Hard");
        int difficulty = scanner.nextInt();
        int maxBallValue = switch (difficulty) {
            case 1 -> 4; // Easy
            case 2 -> 5; // Medium
            case 3 -> 6; // Hard
            default -> 6;
        };

        playGame(playerName, overs, maxBallValue);

        System.out.println("Would you like to play again? (yes/no)");
        String replay = scanner.next();
        if (replay.equalsIgnoreCase("yes")) {
            main(null);
        } else {
            System.out.println("👋 Thanks for playing!");
        }
    }

    public static void playGame(String playerName, int overs, int maxBallValue) {
        int totalBalls = overs * 6;
        int totalRuns = 0;
        int wickets = 2;
        List<Integer> runsPerBall = new ArrayList<>();

        System.out.println("\n🎮 Game Starts! " + playerName + " has " + wickets + " wickets & " + totalBalls + " balls.");

        for (int i = 1; i <= totalBalls; i++) {
            System.out.print("\nBall " + i + " - Enter your shot (0 to 6): ");
            int playerShot = scanner.nextInt();

            if (playerShot < 0 || playerShot > 6) {
                System.out.println("❌ Invalid input! Try again.");
                i--;
                continue;
            }

            int bowlerBall = random.nextInt(maxBallValue + 1); // based on difficulty

            if (playerShot == bowlerBall) {
                System.out.println("💥 OUT! You played " + playerShot + ", bowler bowled " + bowlerBall);
                wickets--;
                runsPerBall.add(-1); // -1 represents OUT

                if (wickets == 0) {
                    System.out.println("🏁 All out!");
                    break;
                } else {
                    System.out.println("⚠️ " + wickets + " wicket(s) remaining.");
                    continue;
                }
            } else {
                System.out.println("✅ You scored " + playerShot + " run(s).");
                totalRuns += playerShot;
                runsPerBall.add(playerShot);
            }
        }

        // 🧾 Final Scorecard
        System.out.println("\n📋 MATCH SUMMARY:");
        System.out.println("Player: " + playerName);
        System.out.println("Total Runs: " + totalRuns);
        System.out.println("Overs Played: " + overs);
        System.out.println("Wickets Lost: " + (2 - wickets));

        System.out.println("\n📊 Ball-by-ball Performance:");
        for (int i = 0; i < runsPerBall.size(); i++) {
            String result = (runsPerBall.get(i) == -1) ? "WICKET" : runsPerBall.get(i) + " run(s)";
            System.out.println("Ball " + (i + 1) + ": " + result);
        }

        if (wickets > 0) {
            System.out.println("\n🏁 Innings Completed.");
        }
    }
}