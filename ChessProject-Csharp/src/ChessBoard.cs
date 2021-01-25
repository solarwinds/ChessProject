using System;
using System.Collections.Generic;

namespace SolarWinds.MSP.Chess
{
    public class ChessBoard
    {
        public static readonly int MaxBoardWidth = 8;
        public static readonly int MaxBoardHeight = 8;
        private Piece[,] pieces;
        /*
         * would like a better method of storing piece count per colour but can't think of one at the moment. Didn't want to restrict Add to 
         * certain rows so that "...you can set up a board with many initial configurations to replay famous chess games..." is valid.
         */
        private int[] pawns; 

        public ChessBoard ()
        {
            pieces = new Piece[MaxBoardWidth, MaxBoardHeight];
            pawns = new int[Enum.GetNames(typeof(PieceColor)).Length];
        }

        public void Add(Pawn pawn, int xCoordinate, int yCoordinate, PieceColor pieceColor)
        {
            if (IsLegalBoardPosition(xCoordinate, yCoordinate) && IsUnoccupied(xCoordinate, yCoordinate) && PawnsAvailable(pieceColor))
            {
                pawn.ChessBoard = this;
                pawn.XCoordinate = xCoordinate;
                pawn.YCoordinate = yCoordinate;
                pieces[xCoordinate, yCoordinate] = pawn;
                pawns[(int)pieceColor]++;
            }
            else
            {
                pawn.XCoordinate = -1;
                pawn.YCoordinate = -1;
            }
        }

        public bool IsLegalBoardPosition(int xCoordinate, int yCoordinate)
        {
            return ((xCoordinate < MaxBoardWidth && xCoordinate > -1) && (yCoordinate < MaxBoardHeight && yCoordinate > -1));
        }

        public bool IsUnoccupied(int xCoordinate, int yCoordinate)
        {
            return pieces[xCoordinate, yCoordinate] == null;
        }

        public bool PawnsAvailable(PieceColor pieceColor)
        {
            return pawns[(int)pieceColor] < 8;
        }
    }
}
