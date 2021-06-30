using System;
using src;

namespace SolarWinds.MSP.Chess
{
    /// <summary>
    /// Chess board class to add and store chess pieces and their positions within the board
    /// </summary>
    public class ChessBoard
    {
        public static readonly int MaxBoardWidth = 8;
        public static readonly int MaxBoardHeight = 8;
        private ChessPiece[,] pieces;

        /// <summary>
        /// Constructor
        /// </summary>
        public ChessBoard ()
        {
            pieces = new ChessPiece[MaxBoardWidth, MaxBoardHeight];
        }

        /// <summary>
        /// Add chess piece to chess board at given coordinates
        /// </summary>
        /// <param name="piece"><see cref="ChessPiece"/></param>
        /// <param name="xCoordinate">X coordinate</param>
        /// <param name="yCoordinate">Y coordinate</param>
        public void Add(ChessPiece piece, int xCoordinate, int yCoordinate)
        {
            if (IsLegalBoardPosition(xCoordinate, yCoordinate) && !HasMaxNumberOfPieces(piece))
            {
                piece.XCoordinate = xCoordinate;
                piece.YCoordinate = yCoordinate;
                piece.ChessBoard = this;
                
                pieces[xCoordinate, yCoordinate] = piece;
            }
            else
            {
                piece.XCoordinate = -1;
                piece.YCoordinate = -1;
            }
        }
            
        /// <summary>
        /// Checks if board position is legal
        /// </summary>
        /// <param name="xCoordinate">X coordinate</param>
        /// <param name="yCoordinate">Y coordinate</param>
        /// <returns>True if legal position, else false</returns>
        public bool IsLegalBoardPosition(int xCoordinate, int yCoordinate)
        {
            if ((xCoordinate < 0 || xCoordinate >= MaxBoardWidth) || (yCoordinate < 0 || yCoordinate >= MaxBoardHeight))
                return false;

            if (IsPositionOccupied(xCoordinate, yCoordinate))
                return false;

            return true;
        }

        /// <summary>
        /// Checks if position on board is already taken
        /// </summary>
        /// <param name="xCoordinate">X coordinate</param>
        /// <param name="yCoordinate">Y coordinate</param>
        /// <returns>True if position is occupied, else false</returns>
        public bool IsPositionOccupied(int xCoordinate, int yCoordinate)
        {
            return pieces[xCoordinate, yCoordinate] != null;
        }

        /// <summary>
        /// Checks if number of pieces for type on the board is at its maximum 
        /// </summary>
        /// <param name="piece"><see cref="ChessPiece"/></param>
        /// <returns>
        /// True if board already has max number of pieces for type. 
        /// False if there is space for piece
        /// </returns>
        /// <remarks>
        /// Would like to make this more efficient than having to loop 
        /// through the entire board every time to check.
        /// </remarks>
        private bool HasMaxNumberOfPieces(ChessPiece piece)
        {
            int currentPieceTypeCount = 0;

            for (int i = 0; i < MaxBoardWidth; i++)
            {
                for (int j = 0; j < MaxBoardWidth; j++)
                {
                    ChessPiece currentPiece = pieces[i, j];

                    if (currentPiece != null && 
                        currentPiece.GetType() == piece.GetType() && 
                        currentPiece.PieceColor == piece.PieceColor)
                    {
                        currentPieceTypeCount++;

                        if (currentPieceTypeCount >= piece.MaxNumberOfPiecesPerColour)
                            return true;
                    }
                }
            }

            return false;
        }
    }
}
