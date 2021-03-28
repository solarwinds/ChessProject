using SolarWinds.MSP.Chess.Core.Interfaces;
using src.Core;
using src.Core.BaseImplementations;
using src.Core.Interfaces;
using System;
using System.Linq;

namespace SolarWinds.MSP.Chess
{
    public class ChessBoard
    {
        public static readonly int MaxBoardWidth = 8;
        public static readonly int MaxBoardHeight = 8;
        private IChessPieceContainer m_pieceContainer;

        public bool HasPieces => m_pieceContainer.GetPiecesOfType<ChessPieceBase>().Any();

        public ChessBoard ()
        {
            m_pieceContainer = new ChessPieceContainer();
        }

        // Allows us to mock the container and properly unit test the Add functionality
        public ChessBoard(IChessPieceContainer chessPieceContainer)
        {
            m_pieceContainer = chessPieceContainer;
        }

        /// <summary>
        /// Adds an <see cref="IChessPiece"/> to the board
        /// </summary>
        /// <param name="piece">An implementation of <see cref="IChessPiece"/> to add to the game board</param>
        /// <param name="xCoordinate">The X coordinate the piece takes up</param>
        /// <param name="yCoordinate">The Y coordinate the piece takes up</param>
        /// <returns>True if the piece was successfully added</returns>
        public bool Add(IChessPiece piece, int xCoordinate, int yCoordinate)
        {
            if (piece == null)
                return false;

            if (m_pieceContainer == null)
                m_pieceContainer = new ChessPieceContainer();

            if (!IsLegalBoardPosition(xCoordinate, yCoordinate) || 
                !piece.IsValidStartingPosition(xCoordinate, yCoordinate) ||
                !m_pieceContainer.HasCapacityFor(piece) || 
                m_pieceContainer.GetPieceAtCoordinates(xCoordinate, yCoordinate) != null)
            {
                piece.XCoordinate = -1;
                piece.YCoordinate = -1;
                return false;
            }
            
            m_pieceContainer.RegisterPiece(piece, xCoordinate, yCoordinate);

            return true;
        }

        /// <summary>
        /// Determines if the passed in coordinates are within the limits of the game board
        /// </summary>
        /// <param name="xCoordinate">The X coordinate</param>
        /// <param name="yCoordinate">The Y coordinate</param>
        /// <returns>True if the coodinates are within the limits of the board</returns>
        public bool IsLegalBoardPosition(int xCoordinate, int yCoordinate)
        {
            return (xCoordinate < MaxBoardWidth && xCoordinate >= 0) && (yCoordinate < MaxBoardHeight && yCoordinate >= 0);
        }
    }
}
