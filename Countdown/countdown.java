import java.util.Scanner;

public class Countdown {
    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        int start;

        System.out.print("🎯 From where do you want to start the Countdown? Enter a positive number: ");

        // Input validation loop
        while (true) {
            if (scanner.hasNextInt()) {
                start = scanner.nextInt();
                if (start > 0) break;
                else System.out.print("⛔ Please enter a number greater than 0: ");
            } else {
                System.out.print("⛔ That's not a valid number! Try again: ");
                scanner.next(); // clear invalid input
            }
        }

        System.out.println("\n🚀 Countdown begins!\n");

        for (int i = start; i > 0; i--) {
            System.out.printf("⏳ %2d second%s remaining...\u0007\n", i, i == 1 ? "" : "s");
            Thread.sleep(1000);
        }

        // Final message with emojis/art
        System.out.println("\n🎉🎊🎆 Happy New Year! 🎆🎊🎉");
        System.out.println("✨ Wishing you joy, success, and good vibes! ✨");

        scanner.close();
    }
}