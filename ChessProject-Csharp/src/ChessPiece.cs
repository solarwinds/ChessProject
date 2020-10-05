using System;

namespace SolarWinds.MSP.Chess
{
    public abstract class ChessPiece
    {
        private PieceColor pieceColor;

        private string strType;

        private string strColor;
        
        public ChessBoard ChessBoard { get; set; }

        public int XCoordinate { get; set; }
        
        public int YCoordinate { get; set; }

        public PieceColor PieceColor
        {
            get { return pieceColor; }
            protected set { pieceColor = value; }
        }

        public string StrType
        {
            get { return strType; }
            protected set { strType = value; }
        }

        public string StrColor
        {
            get { return strColor; }
            protected set { strColor = value; }
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
