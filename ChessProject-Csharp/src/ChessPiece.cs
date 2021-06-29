using SolarWinds.MSP.Chess;
using src.Interfaces;

namespace src
{
    public abstract class ChessPiece : IChessPiece
    {
        private ChessBoard _chessBoard;
        private int _xCoordinate;
        private int _yCoordinate;
        private PieceColor _pieceColor;

        public ChessBoard ChessBoard
        {
            get { return _chessBoard; }
            set { _chessBoard = value; }
        }

        public int XCoordinate
        {
            get { return _xCoordinate; }
            set { _xCoordinate = value; }
        }

        public int YCoordinate
        {
            get { return _yCoordinate; }
            set { _yCoordinate = value; }
        }

        public PieceColor PieceColor
        {
            get { return _pieceColor; }
            private set { _pieceColor = value; }
        }

        public abstract int MaxNumberOfPiecesPerColour { get; }

        public ChessPiece(PieceColor pieceColor)
        {
            _pieceColor = pieceColor;
        }

        public abstract void Move(MovementType movementType, int newX, int newY);
    }
}
