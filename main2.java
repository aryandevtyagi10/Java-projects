import java.util.Scanner;

public class main2 {
    public static void main(String[] args) {
//        Calculate Area of Rectangle
        double l = 0;
        double w = 0;
        double area = 0;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Length: ");
        l = scanner.nextDouble();
        System.out.print("Enter Width: ");
        w = scanner.nextDouble();
        area = l*w;
        System.out.println("Area of Rectangle: " + area + "cm²"); //alt + 0178 to show cm^2
        scanner.close();
    }
}
