package com.solarwindsmsp.chess;

public interface IPiece {

    ChessBoard getChesssBoard();
    void setChessBoard(ChessBoard chessBoard);

    int getXCoordinate();
    void setXCoordinate(int value);

    int getYCoordinate();
    void setYCoordinate(int value);

    void Move(MovementType movementType, int newX, int newY);

    PieceColor getPieceColor();

}
