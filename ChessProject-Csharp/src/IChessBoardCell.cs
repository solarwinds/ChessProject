namespace SolarWinds.MSP.Chess
{
    /// <summary>
    /// Interface representing a chess board cell
    /// </summary>
    public interface IChessBoardCell
    {
        /// <summary>
        /// Position of the cell
        /// </summary>
        Position CellPosition { get; }

        /// <summary>
        /// Chess piece the cell is holding
        /// </summary>
        IPiece ContainingChessPiece { get; }

        /// <summary>
        /// Flag indicating if the cell is empty
        /// </summary>
        bool IsEmpty { get; }

        /// <summary>
        /// Places a chess piece on the cell
        /// </summary>
        void PlaceChessPiece(IChessPiece chessPiece);

        /// <summary>
        /// Removes the chess piece from the cell
        /// </summary>
        void RemoveChessPiece();
    }
}
