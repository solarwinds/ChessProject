package com.solarwindsmsp.chess;

public class IllegalBoardCoordinatesException extends Exception {

    public IllegalBoardCoordinatesException(String message, int x, int y) {
        super(message + " Coordinates: x=" + x + " and y=" + y);
    }

}
