using System;
using SolarWinds.MSP.Chess.Models.Base;
using System.Collections.Generic;

namespace SolarWinds.MSP.Chess.Models
{
    public class Pawn : Piece
    {
        public Pawn(PieceColor pieceColor)
        {
            PieceColor = pieceColor;
        }

        public override int MaxForColour
        {
            get
            {
                return ChessBoard.MaxBoardWidth;
            }
        }

        public override void Move(MovementType movementType, int newX, int newY)
        {
            if(movementType == MovementType.Move)
            {
                if(IsValidNonCaptureMove(newX, newY))
                {
                    XCoordinate = newX;
                    YCoordinate = newY;
                }
            }
            else
            {
                throw new NotImplementedException("Need to implement capture move type.");
            }
            
        }

        private bool IsValidNonCaptureMove(int newX, int newY)
        {
            if (newX != XCoordinate)
                return false;

            if(PieceColor == PieceColor.Black)
            {
                return IsOnHomeRow() ? newY == YCoordinate - 1 || newY == YCoordinate - 2 : newY == YCoordinate - 1;
            }
            else
            {
                return IsOnHomeRow() ? newY == YCoordinate + 1 || newY == YCoordinate + 2 : newY == YCoordinate + 1;
            }
        }

        public bool IsOnHomeRow()
        {
            return PieceColor == PieceColor.Black ? YCoordinate == ChessBoard.MaxBoardHeight - 1 :  YCoordinate == 1;
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
