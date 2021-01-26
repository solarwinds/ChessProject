package com.solarwindsmsp.chess;

public interface PieceActions {

    void move(MovementType movementType, int newX, int newY);

    boolean isLegalMove(int newX, int newY);

    boolean isLegalCapture(int newX, int newY);
}
