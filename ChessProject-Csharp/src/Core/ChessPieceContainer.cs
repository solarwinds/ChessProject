using SolarWinds.MSP.Chess;
using SolarWinds.MSP.Chess.Core.Interfaces;
using src.Core.Interfaces;
using System;
using System.Collections.Generic;
using System.Linq;

namespace src.Core
{
    // Consider adding an IPieceContainer interface to enable mocking this in chessboard class tests
    public class ChessPieceContainer : IChessPieceContainer
    {
        private List<IChessPiece> m_ChessPieces = new List<IChessPiece>();

        /// <summary>
        /// The white pieces currently in the contaier
        /// </summary>
        public IEnumerable<IChessPiece> WhitePieces => m_ChessPieces.Where(piece => piece.PieceColor == PieceColor.White);

        /// <summary>
        /// The black pieces currently in the container
        /// </summary>
        public IEnumerable<IChessPiece> BlackPieces => m_ChessPieces.Where(piece => piece.PieceColor == PieceColor.Black);

        /// <summary>
        /// Adds a piece to the container with the specified coordinates
        /// </summary>
        /// <param name="piece">An implementation of <see cref="IChessPiece"/> to add to the contaier</param>
        /// <param name="xCoordinate">The X coordinate to assign to the piece</param>
        /// <param name="yCoordinate">The Y coordinate to assign to the piece</param>
        public void RegisterPiece(IChessPiece piece, int xCoordinate, int yCoordinate)
        {
            piece.XCoordinate = xCoordinate;
            piece.YCoordinate = yCoordinate;

            m_ChessPieces.Add(piece);
        }

        /// <summary>
        /// Returns the <see cref="IChessPiece"/> at the designated coordinates
        /// </summary>
        /// <param name="xCoordinate">The X coordinate to search for</param>
        /// <param name="yCoordinate">The Y coordinate to search for</param>
        /// <returns>Null if no piece found at the coordinates</returns>
        public IChessPiece GetPieceAtCoordinates(int xCoordinate, int yCoordinate)
        {
            return m_ChessPieces.FirstOrDefault(piece => piece.XCoordinate == xCoordinate && piece.YCoordinate == yCoordinate);
        }

        /// <summary>
        /// Finds all instances of implementations of <see cref="IChessPiece"/> of the specified type"/>
        /// </summary>
        /// <typeparam name="T">The <see cref="Type"/> to look for</typeparam>
        /// <returns>A list of the chess pieces of the specified type</returns>
        public IEnumerable<T> GetPiecesOfType<T>() where T : IChessPiece
        {
            return m_ChessPieces.OfType<T>();
        }

        /// <summary>
        /// Finds all instances of implementations of <see cref="IChessPiece"/> of the specified type and colour"/>
        /// </summary>
        /// <typeparam name="T">The <see cref="Type"/> to look for</typeparam>
        /// <param name="color">The <see cref="PieceColor"/> of the pieces to look for</param>
        /// <returns>A list of the chess pieces of the specified type and colour</returns>
        // Consider overload with no parameter and filtering after retrieval
        public IEnumerable<T> GetPiecesOfType<T>(PieceColor color) where T : IChessPiece
        {
            if (color == PieceColor.Black)
                return BlackPieces.OfType<T>();
            else
                return WhitePieces.OfType<T>();
        }

        /// <summary>
        /// Determines of the container has capacity for the passed in chess piece
        /// </summary>
        /// <param name="piece">The <see cref="IChessPiece"/> to be added</param>
        /// <returns>True if there is enough capacity</returns>
        public bool HasCapacityFor(IChessPiece piece)
        {
            Type pieceType = piece.GetType();

            if (piece.PieceColor == PieceColor.Black)
                return BlackPieces.Where(p => p.GetType() == pieceType).Count() < piece.NumberPiecesAllowedPerColour;
            else
                return WhitePieces.Where(p => p.GetType() == pieceType).Count() < piece.NumberPiecesAllowedPerColour;
        }
    }
}
