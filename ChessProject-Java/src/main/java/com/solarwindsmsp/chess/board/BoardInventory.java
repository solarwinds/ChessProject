package com.solarwindsmsp.chess.board;


import com.solarwindsmsp.chess.Constants;
import com.solarwindsmsp.chess.exception.MaxNumberOfPiecesReachedException;
import com.solarwindsmsp.chess.piece.Pawn;
import com.solarwindsmsp.chess.piece.Piece;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@NoArgsConstructor
@Getter
public class BoardInventory {

    public static Map<Class, Integer> maxAllowed;

    static {
        maxAllowed = new HashMap<>();
        maxAllowed.put(Pawn.class, Constants.MAX_NUMBER_OF_PAWNS);
    }

    List<Piece> onBoard = new ArrayList<>();
    List<Piece> captured = new ArrayList<>();

    public boolean canAdd(Piece piece) {
        int samePiecesOfSameColour = (int)onBoard.stream()
                .filter(p -> p.getClass().equals(piece.getClass()))
                .filter(p -> p.getPieceColor().equals(piece.getPieceColor()))
                .count();

        return samePiecesOfSameColour < maxAllowed.get(piece.getClass());
    }

    public void addToInventory(Piece piece) {
        if(canAdd(piece)) {
            onBoard.add(piece);
        } else {
            throw new MaxNumberOfPiecesReachedException(piece);
        }
    }
}
