namespace SolarWinds.MSP.Chess
{
    /// <summary>
    /// Interface for L-shape moves
    /// </summary>
    public interface ILShapeMoveCommand
    {
        /// <summary>
        /// Tries to move the chess piece forward and then right
        /// </summary>
        /// <returns>Flag indicating whether the move was successful</returns>
        bool TryMoveForwardRight();

        /// <summary>
        /// Tries to move the chess piece forward and then left
        /// </summary>
        /// <returns>Flag indicating whether the move was successful</returns>
        bool TryMoveForwardLeft();

        /// <summary>
        /// Tries to move the chess piece backward and then right
        /// </summary>
        /// <returns>Flag indicating whether the move was successful</returns>
        bool TryMoveBackwardRight();

        /// <summary>
        /// Tries to move the chess piece backward and then left
        /// </summary>
        /// <returns>Flag indicating whether the move was successful</returns>
        bool TryMoveBackwardLeft();

        /// <summary>
        /// Tries to move the chess piece right and then forward
        /// </summary>
        /// <returns>Flag indicating whether the move was successful</returns>
        bool TryMoveRightForward();

        /// <summary>
        /// Tries to move the chess piece right and then backward
        /// </summary>
        /// <returns>Flag indicating whether the move was successful</returns>
        bool TryMoveRightBackward();

        /// <summary>
        /// Tries to move the chess piece left and then forward
        /// </summary>
        /// <returns>Flag indicating whether the move was successful</returns>
        bool TryMoveLeftForward();

        /// <summary>
        /// Tries to move the chess piece left and then backward
        /// </summary>
        /// <returns>Flag indicating whether the move was successful</returns>
        bool TryMoveLeftBackward();
    }
}
