package com.solarwindsmsp.chess;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ChessBoardTest extends TestCase {

    private ChessBoard testSubject;
    
    private String eol = System.lineSeparator();

    @Before
    public void setUp() throws Exception {
        testSubject = new ChessBoard();
    }

    // Test modified to fit with change to idiomatic Java range 0 <= x < n
    @Test
    public void testHas_MaxBoardWidth_of_8() {
        assertEquals(8, ChessBoard.MAX_BOARD_WIDTH);
    }

    // Test modified to fit with change to idiomatic Java range 0 <= x < n    
    @Test
    public void testHas_MaxBoardHeight_of_8() {
        assertEquals(8, ChessBoard.MAX_BOARD_HEIGHT);
    }

    @Test
    public void testIsLegalBoardPosition_True_X_equals_0_Y_equals_0() {
        boolean isValidPosition = testSubject.IsLegalBoardPosition(0, 0);
        assertTrue(isValidPosition);
    }

    // Test modified to make call to assertTrue consistent 
    @Test
    public void testIsLegalBoardPosition_True_X_equals_5_Y_equals_5() {
        boolean isValidPosition = testSubject.IsLegalBoardPosition(5, 5);
        assertTrue(isValidPosition); 
    }

    // Test modified- expected result changed as should be false - not a legal position
    @Test
    public void testIsLegalBoardPosition_False_X_equals_11_Y_equals_5() {
        boolean isValidPosition = testSubject.IsLegalBoardPosition(11, 5);
        assertFalse(isValidPosition); 
    }

    @Test
    public void testIsLegalBoardPosition_False_X_equals_0_Y_equals_9() {
        boolean isValidPosition = testSubject.IsLegalBoardPosition(0, 9);
        assertFalse(isValidPosition);
    }

    @Test
    public void testIsLegalBoardPosition_False_X_equals_11_Y_equals_0() {
        boolean isValidPosition = testSubject.IsLegalBoardPosition(11, 0);
        assertFalse(isValidPosition);
    }

    // Test modified to make usage of assertFalse consistent
    @Test
    public void testIsLegalBoardPosition_False_For_Negative_Y_Values() {
        boolean isValidPosition = testSubject.IsLegalBoardPosition(5, -1);
        assertFalse(isValidPosition);
    }

    // Added "test" prefix to name to make consistent and discoverable by Eclipse JUnit Runner
    @Test
    public void testAvoids_Duplicate_Positioning() { 
        ChessPiece firstPawn = new Pawn(PieceColor.BLACK);
        ChessPiece secondPawn = new Pawn(PieceColor.BLACK);
        // Call to Add modified remove color.
        testSubject.Add(firstPawn, 6, 3);
        testSubject.Add(secondPawn, 6, 3);
        assertEquals(6, firstPawn.getXCoordinate());
        assertEquals(3, firstPawn.getYCoordinate());
        assertEquals(-1, secondPawn.getXCoordinate());
        assertEquals(-1, secondPawn.getYCoordinate());
    }

    @Test
    public void testLimits_The_Number_Of_Pawns()
    {
        for (int i = 0; i < 10; i++)
        {
            ChessPiece pawn = new Pawn(PieceColor.BLACK);
            int col = i / (ChessBoard.MAX_BOARD_WIDTH);
            testSubject.Add(pawn, 6 + col, i % (ChessBoard.MAX_BOARD_WIDTH ));
            if (col < 1)
            {
                assertEquals(6 + col, pawn.getXCoordinate());
                assertEquals(i % (ChessBoard.MAX_BOARD_WIDTH), pawn.getYCoordinate());
            }
            else
            {
                assertEquals(-1, pawn.getXCoordinate());
                assertEquals(-1, pawn.getYCoordinate());
            }
        }
    }
    
    ///////////////////////////////////////////////////////////////////////////
    //New tests added below

    @Test
    public void testIndependentlyCount_Black_White_Pawns() {
        for (int i = 0; i < 8; i++)
        {
            ChessPiece pawn = new Pawn(PieceColor.BLACK);
            testSubject.Add(pawn, i, 6 );            
            assertEquals(i, pawn.getXCoordinate());
            assertEquals(6, pawn.getYCoordinate());

            ChessPiece whitePawn = new Pawn(PieceColor.WHITE);
            testSubject.Add(whitePawn, i,  2);
            assertEquals(i, whitePawn.getXCoordinate());
            assertEquals(2, whitePawn.getYCoordinate());
        }    	
    }
    
    
    @Test
    public void testIsLegalBoardPositionEdge_True_X_equals_7_Y_equals_7() {
        boolean isValidPosition = testSubject.IsLegalBoardPosition(7, 7);
        assertTrue(isValidPosition);
    }

    @Test
    public void testIsIlegalBoardPositionBeyondEdge_False_X_equals_8_Y_equals_8() {
        boolean isValidPosition = testSubject.IsLegalBoardPosition(8, 8);
        assertFalse(isValidPosition);
    }

    @Test
    public void testIsOccupiedBoardPosition_False_EmptyBoard_X_equals_4_Y_equals_4() {
    	boolean isUnoccupied = !testSubject.IsOccupiedBoardPosition(4, 4);
    	assertTrue(isUnoccupied);
    }

    @Test
    public void testIsOccupiedBoardPosition_True_OccupiedSquare_X_equals_4_Y_equals_4() {
    	testSubject.Add(PieceType.PAWN, PieceColor.BLACK, 4,4);
    	boolean isOccupied = testSubject.IsOccupiedBoardPosition(4, 4);
    	assertTrue(isOccupied);
    }

    
    @Test
    public void testCreateBoardRepresentation_EmptyBoard_AllUnoccupied() {
    	assertEquals(
    	    	"_01234567"+ eol + 
    	    	"7........"+ eol + 
    	    	"6........"+ eol + 
    	    	"5........"+ eol + 
    	    	"4........"+ eol + 
    	    	"3........"+ eol + 
    	    	"2........"+ eol + 
    	    	"1........"+ eol +
    	    	"0........"+ eol,
    	    	testSubject.CreateBoardRepresentation());
    }

    @Test
    public void testCreateBoardRepresentation_Occupied_Squares_White_X_EQUALS_1_Y_EQUALS_2_Black_X_EQUALS_5_Y_EQUALS_6() {
    	testSubject.Add(PieceType.PAWN,  PieceColor.WHITE, 1,2);
    	testSubject.Add(PieceType.PAWN,  PieceColor.BLACK, 5,6);
    	
    	assertEquals(
    	"_01234567"+ eol + 
    	"7........"+ eol + 
    	"6.....P.."+ eol + 
    	"5........"+ eol + 
    	"4........"+ eol + 
    	"3........"+ eol + 
    	"2.p......"+ eol + 
    	"1........"+ eol +
    	"0........"+ eol,
    	testSubject.CreateBoardRepresentation());
    }
    

    @Test
    public void testAdd_BoardSetup_BlackPawnRowWhitePawnRow() {
    	
    	for (int i = 0; i < 8; ++i) {
    		testSubject.Add(PieceType.PAWN,  PieceColor.WHITE, i,1);
    		testSubject.Add(PieceType.PAWN,  PieceColor.BLACK, i,6);
    	}
    	
    	assertEquals(
    	"_01234567"+ eol + 
    	"7........"+ eol + 
    	"6PPPPPPPP"+ eol + 
    	"5........"+ eol + 
    	"4........"+ eol + 
    	"3........"+ eol + 
    	"2........"+ eol + 
    	"1pppppppp"+ eol +
    	"0........"+ eol,
    	testSubject.CreateBoardRepresentation());
    }
    

    @Test
    public void testMove_FromUnoccupied_DoesNothing() {
    	testSubject.Move(PieceColor.WHITE, 4, 1, 4,2);
    	assertEquals(
    	    	"_01234567"+ eol + 
    	    	"7........"+ eol + 
    	    	"6........"+ eol + 
    	    	"5........"+ eol + 
    	    	"4........"+ eol + 
    	    	"3........"+ eol + 
    	    	"2........"+ eol + 
    	    	"1........"+ eol +
    	    	"0........"+ eol,
    	    	testSubject.CreateBoardRepresentation());    	
    }
    
    
    @Test
    public void testMove_FromOpponentsPiece_DoesNothing() {
    	
    	testSubject.Add(PieceType.PAWN, PieceColor.BLACK, 4,1);
    	assertEquals(
    	    	"_01234567"+ eol + 
    	    	"7........"+ eol + 
    	    	"6........"+ eol + 
    	    	"5........"+ eol + 
    	    	"4........"+ eol + 
    	    	"3........"+ eol + 
    	    	"2........"+ eol + 
    	    	"1....P..."+ eol +
    	    	"0........"+ eol,
    	    	testSubject.CreateBoardRepresentation());    	
    	testSubject.Move(PieceColor.WHITE, 4, 1, 4,2);
    	assertEquals(
    	    	"_01234567"+ eol + 
    	    	"7........"+ eol + 
    	    	"6........"+ eol + 
    	    	"5........"+ eol + 
    	    	"4........"+ eol + 
    	    	"3........"+ eol + 
    	    	"2........"+ eol + 
    	    	"1....P..."+ eol +
    	    	"0........"+ eol,
    	    	testSubject.CreateBoardRepresentation());    	
    }

    @Test
    public void testMove_OffBoard_DoesNothing() {
    	
    	testSubject.Add(PieceType.PAWN, PieceColor.WHITE, 7,7);
    	assertEquals(
    	    	"_01234567"+ eol + 
    	    	"7.......p"+ eol + 
    	    	"6........"+ eol + 
    	    	"5........"+ eol + 
    	    	"4........"+ eol + 
    	    	"3........"+ eol + 
    	    	"2........"+ eol + 
    	    	"1........"+ eol +
    	    	"0........"+ eol,
    	    	testSubject.CreateBoardRepresentation());    	
    	testSubject.Move(PieceColor.WHITE, 7, 7, 7,8);
    	assertEquals(
    	    	"_01234567"+ eol + 
    	    	"7.......p"+ eol + 
    	    	"6........"+ eol + 
    	    	"5........"+ eol + 
    	    	"4........"+ eol + 
    	    	"3........"+ eol + 
    	    	"2........"+ eol + 
    	    	"1........"+ eol +
    	    	"0........"+ eol,
    	    	testSubject.CreateBoardRepresentation());    	
    }

    
    @Test
    public void testMove_FromSelf_UpdatesBoard() {
    	
    	testSubject.Add(PieceType.PAWN, PieceColor.WHITE, 4,1);
    	assertEquals(
    	    	"_01234567"+ eol + 
    	    	"7........"+ eol + 
    	    	"6........"+ eol + 
    	    	"5........"+ eol + 
    	    	"4........"+ eol + 
    	    	"3........"+ eol + 
    	    	"2........"+ eol + 
    	    	"1....p..."+ eol +
    	    	"0........"+ eol,
    	    	testSubject.CreateBoardRepresentation());    	
    	testSubject.Move(PieceColor.WHITE, 4, 1, 4,2);
    	assertEquals(
    	    	"_01234567"+ eol + 
    	    	"7........"+ eol + 
    	    	"6........"+ eol + 
    	    	"5........"+ eol + 
    	    	"4........"+ eol + 
    	    	"3........"+ eol + 
    	    	"2....p..."+ eol + 
    	    	"1........"+ eol +
    	    	"0........"+ eol,
    	    	testSubject.CreateBoardRepresentation());    	
    }

    @Test
    public void testCapture_FromSelfToSelf_DoesNothing() {
    	
    	testSubject.Add(PieceType.PAWN, PieceColor.WHITE, 4,1);
    	testSubject.Add(PieceType.PAWN, PieceColor.WHITE, 5,2);    	
    	assertEquals(
    	    	"_01234567"+ eol + 
    	    	"7........"+ eol + 
    	    	"6........"+ eol + 
    	    	"5........"+ eol + 
    	    	"4........"+ eol + 
    	    	"3........"+ eol + 
    	    	"2.....p.."+ eol + 
    	    	"1....p..."+ eol +
    	    	"0........"+ eol,
    	    	testSubject.CreateBoardRepresentation());    	
    	testSubject.Move(PieceColor.WHITE, 4, 1, 5,2);
    	assertEquals(
    	    	"_01234567"+ eol + 
    	    	"7........"+ eol + 
    	    	"6........"+ eol + 
    	    	"5........"+ eol + 
    	    	"4........"+ eol + 
    	    	"3........"+ eol + 
    	    	"2.....p.."+ eol + 
    	    	"1....p..."+ eol +
    	    	"0........"+ eol,
    	    	testSubject.CreateBoardRepresentation());    	
    }
    
    @Test
    public void testCapture_FromSelfToOpponent_UpdatesBoard() {
    	// White captures
    	testSubject.Add(PieceType.PAWN, PieceColor.WHITE, 4,1);
    	testSubject.Add(PieceType.PAWN, PieceColor.BLACK, 5,2);    	
    	assertEquals(
    	    	"_01234567"+ eol + 
    	    	"7........"+ eol + 
    	    	"6........"+ eol + 
    	    	"5........"+ eol + 
    	    	"4........"+ eol + 
    	    	"3........"+ eol + 
    	    	"2.....P.."+ eol + 
    	    	"1....p..."+ eol +
    	    	"0........"+ eol,
    	    	testSubject.CreateBoardRepresentation());    	
    	testSubject.Move(PieceColor.WHITE, 4, 1, 5,2);
    	assertEquals(
    	    	"_01234567"+ eol + 
    	    	"7........"+ eol + 
    	    	"6........"+ eol + 
    	    	"5........"+ eol + 
    	    	"4........"+ eol + 
    	    	"3........"+ eol + 
    	    	"2.....p.."+ eol + 
    	    	"1........"+ eol +
    	    	"0........"+ eol,
    	    	testSubject.CreateBoardRepresentation());    	
    }

    @Test
    public void testCapture_FromSelfToOpponentReversed_UpdatesBoard() {
    	//Black captures
    	testSubject.Add(PieceType.PAWN, PieceColor.WHITE, 4,1);
    	testSubject.Add(PieceType.PAWN, PieceColor.BLACK, 5,2);    	
    	assertEquals(
    	    	"_01234567"+ eol + 
    	    	"7........"+ eol + 
    	    	"6........"+ eol + 
    	    	"5........"+ eol + 
    	    	"4........"+ eol + 
    	    	"3........"+ eol + 
    	    	"2.....P.."+ eol + 
    	    	"1....p..."+ eol +
    	    	"0........"+ eol,
    	    	testSubject.CreateBoardRepresentation());    	
    	testSubject.Move(PieceColor.BLACK, 5,2, 4,1);
    	assertEquals(
    	    	"_01234567"+ eol + 
    	    	"7........"+ eol + 
    	    	"6........"+ eol + 
    	    	"5........"+ eol + 
    	    	"4........"+ eol + 
    	    	"3........"+ eol + 
    	    	"2........"+ eol + 
    	    	"1....P..."+ eol +
    	    	"0........"+ eol,
    	    	testSubject.CreateBoardRepresentation());    	
    }

    
    @Test
    public void testValidMoveSeries_UpdatesBoard() {
    	
    	for (int i = 0; i < 8; ++i) {
    		testSubject.Add(PieceType.PAWN,  PieceColor.WHITE, i,1);
    		testSubject.Add(PieceType.PAWN,  PieceColor.BLACK, i,6);
    	}    	
    	// Initial state
    	assertEquals(
    	"_01234567"+ eol + 
    	"7........"+ eol + 
    	"6PPPPPPPP"+ eol + 
    	"5........"+ eol + 
    	"4........"+ eol + 
    	"3........"+ eol + 
    	"2........"+ eol + 
    	"1pppppppp"+ eol +
    	"0........"+ eol,
    	testSubject.CreateBoardRepresentation());

    	// Advance towards each other
    	for (int i = 0; i < 2; i++) {
    		testSubject.Move(PieceColor.WHITE, 2, i+1, 2, i+2);
    		testSubject.Move(PieceColor.BLACK, 3, 6-i, 3, 5-i);    		
    	}
    	// White pawn (2,3) takes black pawn (3,4)
    	testSubject.Move(PieceColor.WHITE, 2, 3, 3, 4);

    	// Final state
    	assertEquals(
    	"_01234567"+ eol + 
    	"7........"+ eol + 
    	"6PPP.PPPP"+ eol + 
    	"5........"+ eol + 
    	"4...p...."+ eol + 
    	"3........"+ eol + 
    	"2........"+ eol + 
    	"1pp.ppppp"+ eol +
    	"0........"+ eol,
    	testSubject.CreateBoardRepresentation());
    }
    
}
