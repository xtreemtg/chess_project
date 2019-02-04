import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Tests {
    public static void main(String[] args) {
        Tests tests = new Tests();
        int N = 40;
        for(int a = 0; a < N; a++){
            for(int b = 0; b < N; b++){
                for(int c = 0; c < N; c++){
                    for(int d = 0; d < N; d++){
                        if(Math.pow(a, 3) + Math.pow(b, 3) == Math.pow(c, 3) + Math.pow(d,3)){
                            System.out.print(a + " ");
                            System.out.print(b+ " ");
                            System.out.print(c+ " ");
                            System.out.println(d+ " ");
                            break;
                        }
                    }
                }
            }
        }

    }



}
