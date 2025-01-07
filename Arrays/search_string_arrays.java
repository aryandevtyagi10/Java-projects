

public class search_string_arrays {
    public static void main(String[] args) {

        String[] fruits = {"apple","mango","banana","melon","orange"};
        String target = "banana";
        boolean isFound = false;

        for(int i = 0; i < fruits.length;i++){
            if(fruits[i].equals(target)){             //use equals methods for comparing strings
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
