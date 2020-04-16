using System;

namespace SolarWinds.MSP.Chess
{
    public class Pawn // DONE pass all SolarWinds unit tests
    {
        private ChessBoard chessBoard;
        private int xCoordinate;
        private int yCoordinate;
        private PieceColor pieceColor;
        
        public ChessBoard ChessBoard
        {
            get { return chessBoard; }
            set { chessBoard = value; }
        }

        public int XCoordinate
        {
            get { return xCoordinate; }
            set { xCoordinate = value; }
        }
        
        public int YCoordinate
        {
            get { return yCoordinate; }
            set { yCoordinate = value; }
        }

        public PieceColor PieceColor
        {
            get { return pieceColor; }
            private set { pieceColor = value; }
        }

        // ctor
        public Pawn(PieceColor pieceColor)
        {
            this.pieceColor = pieceColor;
        }

        public void Move(MovementType movementType, int newX, int newY)
        {
            // if moveType is capture, calculate points for capture
            switch(movementType)
            {
                case MovementType.Capture:
                    // TODO future implementation
                    break;
                case MovementType.Move:
                    if(yCoordinate == newY) // only allow Pawn movement on same file
                    {
                        chessBoard.Add(this, newX, newY, this.pieceColor);
                    }
                    break;
                default:
                    Console.WriteLine("Illegal move!");
                    break;
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

        public string PrintPiece()
        {
            if(pieceColor == PieceColor.White)
            {
                // unicode flag
                return "\u2659";
            }
            else // if black
            {
                return "\u265F";
            }
        }

    }
}
