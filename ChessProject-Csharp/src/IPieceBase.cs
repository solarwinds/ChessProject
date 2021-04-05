namespace SolarWinds.MSP.Chess
{
    /// <summary>
    /// Interface for chess piece
    /// </summary>
    public interface IPieceBase
    {
        /// <summary>
        /// Current position of the chess piece
        /// </summary>
        Position CurrentPosition { get; }

        /// <summary>
        /// Color of the chess piece
        /// </summary>
        PieceColor Color { get; }

        /// <summary>
        /// Flag indicating whether the chess piece is captured
        /// </summary>
        bool IsCaptured { get; }
    }
}
