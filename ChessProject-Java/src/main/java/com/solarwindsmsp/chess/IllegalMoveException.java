package com.solarwindsmsp.chess;

public class IllegalMoveException extends Exception {
    public IllegalMoveException(String reason) {
        super(reason);
    }
}
