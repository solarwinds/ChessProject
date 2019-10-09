package com.solarwindsmsp.chess;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class PawnTest {

    private static final String EXPECTED_POSITION_STRING = 
    		"Current X: 2 \r\nCurrent Y: 4 \r\nColour: BLACK";
	private Pawn testSubject;

    @Before
    public void setUp() {
        new ChessBoard();
        this.testSubject = new Pawn(PieceColor.BLACK);
    }

    @Test
    public void displayPawnDetails() {
    	Cell testCell = new Cell (2, 4);
    	testSubject.setCell(testCell);
    	String details = testSubject.getDetails();
    	assertEquals(EXPECTED_POSITION_STRING, details);
    }
    
}