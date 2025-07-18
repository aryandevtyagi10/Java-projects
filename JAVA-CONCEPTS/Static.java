class Mobile{
    int price;   // instance variables
    String brand;
    static String name;//all objects share a common variable //static variable

//    static { //static block will be called only once and first in order
//        name = "Phone";
//        System.out.println("In static block");
//    }
//
//    public Mobile(){
//        price = 60000;
//        brand = "MOTOROLA";
//        System.out.println("in constructor block");//printed twice
//    }
    public void show (){
        System.out.println(brand + " " + price + " " + name);
    }
    public static void show1(){
        System.out.println("static method");
    }
}

public class Static {
    public static void main (String[] args){ // to call main we don't need to call/create demo object due to static keyword
    Mobile obj1 = new Mobile();
    obj1.price = 40000;
    obj1.brand = "OnePlus";
    Mobile.name = "Android"; //static variable "name" should be accessed using "class" name and not object (although it may work)

        Mobile obj2 = new Mobile();
        obj2.price = 70000;
        obj2.brand = "Apple";
        Mobile.name = "Android";

        Mobile.name = "IOS"; //both obj will be changed
        obj1.show();
        obj2.show();
        Mobile.show1(); // you can call a static method directly with a class name
    }
    }
