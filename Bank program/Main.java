import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args){
        //Banking Program

        double balance = 0;
        int choice;
        boolean isRunning = true;

        while (isRunning){
            System.out.println("**********************");
            System.out.println("Welcome to Our Bank! ");
            System.out.println("What would you like to do?");
            System.out.println("1.SHOW BALANCE 2.DEPOSIT CASH 3. WITHDRAW CASH 4. EXIT");
            System.out.print("Your choice: ");
            choice = scanner.nextInt();


            switch (choice) {
                case 1 -> showBalance(balance);
                case 2 -> balance = balance + deposit();
                case 3 -> balance = balance - withdraw(balance);
                case 4 -> isRunning = false;
                default -> System.out.println("Invalid choice entered!");
            }
        }
        System.out.println("Thank you! ");
        scanner.close();
    }
        static void showBalance ( double balance){
            System.out.println("**************");
            System.out.printf("Your Balance: $%.2f\n", balance);
            System.out.println("**************");
        }
        static double deposit(){
        double amt;
            System.out.println("Enter Amount to be deposited: ");
            amt = scanner.nextDouble();
            if(amt<0){
                System.out.println("Amount cannot be negative! ");
                return 0;
            }
            else {
                return amt;
            }
        }
        static double withdraw (double balance){
        double amt;
            System.out.print("Enter amount to be withdrawn: ");
            amt = scanner.nextDouble();
            if (amt>balance){
                System.out.println("Insufficient Balance!");
                return 0;
            }
            else if (amt<0) {
                System.out.println("amount cannot be negative");
                return 0;
            }
            else{
                return amt;
            }
        }
    }
