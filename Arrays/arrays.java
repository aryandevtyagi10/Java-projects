import java.util.Arrays;

public class arrays {
    public static void main(String[] args){
        String[] fruits = {"apple","mango","banana","melon","orange"};
//        Arrays.sort(fruits);
        Arrays.fill(fruits,"hahhaha");

        for(String fruit /*(identifier)*/: fruits){               //enhanced for loop
            System.out.println(fruit);
        }
    }
}