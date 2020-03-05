using System;

namespace SolarWinds.MSP.Chess
{
    /// <summary>
    /// The pawn class.
    /// </summary>
    public class Pawn : Piece
    {

        /// <summary>
        /// Tries to move the pawn to the designated square.
        /// </summary>
        /// <returns>
        /// Return true when the pawn has been successfully moved, returns false otherwise.
        /// </returns>
        /// <param name="movementType"> This determines whether we are moving or capturing</param>
        /// <param name="newX"> The x coordinate of the square we are trying to move to.</param>
        /// <param name="newY"> The y coordinate of the square we are trying to move to.</param>
        public override bool Move(MovementType movementType, int newX, int newY)
        {
            // white pawns move up the board, black pawns move down the board.
            var direction = this.pieceColor == PieceColor.White ? 1 : -1;

            if(movementType == MovementType.Move)
            {
                if (newX != this.xCoordinate)
                    return false;

                if (newY != this.yCoordinate + direction)
                    return false;

                this.yCoordinate = newY;
            }
            else if(movementType == MovementType.Capture)
            {
                throw new NotImplementedException();
            }

            return true;

        }

        public Pawn(PieceColor pieceColor) : base(pieceColor){}
    }
}
