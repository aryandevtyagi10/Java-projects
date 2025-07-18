class A //every class in java extends the object class
{ // every constructor in java has this super() method
public A(){
    super();
    System.out.println("in A");
}
public A (int a){
    super();
        System.out.println("in A int");
    }
}
class B extends A
{
public B(){
    super(); // no need to write super() only when explicitly need a parameterized constructor's value
    System.out.println("in B ");
}
public B (int a){ //super() will print "super class" default constructor,
    super(a);  // so we have to use super(a); to get parameterized one
//    this(); // this() will execute the constructor of the same class
    System.out.println("in B int");
}
}
public class This_Super {
    public static void main (String[] a){
        B obj = new B(5 );
    }

}
