import java.util.Scanner;
public class main {
    public static void main (String[] args){
          //        Shopping Cart
        Scanner scanner = new Scanner(System.in);
        String item;
        double price,total;
        int quant;
        char currency = '$';
        System.out.print("Item to buy? : ");
        item = scanner.nextLine();
        System.out.print("What is the price for each : ");
        price = scanner.nextDouble();
        System.out.print("How many do you want?: ");
        quant = scanner.nextInt();
        total = price * quant;
        System.out.println("Total amount to be paid: " + currency + total );
        System.out.println("Congratulations! "+ quant + " " + item + " for " +currency +total);
        scanner.close();
    }
}
