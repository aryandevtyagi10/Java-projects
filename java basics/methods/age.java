public class age {
    public static void main(String[] args) {
        int age = 17;
      if(checkage(age)){
          System.out.println("eligible");
      }
      else System.out.println("too young");
    }
    static boolean checkage(int age){
        return age >= 18;
    }
}
