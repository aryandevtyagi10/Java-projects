class Human {
    //int age; //using private make it inaccessible directly
    //String name;
    private int age ; //instance variables
    private String name ;

    public int getAge(){
        return age;
    }
    public void setAge(/*int a*/ int age){
//        age = a;
        this.age = age; //represents current object
    }

    public String getName() {
        return name;
    }
    public void setName (/*String n*/ String name){
//        name = n;
        this.name = name; //use this keyword
    }
}

public class Encapsulation {
        public static void main (String[] a){
        Human obj = new Human();
        obj.setAge(21);
        obj.setName("Aryan");
//        obj.age = 21;
//        obj.name = "Aryan";
//        System.out.println(obj.name); before using getter setters
//        System.out.println(obj.age);
            System.out.println(obj.getAge()+ " : " + obj.getName());
        }
    }

