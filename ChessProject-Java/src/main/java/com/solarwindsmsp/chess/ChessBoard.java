package com.solarwindsmsp.chess;

import com.solarwindsmsp.chess.model.ChessPiece;
import lombok.Data;

@Data
public class ChessBoard {

    public static int MAX_BOARD_WIDTH = 7;
    public static int MAX_BOARD_HEIGHT = 7;

    private ChessPiece[][] pieces;

    public ChessBoard() {
        pieces = new ChessPiece[MAX_BOARD_WIDTH][MAX_BOARD_HEIGHT];
    }

    public void addPiece(ChessPiece piece, int xCoordinate, int yCoordinate, PieceColor pieceColor) {
        if (!isLegalBoardPosition(xCoordinate, yCoordinate)) {
            throw new IllegalStateException("Illegal board position.");
        }

        this.pieces[xCoordinate][yCoordinate] = piece;
    }

    public boolean isLegalBoardPosition(int xCoordinate, int yCoordinate) {
        return xCoordinate >= 0 && xCoordinate < MAX_BOARD_WIDTH &&
                yCoordinate >= 0 && yCoordinate < MAX_BOARD_HEIGHT;
    }

    public boolean isEmptyAtCoordinates(int xCoordinate, int yCoordinate) {
        return this.pieces[xCoordinate][yCoordinate] == null;
    }
}
