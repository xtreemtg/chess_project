package Pieces;

import java.util.ArrayList;
import java.util.Arrays;

public class Rook extends Piece {
    String name = "Rook  ";
    public Rook(ArrayList<Integer> currentCoordinates, boolean isWhite){
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


        return this.possibleCoordinates;
    }
}
