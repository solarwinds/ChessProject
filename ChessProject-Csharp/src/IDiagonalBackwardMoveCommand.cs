﻿namespace SolarWinds.MSP.Chess
{
    /// <summary>
    /// Interface for moving backwards diagonally on the chess board
    /// </summary>
    public interface IDiagonalBackwardMoveCommand
    {
        /// <summary>
        /// Tries to move the chess piece diagonally left
        /// </summary>
        /// <param name="steps">Number of steps to move</param>
        /// <returns>Flag indicating whether the move was successful</returns>
        bool TryMoveDiagonalLeftBackward(int steps);

        /// <summary>
        /// Tries to move the chess piece diagonally right
        /// </summary>
        /// <param name="steps">Number of steps to move</param>
        /// <returns>Flag indicating whether the move was successful</returns>
        bool TryMoveDiagonalRightBackward(int steps);
    }
}
