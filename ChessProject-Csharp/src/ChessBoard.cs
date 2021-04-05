using System;
using System.Collections.Generic;
using System.Linq;

namespace SolarWinds.MSP.Chess
{
    /// <summary>
    /// Class representing chess board
    /// </summary>
    public partial class ChessBoard
    {
        /// <summary>
        /// Maximum width of chess board
        /// </summary>
        public static readonly int MaxBoardWidth = 7;

        /// <summary>
        /// Maximum height of chess board
        /// </summary>
        public static readonly int MaxBoardHeight = 7;

        private readonly IChessPieceCreationFactory factory;
        private readonly Dictionary<PieceColor, Direction> directions;

        /// <summary>
        /// Collection of cells on the chess board
        /// </summary>
        public IChessBoardCell[,] Cells { get; }

        /// <summary>
        /// Initializes ChessBoard
        /// </summary>
        /// <param name="direction">Indicates the direction towards that white chess pieces can move. Black chess pieces can then move the opposite direction.</param>
        /// <param name="factory">Factory for creating chess pieces</param>
        /// <exception cref="ArgumentNullException">direction or factory</exception>
        public ChessBoard(Direction direction, IChessPieceCreationFactory factory)
        {
            if (direction == null)
                throw new ArgumentNullException(nameof(direction));

            this.factory = factory ?? throw new ArgumentNullException(nameof(factory));

            directions = new Dictionary<PieceColor, Direction>() { { PieceColor.White, direction }, { PieceColor.Black, direction.GetOppositeDirection() } };

            Cells = new ChessBoardCell[MaxBoardWidth + 1, MaxBoardHeight + 1];
            for (int i = 0; i <= MaxBoardWidth; i++)
            {
                for (int j = 0; j <= MaxBoardHeight; j++)
                {
                    Cells[i, j] = new ChessBoardCell(new Position(i, j));
                }
            }
        }

        /// <summary>
        /// Places pawn at a specfic position on the chess board
        /// </summary>
        /// <param name="position">Position for placing the pawn</param>
        /// <param name="pieceColor">Color of pawn piece</param>
        /// <returns>Instance of pawn</returns>
        /// <exception cref="InvalidOperationException">Invalid position for placing pawn.</exception>
        /// <exception cref="InvalidOperationException">Chess board already contains a pawn at this position.</exception>
        public IChessPiecePawn PlacePawn(Position position, PieceColor pieceColor)
        {
            if (!directions[pieceColor].GetInitialPositionsForPawn().Contains(position))
                throw new InvalidOperationException("Invalid position for placing pawn.");

            if (!Cells[position.XCoordinate, position.YCoordinate].IsEmpty)
                throw new InvalidOperationException("Chess board already contains a pawn at this position.");

            IChessPiece<IChessPiecePawn> pawn = factory.CreatePawn(this, directions[pieceColor], pieceColor);
            Cells[position.XCoordinate, position.YCoordinate].PlaceChessPiece(pawn);

            return pawn.Piece;
        }

        /// <summary>
        /// Checks whether the position is valid
        /// </summary>
        /// <param name="position">Position to check</param>
        /// <returns>Flag indicating whether the position is valid</returns>
        public bool IsLegalBoardPosition(Position position)
        {
            if (position.XCoordinate < 0 || position.YCoordinate < 0)
                return false;

            if (position.XCoordinate > MaxBoardWidth || position.YCoordinate > MaxBoardWidth)
                return false;

            return true;
        }
    }
}
