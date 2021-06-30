using SolarWinds.MSP.Chess;
using System;

namespace src.Interfaces
{
    /// <summary>
    /// Interface for chess pieces
    /// </summary>
    public interface IChessPiece
    {
        /// <summary>
        /// Chess board that piece belongs to
        /// </summary>
        ChessBoard ChessBoard { get; set; }

        /// <summary>
        /// Current X coordinate
        /// </summary>
        int XCoordinate { get; set; }

        /// <summary>
        /// Current Y coordinate
        /// </summary>
        int YCoordinate { get; set; }

        /// <summary>
        /// Chess piece colour. Black or White
        /// </summary>
        PieceColor PieceColor { get; }

        /// <summary>
        /// Move chess piece to given coordinates if valid move
        /// </summary>
        /// <param name="movementType"><see cref="MovementType"/></param>
        /// <param name="newX">X coordinate to move to</param>
        /// <param name="newY">Y coordinate to move to</param>
        void Move(MovementType movementType, int newX, int newY);
    }
}
