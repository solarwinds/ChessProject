package com.solarwindsmsp.chess.model;

import com.solarwindsmsp.chess.ChessBoard;
import com.solarwindsmsp.chess.MovementType;
import com.solarwindsmsp.chess.PieceColor;

public interface ChessPiece {

    void setCoordinates(int xCoordinate, int yCoordinate);

    void setChessBoard(ChessBoard chessBoard);

    void move(MovementType movementType, int newX, int newY);

    boolean isLegalMove(MovementType movementType, int newX, int newY);

    boolean isLegalInitialCoordinates(int x, int y);

    ChessBoard getChessBoard();
    PieceColor getPieceColor();
}
