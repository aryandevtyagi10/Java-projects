public class overload2 {
    public static void main (String [] args){
        String base = bakepizza("Pepperoni" , "double cheese", 4);
        System.out.println(base);
    }
    static String bakepizza (String pizza , String cheese , int quant){
        return  pizza + " Yummy " + cheese + quant;
    }
}
