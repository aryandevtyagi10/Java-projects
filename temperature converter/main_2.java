import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;
        double temp, newTemp;
        String fromUnit = "", toUnit = "";

        System.out.println("TEMPERATURE CONVERTER");
        System.out.println("1: Fahrenheit to Celsius");
        System.out.println("2: Celsius to Fahrenheit");
        System.out.println("3: Celsius to Kelvin");
        System.out.println("4: Kelvin to Celsius");
        System.out.println("5: Fahrenheit to Kelvin");
        System.out.println("6: Kelvin to Fahrenheit");
        System.out.print("CHOOSE AN OPTION (1-6): ");

        if (!scanner.hasNextInt()) {
            System.out.println("Invalid input! Please enter a number.");
            return;
        }

        choice = scanner.nextInt();

        switch (choice) {
            case 1:
                fromUnit = "F";
                toUnit = "C";
                break;
            case 2:
                fromUnit = "C";
                toUnit = "F";
                break;
            case 3:
                fromUnit = "C";
                toUnit = "K";
                break;
            case 4:
                fromUnit = "K";
                toUnit = "C";
                break;
            case 5:
                fromUnit = "F";
                toUnit = "K";
                break;
            case 6:
                fromUnit = "K";
                toUnit = "F";
                break;
            default:
                System.out.println("Invalid Choice!");
                scanner.close();
                return;
        }

        System.out.printf("Enter Temperature in °%s: ", fromUnit);
        if (!scanner.hasNextDouble()) {
            System.out.println("Invalid temperature value!");
            scanner.close();
            return;
        }

        temp = scanner.nextDouble();

        switch (choice) {
            case 1:
                newTemp = (temp - 32) * 5 / 9;
                break;
            case 2:
                newTemp = temp * 9 / 5 + 32;
                break;
            case 3:
                newTemp = temp + 273.15;
                break;
            case 4:
                newTemp = temp - 273.15;
                break;
            case 5:
                newTemp = (temp - 32) * 5 / 9 + 273.15;
                break;
            case 6:
                newTemp = (temp - 273.15) * 9 / 5 + 32;
                break;
            default:
                newTemp = temp; // shouldn't happen
        }

        System.out.printf("Converted Temperature: %.2f °%s\n", newTemp, toUnit);
        scanner.close();
    }
}