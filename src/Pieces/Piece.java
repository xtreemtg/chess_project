package Pieces;
import GamePlay.Board;
import GamePlay.Player;


import java.util.ArrayList;
import java.util.Arrays;

public class Piece {
    ArrayList<Integer> currentCoordinates;
    ArrayList<ArrayList<Integer>> possibleCoordinates;
    Piece [][] board = Board.board;
    boolean isWhite;
    boolean isPinned;


    Piece(ArrayList<Integer> currentCoordinates, boolean isWhite){
        this.currentCoordinates = currentCoordinates;
        this.possibleCoordinates = new ArrayList<>();
        this.isWhite = isWhite;
    }

    public ArrayList<Integer> currentCoordinates() {
        return currentCoordinates;
    }

    public void setCurrentCoordinates(ArrayList<Integer> currentCoordinates){
        this.currentCoordinates = currentCoordinates;
    }

    public boolean isWhite() {
        return isWhite;
    }

    boolean occupiedByEnemyPiece(int x, int y){
        return ((board[x][y] != null) && (board[x][y].isWhite != isWhite));
    }

    boolean isEmptySpace(int x, int y){
        return (board[x][y] == null);
    }

    boolean isEmptySpaceOrOccupiedByEnemyPiece(int x, int y){
        return (isEmptySpace(x, y) || occupiedByEnemyPiece(x, y));
    }

    boolean occupiedByFriendlyPiece(int x, int y){
        return (board[x][y] != null && board[x][y].isWhite == isWhite);
    }

    public ArrayList<ArrayList<Integer>> possibleCoordinates() {
        return possibleCoordinates;
    }

    public ArrayList<ArrayList<Integer>> calculatePossibleCoordinates(){
        return null;
    }

    public void setBoard(Piece[][] board) {
        this.board = board;
    }
}
