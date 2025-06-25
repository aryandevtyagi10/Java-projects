import java.util.TreeMap;

public class TreeMapExample {
    public static void main(String[] args) {
        TreeMap<String, Integer> treeMap = new TreeMap<>();

        treeMap.put("Banana", 20);
        treeMap.put("Apple", 30);
        treeMap.put("Mango", 25);

        for (String key : treeMap.keySet()) {
            System.out.println(key + " => " + treeMap.get(key));
        }
    }
}
