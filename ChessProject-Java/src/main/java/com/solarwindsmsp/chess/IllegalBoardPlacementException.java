package com.solarwindsmsp.chess;

public class IllegalBoardPlacementException extends Exception {
    public IllegalBoardPlacementException(String reason) {
        super(reason);
    }
}
