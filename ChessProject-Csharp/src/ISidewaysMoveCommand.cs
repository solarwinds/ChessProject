namespace SolarWinds.MSP.Chess
{
    /// <summary>
    /// Interface for moving sideways on the chess board
    /// </summary>
    public interface ISidewaysMoveCommand
    {
        /// <summary>
        /// Tries to move the chess piece right
        /// </summary>
        /// <param name="steps">Number of steps to move</param>
        /// <returns>Flag indicating whether the move was successful</returns>
        bool TryMoveRight(int steps);

        /// <summary>
        /// Tries to move the chess piece left
        /// </summary>
        /// <param name="steps">Number of steps to move</param>
        /// <returns>Flag indicating whether the move was successful</returns>
        bool TryMoveLeft(int steps);
    }
}
