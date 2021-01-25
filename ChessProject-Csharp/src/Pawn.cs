using System;

namespace SolarWinds.MSP.Chess
{
    public class Pawn : Piece
    {
        public Pawn(PieceColor pieceColor) : base(pieceColor) 
        { 
        }

        public void Move(MovementType movementType, int newX, int newY)
        {
            switch (movementType)
            {
                case MovementType.Capture:
                    throw new NotImplementedException("Need to implement Capture movement");
                case MovementType.Move:
                    if (ChessBoard.IsLegalBoardPosition(newX, newY) && ChessBoard.IsUnoccupied(newX, newY))
                    {
                        switch(PieceColor)
                        {
                            case PieceColor.Black:
                                if ((newX == XCoordinate) && (YCoordinate - newY == 1))
                                    YCoordinate = newY;
                                break;
                            case PieceColor.White:
                                if ((newX == XCoordinate) && (newY - YCoordinate == 1))
                                    YCoordinate = newY;
                                break;
                        }
                    }
                    break;
                default:
                    throw new ArgumentException("Invalid MovementType");
            }
        }
    }
}
