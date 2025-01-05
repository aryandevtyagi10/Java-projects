public class variablescope {
    static  int x = 3; //CLASS scope
    public static void main (String [] args){
        //variable scope = where a variable can be accessed
        int x = 1; //local variable
        System.out.println(x);
        ddoSomething();
        System.out.println(x);
              //local priority over class scope
    }
    static void ddoSomething(){
        int x = 2;//local variable

        System.out.println(x);
    }

}
