public class Main {
    public static void main (String[] args) {
        String name = "aryan dev ";
        int len = name.length();
        char letter = name.charAt(0);
        int index = name.indexOf("a");
        int last_index = name.lastIndexOf("a");


//      name = name.toLowerCase();
//        name = name.toUpperCase();
//        name = name.trim();
//        name = name.replace("a","o");
//        System.out.println(name.isEmpty());
//        if (name.equals("password")) {       you can use equalsIgnoreCase for case sensitivity
//            System.out.println("name cant be password ");
//        }
//        else{
//            System.out.println("hello"+name);
//        }
        System.out.println(len);
        System.out.println(letter);
        System.out.println(index);
        System.out.println(last_index);
        System.out.println(name);
    }
}
