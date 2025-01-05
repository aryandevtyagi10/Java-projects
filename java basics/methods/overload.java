public class overload {
    public static void main (String [] args){
        //OVERLOADED methods - methods that share same name,
//        but they have different parameters i.e. different signatures
//        signature = name + parameters

        System.out.println(add(1,2));
        System.out.println(add(1,2,3));
    }
    static  double add(double a , double b){
        return a + b;
    }
    static  double add(double a , double b,double c){
        return a + b + c;
    }

}
