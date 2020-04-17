using System;

namespace SolarWinds.MSP.Chess
{
    public class Pawn
    {
        private ChessBoard chessBoard;
        private int xCoordinate; // file
        private int yCoordinate; // rank
        private PieceColor pieceColor;
        public bool firstMove { get; private set; }
        
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
            firstMove = true;
        }

        public void Move(MovementType movementType, int newX, int newY) // x = file, y = rank
        { 
            switch(movementType)
            {
                case MovementType.Capture:
                    // TODO future implementation
                    // if moveType is capture, calculate points for capture
                    break;
                case MovementType.Move:
                    // only allow Pawn movement on same file and advance one rank at a time or two ranks on Pawn's first move
                    if (xCoordinate == newX) // validate same file
                    {
                        if (this.pieceColor.Equals(PieceColor.Black)
                            && (newY == yCoordinate - 1 || (newY == yCoordinate - 2 && firstMove))) // validate increment rank only by one or two (if first move)
                        {
                            chessBoard.Add(this, newX, newY, this.pieceColor); // pass file, rank
                        }
                        if (this.pieceColor.Equals(PieceColor.White)
                            && (newY == yCoordinate + 1 || (newY == yCoordinate + 2 && firstMove)))
                        {
                            chessBoard.Add(this, newX, newY, this.pieceColor);
                        }
                    }
                    break;
            }
            if(firstMove)
            {
                firstMove = false;
            }
            if(chessBoard.WhitesMove)
            {
                chessBoard.WhitesMove = false;
            }
            else
            {
                chessBoard.WhitesMove = true;
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
