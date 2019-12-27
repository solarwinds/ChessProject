package com.solarwindsmsp.chess;

/**
 * Exception class for invalid piece movement
 */
public class InvalidMoveException extends Exception {
    public InvalidMoveException(String errorMessage) {
        super(errorMessage);
    }   
}

