package com.solarwindsmsp.chess;

import com.solarwindsmsp.chess.chessboard.ChessBoard;
import com.solarwindsmsp.chess.piece.ChessPiece;
import com.solarwindsmsp.chess.piece.PieceColor;
import com.solarwindsmsp.chess.piece.PieceName;
import com.solarwindsmsp.chess.piece.factory.ChessPieceFactory;
import com.solarwindsmsp.chess.piece.manager.ChessPieceManager;
import com.solarwindsmsp.chess.piece.manager.MovementType;

public class Main {

    public static void main(String[] args){
        ChessBoard chessBoard = new ChessBoard();

        ChessPiece pawn = ChessPieceFactory.create(PieceName.PAWN, PieceColor.WHITE);

        chessBoard.add(pawn, 0, 1);

        System.out.println("==== Pawn Position ====");
        System.out.println(pawn.toString());

        ChessPieceManager.performAction(pawn, MovementType.MOVE, 0, 2);

        System.out.println("==== Pawn Position After Legal Move ====");
        System.out.println(pawn.toString());

        ChessPieceManager.performAction(pawn, MovementType.MOVE, 7, 7);

        System.out.println("==== Pawn Position After Illegal Move ====");
        System.out.println(pawn.toString());
    }
}