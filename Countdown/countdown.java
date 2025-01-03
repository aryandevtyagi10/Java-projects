import java.util.Scanner;

public class countdown {
    public static void main(String[] args) throws InterruptedException { //ADVANCED JAVA
        Scanner scanner = new Scanner(System.in);
        int start ;
        System.out.print("From where do you want to start the Countdown?: ");
        start = scanner.nextInt();
        for (int i = start; i>0; i--){
            System.out.println(i);
            Thread.sleep(1000); //advance method
        }
        System.out.println("Happy New Year!");
    }
}