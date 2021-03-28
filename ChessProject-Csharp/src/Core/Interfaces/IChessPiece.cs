using System.Collections.Generic;
using System.Drawing;

namespace SolarWinds.MSP.Chess.Core.Interfaces
{
    // TODO: add docs here
    public interface IChessPiece
    {
        PieceColor PieceColor { get; }

        int XCoordinate { get; set; }

        int YCoordinate { get; set; }

        int NumberPiecesAllowedPerColour { get; }

        IEnumerable<Point> ValidWhiteStartPositions { get; }

        IEnumerable<Point> ValidBlackStartPositions { get; }

        void Move(MovementType movementType, int newX, int newY);

        bool IsValidMove(int xCoordinate, int yCoordinate);

        bool IsValidStartingPosition(int xCoodrinate, int yCoordinate);

        string ToString();
    }
}