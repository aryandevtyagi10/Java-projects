import java.util.Scanner;
public class print {
    public static void main(String[] args) {
//        printf() => is a method used to format output
//        %[flags][width][.precision][specifier-character]
        String name = "Goku";
        char firstLetter ='G';
        int age = 32;
        double h = 183.6;
        double l = 10000.35;
        double n = -54.43;
        boolean isSaiyan = true;

        System.out.printf("Hello %s\n",name);
        System.out.printf("First letter is: %c\n",firstLetter);
        System.out.printf("%d\n",age);
        System.out.printf("%.1f\n",h);//%.1f precision
        System.out.printf("%.3f\n",h);
        System.out.printf("%+.1f\n",h); //flags +
        System.out.printf("%,.1f\n",l); //comma separator
        System.out.printf("%(.1f\n",n); // negative numbers enclosed in ()
        System.out.printf("% .1f\n",h); //display  a minus if negative , space if positive
        System.out.printf("%b\n",isSaiyan);
        System.out.printf("%s is %d years old \n",name,age);

        int a=1; //padding
        int b = 324;
        int c = 23847653;
        System.out.printf("%04d\n",a);
        System.out.printf("%04d\n",b); //-1 , 4, -4 , 0
        System.out.printf("%04d\n",c);


    }
}