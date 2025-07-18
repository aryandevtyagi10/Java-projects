class Humans {
    private int age ;
    private String name ;

    public Humans(){  //Constructors are used to provide a default values
        age = 12; //default constructor
        name = "Ryan";
    }
    public Humans(int age , String name){ //parameterized constructor
        this.age = age;
        this.name= name;
    }
    public int getAge(){
        return age;
    }
    public void setAge(int age){
        this.age = age;
    }

    public String getName() {
        return name;
    }
    public void setName ( String name){
        this.name = name;
    }
}

public class Constructors {
    public static void main (String[] a){
        Humans obj = new Humans(); //everytime an object is created it will call the constructor
        Humans obj2 = new Humans(18,"Jim");
        obj.setAge(21);
        obj.setName("Aryan");
        System.out.println(obj.getAge()+ " : " + obj.getName());
        System.out.println(obj2.getAge()+ " : " + obj2.getName());
    }
}

