namespace SolarWinds.MSP.Chess
{
    /// <summary>
    /// Interface for moving forwards on the chess board
    /// </summary>
    public interface IForwardMoveCommand
    {
        /// <summary>
        /// Tries to move the chess piece forwards
        /// </summary>
        /// <param name="steps">Number of steps to move</param>
        /// <returns>Flag indicating whether the move was successful</returns>
        bool TryMoveForward(int steps);
    }
}
