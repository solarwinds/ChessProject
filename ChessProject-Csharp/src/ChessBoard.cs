using SolarWinds.MSP.Chess.Core.Interfaces;
using src.Core;
using src.Core.BaseImplementations;
using System;
using System.Linq;

namespace SolarWinds.MSP.Chess
{
    public class ChessBoard
    {
        public static readonly int MaxBoardWidth = 8;
        public static readonly int MaxBoardHeight = 8;
        private ChessPieceContainer m_pieceContainer;
        public bool HasPieces => m_pieceContainer.GetPiecesOfType<ChessPieceBase>().Any();

        public ChessBoard ()
        {
            m_pieceContainer = new ChessPieceContainer();
        }

        public bool Add(IChessPiece piece, int xCoordinate, int yCoordinate)
        {
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

        public bool IsLegalBoardPosition(int xCoordinate, int yCoordinate)
        {
            return (xCoordinate < MaxBoardWidth && xCoordinate >= 0) && (yCoordinate < MaxBoardHeight && yCoordinate >= 0);
        }
    }
}
