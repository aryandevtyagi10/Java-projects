public class varargs {
    public static void main(String[] args ){

//        varargs = allow a method to accept varying # of arguements
//                  makes methods more flexible, no need for overloaded methods
//                  java will pack the arguements into an array
//                  ...(ellipsis)
        System.out.println(add(1,2,3,4));
    }
    static int add(int...numbers){
        int sum = 0;
        for(int number: numbers){
            sum+= number;
        }
        return sum;
    }
}
