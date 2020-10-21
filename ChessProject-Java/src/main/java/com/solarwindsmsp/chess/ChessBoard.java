package com.solarwindsmsp.chess;

import com.solarwindsmsp.chess.model.ChessPiece;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
/**
 * Chessboard with usual coordinates system
 * y
 * ^
 * |
 * |
 * |
 * -------> x
 * */
public class ChessBoard {

    public static int MAX_BOARD_WIDTH = 7;
    public static int MAX_BOARD_HEIGHT = 7;

    private ChessPiece[][] pieces;

    public ChessBoard() {
        pieces = new ChessPiece[MAX_BOARD_WIDTH][MAX_BOARD_HEIGHT];
    }

    // for initial setup of the board
    public void addPiece(ChessPiece piece, int xCoordinate, int yCoordinate) {
        log.info("addPiece {} at coordinates x {} y {}", piece, xCoordinate, yCoordinate);

        if (!isLegalBoardPosition(xCoordinate, yCoordinate)) {
            throw new IllegalArgumentException("Illegal board position.");
        }
        if (this.pieces[xCoordinate][yCoordinate] != null) {
            // element is not added to board and set to invalid coordinates
            piece.setCoordinates(-1, -1);
            return;
        }

//        if (piece.isLegalInitialCoordinates(xCoordinate, yCoordinate)) {
            piece.setCoordinates(xCoordinate, yCoordinate);
            this.pieces[xCoordinate][yCoordinate] = piece;
//        }

    }

    public boolean isLegalBoardPosition(int xCoordinate, int yCoordinate) {
        return xCoordinate >= 0 && xCoordinate <= MAX_BOARD_WIDTH &&
                yCoordinate >= 0 && yCoordinate <= MAX_BOARD_HEIGHT;
    }

    public boolean isEmptyAtCoordinates(int xCoordinate, int yCoordinate) {
        return this.pieces[xCoordinate][yCoordinate] == null;
    }
}
