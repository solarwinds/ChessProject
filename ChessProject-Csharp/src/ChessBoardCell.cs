using System;

namespace SolarWinds.MSP.Chess
{
    public partial class ChessBoard
    {
        /// <summary>
        /// Class representing chess board cell
        /// </summary>
        class ChessBoardCell : IChessBoardCell
        {
            private IChessPiece containingChessPiece;

            /// <see cref="IChessBoardCell.CellPosition"/>
            public Position CellPosition
            {
                get;
            }

            /// <see cref="IChessBoardCell.ContainingChessPiece"/>
            public IPiece ContainingChessPiece
            {
                get
                {
                    return containingChessPiece;
                }
            }

            /// <see cref="IChessBoardCell.IsEmpty"/>
            public bool IsEmpty
            {
                get
                {
                    return containingChessPiece == null;
                }
            }

            /// <summary>
            /// Initializes chess board cell
            /// </summary>
            /// <param name="cellPosition">Position of the cell on the chess board</param>
            public ChessBoardCell(Position cellPosition)
            {
                containingChessPiece = null;
                CellPosition = cellPosition;
            }

            /// <see cref="IChessBoardCell.PlaceChessPiece(IChessPiece)"/>
            public void PlaceChessPiece(IChessPiece chessPiece)
            {
                if (!IsEmpty)
                    throw new InvalidOperationException("Cell already contains a chess piece");
                if (chessPiece == null)
                    throw new ArgumentNullException(nameof(chessPiece));

                containingChessPiece = chessPiece;
                containingChessPiece.SetParent(this);
            }

            /// <see cref="IChessBoardCell.RemoveChessPiece"/>
            public void RemoveChessPiece()
            {
                if (containingChessPiece == null)
                    return;

                containingChessPiece.SetParent(null);
                containingChessPiece = null;
            }
        }
    }
}
