using SolarWinds.MSP.Chess.Models;
using System;

namespace SolarWinds.MSP.Chess.Interfaces
{
    interface IPiece
    {
        int XCoordinate { get; set; }
        int YCoordinate { get; set; }
        PieceColor PieceColor { get; }
        int MaxForColour { get; }

        void Move(MovementType movementType, int newX, int newY);
    }
}
