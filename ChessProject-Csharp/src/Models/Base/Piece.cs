using SolarWinds.MSP.Chess.Interfaces;

namespace SolarWinds.MSP.Chess.Models.Base
{
    public abstract class Piece : IPiece
    {
        public int XCoordinate { get; set; }
        public int YCoordinate { get; set; }
        public PieceColor PieceColor { get; protected set; }
        public abstract int MaxForColour { get; }


        public abstract void Move(MovementType movementType, int newX, int newY);
        
    }
}
