package com.solarwindsmsp.chess.models;

import com.solarwindsmsp.chess.enums.PieceColor;
import com.solarwindsmsp.chess.enums.PieceType;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class ChessBoard {

    public static final int CHESS_BOARD_SIZE = 8;
    private final Map<Cell, ChessPiece> pieceMap = new HashMap<>();

    public void addPiece(ChessPiece chessPiece, int xCoordinate, int yCoordinate) {
        chessPiece.setChessBoard(this);

        long pieceNoByTypeAndColor = getPieceNoByTypeAndColor(chessPiece.getPieceType(), chessPiece.getPieceColor());
        if (pieceNoByTypeAndColor >= chessPiece.getPieceType().getMaxAllowed()) {
            chessPiece.setCell(new Cell(-1, -1));
            return;
        }

        if (isLegalBoardPosition(xCoordinate, yCoordinate)) {
            chessPiece.setCell(new Cell(xCoordinate, yCoordinate));
            pieceMap.put(chessPiece.getCell(), chessPiece);
        } else {
            chessPiece.setCell(new Cell(-1, -1));
        }
    }

    public boolean isLegalBoardPosition(int xCoordinate, int yCoordinate) {
        Cell newCell = new Cell(xCoordinate, yCoordinate);

        if (xCoordinate < 0 || xCoordinate > CHESS_BOARD_SIZE) {
            return false;
        }

        if (yCoordinate < 0 || yCoordinate > CHESS_BOARD_SIZE) {
            return false;
        }

        ChessPiece piece = pieceMap.get(newCell);
        return piece == null;
    }

    private long getPieceNoByTypeAndColor(PieceType pieceType, PieceColor pieceColor) {
        Map<PieceType, Long> chessPiecesByTypeAndColor =
                pieceMap.values().stream()
                        .filter(piece -> Objects.nonNull(piece) && pieceColor == piece.getPieceColor())
                        .collect(groupingBy(ChessPiece::getPieceType, counting()));
        return Optional.ofNullable(chessPiecesByTypeAndColor.get(pieceType)).orElse(0L);
    }

}
