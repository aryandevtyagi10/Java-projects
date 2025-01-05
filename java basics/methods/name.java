public class name {
    public static void main(String[] args) {
        String first = "jon";
        String last = "doe";

        String fullname = name(first,last);
        System.out.println(fullname);
    }
    static  String name(String first , String last ){
        return first + " " + last;
    }
}