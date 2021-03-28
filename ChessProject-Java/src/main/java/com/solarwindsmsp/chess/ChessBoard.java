package com.solarwindsmsp.chess;

public class ChessBoard {

    //Setting the board to the correct MAX values

    public static int MAX_BOARD_WIDTH = 8;
    public static int MAX_BOARD_HEIGHT = 8;

    private Pawn[][] pieces;

    public ChessBoard() {
        pieces = new Pawn[MAX_BOARD_WIDTH][MAX_BOARD_HEIGHT];
    }

    public void addPiece(Pawn pawn, int xCoordinate, int yCoordinate, PieceColor pieceColor) {
        //Method will utilise isLegalBoardPosition to determine if placement is possible
        if (isLegalBoardPosition(xCoordinate, yCoordinate)){
            //Can call Pawn creation after validating  

        }
        

    }

    public boolean isLegalBoardPosition(int xCoordinate, int yCoordinate) {
        //Method to check x and y position is within the bounds of the Chessboard setting && to ensure all conditions are true
        if (xCoordinate > -1 && xCoordinate < MAX_BOARD_WIDTH && yCoordinate > -1 && yCoordinate < MAX_BOARD_HEIGHT){
            //Return a true value to confirm the position is valid
            return true;
        }

        //Return a false value to confirm that the position is not valid
        return false;
        
    }



}
