using SolarWinds.MSP.Chess;
using SolarWinds.MSP.Chess.Core.Interfaces;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace src.Core
{
    // Consider adding an IPieceContainer interface to enable mocking this in chessboard class tests
    public class ChessPieceContainer
    {
        private List<IChessPiece> m_ChessPieces = new List<IChessPiece>();

        public IEnumerable<IChessPiece> WhitePieces => m_ChessPieces.Where(piece => piece.PieceColor == PieceColor.White);
        public IEnumerable<IChessPiece> BlackPieces => m_ChessPieces.Where(piece => piece.PieceColor == PieceColor.Black);

        public void RegisterPiece(IChessPiece piece, int xCoordinate, int yCoordinate)
        {
            piece.XCoordinate = xCoordinate;
            piece.YCoordinate = yCoordinate;

            m_ChessPieces.Add(piece);
        }

        public IChessPiece GetPieceAtCoordinates(int xCoordinate, int yCoordinate)
        {
            return m_ChessPieces.FirstOrDefault(piece => piece.XCoordinate == xCoordinate && piece.YCoordinate == yCoordinate);
        }

        // Consider overload with no parameter and filtering after retrieval
        public IEnumerable<T> GetPiecesOfType<T>(PieceColor color) where T : IChessPiece
        {
            if (color == PieceColor.Black)
                return BlackPieces.OfType<T>();
            else
                return WhitePieces.OfType<T>();
        }

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
