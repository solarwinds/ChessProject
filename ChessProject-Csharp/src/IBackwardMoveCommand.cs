namespace SolarWinds.MSP.Chess
{
    /// <summary>
    /// Interface for moving backwards on the chess board
    /// </summary>
    public interface IBackwardMoveCommand
    {
        /// <summary>
        /// Tries to move the chess piece backwards
        /// </summary>
        /// <param name="steps">Number of steps to move</param>
        /// <returns>Flag indicating whether the move was successful</returns>
        bool TryMoveBackward(int steps);
    }
}
