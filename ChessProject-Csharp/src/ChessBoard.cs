using System;

namespace SolarWinds.MSP.Chess
{
    public class ChessBoard
    {
        public static readonly int MaxBoardWidth = 7;
        public static readonly int MaxBoardHeight = 7;
        private Pawn[,] pieces;

        public ChessBoard ()
        {
            pieces = new Pawn[MaxBoardWidth, MaxBoardHeight];
        }

        public void Add(Pawn pawn, int xCoordinate, int yCoordinate, PieceColor pieceColor)
        {
            if (IsLegalBoardPosition(xCoordinate, yCoordinate))
            {
                pawn.XCoordinate = xCoordinate;
                pawn.YCoordinate = yCoordinate;
                
                pieces[xCoordinate, yCoordinate] = pawn;
            }
            else
            {
                pawn.XCoordinate = -1;
                pawn.YCoordinate = -1;
            }
        }
            

        public bool IsLegalBoardPosition(int xCoordinate, int yCoordinate)
        {
            if ((xCoordinate < 0 || xCoordinate >= MaxBoardWidth) || (yCoordinate < 0 || yCoordinate >= MaxBoardHeight))
                return false;

            if (pieces[xCoordinate, yCoordinate] != null)
                return false;

            return true;
        }
    }
}
