import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] units = {"kg", "lbs", "g", "oz", "st"};
        double[][] conversionFactors = {
            // To:     kg     lbs      g       oz      st
            /*kg*/   {1.0,   2.20462, 1000.0, 35.274, 0.157473},
            /*lbs*/  {0.453592, 1.0, 453.592, 16.0,   0.071429},
            /*g*/    {0.001,  0.002205, 1.0,   0.035274, 0.000157},
            /*oz*/   {0.02835, 0.0625, 28.35,  1.0,     0.004464},
            /*st*/   {6.35029, 14.0,  6350.29, 224.0,   1.0}
        };

        System.out.println("Welcome to the Weight Converter!");

        // Input weight
        System.out.print("Enter weight: ");
        if (!scanner.hasNextDouble()) {
            System.out.println("Invalid input! Please enter a numeric weight.");
            scanner.close();
            return;
        }
        double weight = scanner.nextDouble();

        // Display unit options
        System.out.println("\nAvailable Units:");
        for (int i = 0; i < units.length; i++) {
            System.out.println((i + 1) + ": " + units[i]);
        }

        // Input source unit
        System.out.print("Convert from (enter number): ");
        int from = scanner.nextInt() - 1;

        // Input target unit
        System.out.print("Convert to (enter number): ");
        int to = scanner.nextInt() - 1;

        if (from < 0 || from >= units.length || to < 0 || to >= units.length) {
            System.out.println("Invalid unit selection!");
            scanner.close();
            return;
        }

        // Conversion
        double convertedWeight = weight * conversionFactors[from][to];
        System.out.printf("\n%.2f %s = %.2f %s\n", weight, units[from], convertedWeight, units[to]);

        scanner.close();
    }
}