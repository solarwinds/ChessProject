package com.solarwindsmsp.chess.exceptions;

public class IllegalMoveException extends Exception {

    private String message;

    public IllegalMoveException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
