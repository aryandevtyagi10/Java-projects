public class average_varargs {
    public static void main(String[] args){
        System.out.println(avg(1,2,3,4)); //if we dont pass no arguements we get NaN
    }
    static double avg(double... numbers){
        double sum = 0;
        if (numbers.length == 0){
            return 0;
        }
        for (double number: numbers){
            sum += number;
        }
        return sum / numbers.length;
    }
}
