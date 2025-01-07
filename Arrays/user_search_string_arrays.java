import java.util.Scanner;

public class user_search_string_arrays {
    public static void main (String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean isFound = false;
        int size;
        String target;
        String[] fruits;

        System.out.print("How many fruits? :");
        size = scanner.nextInt();
        scanner.nextLine();

        fruits = new String[size];
        for (int i = 0; i < fruits.length; i++) {
            System.out.print("Enter fruits you want: ");
            fruits[i] = scanner.nextLine();
        }
        System.out.print("Enter fruit you want to search for: ");
        target = scanner.nextLine();
        for (int i = 0; i < fruits.length; i++) {
            if (fruits[i].equals(target)) {                  //use equals methods for comparing strings
                System.out.println("fruit found at index: " + i);
                isFound = true;
                break;
            }
        }
        if (!isFound) {
            System.out.println("Fruit not found!");
        }
        scanner.close();
    }
 }
// trial version
//import java.util.Scanner;
//
//public class user_search_string_arrays {
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//
//        String[] fruits = new String[3];
//        boolean isFound = false;
//        String target;
//        for(int i = 0;i< fruits.length;i++) {
//            System.out.print("Enter fruits: ");
//            fruits[i] = scanner.nextLine();
//        }
//        System.out.print("Enter fruit you want to search for: ");
//        target = scanner.nextLine();
//        for(int i = 0; i < fruits.length;i++){
//            if(fruits[i].equals(target)){
//                System.out.println("FOUND THE TARGET at index: " + i);
//                isFound = true;
//                break;
//            }
//        }
//        if(!isFound){
//            System.out.println("Not found!");
//        }
//    }
//}
