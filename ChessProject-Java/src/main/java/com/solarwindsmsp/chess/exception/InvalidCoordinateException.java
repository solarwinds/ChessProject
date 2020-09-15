package com.solarwindsmsp.chess.exception;

import java.text.MessageFormat;

public class InvalidCoordinateException extends RuntimeException {

    public InvalidCoordinateException(int row, int column) {
        super(MessageFormat.format("The coordinates [{0}, {1}] are invalid", row, column));
    }
}
