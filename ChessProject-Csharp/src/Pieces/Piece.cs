using src.Enums;
using System;

namespace src.Pieces
{
    public abstract class Piece
    {
        public ChessBoard ChessBoard { get; set; }

        public int XCoordinate { get; set; }

        public int YCoordinate { get; set; }

        public PieceColor PieceColor { get; private set; }

        public PieceType PieceType { get; private set; }

        protected Piece(PieceColor pieceColor, PieceType pieceType)
        {
            this.PieceColor = pieceColor;
            this.PieceType = pieceType;
        }

        public abstract bool Move(MovementType movementType, int newX, int newY);


        public override string ToString()
        {
            return CurrentPositionAsString();
        }

        protected string CurrentPositionAsString()
        {
            return string.Format("Current X: {1}{0}Current Y: {2}{0}Piece Color: {3}{0}Piece Type: {4}{0}", Environment.NewLine, XCoordinate, YCoordinate, PieceColor, PieceType);
        }
    }
}