package com.solarwindsmsp.chess;


public class ChessBoard {

    public static int MAX_BOARD_WIDTH = 8; 	// Changed to 8. More idiomatic Java for array allocation and specifying ranges as 0 <= x < n
    public static int MAX_BOARD_HEIGHT = 8;
    
    public static int INVALID_POSITION = -1;

    private ChessPiece[][] pieces; // Changed to use class hierarchy for pieces, anticipating addition of other chess piece types.
    // TODO consider Optional<ChessPiece> for element type, avoiding reliance on null signifying unoccupied.
    
    private int blackPawnCount = 0;
    private int whitePawnCount = 0;
    // TODO: Once other piece types added, need to change, e.g. to EnumMap

    public ChessBoard() {
        pieces = new ChessPiece[MAX_BOARD_WIDTH][MAX_BOARD_HEIGHT]; // Note: Arrays index 0..n-1 
    }

    //Changed - color parameter removed as it is redundant - specified when Pawn created
    public void Add(ChessPiece piece, int xCoordinate, int yCoordinate /*, PieceColor pieceColor*/) {
    	if (IsLegalBoardPosition(xCoordinate, yCoordinate) 
    			&& !IsOccupiedBoardPosition(xCoordinate, yCoordinate)
    			&& !IsMaxNumberPieces(piece)) {
    		piece.setXCoordinate(xCoordinate);
    		piece.setYCoordinate(yCoordinate);
    		pieces[xCoordinate][yCoordinate] = piece;
    		// TODO: Replace this once other piece types added as well as pawns
    		if (piece.getPieceColor()==PieceColor.BLACK) {
    			++blackPawnCount;
    		} else {
    			++whitePawnCount;
    		}
    	} else {
      		piece.setXCoordinate(INVALID_POSITION);
    		piece.setYCoordinate(INVALID_POSITION);  		
    	}
    }
 
    /**
     * Adds a piece of specific type and color to the specified location on the board.
     * Simpler for calling from UI or, e.g., game replay as caller does not need to create piece objects.
     * @param type Type of chess piece. Only Pawns in this version.
     * @param color BLACK or WHITE
     * @param xCoordinate  0..7
     * @param yCoordinate  0..7
     * @return true iff coordinates valid and position unoccupied.
     */
    public boolean Add(PieceType type, PieceColor color, int xCoordinate, int yCoordinate) {
        // Overloaded Add making use of factory and forwarding to existing Add
    	ChessPiece piece = ChessPieceFactory.MakeChessPiece(type, color);
    	Add(piece, xCoordinate,yCoordinate);
    	return piece.getXCoordinate() >=0 && piece.getYCoordinate() >=0;
       	// TODO: Consider use of exceptions to signify reason for failure rather than return value    	 
    }
    
    /**
     * Move by playerColor between specified coordinates.
     * Preconditions: 
     * 1) Coordinates are in range 0..7
     * 2) Source square occupied by piece matching playerColor.
     * 3) Destination square unoccupied (move) or occupied by piece of opponent's color (capture)
     * No effect if preconditions not met.
     * 
     * @param playerColor BLACK or WHITE
     * @param curXCoordinate Move from 0..7
     * @param curYCoordinate Move from 0..7
     * @param newXCoordinate Move to 0..7
     * @param newYCoordinate Move to 0..7
     */
    public void Move(PieceColor playerColor, int curXCoordinate, int curYCoordinate, int newXCoordinate, int newYCoordinate) {
    	
    	boolean isMove = false;
    	boolean isCapture = false;
    	
    	if (IsLegalBoardPosition(curXCoordinate, curYCoordinate) && IsLegalBoardPosition(newXCoordinate, newYCoordinate)) {
    		// Safe to lookup board positions
    		ChessPiece candidate = pieces[curXCoordinate][curYCoordinate];
       		ChessPiece target = pieces[newXCoordinate][newYCoordinate];
       		
       		// Does the player own the piece?
       		if (candidate != null && playerColor == candidate.getPieceColor()) {
       			// Is it an empty square? TODO: Consider Optional<ChessPiece> as element type of board.
       			if (target == null) {
       				isMove = true;
       			} else if (playerColor != target.getPieceColor()) {
       				// target square is occupied by opponent.
       				isCapture = true;
       			}
       		}       	
       		
       		if (isMove && candidate.isValidMove(MovementType.MOVE, newXCoordinate, newYCoordinate)) {
       			candidate.Move(MovementType.MOVE, newXCoordinate, newYCoordinate);
       			pieces[newXCoordinate][newYCoordinate] = candidate;
       			pieces[curXCoordinate][curYCoordinate] = null;
       		} else if (isCapture && candidate.isValidMove(MovementType.CAPTURE, newXCoordinate, newYCoordinate)) {
       			candidate.Move(MovementType.CAPTURE, newXCoordinate, newYCoordinate);
       			pieces[newXCoordinate][newYCoordinate] = candidate;
       			pieces[curXCoordinate][curYCoordinate] = null;
       			if (target.getPieceColor() == PieceColor.BLACK) {
       				--blackPawnCount;
       			} else {
       				--whitePawnCount;       				
       			}
       		}
    	}
    }

    
    public String toString() {
    	return CreateBoardRepresentation();
    }

    protected String CreateBoardRepresentation() {
        // Print board as 8x8 array as per spec, with x,y indices
    	StringBuilder boardRep = new StringBuilder("_");
		for (int j = 0; j < MAX_BOARD_WIDTH; ++j) {
			boardRep.append(j);
		}
		boardRep.append(System.lineSeparator());
		for (int i = MAX_BOARD_HEIGHT - 1; i >= 0; --i) {
			boardRep.append(i);
			for (int j = 0; j < MAX_BOARD_WIDTH; ++j) {
				ChessPiece piece = pieces[j][i];
				boardRep.append(piece==null?".":piece.toSymbol());
			}
	    	boardRep.append(System.lineSeparator());    		
		}
		String strBoard = boardRep.toString();
		return strBoard;
    }

    
    public boolean IsLegalBoardPosition(int xCoordinate, int yCoordinate) {
    	return xCoordinate >= 0 && xCoordinate < MAX_BOARD_WIDTH
    			&& yCoordinate >=0 && yCoordinate < MAX_BOARD_HEIGHT;
    }
    
    public boolean IsOccupiedBoardPosition(int xCoordinate, int yCoordinate) {
    	return pieces[xCoordinate][yCoordinate] != null;
    }
    
    public boolean IsMaxNumberPieces(ChessPiece piece) {
    	int count = (piece.getPieceColor() == PieceColor.BLACK)? blackPawnCount : whitePawnCount;
    	// TODO- Would need to change once other piece types added.
    	return count >= piece.getMaxPiecesPerColor();
    }
    
}
