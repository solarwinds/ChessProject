using System.Collections.Generic;

namespace SolarWinds.MSP.Chess
{
    /// <summary>
    /// East side of the chess board
    /// </summary>
    public class EastSide : Direction
    {
        /// <see cref="Direction.MoveForward(Position)"/>
        public override Position MoveForward(Position currentPosition) => new Position(currentPosition.XCoordinate + 1, currentPosition.YCoordinate);

        /// <see cref="Direction.MoveBackward(Position)"/>
        public override Position MoveBackward(Position currentPosition) => new Position(currentPosition.XCoordinate - 1, currentPosition.YCoordinate);

        /// <see cref="Direction.MoveLeft(Position)"/>
        public override Position MoveLeft(Position currentPosition) => new Position(currentPosition.XCoordinate, currentPosition.YCoordinate + 1);

        /// <see cref="Direction.MoveRight(Position)"/>
        public override Position MoveRight(Position currentPosition) => new Position(currentPosition.XCoordinate, currentPosition.YCoordinate - 1);

        /// <see cref="Direction.GetOppositeDirection()"/>
        public override Direction GetOppositeDirection() => new WestSide();

        /// <see cref="Direction.GetInitialPositionsForPawn()"/>
        public override IEnumerable<Position> GetInitialPositionsForPawn() => new[] { new Position(1, 0), new Position(1, 1), new Position(1, 2), new Position(1, 3), new Position(1, 4), new Position(1, 5), new Position(1, 6), new Position(1, 7) };
    }
}
