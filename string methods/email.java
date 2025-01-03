import java.util.Scanner;
public class email {
    public static void main(String[] args){
        // .substring() = A method used to extract a portion of string
        // .substring(start , end)

//        EMAIL SLICER PROGRAM
        String email,username,domain;
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Your EMAIL address: ");
         email = scanner.nextLine();
        if (email.contains("@")) {
//        String username = email.substring(0,8);
            username = email.substring(0, email.indexOf('@'));//in this way we can  find index of any string without counting
            domain = email.substring(email.indexOf('@') + 1);

            System.out.println(username);
            System.out.println(domain);
        }
        else
            System.out.println("Invalid email");

        scanner.close();
    }
}
