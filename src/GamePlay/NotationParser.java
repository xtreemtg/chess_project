package GamePlay;

import java.util.ArrayList;
import java.util.Arrays;

public class NotationParser {
    public static ArrayList<Integer> parse(String move){

       if(move.length() > 6){
           System.out.println("Invalid notation! Please input your move in Algebraic notation only.");
           return null;
       }


        if(move.matches("(K|N|B|R|Q|O).*")){

        }


        return new ArrayList<Integer>(Arrays.asList(7, 6, 7, 4));
    }

    public static ArrayList<Integer> simpleParse(String move){
        ArrayList<Integer> list = new ArrayList<>();
        int c =Character.getNumericValue( move.charAt(0));
        list.add(Character.getNumericValue( move.charAt(0)));
        list.add(Character.getNumericValue( move.charAt(1)));
        list.add(Character.getNumericValue( move.charAt(2)));
        list.add(Character.getNumericValue( move.charAt(3)));

        return list;
    }
}
