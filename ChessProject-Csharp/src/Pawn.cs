using src;
using System;

namespace SolarWinds.MSP.Chess
{
    /// <summary>
    /// Class for Pawn chess piece
    /// </summary>
    public class Pawn : ChessPiece
    {
        private const int MaxNumberOfPawnsPerColour = 8;
       
        /// <summary>
        /// Returns max numbers of pawns allowed on the board per colour
        /// </summary>
        public override int MaxNumberOfPiecesPerColour => MaxNumberOfPawnsPerColour;

        /// <summary>
        /// Constructor
        /// </summary>
        /// <param name="pieceColor">Chess piece colour</param>
        public Pawn(PieceColor pieceColor) : base(pieceColor)
        {
        }

        /// <inheritdoc/>
        public override void Move(MovementType movementType, int newX, int newY)
        {
            if (movementType == MovementType.Move)
            {
                if (ChessBoard.IsPositionOccupied(newX, newY))
                    return;

                if (newX != XCoordinate)
                    return;

                if (PieceColor == PieceColor.Black && newY == (YCoordinate - 1))
                {
                    YCoordinate = newY;
                    return;
                }

                if (PieceColor == PieceColor.White && newY == (YCoordinate + 1))
                {
                    YCoordinate = newY;
                    return;
                }
            }
        }

        /// <summary>
        /// Gets current position as a string
        /// </summary>
        public override string ToString()
        {
            return CurrentPositionAsString();
        }

        /// <summary>
        /// Gets current position as a string
        /// </summary>
        protected string CurrentPositionAsString()
        {
            return string.Format("Current X: {1}{0}Current Y: {2}{0}Piece Color: {3}", Environment.NewLine, XCoordinate, YCoordinate, PieceColor);
        }
    }
}
