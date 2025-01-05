public class methods {
    public static void main (String [] args){
         //Method - a block of reusable code that is executed when called ()
        String  name = "tom";
         int age =21;

        happybirthday(name , age);  //order must be same cant be age first and name last  here
//       happybirthday();
    }
    static void happybirthday(String name,int age){
        System.out.printf("Happy birthday %s \n",name);
        System.out.printf(" a very very very happy bday %d \n",age);
        System.out.println(" happppy hoo hohoho ho");
    }
}
