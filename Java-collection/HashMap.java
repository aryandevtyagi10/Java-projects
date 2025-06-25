import java.util.HashMap;

public class HashMapExample {
    public static void main(String[] args) {
        HashMap<Integer, String> map = new HashMap<>();

        map.put(1, "Red");
        map.put(2, "Green");
        map.put(3, "Blue");

        map.remove(2);

        for (Integer key : map.keySet()) {
            System.out.println(key + " => " + map.get(key));
        }
    }
}
