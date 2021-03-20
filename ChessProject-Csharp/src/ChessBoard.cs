using System;
using src.Consts;
using src.Enums;
using src.Extensions;
using src.Pieces;
using System.Collections.Generic;

namespace src
{
    public class ChessBoard
    {
        public static readonly int MaxBoardWidth = 8;
        public static readonly int MaxBoardHeight = 8;
        private readonly Piece[,] _pieces;
        public Dictionary<(PieceType, PieceColor), ushort> PieceCount { get; private set; }

        public ChessBoard()
        {
            _pieces = new Piece[MaxBoardWidth, MaxBoardHeight];

            PieceCount = new Dictionary<(PieceType, PieceColor), ushort>();
            foreach (PieceType type in Enum.GetValues(typeof(PieceType)))
            {
                foreach (PieceColor color in Enum.GetValues(typeof(PieceColor)))
                {
                    PieceCount.Add((type, color), 0);
                }
            }
        }

        public bool AddPiece(Piece piece, int xCoordinate, int yCoordinate)
        {
            var result = false;
            if (IsLegalBoardPosition(xCoordinate, yCoordinate) && IsSquareEmpty(xCoordinate, yCoordinate) &&
                IsUnderPieceCountLimit(piece))
            {
                PieceCount[(piece.PieceType, piece.PieceColor)]++;
                _pieces[xCoordinate, yCoordinate] = piece;
                piece.XCoordinate = xCoordinate;
                piece.YCoordinate = yCoordinate;
                piece.ChessBoard = this;
                result = true;
            }

            return result;
        }

        public bool MovePiece(Piece piece, int xCoordinate, int yCoordinate, MovementType moveType)
        {
            var result = false;
            if (piece != null && piece == _pieces[piece.XCoordinate, piece.YCoordinate])
            {
                if (moveType == MovementType.Capture)
                {
                    var capturedPiece = _pieces[xCoordinate, yCoordinate];
                    if (capturedPiece != null)
                    {
                        PieceCount[(capturedPiece.PieceType, capturedPiece.PieceColor)]--;
                    }
                }
                _pieces[xCoordinate, yCoordinate] = piece;
                _pieces[piece.XCoordinate, piece.YCoordinate] = null;
                result = true;
            }

            return result;
        }

        public bool IsSquareEmpty(int xCoordinate, int yCoordinate)
        {
            var result = false;
            var piece = _pieces[xCoordinate, yCoordinate];
            if (piece == null)
                result = true;
            return result;
        }

        public bool IsLegalBoardPosition(int xCoordinate, int yCoordinate)
        {
            var validX = xCoordinate.IsWithinRange(0, 7);
            var validY = yCoordinate.IsWithinRange(0, 7);

            return validX && validY;
        }

        private bool IsUnderPieceCountLimit(Piece piece)
        {
            return PieceCount[(piece.PieceType, piece.PieceColor)] < PieceTypeLimit.GetPieceLimit(piece.PieceType);
        }
    }
}
