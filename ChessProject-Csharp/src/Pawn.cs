using src;
using System;

namespace SolarWinds.MSP.Chess
{
    public class Pawn : ChessPiece
    {
        private const int MaxNumberOfPawnsPerColour = 8;
       
        public override int MaxNumberOfPiecesPerColour => MaxNumberOfPawnsPerColour;

        public Pawn(PieceColor pieceColor) : base(pieceColor)
        {
        }

        public override void Move(MovementType movementType, int newX, int newY)
        {
            if (movementType == MovementType.Move)
            {
                if (ChessBoard.IsPositionOccupied(newX, newY))
                    return;

                if (newX != XCoordinate)
                    return;

                if (PieceColor == PieceColor.Black && newY == (YCoordinate - 1))
                {
                    YCoordinate = newY;
                    return;
                }

                if (PieceColor == PieceColor.White && newY == (YCoordinate + 1))
                {
                    YCoordinate = newY;
                    return;
                }
            }
        }

        public override string ToString()
        {
            return CurrentPositionAsString();
        }

        protected string CurrentPositionAsString()
        {
            return string.Format("Current X: {1}{0}Current Y: {2}{0}Piece Color: {3}", Environment.NewLine, XCoordinate, YCoordinate, PieceColor);
        }
    }
}
