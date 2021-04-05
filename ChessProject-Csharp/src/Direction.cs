using System.Collections.Generic;

namespace SolarWinds.MSP.Chess
{
    /// <summary>
    /// Direction for chess pieces
    /// </summary>
    public abstract class Direction
    {
        /// <summary>
        /// Moves forward one step
        /// </summary>
        /// <param name="currentPosition">Current Position</param>
        /// <returns>New position</returns>
        public abstract Position MoveForward(Position currentPosition);

        /// <summary>
        /// Moves backward one step
        /// </summary>
        /// <param name="currentPosition">Current Position</param>
        /// <returns>New position</returns>
        public abstract Position MoveBackward(Position currentPosition);

        /// <summary>
        /// Moves left one step
        /// </summary>
        /// <param name="currentPosition">Current Position</param>
        /// <returns>New position</returns>
        public abstract Position MoveLeft(Position currentPosition);

        /// <summary>
        /// Moves right one step
        /// </summary>
        /// <param name="currentPosition">Current Position</param>
        /// <returns>New position</returns>
        public abstract Position MoveRight(Position currentPosition);

        /// <summary>
        /// Gets the opposite direction
        /// </summary>
        /// <returns>Opposite direction</returns>
        public abstract Direction GetOppositeDirection();

        /// <summary>
        /// Gets valid initial positions for pawn
        /// </summary>
        /// <returns>Valid initial positions for pawn</returns>
        public abstract IEnumerable<Position> GetInitialPositionsForPawn();
    }
}
