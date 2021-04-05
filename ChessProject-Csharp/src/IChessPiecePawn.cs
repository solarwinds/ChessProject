namespace SolarWinds.MSP.Chess
{
    /// <summary>
    /// Interface for pawn chess piece
    /// </summary>
    public interface IChessPiecePawn : IPieceBase, IForwardMoveCommand, IDiagonalForwardMoveCommand
    {
    }
}
