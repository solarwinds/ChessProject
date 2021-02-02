package com.solarwindsmsp.chess;

public interface MovementStrategy {
    boolean canMove(Piece piece, int x, int y);
}
