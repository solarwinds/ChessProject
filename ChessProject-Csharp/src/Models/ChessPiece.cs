using SolarWinds.MSP.Chess;
using src.Interfaces;

namespace src
{
    /// <summary>
    /// Abstract class for creating chess pieces
    /// </summary>
    public abstract class ChessPiece : IChessPiece
    {
        private ChessBoard _chessBoard;
        private int _xCoordinate;
        private int _yCoordinate;
        private PieceColor _pieceColor;

        /// <inheritdoc/>
        public ChessBoard ChessBoard
        {
            get { return _chessBoard; }
            set { _chessBoard = value; }
        }

        /// <inheritdoc/>
        public int XCoordinate
        {
            get { return _xCoordinate; }
            set { _xCoordinate = value; }
        }

        /// <inheritdoc/>
        public int YCoordinate
        {
            get { return _yCoordinate; }
            set { _yCoordinate = value; }
        }

        /// <inheritdoc/>
        public PieceColor PieceColor
        {
            get { return _pieceColor; }
            private set { _pieceColor = value; }
        }

        /// <summary>
        /// Max number of pieces per colour for the chess piece type
        /// </summary>
        public abstract int MaxNumberOfPiecesPerColour { get; }

        /// <summary>
        /// Constructor
        /// </summary>
        /// <param name="pieceColor"><see cref="ChessPiece"/></param>
        public ChessPiece(PieceColor pieceColor)
        {
            _pieceColor = pieceColor;
        }

        /// <inheritdoc/>
        public abstract void Move(MovementType movementType, int newX, int newY);
    }
}
