using SolarWinds.MSP.Chess;
using System;

namespace src.Interfaces
{
    public interface IChessPiece
    {
        ChessBoard ChessBoard { get; set; }

        int XCoordinate { get; set; }

        int YCoordinate { get; set; }

        PieceColor PieceColor { get; }

        void Move(MovementType movementType, int newX, int newY);
    }
}
