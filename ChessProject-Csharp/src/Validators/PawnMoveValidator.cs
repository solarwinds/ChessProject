using SolarWinds.MSP.Chess;
using src.Interfaces;
using System;

namespace src
{
    /// <summary>
    /// Validates movements for pawns
    /// </summary>
    public class PawnMoveValidator : IMoveValidator
    {
        private PieceColor _pieceColor;

        /// <inheritdoc/>
        public IChessPiece ChessPiece { get; set; }

        /// <summary>
        /// Constructor
        /// </summary>
        /// <param name="chessPiece">Pawn chess piece</param>
        public PawnMoveValidator(IChessPiece chessPiece)
        {
            ChessPiece = chessPiece;
            _pieceColor = chessPiece.PieceColor;
        }

        /// <inheritdoc/>
        /// <remarks>
        /// Would ideally have separate unit tests for this but code is covered in current PawnTests
        /// </remarks>
        public bool IsValidMove(int newX, int newY)
        {
            if (ChessPiece.ChessBoard.IsPositionOccupied(newX, newY))
                return false;

            if (newX != ChessPiece.XCoordinate)
                return false;

            if (_pieceColor == PieceColor.Black && newY == (ChessPiece.YCoordinate - 1))           
                return true;   

            if (_pieceColor == PieceColor.White && newY == (ChessPiece.YCoordinate + 1))
                return true;
            
            return false;
        }
    }
}
