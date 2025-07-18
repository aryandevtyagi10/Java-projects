class Human {
    private int age = 21;
    private String name = "Aryan";

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }
}

public class Encapsulation {
    public static void main(String[] args) {
        Human obj = new Human();
        System.out.println(obj.getAge() + " " + obj.getName());
    }
}