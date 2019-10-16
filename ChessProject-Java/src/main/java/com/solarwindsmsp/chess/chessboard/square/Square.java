package com.solarwindsmsp.chess.chessboard.square;

import com.solarwindsmsp.chess.piece.ChessPiece;

import java.util.Optional;

public class Square {

    private final SquareColor squareColor;
    private ChessPiece chessPiece;

    public Square(SquareColor squareColor) {
        this.squareColor = squareColor;
    }

    public SquareColor getSquareColor() {
        return squareColor;
    }

    public Optional<ChessPiece> getChessPiece() {
        return Optional.ofNullable(chessPiece);
    }

    public void setPiece(ChessPiece chessPiece) {
        this.chessPiece = chessPiece;
    }

    public void removePiece() {
        this.chessPiece = null;
    }
}