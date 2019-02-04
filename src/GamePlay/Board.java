package GamePlay;


import Pieces.*;

import java.nio.channels.Pipe;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class Board {
    public static Piece [][] board;
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_WHITE = "\u001B[37m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";
    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";

    Board(){
       board = new Piece[8][8];
       setupBoard2();
       getAllOccupiedSquares(Game.playerWhite, false, board);
       getAllOccupiedSquares(Game.playerBlack, false, board);
       System.out.println();
    }

//    private void setupBoard(){
//        for(Integer i = 0; i < 8; i++){
//            if(i == 1 || i == 6){
//                for(Integer j = 0; j < 8; j++){
//                    Pawn pawn = (i == 1) ? new Pawn(new ArrayList<Integer>(Arrays.asList(i, j}, true) : new Pawn(new ArrayList<Integer>(Arrays.asList(i, j}, false);
//                    board[i][j] = pawn;
//                }
//            } else{
//                Piece piece = null;
//                for(Integer j = 0; j < 8; j++){
//                    if(j == 0 || j == 7){
//                        piece =  (i == 0) ? new Rook(new ArrayList<Integer>(Arrays.asList(i, j}, true) : new Rook(new ArrayList<Integer>(Arrays.asList(i, j}, false);
//                    }else if(j == 1 || j == 6){
//                        piece =  (i == 0) ? new Knight(new ArrayList<Integer>(Arrays.asList(i, j}, true) : new Knight(new ArrayList<Integer>(Arrays.asList(i, j}, false);
//                    }else if(j == 2 || j == 5){
//                        piece =  (i == 0) ? new Bishop(new ArrayList<Integer>(Arrays.asList(i, j}, true) : new Bishop(new ArrayList<Integer>(Arrays.asList(i, j}, false);
//                    }else if(j == 3){
//                        piece =  (i == 0) ? new Queen(new ArrayList<Integer>(Arrays.asList(i, j}, true) : new Queen(new ArrayList<Integer>(Arrays.asList(i, j}, false);
//                    }else if(j == 4){
//                        piece =  (i == 0) ? new King(new ArrayList<Integer>(Arrays.asList(i, j}, true) : new King(new ArrayList<Integer>(Arrays.asList(i, j}, false);
//                    }
//                    board[i][j] = piece;
//                }
//            }
//            if(i == 1) i = 5;
//        }
//    }

    private void setupBoard2(){
        for(Integer i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++)
            if(j== 1 || j == 6){
                    Pawn pawn = (j == 1) ? new Pawn(new ArrayList<Integer>(Arrays.asList(i, j)), true) : new Pawn(new ArrayList<Integer>(Arrays.asList(i, j)), false);
                    if (pawn.isWhite()) Game.playerWhite.pieces().add(pawn);
                    else Game.playerBlack.pieces().add(pawn);
                    board[i][j] = pawn;
                    if(j == 1) j = 5; // skip the middle 4 rows
            } else{
                Piece piece = null;
                if(i == 0 || i == 7){
                    piece =  (j == 0) ? new Rook(new ArrayList<Integer>(Arrays.asList(i, j)), true) : new Rook(new ArrayList<Integer>(Arrays.asList(i, j)), false);
                }else if(i == 1 || i == 6){
                    piece =  (j == 0) ? new Knight(new ArrayList<Integer>(Arrays.asList(i, j)), true) : new Knight(new ArrayList<Integer>(Arrays.asList(i, j)), false);
                }else if(i == 2 || i == 5){
                    piece =  (j == 0) ? new Bishop(new ArrayList<Integer>(Arrays.asList(i, j)), true) : new Bishop(new ArrayList<Integer>(Arrays.asList(i, j)), false);
                }else if(i == 3){
                    piece =  (j == 0) ? new Queen(new ArrayList<Integer>(Arrays.asList(i, j)), true) : new Queen(new ArrayList<Integer>(Arrays.asList(i, j)), false);
                }else if(i == 4){
                    if(j == 0){
                        piece = new King(new ArrayList<Integer>(Arrays.asList(i, j)), true);
                        Game.playerWhite.setKing((King) piece);
                    } else {
                        piece = new King(new ArrayList<Integer>(Arrays.asList(i, j)), false);
                        Game.playerBlack.setKing((King) piece);
                    }
                }
                if (piece != null && piece.isWhite()) Game.playerWhite.pieces().add(piece);
                else Game.playerBlack.pieces().add(piece);
                board[i][j] = piece;
            }

        }
    }

    public void printBoard(){
        System.out.println();
        for (int i = 7; i >= 0; i--){
            for (int j = 0; j < 8; j++) {
                if (board[j][i] !=null){
                    if(board[j][i].isWhite()){
                        System.out.format(ANSI_WHITE + board[j][i] + " "+ ANSI_RESET, "%30s");
                    }else if(!board[j][i].isWhite()){
                        System.out.format(ANSI_BLACK + board[j][i] + " "+ANSI_RESET, "%30s");
                    }
                }else System.out.format("-----  ", "%30s");
            }
            System.out.println("\n");
        }
        System.out.println();

    }

    static boolean move(Player player, ArrayList<Integer> moveSet){
        Piece piece;
        if(board[moveSet.get(0)][moveSet.get(1)] != null) {
            piece = board[moveSet.get(0)][moveSet.get(1)];
            if(piece.isWhite() != player.isWhite()) {
                System.out.println("That is not your piece!");
                return false;
            }
            Player opponent = !player.isWhite() ? Game.playerWhite : Game.playerBlack;
            Piece oldOpponentPiece = null;
            //collect all the possible old coordinates in case move is illegal
            ArrayList<Integer> oldCoordinates = piece.currentCoordinates();
            HashSet<ArrayList<Integer>> oldWhiteControlledSquares = (HashSet<ArrayList<Integer>>) Game.playerWhite.allControlledSquares().clone();
            HashSet<ArrayList<Integer>> oldBlackControlledSquares = (HashSet<ArrayList<Integer>>) Game.playerBlack.allControlledSquares().clone();
            if(board[moveSet.get(2)][moveSet.get(3)] != null && (board[moveSet.get(2)][moveSet.get(3)].isWhite() == opponent.isWhite())){
               oldOpponentPiece = board[moveSet.get(2)][moveSet.get(3)];
            }

            String pieceName = piece.getClass().getSimpleName();
            ArrayList<ArrayList<Integer>> list = piece.possibleCoordinates(); // was already calculated
            if(list.size() == 0) {
                System.out.println("The " + pieceName + " cannot move! Enter another move.\n");
                return false;
            }
            List<Integer> tail =  moveSet.subList(moveSet.size() / 2, moveSet.size()); //the destination coordinates
            boolean flag = false;
            for (ArrayList<Integer> coordinates : list){
                if (coordinates.equals(tail)){
                    //the destination coordinates matched with the piece's possible coordinates
                    //so we can actually go ahead and move the piece
                    board[moveSet.get(2)][moveSet.get(3)] = piece;
                    board[moveSet.get(0)][moveSet.get(1)] = null;
                    if(oldOpponentPiece != null) {
                        opponent.pieces().remove(oldOpponentPiece);
                        System.out.println("You captured the " + oldOpponentPiece);
                    }
                    flag = true;
                    break;
                }
            }
            if(!flag){
                System.out.println("Your " +pieceName+ " cannot move there!\n");
                return false;
            }
            piece.setCurrentCoordinates(new ArrayList<>(tail));
            player.allControlledSquares().clear();
            opponent.allControlledSquares().clear();
            getAllOccupiedSquares(player, true, board);
            getAllOccupiedSquares(opponent, false, board);
            //printAllOccupiedSquares(Game.playerWhite);

            /*
             * we have to actually check that upon moving the piece that the player did not
             * place themselves in check. This is done in the following loop.
             */
            for(ArrayList<Integer> coordinates : opponent.allControlledSquares()){
                if(player.king().currentCoordinates().equals(coordinates)){
                    //an illegal move as you'll be in check. Gotta reset the board to the way it was
                    board[moveSet.get(0)][moveSet.get(1)] = piece;
                    board[moveSet.get(2)][moveSet.get(3)] = oldOpponentPiece;
                    piece.setCurrentCoordinates(oldCoordinates);
                    Game.playerWhite.setAllControlledSquares(oldWhiteControlledSquares);
                    Game.playerBlack.setAllControlledSquares(oldBlackControlledSquares);
                    if(opponent.isInCheck()) opponent.setInCheck(false); //current put opponent in check with
                    //the most recent move, but move was illegal, so must remove the check
                    if(!player.isInCheck()) System.out.println("Illegal move! You'll put yourself in Check");
                    else System.out.println("You're in Check! You must get out of Check");
                    return false;
                }
            }
//            for (ArrayList<Integer> coordinates : player.allControlledSquares()){
//                if(opponent.king().currentCoordinates().equals(coordinates)){
//                    opponent.setInCheck(true);
//                    break;
//                }
//            }
            if(opponent.isInCheck()){
                checkForCheckMate(player, opponent);
            }
            if (player.isInCheck()) {
                player.setInCheck(false); //King got out of check, otherwise, the code would never reach this line
                player.king().checkedBy().clear();
            }
           // printAllOccupiedSquares(Game.playerWhite);
            System.out.println();
           // printAllOccupiedSquares(Game.playerBlack);
            return true;
        } else {
            System.out.println("There is nothing on that square! Enter another move.\n");
            return false;
        }

    }

    private void checkCurrentPlayerNotInCheck(Player player, ArrayList<Integer> moveSet){

    }

    private static  boolean checkForCheckMate(Player player, Player opponent){
        King opponentKing = opponent.king();
        opponentKing.calculatePossibleCoordinates();
        if(opponentKing.checkedBy().size() == 0) throw new IllegalArgumentException("Error, The king must be Checked by some piece");
        if (opponentKing.checkedBy().size() > 1){ //i.e. it's double check and king must move
//           for (int i = opponentKing.possibleCoordinates().size() - 1; i >= 0; i-- ){
//               if (player.allControlledSquares().contains(opponentKing.possibleCoordinates().get(i))){
//                   opponentKing.possibleCoordinates().remove(i);
//               }
//           }
//           if (opponentKing.possibleCoordinates().size() == 0){
//               return true;
//           }
            if(opponentKing.possibleCoordinates().size() == 0) opponent.setCheckmated(true);
           return false;
        } else{
            for (int i = opponentKing.possibleCoordinates().size() - 1; i >= 0; i-- ){
                if (player.allControlledSquares().contains(opponentKing.possibleCoordinates().get(i))){
                    opponentKing.possibleCoordinates().remove(i);
                }
            }
            if (opponentKing.possibleCoordinates().size() != 0){
                return true;
            }
            ArrayList<Integer> coordOfPieceGivingCheck = opponentKing.checkedBy().get(0).currentCoordinates();
            //checking if the piece can be captured
            //TODO we dont know if this piece is pinned... gotta fix this
            if(opponent.allControlledSquares().contains(coordOfPieceGivingCheck)) {
                boolean pieceCanBeTaken = false;
                for (Piece piece : opponent.pieces()) {
                    if (piece.possibleCoordinates().contains(coordOfPieceGivingCheck)) {
                        Piece[][] tempBoard = board.clone();
                        tempBoard[coordOfPieceGivingCheck.get(0)][coordOfPieceGivingCheck.get(1)] = piece;
                        tempBoard[piece.currentCoordinates().get(0)][piece.currentCoordinates().get(1)] = null;
                        getAllOccupiedSquares(player, false, tempBoard);
                        getAllOccupiedSquares(opponent, false, tempBoard);
                        if (player.allControlledSquares().contains(opponent.king().currentCoordinates())) {
                            getAllOccupiedSquares(player, false, board);
                            getAllOccupiedSquares(opponent, false, board);
                        } else {
                            pieceCanBeTaken = true;
                            getAllOccupiedSquares(player, false, board);
                            getAllOccupiedSquares(opponent, false, board);
                            break;
                        }

                    }
                }
                if(pieceCanBeTaken) return false;
            }
            //if it's a knight, then it's checkmate bc already proved knight can't be captured or blocked
            if (opponentKing.checkedBy().get(0).getClass().getSimpleName().equals("Knight")) return false;


        }
        return true;
    }

    private static void printAllOccupiedSquares(Player player){
        for(ArrayList<Integer> coordinates : player.allControlledSquares()){
            System.out.println(coordinates);
        }
    }

    private  static void getAllOccupiedSquares(Player player, boolean currentPlayer, Piece[][] tempBoard){
        ArrayList<Piece> allPieces= player.pieces();
            for(Piece piece : allPieces){
                piece.possibleCoordinates().clear();
                ArrayList<ArrayList<Integer>> list;
                if(tempBoard == board) {
                  list = piece.calculatePossibleCoordinates();
                } else {
                    piece.setBoard(tempBoard);
                    list = piece.calculatePossibleCoordinates();
                    //piece.setBoard(board);
                }
                Player opposite = null;
                if(currentPlayer){
                  opposite = player.isWhite() ? Game.playerBlack : Game.playerWhite;
                }
                for(ArrayList<Integer> coordinates : list){
                    if(opposite != null){
                        if(opposite.king().currentCoordinates().equals(coordinates)){
                            opposite.setInCheck(true);
                            opposite.king().checkedBy().add(piece);
                        }
                    }
                    if(!player.allControlledSquares().add(coordinates)){
                       // System.out.println("duplicate coordinates");
                    }
                }

            }

    }
}
