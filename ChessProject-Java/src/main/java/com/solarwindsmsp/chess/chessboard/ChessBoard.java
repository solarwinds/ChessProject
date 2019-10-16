package com.solarwindsmsp.chess.chessboard;

import com.solarwindsmsp.chess.chessboard.square.Square;
import com.solarwindsmsp.chess.piece.ChessPiece;

import static com.solarwindsmsp.chess.chessboard.ChessBoardConstants.MAX_BOARD_WIDTH;
import static com.solarwindsmsp.chess.chessboard.ChessBoardConstants.MIN_BOARD_WIDTH;
import static com.solarwindsmsp.chess.chessboard.ChessBoardConstants.MAX_BOARD_HEIGHT;
import static com.solarwindsmsp.chess.chessboard.ChessBoardConstants.MIN_BOARD_HEIGHT;

public class ChessBoard {

    private Square[][] board;

    public ChessBoard() {
        board = BoardFactory.create();
    }

    public Square[][] getBoard() {
        return board;
    }

    public boolean add(ChessPiece chessPiece, int xCoordinate, int yCoordinate) {
        if (isInvalidBoardPosition(xCoordinate, yCoordinate)) {
            return false;
        }

        Square square = board[xCoordinate][yCoordinate];

        chessPiece.setChessBoard(this);
        chessPiece.setXCoordinate(xCoordinate);
        chessPiece.setYCoordinate(yCoordinate);

        square.setPiece(chessPiece);

        return true;
    }

    public boolean move(int oldXCoordinate, int oldYCoordinate, int newXCoordinate, int newYCoordinate) {
        if (isInvalidBoardPosition(newXCoordinate, newYCoordinate)) {
            return false;
        }

        Square oldPosition = board[oldXCoordinate][oldYCoordinate];
        if (!oldPosition.getChessPiece().isPresent()) {
            return false;
        }

        ChessPiece chessPiece = oldPosition.getChessPiece().get();
        chessPiece.setXCoordinate(newXCoordinate);
        chessPiece.setYCoordinate(newYCoordinate);

        board[newXCoordinate][newYCoordinate].setPiece(chessPiece);
        board[oldXCoordinate][oldYCoordinate].removePiece();

        return true;
    }

    boolean isIllegalBoardPosition(int xCoordinate, int yCoordinate) {
        boolean invalidXCoordinate = xCoordinate < MIN_BOARD_HEIGHT || xCoordinate > MAX_BOARD_HEIGHT;
        boolean invalidYCoordinate = yCoordinate < MIN_BOARD_WIDTH || yCoordinate > MAX_BOARD_WIDTH;

        return invalidXCoordinate || invalidYCoordinate;
    }

    private boolean isInvalidBoardPosition(int xCoordinate, int yCoordinate) {
        return isIllegalBoardPosition(xCoordinate, yCoordinate) || isBoardPositionTaken(xCoordinate, yCoordinate);
    }

    private boolean isBoardPositionTaken(int xCoordinate, int yCoordinate) {
        return board[xCoordinate][yCoordinate].getChessPiece().isPresent();
    }
}