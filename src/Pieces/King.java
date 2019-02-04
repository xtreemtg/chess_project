package Pieces;


import GamePlay.Game;
import GamePlay.Player;

import java.util.ArrayList;
import java.util.Arrays;

public class King extends Piece{
    private String name = "King  ";
    boolean hasMoved = false;
    private ArrayList<Piece> checkedBy;

    public King(ArrayList<Integer> currentCoordinates, boolean isWhite){
        super(currentCoordinates, isWhite);
        checkedBy = new ArrayList<>();
    }
    @Override
    public String toString() {
        return name;
    }

    public ArrayList<Piece> checkedBy() {
        return checkedBy;
    }

    public ArrayList<ArrayList<Integer>> calculatePossibleCoordinates() {
        this.possibleCoordinates.clear();
        int x = currentCoordinates.get(0);
        int y = currentCoordinates.get(1);
        try {
            if (y != 7 && x != 7 && isEmptySpaceOrOccupiedByEnemyPiece(x + 1, y + 1))
                this.possibleCoordinates.add(new ArrayList<Integer>(Arrays.asList(x + 1, y + 1)));
            if (y != 0 && x !=7 && isEmptySpaceOrOccupiedByEnemyPiece(x + 1, y - 1))
                this.possibleCoordinates.add(new ArrayList<Integer>(Arrays.asList(x + 1, y - 1)));
            if (y != 0 && x != 0 && isEmptySpaceOrOccupiedByEnemyPiece(x - 1, y - 1))
                this.possibleCoordinates.add(new ArrayList<Integer>(Arrays.asList(x - 1, y - 1)));
            if (x != 0 && y != 7 && isEmptySpaceOrOccupiedByEnemyPiece(x - 1, y + 1))
                this.possibleCoordinates.add(new ArrayList<Integer>(Arrays.asList(x - 1, y + 1)));
            if (y != 7 && isEmptySpaceOrOccupiedByEnemyPiece(x, y + 1)) this.possibleCoordinates.add(new ArrayList<Integer>(Arrays.asList(x, y + 1)));
            if (y != 0 && isEmptySpaceOrOccupiedByEnemyPiece(x, y - 1)) this.possibleCoordinates.add(new ArrayList<Integer>(Arrays.asList(x, y - 1)));
            if (x != 0 && isEmptySpaceOrOccupiedByEnemyPiece(x - 1, y)) this.possibleCoordinates.add(new ArrayList<Integer>(Arrays.asList(x - 1, y)));
            if (x != 7 && isEmptySpaceOrOccupiedByEnemyPiece(x + 1, y)) this.possibleCoordinates.add(new ArrayList<Integer>(Arrays.asList(x + 1, y)));
        } catch (ArrayIndexOutOfBoundsException e){
            e.printStackTrace();
        }

        for(int i = this.possibleCoordinates.size() - 1; i >= 0; i-- ){
            Player oppositePlayer = !this.isWhite ? Game.playerWhite() : Game.playerBlack();
            if(oppositePlayer.allControlledSquares().contains(this.possibleCoordinates.get(i))){
                this.possibleCoordinates.remove(i);
            }

        }

        return this.possibleCoordinates;
    }
}
