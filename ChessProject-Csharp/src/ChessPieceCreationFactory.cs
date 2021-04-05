namespace SolarWinds.MSP.Chess
{
    /// <summary>
    /// Factory class for creating chess pieces
    /// </summary>
    public class ChessPieceCreationFactory : IChessPieceCreationFactory
    {
        /// <see cref="IChessPieceCreationFactory.CreatePawn(ChessBoard, Direction, PieceColor)"/>
        public IChessPiece<IChessPiecePawn> CreatePawn(ChessBoard chessBoard, Direction direction, PieceColor color)
        {
            return new Pawn(chessBoard, direction, color);
        }
    }
}
