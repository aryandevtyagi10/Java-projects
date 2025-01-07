import java.util.Scanner;
import java.util.Stack;

public class user_input {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

//        String[] foods = new String[4];     //empty  array declaration
//        foods[0] = "pizza";
//        foods[1] = "burger";
//        foods[2] = "pasta";

        String[] foods;
        int size;
        System.out.println("how many items you want? : ");
        size = scanner.nextInt();                  //must write nextline to change output cursor
        scanner.nextLine();

        foods = new String[size];
        for (int i = 0; i<foods.length ; i++){
            System.out.print("Enter food you like to eat: ");
            foods[i] = scanner.nextLine();
        }

        for (String food : foods){
            System.out.println(food);
        }
        scanner.close();
    }
}
