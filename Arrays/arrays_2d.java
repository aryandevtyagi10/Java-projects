package arrays_2D;
public class arrays_2d {
    public static void main(String[]args){
        String[] fruits = {"apple","mango","banana","melon","orange"};
        String[] veggies = {"spinach","tomato","potato","carrot","olives"};
        String[] junks = {"chocolate","pasta","burger","pie","pizza"};

        String[][] items = {fruits,veggies,junks};
        //OTHER METHOD TO WRITE THIS!
        /*  String[][] items = {
                                {"apple","mango","banana","melon","orange"},
                                {"spinach","tomato","potato","carrot","olives"},
                                {"chocolate","pasta","burger","pie","pizza"}
        };
         */

        items[2][2] = "pineapple";
        for(String[] foods :  items){
           for(String food:foods){
               System.out.print(food+ " ");
           }
            System.out.println();
        }
    }
}
