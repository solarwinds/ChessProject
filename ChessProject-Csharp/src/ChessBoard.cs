using System;

namespace SolarWinds.MSP.Chess
{
    /// <summary>
    /// An instance of a chessboard
    /// </summary>
    public class ChessBoard
    {
        //board goes from {0,0} to {7,7}. So the default board width and height must be 8 to accommodate this.
        public static readonly int MaxBoardWidth = 8;
        public static readonly int MaxBoardHeight = 8;
        public static readonly int MinBoardWidth = 0;
        public static readonly int MinBoardHeight = 0;
        private Piece[,] board;

        /// <summary>
        /// Initialises the board.
        /// </summary>
        public ChessBoard ()
        {
            board = new Piece[MaxBoardWidth, MaxBoardHeight];
        }

        /// <summary>
        /// Places a piece on a valid square
        /// </summary>
        /// <returns>
        /// Return true when the square is a valid selection, false when it isn't
        /// </returns>
        public bool Add(Piece piece, int xCoordinate, int yCoordinate, PieceColor pieceColor)
        {
            if (isValidSquareForPlacement(xCoordinate, yCoordinate) == false)
                return false;

            piece.XCoordinate = xCoordinate;
            piece.YCoordinate = yCoordinate;

            board[xCoordinate, yCoordinate] = piece;
            return true;
        }

        /// <summary>
        /// Used to check if the square is valid for placement.
        /// </summary>
        /// <returns>
        /// Return true when this square is a valid square for placement of a piece
        /// </returns>
        /// <param name="xCoordinate"> The x coordinate of the square we are going to check.</param>
        /// <param name="yCoordinate"> The y coordinate of the square we are going to check.</param>
        protected bool isValidSquareForPlacement(int xCoordinate, int yCoordinate)
        {
            if (IsLegalBoardPosition(xCoordinate, yCoordinate) == true)
            {
                if (PieceExistsOnSquare(xCoordinate, yCoordinate) == false)
                {
                    return true;
                }
            }

            return false;
        }

        /// <summary>
        /// Used to check if the square is in the bounds of the board
        /// </summary>
        /// <returns>
        /// Return true when both coordinates are within the range of the board. Returns false when one of the coordinates is not valid
        /// </returns>
        /// <param name="xCoordinate"> The x coordinate of the square we are going to check.</param>
        /// <param name="yCoordinate"> The y coordinate of the square we are going to check.</param>
        protected bool IsLegalBoardPosition(int xCoordinate, int yCoordinate)
        {
            if (xCoordinate >= MaxBoardWidth || xCoordinate < MinBoardWidth)
                return false;

            if (yCoordinate >= MaxBoardHeight || yCoordinate < MinBoardHeight)
                return false;

            return true;
        }

        /// <summary>
        /// Checks to see if a square already has a piece on it.
        /// </summary>
        /// <returns>
        /// Return true when this square has a piece on it. Returns false when the square is empty
        /// </returns>
        /// <param name="xCoordinate"> The x coordinate of the square we are going to check.</param>
        /// <param name="yCoordinate"> The y coordinate of the square we are going to check.</param>
        protected bool PieceExistsOnSquare(int xCoordinate, int yCoordinate)
        {
            if (IsLegalBoardPosition(xCoordinate, yCoordinate) == false)
                return false;

            if (board[xCoordinate, yCoordinate] == null)
                return false;

            return true;
        }

    }
}
