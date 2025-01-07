package arrays_2D;

public class telepad {
    public static void main(String[]args){


        char[][] telepad = {{'1','2','3'},
            {'4','5','6'},
            {'7','8','9'},
            {'*','0','#'} };
        for(char[]row:telepad){
            for (char number:row){
                System.out.print(number+" ");
            }
            System.out.println();
        }

    }
}
