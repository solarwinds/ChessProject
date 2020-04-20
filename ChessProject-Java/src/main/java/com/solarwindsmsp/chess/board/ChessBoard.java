package com.solarwindsmsp.chess.board;

import com.solarwindsmsp.chess.Position;
import com.solarwindsmsp.chess.piece.Piece;
import lombok.Getter;

import static com.solarwindsmsp.chess.Constants.MAX_BOARD_HEIGHT_INDEX;
import static com.solarwindsmsp.chess.Constants.MAX_BOARD_WIDTH_INDEX;

public class ChessBoard {

    @Getter
    private Position[][] positions;

    @Getter
    private BoardInventory boardInventory;

    public ChessBoard() {
        positions = new Position[MAX_BOARD_WIDTH_INDEX + 1][MAX_BOARD_HEIGHT_INDEX + 1];
        for (int i = 0; i < MAX_BOARD_WIDTH_INDEX + 1; i++) {
            for (int j = 0; j < MAX_BOARD_HEIGHT_INDEX + 1; j++) {
                positions[i][j] = new Position(i, j, this);
            }
        }
        this.boardInventory = new BoardInventory();
    }

    public void addPiece(Piece piece, int xCoordinate, int yCoordinate) {
        if (isLegalBoardPosition(xCoordinate, yCoordinate)
                && positions[xCoordinate][yCoordinate] != null
                && positions[xCoordinate][yCoordinate].getPiece() == null
                && boardInventory.canAdd(piece)) {
            Position position = positions[xCoordinate][yCoordinate];
            position.setPiece(piece);
            piece.setPosition(position);

            boardInventory.addToInventory(piece);
        } else {
            Position position = new Position(-1, -1, this);
            piece.setPosition(position);
        }
    }

    public boolean isLegalBoardPosition(int xCoordinate, int yCoordinate) {
        return xCoordinate <= MAX_BOARD_WIDTH_INDEX
                && xCoordinate >= 0
                && yCoordinate <= MAX_BOARD_HEIGHT_INDEX
                && yCoordinate >= 0;
    }

    public Position getPosition(int x, int y) {
        try {
            return positions[x][y];
        } catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }

    public void capture(Piece pieceToRemove) {
        boardInventory.getOnBoard().remove(pieceToRemove);
        boardInventory.getCaptured().add(pieceToRemove);
    }
}
