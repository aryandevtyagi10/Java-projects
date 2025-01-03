import java.util.Random;
public class random {
    public static void main(String[] args){
        Random random = new Random();
        int num ;

//      num = random.nextInt(); //very large number is generated ranging from almost  (-2 to +2 Billions)
        num = random.nextInt(1,7);//rolling a dice
        System.out.println(num);
        double number ;
        number = random.nextDouble(1.0,7.0);
        System.out.println(number);
        boolean isNum = random.nextBoolean();
        System.out.println(isNum);
        if(isNum){
            System.out.println("Tails");
        }
        else System.out.println("Heads");
    }
}
