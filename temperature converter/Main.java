import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;
        String unit;
        double temp, newTemp;
        System.out.println("TEMPERATURE CONVERTER");
        System.out.println("1: fahrenheit to celsius");
        System.out.println("2: celsius to fahrenheit");
        System.out.print("CHOOSE AN OPTION: ");
        choice = scanner.nextInt();

        if (choice != 1 && choice !=2){
            System.out.println("Invalid Choice!");
        }
        else {
            System.out.print("Enter Temperature: ");
            temp = scanner.nextDouble();

            newTemp = (choice == 1) ? ((temp - 32) * 5 / 9) : (temp * 9 / 5 + 32);

            System.out.println("F or C ");
            unit = scanner.next().toUpperCase();

            System.out.printf("%.1f °%s", newTemp, unit);
        }
        scanner.close();
    }
}