package Pieces;


import java.util.ArrayList;
import java.util.Arrays;

public class Pawn extends Piece {
    String name = "Pawn  ";

    public Pawn(ArrayList<Integer> currentCoordinates, boolean isWhite){
        super(currentCoordinates, isWhite);
    }

    @Override
    public String toString() {
        return name;
    }

    public ArrayList<ArrayList<Integer>> calculatePossibleCoordinates() {
        int x = currentCoordinates.get(0);
        int y = currentCoordinates.get(1);
        if(isWhite) {
            if (board[x][y + 1] == null) this.possibleCoordinates.add(new ArrayList<Integer>(Arrays.asList(x, y + 1)));
            if (y == 1 && board[x][y + 2] == null && board[x][y + 1] == null)
                this.possibleCoordinates.add(new ArrayList<Integer>(Arrays.asList(x, y + 2)));
            else if (y == 4) checkEnPassant(x, y);
            if (x != 7 && (occupiedByEnemyPiece(x + 1, y + 1)))
                this.possibleCoordinates.add(new ArrayList<Integer>(Arrays.asList(x + 1, y + 1)));
            else if (x != 0 && (occupiedByEnemyPiece(x - 1, y + 1)))
                this.possibleCoordinates.add(new ArrayList<Integer>(Arrays.asList(x - 1, y + 1)));
        }else {
            if (board[x][y - 1] == null) this.possibleCoordinates.add(new ArrayList<Integer>(Arrays.asList(x, y - 1)));
            if (y == 6 && board[x][y - 2] == null && board[x][y - 1] == null)
                this.possibleCoordinates.add(new ArrayList<Integer>(Arrays.asList(x, y - 2)));
            else if (y == 5) checkEnPassant(x, y);
            if (x != 7 && (occupiedByEnemyPiece(x + 1, y - 1)))
                this.possibleCoordinates.add(new ArrayList<Integer>(Arrays.asList(x + 1, y - 1)));
            else if (x != 0 && (occupiedByEnemyPiece(x - 1, y - 1)))
                this.possibleCoordinates.add(new ArrayList<Integer>(Arrays.asList(x - 1, y - 1)));
        }


        return this.possibleCoordinates;
    }

    private void checkEnPassant(int x, int y){
        if(isWhite) {
            if (x != 7 && occupiedByEnemyPiece(x + 1, y))
                this.possibleCoordinates.add(new ArrayList<Integer>(Arrays.asList(x + 1, y + 1)));
            if (x != 0 && occupiedByEnemyPiece(x - 1, y))
                this.possibleCoordinates.add(new ArrayList<Integer>(Arrays.asList(x - 1, y + 1)));
        } else {
            if (x != 7 && occupiedByEnemyPiece(x + 1, y))
                this.possibleCoordinates.add(new ArrayList<Integer>(Arrays.asList(x + 1, y - 1)));
            if (x != 0 && occupiedByEnemyPiece(x - 1, y))
                this.possibleCoordinates.add(new ArrayList<Integer>(Arrays.asList(x - 1, y - 1)));
        }
    }
}
