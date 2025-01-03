import java.util.Scanner;

public class main {
    public static void main (String[] args){
//        MAD LIBS GAME
        Scanner scanner = new Scanner(System.in);
        String adj1;
        String adj2;
        String adj3;
        String noun1;
        String verb;

        System.out.print("Enter an adjective(description): ");
        adj1 = scanner.nextLine();
        System.out.print("Enter a noun(animal or person): ");
        noun1 = scanner.nextLine();
        System.out.print("Enter an adjective(description): ");
        adj2 = scanner.nextLine();
        System.out.print("Enter a verb end-ing : ");
        verb = scanner.nextLine();
        System.out.print("Enter an adjective(description): ");
        adj3 = scanner.nextLine();

        System.out.println("Today I went to " + adj1 + " Epstein Island!");
        System.out.println("In a corner, I saw  " + noun1+".");
        System.out.println(noun1+ " was looking rather " + adj2 + " and screaming in his "  + verb + "!");
        System.out.println("I was "+ adj3 + "!");


        scanner.close();
    }
}
