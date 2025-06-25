import java.util.LinkedList;

public class LinkedListExample {
    public static void main(String[] args) {
        LinkedList<String> names = new LinkedList<>();

        names.add("Alice");
        names.add("Bob");
        names.addFirst("Zara");

        System.out.println("First: " + names.getFirst());

        for (String name : names) {
            System.out.println(name);
        }
    }
}
