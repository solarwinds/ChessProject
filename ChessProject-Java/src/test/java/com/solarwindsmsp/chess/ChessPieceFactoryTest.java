package com.solarwindsmsp.chess;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ChessPieceFactoryTest extends TestCase {

	
	@Before
	public void setUp() throws Exception {
		// No setup necessary as invoking static method
	}


	@Test
	public void testMakeChessPiece_Class_Pawn() {
		ChessPiece piece = ChessPieceFactory.MakeChessPiece(PieceType.PAWN, PieceColor.BLACK);	
		assertTrue(piece instanceof Pawn);
	}

	@Test
	public void testMakeChessPiece_Color_Black() {
		ChessPiece piece = ChessPieceFactory.MakeChessPiece(PieceType.PAWN, PieceColor.BLACK);	
		assertEquals(PieceColor.BLACK, piece.getPieceColor());
	}

	@Test
	public void testMakeChessPiece_Color_White() {
		ChessPiece piece = ChessPieceFactory.MakeChessPiece(PieceType.PAWN, PieceColor.WHITE);	
		assertEquals(PieceColor.WHITE, piece.getPieceColor());
	}

	@Test
	public void testMakeChessPiece_Class_Null() {
		ChessPiece piece = ChessPieceFactory.MakeChessPiece(null, PieceColor.WHITE);	
		assertEquals(null, piece);
	}

	@Test
	public void testMakeChessPiece_Color_Null() {
		ChessPiece piece = ChessPieceFactory.MakeChessPiece(PieceType.PAWN, null);	
		assertEquals(null, piece);
	}

}
