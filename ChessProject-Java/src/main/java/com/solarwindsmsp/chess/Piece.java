package com.solarwindsmsp.chess;

public interface Piece {

    void move(MovementType movementType, int newX, int newY);

    int getXCoordinate();

    void setXCoordinate(int value);

    int getYCoordinate();

    void setYCoordinate(int value);

    PieceColor getColor();

    ChessBoard getChessBoard();

    void setChessBoard(ChessBoard chessBoard);
}
