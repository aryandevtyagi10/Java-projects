import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //OFFICE  QUIZ GAME

        Scanner scanner = new Scanner(System.in);
        String[] questions = {"Q1. What is the Michael Scott middle name?",
                "Q2. Who was Pam's fiance in S1?",
                "Q3. What year was Office(US) launched?",
                "Q4. Where did Jim proposed Pam?",};
        String[][] options = {{"1. Grey","2. Gary","3. Lucy","4. Kurt"},
                {"1. Roy", "2. Dwight", "3. Darryl", "4. JIM"},
                { "1. 2000", "2. 2004", "3. 2005", "4. 2003"},
                {"1.  Office ", "2. Wearhouse", "3. Restaurant","4. Gas Station"}};
        int[] answers = {2,1,3,4};
        int score = 0;
        int guess;
        System.out.println("********************************");
        System.out.println("Welcome to the Office Quiz Game!");
        System.out.println("********************************");

        for(int i = 0;i < questions.length;i++){
            System.out.println(questions[i]);
            for(String option:options[i]){
                System.out.println(option);
            }
            System.out.print("Enter your guess: ");
            guess = scanner.nextInt();
            if(guess == answers[i]){
                System.out.println("**********");
                System.out.println("CORRECT!!!");
                System.out.println("**********");
                score++;
            }
            else{
                System.out.println("**********");
                System.out.println("INCORRECT!");
                System.out.println("**********");
            }
        }
        System.out.println("""
                Congratulations! You always left me satisfied and smiling 
                That's What She Said 😂😂😂
                """);
        System.out.println("Your Final Score: " + score + " out of " + questions.length);
        scanner.close();
    }
}