using System;

namespace SolarWinds.MSP.Chess
{
    public class Pawn : ChessPiece
    {
        private bool initialMove = true;
        private MovementDirection direction;
        public Pawn(PieceColor pieceColor, MovementDirection direction)
        {
            this.PieceColor = pieceColor;
            this.StrType = "Pawn";
            this.StrColor = pieceColor == PieceColor.Black ? "Black" : "White";
            this.direction = direction;
        }

        public override bool Move(MovementType movementType, int newX, int newY)
        {
            int availableY = 1;
            if (initialMove == true){
                availableY = 2;
                this.initialMove = false;
            }

            if (this.direction == MovementDirection.Positive) 
            {
                Console.WriteLine("here");
                if (movementType == MovementType.Move){
                    if (newX == XCoordinate && 
                        (newY > YCoordinate && newY <= YCoordinate + availableY))
                    {
                        return true;
                    }
                    return false;

                } 
                else
                {
                    if ((newX == XCoordinate + 1 || newX == XCoordinate - 1) && 
                        (newY == YCoordinate + 1))
                    {
                        return true;
                    }
                    return false;
                }
            } 
            else
            {
                if (movementType == MovementType.Move)
                {
                    if (newX == XCoordinate && 
                        (newY < YCoordinate && newY >= YCoordinate - availableY))
                    {
                        return true;
                    }
                    return false;

                } 
                else
                {
                    if ((newX == XCoordinate + 1 || newX == XCoordinate - 1) && 
                        (newY == YCoordinate - 1))
                    {
                        return true;
                    }
                    return false;
                }
            }
        }

    }
}
