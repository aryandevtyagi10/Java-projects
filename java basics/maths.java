import java.util.Scanner;
public class maths {
    public static void main(String[] args) {
//        System.out.println(Math.PI); for pi value use this
//        System.out.println(Math.E)  for exponential value

//        double result,r;
//        result = Math.pow(2,3);
//        r = Math.abs(-5);
//        System.out.println(result);
//        System.out.println(r);

//        To find Hypotenuse
        Scanner scanner = new Scanner(System.in);
        double h,b,p;
        System.out.print("Enter perpendicular : ");
        p = scanner.nextDouble();
        System.out.print("Enter base : ");
        b = scanner.nextDouble();
        h = Math.sqrt(Math.pow(p,2) + Math.pow(b,2));
        System.out.println("Hypotenuse : " + h+"cm");
        scanner.close();
    }
}