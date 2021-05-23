using SolarWinds.MSP.Chess.Models;
using SolarWinds.MSP.Chess.Models.Base;
using System;

namespace SolarWinds.MSP.Chess.Interfaces
{
    interface IBoard
    {
        void Add(Piece piece, int xCoordinate, int yCoordinate, PieceColor pieceColor);
    }
}
