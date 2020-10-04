using System;

namespace SolarWinds.MSP.Chess
{
    public class Pawn : ChessPiece
    {
        public Pawn(PieceColor pieceColor)
        {
            this.pieceColor = pieceColor;
        }

        public override void Move(MovementType movementType, int newX, int newY)
        {
            throw new NotImplementedException("Need to implement Pawn.Move()");
        }

    }
}
