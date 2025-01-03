import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        //WEIGHT CONVERSION PROGRAM

        Scanner scanner = new Scanner(System.in);
        double weight ;
        double newWeight;
        int choice;

        // Welcome Message
        System.out.println("Welcome to Weight Converter!");
        // prompt for User choice
        System.out.print("Enter weight: ");
        weight = scanner.nextDouble();



        // prompt for user choice
        System.out.println("1 : convert kgs to lbs ");
        System.out.println("2 : convert lbs to kgs ");
        System.out.print("Choose an option: ");
        choice = scanner.nextInt();

        if (choice == 1){
            newWeight = weight * 2.20462;
            System.out.print("kgs to lbs: " + newWeight +"lbs");
        }
        else if (choice == 2){
            newWeight = weight * 0.453592;
            System.out.print("lbs to kgs: " + newWeight +"kgs");
        }
        else {
            System.out.println("Invalid Choice entered");
        }
        scanner.close();
    }
}
