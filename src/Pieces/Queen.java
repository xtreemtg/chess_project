package Pieces;

import java.util.ArrayList;
import java.util.Arrays;

public class Queen extends Piece {
    String name = "Queen ";
    public Queen(ArrayList<Integer> currentCoordinates, boolean isWhite){
        super(currentCoordinates, isWhite);
    }
    @Override
    public String toString() {
        return name;
    }

    public ArrayList<ArrayList<Integer>> calculatePossibleCoordinates() {
        int x = currentCoordinates.get(0);
        int y = currentCoordinates.get(1);

        for(int i = x + 1; i < 8; i++){
            try {
                if (isEmptySpace(i, y)) {
                    this.possibleCoordinates.add(new ArrayList<Integer>(Arrays.asList(i, y)));
                } else if (occupiedByEnemyPiece(i, y)) {
                    this.possibleCoordinates.add(new ArrayList<Integer>(Arrays.asList(i, y)));
                    break;
                }else break;
            }catch (ArrayIndexOutOfBoundsException e){break;}
        }
        for(int i = x - 1; i >= 0; i--){
            try {
                if (isEmptySpace(i, y)) {
                    this.possibleCoordinates.add(new ArrayList<Integer>(Arrays.asList(i, y)));
                } else if (occupiedByEnemyPiece(i, y)) {
                    this.possibleCoordinates.add(new ArrayList<Integer>(Arrays.asList(i, y)));
                    break;
                }else break;
            }catch (ArrayIndexOutOfBoundsException e){break;}
        }
        for(int i = y + 1; i < 8; i++){
            try {
                if (isEmptySpace(x, i)) {
                    this.possibleCoordinates.add(new ArrayList<Integer>(Arrays.asList(x, i)));
                } else if (occupiedByEnemyPiece(x, i)) {
                    this.possibleCoordinates.add(new ArrayList<Integer>(Arrays.asList(x, i)));
                    break;
                }else break;
            }catch (ArrayIndexOutOfBoundsException e){break;}
        }
        for(int i = y - 1; i >= 0; i--){
            try {
                if (isEmptySpace(x, i)) {
                    this.possibleCoordinates.add(new ArrayList<Integer>(Arrays.asList(x, i)));
                } else if (occupiedByEnemyPiece(x, i)) {
                    this.possibleCoordinates.add(new ArrayList<Integer>(Arrays.asList(x, i)));
                    break;
                }else break;
            }catch (ArrayIndexOutOfBoundsException e){break;}
        }
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
