package com.solarwindsmsp.chess;

import static org.junit.Assert.assertEquals;

public class TestHelper {

    static void assertCoordinates(Piece p, int x, int y) {
        assertEquals(x, p.getXCoordinate());
        assertEquals(y, p.getYCoordinate());
    }

}
