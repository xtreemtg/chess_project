package GamePlay;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Game {
    static Board board;
    static Player playerWhite;
    static Player playerBlack;
    private boolean whiteTurn;
    private boolean isDrawed;
    //private boolean isCheckmate;


    public Game(){
        this.playerWhite = new Player(true);
        this.playerBlack = new Player(false);
        this.whiteTurn = true;
        this.board = new Board();
    }


    public static void main(String[] args) throws IOException {
        Game game = new Game();
        game.playGame();
    }

    public void playGame() throws IOException{
        System.out.println("Welcome to Chess!");
        int drawCount = 0;
        String move;
        while (true){
            board.printBoard();
            move = whiteTurn? playerWhite.enterMove() : playerBlack.enterMove();
            if(move.equals("draw")){
                if (drawCount == 1){
                    System.out.println("The players have agreed to a draw. Game over.");
                    System.exit(1);
                } else {
                    drawCount++;
                    whiteTurn = !whiteTurn;
                    continue;
                }
            }
            drawCount = 0;
            if (whiteTurn) {
                if(!Board.move(playerWhite, NotationParser.simpleParse(move))) continue;
                if( playerBlack.isCheckmated()){
                    System.out.println("Checkmate! White wins! Game over!");
                    System.exit(1);
                } else if( playerBlack.isStalemated()) {
                    System.out.println("Stalemate! Black has no legal moves! Game over!");
                    System.exit(1);
                }else {

                    whiteTurn = !whiteTurn;
                }
            } else {
                if(!Board.move(playerBlack, NotationParser.simpleParse(move))) continue;
                if( playerWhite.isCheckmated()) {
                    System.out.println("Checkmate! Black wins! Game over!");
                    System.exit(1);
                } else if( playerWhite.isStalemated()) {
                    System.out.println("Stalemate! White has no legal moves! Game over!");
                    System.exit(1);
                }
                else{
                    whiteTurn = !whiteTurn;
                }
            }
        }

    }

    public static Player playerBlack() {
        return playerBlack;
    }
    public static Player playerWhite() {
        return playerWhite;
    }

    public boolean isWhiteTurn() {
        return whiteTurn;
    }
}
