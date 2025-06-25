import java.util.ArrayList;

public class ArrayListExample {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();

        list.add("Apple");
        list.add("Banana");
        list.add("Mango");

        list.remove("Banana");

        for (String fruit : list) {
            System.out.println(fruit);
        }
    }
}
