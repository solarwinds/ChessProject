package com.solarwindsmsp.chess;

class ChessBoard {

    private Cell [][] board;

    public ChessBoard() {
        board = new Cell[ChessConstants.BOARD_WIDTH][ChessConstants.BOARD_HEIGHT];
        buildBoard();
    }

    void add(Piece piece, int xCoordinate, int yCoordinate) throws CellOccupiedException {
    	Cell cell = getCell(xCoordinate, yCoordinate);
    	piece.setCell(cell);
    	cell.setPiece(piece);
    }

    private Cell getCell(int x, int y) {
		return board[x][y];
	}

	boolean IsLegalBoardPosition(int xCoordinate, int yCoordinate) {
    	return isLegal(xCoordinate) && isLegal(yCoordinate);
    }

	private boolean isLegal(int coordinate) {
		return(coordinate >= 0 && coordinate < ChessConstants.BOARD_SIZE);
	}

	private void buildBoard() {
		for (int x = 0; x < ChessConstants.BOARD_WIDTH; x++) {
			for (int y = 0; y < ChessConstants.BOARD_HEIGHT; y++) {
				board[x][y] = new Cell(x,y);
			}
		}
	}
	
	int getWidth(){
		return board[0].length;
	}
	int getHeight(){
		return board.length;
	}

	public Cell[][] getBoard() {
		return board;
	}
}
