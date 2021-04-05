using System;

namespace SolarWinds.MSP.Chess
{
    /// <summary>
    /// Class representing a chess piece
    /// </summary>
    /// <typeparam name="T">Type of chess piece</typeparam>
    public abstract class ChessPiece<T> : IChessPiece<T>
       where T : IPieceBase
    {
        private IChessBoardCell parentCell;

        protected ChessBoard ChessBoard { get; }

        protected Direction CurrentDirection { get; }

        /// <see cref="IPieceBase.CurrentPosition"/>
        public Position CurrentPosition
        {
            get
            {
                return parentCell?.CellPosition ?? new Position(-1, -1);
            }
        }

        /// <see cref="IPieceBase.Color"/>
        public PieceColor Color
        {
            get;
        }

        /// <see cref="IPieceBase.IsCaptured"/>
        public bool IsCaptured
        {
            get;
            private set;
        }

        /// <summary>
        /// Initlaizes Chess Piece
        /// </summary>
        /// <param name="chessBoard">Chess board holding the piece</param>
        /// <param name="direction">Direction towards which chess piece can move.</param>
        /// <param name="color">Color of chess piece</param>
        /// <exception cref="ArgumentNullException">chessBoard or direction</exception>
        protected ChessPiece(ChessBoard chessBoard, Direction direction, PieceColor color)
        {
            ChessBoard = chessBoard ?? throw new ArgumentNullException(nameof(chessBoard));
            CurrentDirection = direction ?? throw new ArgumentNullException(nameof(direction));
            Color = color;

            parentCell = null;
            IsCaptured = false;
        }

        protected abstract T GetInstance();

        protected bool TryMoveChessPiece(Position newPosition)
        {
            if (!ChessBoard.IsLegalBoardPosition(newPosition))
                return false;

            IChessBoardCell cell = ChessBoard.Cells[newPosition.XCoordinate, newPosition.YCoordinate];
            if (!cell.IsEmpty)
            {
                if (cell.ContainingChessPiece.Color == Color)
                    return false;

                cell.ContainingChessPiece.Capture();
            }

            parentCell.RemoveChessPiece();
            cell.PlaceChessPiece(this);
            return true;
        }

        /// <see cref="IPiece.IsCaptured"/>
        public void Capture()
        {
            if (IsCaptured)
                throw new InvalidOperationException("Chess piece is already captured.");

            if (parentCell == null)
                throw new InvalidOperationException("Chess piece is not participating.");

            parentCell.RemoveChessPiece();
            IsCaptured = true;
        }

        /// <see cref="IChessPiece.SetParent"/>
        public void SetParent(IChessBoardCell cell)
        {
            parentCell = cell;
        }

        public T Piece => GetInstance();

        public override string ToString()
        {
            return $"Position: {CurrentPosition}, Piece Color: {Color}";
        }
    }
}
