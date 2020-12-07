using System;

namespace SolarWinds.MSP.Chess
{
    public class Pawn : ChessPiece
    {
        public Pawn(PieceColor pieceColor)
        {
            SetPieceColour(pieceColor);
        }

        protected override bool IsValidMovement(int newX, int newY)
        {
            switch (pieceColor)
            {
                case PieceColor.Black:
                    if (XCoordinate == newX && YCoordinate == newY || newY >= YCoordinate)
                    {
                        return false;
                    }
                    break;
                case PieceColor.White:
                    if (XCoordinate == newX && YCoordinate == newY || newY <= YCoordinate)
                    {
                        return false;
                    }
                    break;
            }
            return true;
        }

    }
}
