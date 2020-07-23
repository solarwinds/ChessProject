package com.solarwindsmsp.chess.constants;

import org.junit.jupiter.api.Test;

import static com.solarwindsmsp.chess.constants.Constants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConstantsTest {

    @Test
    public void minBoardIndex_is0() {
        assertEquals(0, MIN_BOARD_INDEX);
    }

    @Test
    public void maxBoardIndex_is7() {
        assertEquals(7, MAX_BOARD_INDEX);
    }

    @Test
    public void invalidIndex_isNegative1() {
        assertEquals(-1, INVALID_INDEX);
    }

    @Test
    public void validWhitePawnStartingPositionIndex_is1() {
        assertEquals(1, VALID_WHITE_PAWN_STARTING_POSITION_INDEX);
    }

    @Test
    public void validBlackPawnStartingPositionIndex_is6() {
        assertEquals(6, VALID_BLACK_PAWN_STARTING_POSITION_INDEX);
    }
}
