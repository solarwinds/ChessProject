using SolarWinds.MSP.Chess;
using SolarWinds.MSP.Chess.Core.Interfaces;
using System.Collections.Generic;

namespace src.Core.Interfaces
{
    // TODO: add docs here
    public interface IChessPieceContainer
    {
        IEnumerable<IChessPiece> BlackPieces { get; }

        IEnumerable<IChessPiece> WhitePieces { get; }

        IChessPiece GetPieceAtCoordinates(int xCoordinate, int yCoordinate);

        IEnumerable<T> GetPiecesOfType<T>() where T : IChessPiece;

        IEnumerable<T> GetPiecesOfType<T>(PieceColor color) where T : IChessPiece;

        bool HasCapacityFor(IChessPiece piece);

        void RegisterPiece(IChessPiece piece, int xCoordinate, int yCoordinate);
    }
}