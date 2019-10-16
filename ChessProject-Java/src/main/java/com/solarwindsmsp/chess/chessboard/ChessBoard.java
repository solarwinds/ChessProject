package com.solarwindsmsp.chess.chessboard;

import com.solarwindsmsp.chess.piece.Pawn;
import com.solarwindsmsp.chess.piece.PieceColor;

import static com.solarwindsmsp.chess.chessboard.ChessBoardConstants.*;
import static com.solarwindsmsp.chess.chessboard.ChessBoardConstants.MIN_BOARD_WIDTH;

public class ChessBoard {

    private Square[][] board;

    public ChessBoard() {
        board = new Square[MAX_BOARD_WIDTH][MAX_BOARD_HEIGHT];
    }

    public void add(Pawn pawn, int xCoordinate, int yCoordinate, PieceColor pieceColor) {
        throw new UnsupportedOperationException("Need to implement ChessBoard.add()");
    }

    boolean isInvalidBoardPosition(int xCoordinate, int yCoordinate) {
        return isIllegalBoardPosition(xCoordinate, yCoordinate) || isBoardPositionTaken(xCoordinate, yCoordinate);
    }

    boolean isBoardPositionTaken(int xCoordinate, int yCoordinate) {
        return board[xCoordinate][yCoordinate].getChessPiece().isPresent();
    }

    boolean isIllegalBoardPosition(int xCoordinate, int yCoordinate) {
        boolean invalidXCoordinate = xCoordinate < MIN_BOARD_HEIGHT || xCoordinate > MAX_BOARD_HEIGHT;
        boolean invalidYCoordinate = yCoordinate < MIN_BOARD_WIDTH || yCoordinate > MAX_BOARD_WIDTH;

        return invalidXCoordinate || invalidYCoordinate;
    }
}