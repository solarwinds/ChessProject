namespace SolarWinds.MSP.Chess
{
    /// <summary>
    /// Interface for chess piece that allows capturing
    /// </summary>
    public interface IPiece : IPieceBase
    {
        /// <summary>
        /// Captures chess piece
        /// </summary>
        void Capture();
    }
}
