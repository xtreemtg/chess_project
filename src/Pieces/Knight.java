package Pieces;

import java.util.ArrayList;
import java.util.Arrays;

public class Knight extends Piece {
    String name = "Knight";
    public Knight(ArrayList<Integer> currentCoordinates, boolean isWhite){
        super(currentCoordinates, isWhite);
    }
    @Override
    public String toString() {
        return name;
    }

    public ArrayList<ArrayList<Integer>> calculatePossibleCoordinates() {
        int x = currentCoordinates.get(0);
        int y = currentCoordinates.get(1);
        double incX = 2;
        double incY = 2;
        for(int i = 0; i < 8; i++){
            int x2 = (int)incrementX((int)Math.floor(incX));
            int y2 = (int) ((int)incrementY(incY) * Math.pow(-1, i));
            try {
                if(isEmptySpaceOrOccupiedByEnemyPiece(x + x2, y + y2)){
                    this.possibleCoordinates.add(new ArrayList<>(Arrays.asList(x + x2, y + y2)));
                }
            } catch (ArrayIndexOutOfBoundsException ignored){
                break;
            }

            incY -= 0.25;
            incX += 0.5;
        }
        return this.possibleCoordinates;
    }

    private double incrementX(int x){
        return  (Math.pow(-1, x) * Math.floor(x / 2));
    }
    private double incrementY(double y){
        return Math.ceil((y));
    }
}
