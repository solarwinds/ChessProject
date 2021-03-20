using src.Enums;
using System;

namespace src.Pieces
{
    public class Pawn : Piece
    {
        public Pawn(PieceColor pieceColor) : base(pieceColor, PieceType.Pawn)
        {
        }

        public override bool Move(MovementType movementType, int newX, int newY)
        {
            var result = false;
            if (ChessBoard.IsLegalBoardPosition(newX, newY) && ChessBoard.IsSquareEmpty(newX, newY))
            {
                if (movementType == MovementType.Move)
                {
                    if (this.XCoordinate == newX
                        && (this.PieceColor == PieceColor.Black && newY == this.YCoordinate - 1
                            || this.PieceColor == PieceColor.White && newY == this.YCoordinate + 1))
                    {
                        result = ChessBoard.MovePiece(this, newX, newY, movementType);
                        this.YCoordinate = newY;
                    }
                }
                else
                {
                    throw new NotImplementedException("Only Move movement implemented");
                }
            }
            return result;
        }
    }
}
