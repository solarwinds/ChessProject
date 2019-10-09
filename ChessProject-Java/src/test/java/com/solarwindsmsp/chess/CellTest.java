package com.solarwindsmsp.chess;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

public class CellTest {
	
	private static final String EXPECTED_POSITION_STRING = "Current X: 3 \r\nCurrent Y: 4 \r\n";
	private static final int FOUR = 4;
	private static final int THREE = 3;
	private Cell cell;
	
	@Before
	public void beforeEachTest() {
		cell = new Cell(THREE, FOUR);
	}
	
	@Test
	public void testCellConstructor() {
		assertEquals(THREE, cell.getxPosition());
		assertEquals(FOUR, cell.getyPosition());
		assertFalse(cell.isOccupied());
	}
	
	@Test
	public void aPieceCanBeAddedToAnEmptyCell() throws CellOccupiedException {
		cell.setPiece(new Pawn(PieceColor.WHITE));
		assertEquals(Pawn.class, cell.getPiece().getClass());
		assertEquals(PieceColor.WHITE, cell.getPiece().getColour());
	}
	
	@Test(expected=CellOccupiedException.class)
	public void whenACellIsOccupiedASecondPieceCannotBeAdded() throws CellOccupiedException {
		cell.setPiece(new Pawn(PieceColor.WHITE));
		cell.setPiece(new Pawn(PieceColor.BLACK));
	}
	
	@Test
	public void clearRemovesThePieceFromAnOccupiedCell() throws CellOccupiedException {
		cell.setPiece(new Pawn(PieceColor.WHITE));
		cell.clear();
		assertNull(cell.getPiece());
	}
	
	@Test
	public void positionAsString() {
		assertEquals(EXPECTED_POSITION_STRING, cell.positionAsString());
	}
}
