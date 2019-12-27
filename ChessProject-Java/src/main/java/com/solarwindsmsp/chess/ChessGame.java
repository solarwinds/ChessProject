package com.solarwindsmsp.chess;

/**
 * Class for managing a chess board and all of its pieces
 */
public class ChessGame {
    private final ChessBoard chessBoard;
    
    public ChessGame() {
        chessBoard = new ChessBoard();
    }
    
    public ChessBoard getChessBoard() {
        return chessBoard;
    }
    
    public Piece add(Class<? extends Piece> pieceClass, int xCoordinate, int yCoordinate, PieceColor pieceColor)
            throws InvalidPlacementException {
        try {
            // construct new instance of the given piece class, and add it to the boatd
            Piece piece = pieceClass.newInstance();
            chessBoard.add(piece, xCoordinate, yCoordinate, pieceColor);
            return piece;
        } catch (InstantiationException e) {
            // wrap up internal exceptions to keep signature of this method clean
            throw new InvalidPlacementException("Failed to create new piece", e);
        } catch (IllegalAccessException e) {
            // wrap up internal exceptions to keep signature of this method clean
            throw new InvalidPlacementException("Illegal access creating new piece", e);
        }
    }
}
