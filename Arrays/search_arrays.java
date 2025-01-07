public class search_arrays {
    public static void main(String[] args) {

     int[] num = {1,2,4,6,7,3,5,8};
     int target = 4;
     boolean isFound = false;

     for(int i = 0; i< num.length;i++){
         if(target == num[i]){
             System.out.println("FOUND THE TARGET at index: " + i);
             isFound = true;
             break;
         }
     }
     if(!isFound){
         System.out.println("not found");
     }
    }
}
