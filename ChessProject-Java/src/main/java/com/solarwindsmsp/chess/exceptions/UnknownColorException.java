package com.solarwindsmsp.chess.exceptions;

public class UnknownColorException extends Exception {

    private String message;

    public UnknownColorException(String message) {
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
