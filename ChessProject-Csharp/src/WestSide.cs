using System.Collections.Generic;

namespace SolarWinds.MSP.Chess
{
    /// <summary>
    /// West side of the chess board
    /// </summary>
    public class WestSide : Direction
    {
        /// <see cref="Direction.MoveForward(Position)"/>
        public override Position MoveForward(Position currentPosition) => new Position(currentPosition.XCoordinate - 1, currentPosition.YCoordinate);

        /// <see cref="Direction.MoveBackward(Position)"/>
        public override Position MoveBackward(Position currentPosition) => new Position(currentPosition.XCoordinate + 1, currentPosition.YCoordinate);

        /// <see cref="Direction.MoveLeft(Position)"/>
        public override Position MoveLeft(Position currentPosition) => new Position(currentPosition.XCoordinate, currentPosition.YCoordinate - 1);

        /// <see cref="Direction.MoveRight(Position)"/>
        public override Position MoveRight(Position currentPosition) => new Position(currentPosition.XCoordinate, currentPosition.YCoordinate + 1);

        /// <see cref="Direction.GetOppositeDirection()"/>
        public override Direction GetOppositeDirection() => new EastSide();

        /// <see cref="Direction.GetInitialPositionsForPawn()"/>
        public override IEnumerable<Position> GetInitialPositionsForPawn() => new[] { new Position(6, 0), new Position(6, 1), new Position(6, 2), new Position(6, 3), new Position(6, 4), new Position(6, 5), new Position(6, 6), new Position(6, 7) };
    }
}
