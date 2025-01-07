public class Main {
    public static void main(String[] args) {
        String[] fruits = {"apple","mango","banana","melon","orange"};
//        fruits[0]= "pineapple";
        int num= fruits.length;
        System.out.println(fruits[0]);
        System.out.println(num);
//        for(int i =0;i< fruits.length;i++){
//            System.out.println(fruits[i]+ " ");
//        }
        for(String fruit /*(identifier)*/: fruits){               //enhanced for loop
            System.out.println(fruit);
        }
    }
}