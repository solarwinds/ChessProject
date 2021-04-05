using System.Collections.Generic;

namespace SolarWinds.MSP.Chess
{
    /// <summary>
    /// South side of the chess board
    /// </summary>
    public class SouthSide : Direction
    {
        /// <see cref="Direction.MoveForward(Position)"/>
        public override Position MoveForward(Position currentPosition) => new Position(currentPosition.XCoordinate, currentPosition.YCoordinate - 1);

        /// <see cref="Direction.MoveBackward(Position)"/>
        public override Position MoveBackward(Position currentPosition) => new Position(currentPosition.XCoordinate, currentPosition.YCoordinate + 1);

        /// <see cref="Direction.MoveLeft(Position)"/>
        public override Position MoveLeft(Position currentPosition) => new Position(currentPosition.XCoordinate + 1, currentPosition.YCoordinate);

        /// <see cref="Direction.MoveRight(Position)"/>
        public override Position MoveRight(Position currentPosition) => new Position(currentPosition.XCoordinate - 1, currentPosition.YCoordinate);

        /// <see cref="Direction.GetOppositeDirection()"/>
        public override Direction GetOppositeDirection() => new NorthSide();

        /// <see cref="Direction.GetInitialPositionsForPawn()"/>
        public override IEnumerable<Position> GetInitialPositionsForPawn() => new[] { new Position(0, 6), new Position(1, 6), new Position(2, 6), new Position(3, 6), new Position(4, 6), new Position(5, 6), new Position(6, 6), new Position(7, 6) };
    }
}
