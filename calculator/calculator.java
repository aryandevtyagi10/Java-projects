import java.util.Scanner;

public class calculator {
    public static void main(String[] args){
        double num1,num2;
        char operator;
        double result = 0;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Calculator Program");
        System.out.print("Enter 1st number: ");
        num1 = scanner.nextDouble();
        System.out.print("Enter 2nd number: ");
        num2 = scanner.nextDouble();

        System.out.print("Enter operator you wish to perform: +,-,*,/,^ ");
        operator = scanner.next().charAt(0);

        switch (operator){
            case '+' -> result = num1 + num2;
            case '-' -> result = num1 - num2;
            case '*' -> result = num1 * num2;
            case '/' -> {
                if (num2 == 0)
                    System.out.println("Invalid entry");
                else result = num1 / num2;
            }
            case '^' -> result = Math.pow(num1,num2);
            default -> System.out.println("Try again!");
        }
        System.out.println("Result: " + result);
        scanner.close();
    }
}
