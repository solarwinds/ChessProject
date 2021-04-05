namespace SolarWinds.MSP.Chess
{
    /// <summary>
    /// Factory interface for creating chess pieces
    /// </summary>
    public interface IChessPieceCreationFactory
    {
        /// <summary>
        /// Creates Pawn
        /// </summary>
        /// <param name="chessBoard">Chess board holding the piece</param>
        /// <param name="direction">Direction towards which chess piece can move.</param>
        /// <param name="color">Color of chess piece</param>
        /// <returns>Pawn instance</returns>
        IChessPiece<IChessPiecePawn> CreatePawn(ChessBoard chessBoard, Direction direction, PieceColor color);
    }
}
