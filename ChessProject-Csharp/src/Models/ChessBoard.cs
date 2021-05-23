using SolarWinds.MSP.Chess.Models.Base;
using System;
using System.Collections.Generic;
using System.Linq;

namespace SolarWinds.MSP.Chess.Models
{
    public sealed class ChessBoard
    {
        private static ChessBoard chessBoardInstance = null;
        public static readonly int MaxBoardWidth = 7;
        public static readonly int MaxBoardHeight = 7;
        private List<Piece> pieces;

        private ChessBoard ()
        {
            pieces = new List<Piece>();
        }

        public static ChessBoard ChessBoardInstance
        {
            get
            {
                if (chessBoardInstance == null)
                {
                    chessBoardInstance = new ChessBoard();
                }
                return chessBoardInstance;
            }
        }

        public void Add(Piece piece, int xCoordinate, int yCoordinate, PieceColor pieceColor)
        {
            List<Piece> piecesForTypeAndColor = GetPiecesByType(piece.GetType()).Where(x => x.PieceColor == pieceColor).ToList();
            var destinationEmpty = CoordinateIsEmpty(xCoordinate, yCoordinate);
            if (IsLegalBoardPosition(xCoordinate, yCoordinate) && piecesForTypeAndColor.Count < piece.MaxForColour && destinationEmpty)
            {
                piece.XCoordinate = xCoordinate;
                piece.YCoordinate = yCoordinate;

                pieces.Add(piece);
            }
            else
            {
                piece.XCoordinate = -1;
                piece.YCoordinate = -1;
            }
        }

        public bool IsLegalBoardPosition(int xCoordinate, int yCoordinate)
        {
            return CoordinateInBoardRange(xCoordinate, MaxBoardWidth) && CoordinateInBoardRange(yCoordinate, MaxBoardHeight);
        }

        public bool CoordinateInBoardRange(int coordinate, int max)
        {
            return coordinate >= 0 && coordinate <= max;
        }

        public bool CoordinateIsEmpty(int xCoordinate, int yCoordinate)
        {
            return pieces.Where(x => x.XCoordinate == xCoordinate && x.YCoordinate == yCoordinate).Count() == 0;
        }

        private IList<Piece> GetPiecesByType(Type type)
        {
            return pieces.Where(x => x.GetType() == type).ToList();
        }

        public void ResetBoard()
        {
            ChessBoardInstance.pieces = new List<Piece>();
        }
    }
}
