import java.util.*;
import java.io.*;

public class TheOfficeQuiz {
    private static final String HIGH_SCORE_FILE = "highscores.txt";
    
    static class Question {
        String text;
        String[] options;
        int correctOptionIndex; // 0-indexed
        String explanation;
        int difficulty; // 1-3 (1=easy, 2=medium, 3=hard)

        Question(String text, String[] options, int correctOptionIndex, String explanation, int difficulty) {
            this.text = text;
            this.options = options;
            this.correctOptionIndex = correctOptionIndex;
            this.explanation = explanation;
            this.difficulty = difficulty;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Question> questions = createQuestions();
        int lifelines = 2;
        int score = 0;
        Map<Integer, Integer> userAnswers = new HashMap<>();
        List<Integer> skippedQuestions = new ArrayList<>();

        System.out.println("**************************************");
        System.out.println("*   WELCOME TO THE OFFICE QUIZ GAME   *");
        System.out.println("**************************************");
        System.out.println("            - DUNDIE AWARD -           ");
        System.out.println("\nRules:");
        System.out.println("- Answer questions about The Office (US)");
        System.out.println("- You have " + lifelines + " lifelines: 50/50 (1) or Skip (2)");
        System.out.println("- Difficulty levels: Easy ★, Medium ★★, Hard ★★★");
        System.out.println("- Try to beat the high score!\n");
        
        Collections.shuffle(questions);

        for (int i = 0; i < questions.size(); i++) {
            Question q = questions.get(i);
            boolean[] disabledOptions = new boolean[4];
            boolean answered = false;
            boolean usedLifeline = false;

            System.out.println("\n" + (i + 1) + "/" + questions.size() + ") " + q.text);
            System.out.println("Difficulty: " + "★".repeat(q.difficulty));
            
            while (!answered) {
                displayOptions(q.options, disabledOptions);
                
                System.out.print("\nEnter your choice (1-4), 5 for lifelines, 0 to skip: ");
                int choice = scanner.nextInt();
                
                if (choice == 0) {
                    skippedQuestions.add(i);
                    System.out.println("\nQuestion skipped!");
                    break;
                } else if (choice == 5) {
                    if (lifelines > 0) {
                        lifelines = useLifeline(lifelines, q, disabledOptions, scanner);
                        usedLifeline = true;
                    } else {
                        System.out.println("No lifelines left!");
                    }
                } else if (choice >= 1 && choice <= 4) {
                    int optionIndex = choice - 1;
                    
                    if (disabledOptions[optionIndex]) {
                        System.out.println("Option disabled! Choose another.");
                        continue;
                    }
                    
                    userAnswers.put(i, optionIndex);
                    
                    if (optionIndex == q.correctOptionIndex) {
                        int points = q.difficulty;
                        score += points;
                        System.out.println("\n████████████████████████████");
                        System.out.println("█      CORRECT! +" + points + " pts     █");
                        System.out.println("████████████████████████████");
                    } else {
                        System.out.println("\n✖✖✖✖✖✖✖✖✖✖✖✖✖✖✖✖✖✖✖✖");
                        System.out.println("✖      INCORRECT!       ✖");
                        System.out.println("✖✖✖✖✖✖✖✖✖✖✖✖✖✖✖✖✖✖✖✖");
                    }
                    System.out.println("Explanation: " + q.explanation);
                    answered = true;
                } else {
                    System.out.println("Invalid choice!");
                }
            }
        }

        displayResults(questions, userAnswers, skippedQuestions, score);
        updateHighScores(score);
        scanner.close();
    }

    private static List<Question> createQuestions() {
        List<Question> questions = new ArrayList<>();
        questions.add(new Question(
            "What is Michael Scott's middle name?",
            new String[]{"Gary", "George", "Garry", "Gene"},
            2, // Correct: Gary (index 2)
            "Michael Gary Scott is his full name. He was named after his father.",
            1
        ));
        questions.add(new Question(
            "Who was Pam's fiancé in Season 1?",
            new String[]{"Roy", "Dwight", "Jim", "Michael"},
            0, // Roy
            "Pam was engaged to Roy Anderson in the early seasons before calling it off.",
            1
        ));
        questions.add(new Question(
            "What year was The Office (US) launched?",
            new String[]{"2003", "2004", "2005", "2002"},
            2, // 2005
            "The first episode aired on March 24, 2005 on NBC.",
            2
        ));
        questions.add(new Question(
            "Where did Jim propose to Pam?",
            new String[]{"The Office", "At a gas station", "At a restaurant", "At Niagara Falls"},
            1, // Gas station
            "Jim proposed at a gas station during a rainstorm in Season 5.",
            2
        ));
        questions.add(new Question(
            "What is Dwight's favorite movie?",
            new String[]{"Die Hard", "Star Wars", "Battlestar Galactica", "The Terminator"},
            2, // Battlestar Galactica
            "Dwight frequently references Battlestar Galactica as his favorite.",
            3
        ));
        questions.add(new Question(
            "What does Dunder Mifflin sell?",
            new String[]{"Printers", "Paper", "Office furniture", "Computers"},
            1, // Paper
            "Dunder Mifflin is a paper distribution company.",
            1
        ));
        questions.add(new Question(
            "What is Angela's cat's name?",
            new String[]{"Bandit", "Princess", "Sprinkles", "Mr. Ash"},
            2, // Sprinkles
            "Sprinkles was eventually frozen and given a funeral by Dwight.",
            3
        ));
        questions.add(new Question(
            "Who started the fire in the office?",
            new String[]{"Ryan", "Michael", "Dwight", "The toaster"},
            2, // Dwight
            "Dwight started the fire while testing emergency procedures.",
            2
        ));
        return questions;
    }

    private static void displayOptions(String[] options, boolean[] disabled) {
        for (int j = 0; j < options.length; j++) {
            if (!disabled[j]) {
                System.out.println((j + 1) + ". " + options[j]);
            }
        }
    }

    private static int useLifeline(int lifelines, Question q, boolean[] disabled, Scanner scanner) {
        System.out.println("\nLifelines available: " + lifelines);
        System.out.println("1. 50/50 (remove two wrong answers)");
        System.out.println("2. Skip Question");
        System.out.print("Choose lifeline: ");
        int choice = scanner.nextInt();

        if (choice == 1) {
            return activateFiftyFifty(q, disabled, lifelines);
        } else if (choice == 2) {
            lifelines--;
            System.out.println("\nQuestion skipped using lifeline!");
        } else {
            System.out.println("Invalid lifeline choice!");
        }
        return lifelines;
    }

    private static int activateFiftyFifty(Question q, boolean[] disabled, int lifelines) {
        List<Integer> wrongOptions = new ArrayList<>();
        for (int i = 0; i < q.options.length; i++) {
            if (i != q.correctOptionIndex) {
                wrongOptions.add(i);
            }
        }
        Collections.shuffle(wrongOptions);
        
        // Disable two random wrong answers
        disabled[wrongOptions.get(0)] = true;
        disabled[wrongOptions.get(1)] = true;
        
        System.out.println("\nTwo incorrect answers removed!");
        lifelines--;
        return lifelines;
    }

    private static void displayResults(List<Question> questions, Map<Integer, Integer> userAnswers, 
                                      List<Integer> skippedQuestions, int score) {
        System.out.println("\n\n========================================");
        System.out.println("           QUIZ RESULTS");
        System.out.println("========================================\n");
        
        System.out.println("Final Score: " + score + " out of " + getMaxPossibleScore(questions));
        System.out.println("Questions skipped: " + skippedQuestions.size());
        
        System.out.println("\nDetailed Review:");
        for (int i = 0; i < questions.size(); i++) {
            Question q = questions.get(i);
            System.out.println("\nQ" + (i + 1) + ": " + q.text);
            
            if (skippedQuestions.contains(i)) {
                System.out.println("  You skipped this question");
            } else if (userAnswers.containsKey(i)) {
                int userChoice = userAnswers.get(i);
                boolean correct = (userChoice == q.correctOptionIndex);
                
                System.out.println("  Your answer: " + (userChoice + 1) + ". " + q.options[userChoice] +
                                  (correct ? " ✓" : " ✗"));
                System.out.println("  Correct answer: " + (q.correctOptionIndex + 1) + ". " + 
                                  q.options[q.correctOptionIndex]);
            }
            System.out.println("  Explanation: " + q.explanation);
        }
        
        System.out.println("\n" + getPersonalizedMessage(score, questions.size()));
        System.out.println("========================================");
    }

    private static int getMaxPossibleScore(List<Question> questions) {
        return questions.stream().mapToInt(q -> q.difficulty).sum();
    }

    private static String getPersonalizedMessage(int score, int totalQuestions) {
        if (score >= totalQuestions * 2) {
            return "You are the Assistant Regional Manager! That's what she said! 😂";
        } else if (score >= totalQuestions) {
            return "Great job! You deserve a Dundie Award! 🏆";
        } else {
            return "Keep watching The Office and try again! \"I'm not superstitious, but I am a little stitious.\"";
        }
    }

    private static void updateHighScores(int score) {
        try {
            File file = new File(HIGH_SCORE_FILE);
            int highScore = 0;
            
            if (file.exists()) {
                Scanner fileScanner = new Scanner(file);
                if (fileScanner.hasNextInt()) {
                    highScore = fileScanner.nextInt();
                }
                fileScanner.close();
            }
            
            if (score > highScore) {
                PrintWriter writer = new PrintWriter(file);
                writer.println(score);
                writer.close();
                System.out.println("\nNEW HIGH SCORE! (" + score + " points)");
            } else {
                System.out.println("\nCurrent High Score: " + highScore);
            }
        } catch (IOException e) {
            System.out.println("Could not save high score: " + e.getMessage());
        }
    }
}