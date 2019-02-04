package GamePlay;
import Pieces.King;
import Pieces.Piece;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;

public class Player {
    private final boolean isWhite;
    private HashSet<ArrayList<Integer>> allControlledSquares = new HashSet<>();
    private ArrayList<Piece> pieces = new ArrayList<>();
    private King king;
    private boolean isInCheck = false;
    private boolean isCheckmated = false;
    private boolean isStalemated = false;
    private static int drawCount = 0;
    public Player(boolean isWhite){
        this.isWhite = isWhite;
    }

    String enterMove() throws IOException {
        String color;
        if(drawCount == 1){
            color = "Player " + (!isWhite ? "White" : "Black") + " wishes to draw. Player " + (isWhite ? "White," : "Black,")+" type 'draw' if you accept.";
            System.out.println(color);
        } else {
            drawCount = 0;

            if(isInCheck) color = "Check! Enter your move, Player " + (isWhite ? "White!" : "Black!");
            else color = "Enter your move, Player " + (isWhite ? "White!" : "Black!");
            System.out.println(color);
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String result = br.readLine();
        if(result.equals("draw")) drawCount++;
        return result;
    }

    void move(ArrayList<Integer> moveSet){
        Board.move(this, moveSet);
    }

    public ArrayList<Piece> pieces() {
        return pieces;
    }

    public void setCheckmated(boolean checkmated) {
        isCheckmated = checkmated;
    }


    public HashSet<ArrayList<Integer>> allControlledSquares() {
        return allControlledSquares;
    }

    public void setAllControlledSquares(HashSet<ArrayList<Integer>> allControlledSquares) {
        this.allControlledSquares = allControlledSquares;
    }

    boolean piecePinned(){
        return false;
    }

    boolean isInCheck(){
        return isInCheck;
    }

    public boolean isCheckmated() {
        return isCheckmated;
    }

    public boolean isStalemated() {
        return isStalemated;
    }

    public void setKing(King king) {
        this.king = king;
    }

    public King king() {
        return king;
    }

    public boolean isWhite() {
        return isWhite;
    }

    public void setInCheck(boolean inCheck) {
        isInCheck = inCheck;
    }
}
