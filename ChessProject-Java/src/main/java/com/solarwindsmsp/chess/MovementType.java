package com.solarwindsmsp.chess;

/**
 * Enum class for movement types
 */
public enum MovementType {

    MOVE, CAPTURE;

    @Override
    public String toString() {
        return name();
    }
}
