import java.util.Scanner;

public class Main1 {
public static void main (String[] args){
    Scanner scanner = new Scanner(System.in);
    System.out.print("Enter your Name: ");
    String name = scanner.nextLine();

    System.out.print("ENTER YOUR AGE: ");
    int age = scanner.nextInt();

    System.out.print("Enter your GPA: ");
    double gpa = scanner.nextDouble();

    System.out.print("Are you above 18? (true/false): ");
    boolean vote = scanner.nextBoolean();

    System.out.println("HELLO! "+ name);
    System.out.println("My age is: " + age );
    System.out.println("My GPA: " + gpa);
//    if(vote) {
//        System.out.println("U CAN VOTEBRO");
//    }
//    else{
//        System.out.println("nahhhh");
//    }
    System.out.println("You can vote: "+ vote);
    scanner.close();
   }
}
