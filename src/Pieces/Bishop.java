package Pieces;

import java.util.ArrayList;
import java.util.Arrays;

public class Bishop extends Piece{
    String name = "Bishop";
    public Bishop(ArrayList<Integer> currentCoordinates, boolean isWhite){
        super(currentCoordinates, isWhite);
    }
    @Override
    public String toString() {
        return name;
    }

    public ArrayList<ArrayList<Integer>> calculatePossibleCoordinates() {
        int x = currentCoordinates.get(0);
        int y = currentCoordinates.get(1);

        int i = x + 1;
        int j = y + 1;
        while (i < 8 && j < 8){
            if (checkSquares(i, j)) break;
            i++; j++;
        }
        i = x - 1;
        j = y - 1;
        while (i >= 0 && j >= 0){
            if (checkSquares(i, j)) break;
            i--; j--;
        }
        i = x + 1;
        j = y - 1;
        while (i < 8 && j >= 0){
            if (checkSquares(i, j)) break;
            i++; j--;
        }
        i = x - 1;
        j = y + 1;
        while (i >= 0 && j < 8){
            if (checkSquares(i, j)) break;
            i--; j++;
        }
        return this.possibleCoordinates;
    }

    private boolean checkSquares(int i, int j) {
        try {
            if (isEmptySpace(i, j)) {
                this.possibleCoordinates.add(new ArrayList<Integer>(Arrays.asList(i, j)));
            } else if (occupiedByEnemyPiece(i, j)) {
                this.possibleCoordinates.add(new ArrayList<Integer>(Arrays.asList(i, j)));
                return true;
            }else return true;
        }catch (ArrayIndexOutOfBoundsException e){
            return true;
        }
        return false;
    }
}
