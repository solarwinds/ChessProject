package com.solarwindsmsp.chess;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class ChessBoardTest {

    private ChessBoard testSubject;
    private List<Cell> positions;

    @Before
    public void setUp() throws Exception {
        testSubject = new ChessBoard();
    }

	@Test
    public void testBoardWidth() {
        assertEquals(ChessConstants.BOARD_WIDTH, testSubject.getWidth());
    }

    @Test
    public void testBoardHeight() {
        assertEquals(ChessConstants.BOARD_HEIGHT, testSubject.getHeight());
    }

    @Test
    public void testLegalBoardPositions() {
    	setUpValidPositions();
    	for(Cell c: positions) {
    		assertTrue("Invalid position found " + positionString(c),
    					testSubject.IsLegalBoardPosition(c.getxPosition(), c.getyPosition()));
    	}
    }

    @Test
    public void testIllegalBoardPositions() {
    	setUpInvalidPositions();
    	for(Cell c: positions) {
    		assertFalse("Unexpected valid position found " + positionString(c),
    					testSubject.IsLegalBoardPosition(c.getxPosition(), c.getyPosition()));
    	}
    }

    private String positionString(Cell c) {
		return "x: " + c.getxPosition() + " y: " + c.getyPosition();
	}

    @Test(expected=CellOccupiedException.class)
    public void aCellMayNotBeOccupiedByTwoPieces() throws CellOccupiedException {
        Piece firstPawn = new Pawn(PieceColor.BLACK);
        Piece secondPawn = new Pawn(PieceColor.BLACK);
        testSubject.add(firstPawn, 6, 3);
        assertEquals(6, firstPawn.getCell().getxPosition());
        assertEquals(3, firstPawn.getCell().getyPosition());
        testSubject.add(secondPawn, 6, 3);
    }

    @Test
    public void addSetsXcoordinate() throws CellOccupiedException {
    	Pawn p = new Pawn(PieceColor.WHITE);
    	testSubject.add(p, 4, 3);
    	Cell c = p.getCell();
    	assertEquals(4, c.getxPosition());
    }
    
    @Test
    public void addSetsYcoordinate() throws CellOccupiedException {
    	Pawn p = new Pawn(PieceColor.WHITE);
    	testSubject.add(p, 4, 3);
    	Cell c = p.getCell();
        assertEquals(3, c.getyPosition());
    }
    
    /*
    @Test
    public void testLimits_The_Number_Of_Pawns()
    {
        for (int i = 0; i < 10; i++)
        {
            Pawn pawn = new Pawn(PieceColor.BLACK);
            int row = i / ChessBoard.MAX_BOARD_WIDTH;
            testSubject.Add(pawn, 6 + row, i % ChessBoard.MAX_BOARD_WIDTH, PieceColor.BLACK);
            if (row < 1)
            {
                assertEquals(6 + row, pawn.getXCoordinate());
                assertEquals(i % ChessBoard.MAX_BOARD_WIDTH, pawn.getYCoordinate());
            }
            else
            {
                assertEquals(-1, pawn.getXCoordinate());
                Assert.assertEquals(-1, pawn.getYCoordinate());
            }
        }
    }
    */

	private void setUpValidPositions() {
		positions = new ArrayList<>();
	    positions.add(new Cell(0,0));
	    positions.add(new Cell(5,5));
	    positions.add(new Cell(7,7));
	}

	private void setUpInvalidPositions() {
		positions = new ArrayList<>();
	    positions.add(new Cell(11,5));
	    positions.add(new Cell(0,9));
	    positions.add(new Cell(11,0));
	    positions.add(new Cell(5,-1));
	}
}