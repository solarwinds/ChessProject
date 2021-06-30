using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace src.Interfaces
{
    /// <summary>
    /// Interface for validating chess piece movement
    /// </summary>
    public interface IMoveValidator
    {
        /// <summary>
        /// Chess piece to validate move
        /// </summary>
        IChessPiece ChessPiece { get; set; }

        /// <summary>
        /// Determines if move is valid
        /// </summary>
        /// <param name="x">X coordinate to move to</param>
        /// <param name="y">Y coordinate to move to</param>
        /// <returns>True if move is valid</returns>
        bool IsValidMove(int x, int y);
    }
}
