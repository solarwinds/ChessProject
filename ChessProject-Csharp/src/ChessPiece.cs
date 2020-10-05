using System;

namespace SolarWinds.MSP.Chess
{
    public abstract class ChessPiece
    {
        private PieceColor pieceColor;
        
        public ChessBoard ChessBoard { get; set; }

        public int XCoordinate { get; set; }
        
        public int YCoordinate { get; set; }

        public PieceColor PieceColor
        {
            get { return pieceColor; }
            protected set { pieceColor = value; }
        }

        public abstract void Move(MovementType movementType, int newX, int newY);

        public override string ToString()
        {
            return CurrentPositionAsString();
        }

        protected string CurrentPositionAsString()
        {
            return string.Format("Current X: {1}{0}Current Y: {2}{0}ChessPiece Color: {3}", Environment.NewLine, XCoordinate, YCoordinate, PieceColor);
        }

    }
}
