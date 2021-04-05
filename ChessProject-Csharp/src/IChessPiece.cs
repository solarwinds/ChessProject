namespace SolarWinds.MSP.Chess
{
    /// <summary>
    /// Interface for chess piece
    /// </summary>
    public interface IChessPiece : IPiece
    {
        /// <summary>
        /// Sets the parent cell for the chess piece
        /// </summary>
        /// <param name="cell">Cell to set as parent</param>
        void SetParent(IChessBoardCell cell);
    }

    /// <summary>
    ///  Interface for typed chess piece
    /// </summary>
    /// <typeparam name="T">Type of chess piece</typeparam>
    public interface IChessPiece<T> : IChessPiece
         where T : IPieceBase
    {
        /// <summary>
        /// Type specific chess piece
        /// </summary>
        T Piece { get; }
    }
}
