using src;
using src.Interfaces;
using System;

namespace SolarWinds.MSP.Chess
{
    /// <summary>
    /// Class for Pawn chess piece
    /// </summary>
    public class Pawn : ChessPiece
    {
        private const int MaxNumberOfPawnsPerColour = 8;
        private MovementService _movementSerivce;
        private IMoveValidator _validator;
       
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
            // Going forward we could potentially get rid of this Pawn class and store the chess piece 
            // type in the main ChessPiece class. Since the movement and movement validation is now handled
            // within the movement service we don't really need to override the move method.
            // Could have a ValidatorFactory or something similar to get the correct
            // validator depending on ChessPiece type.

            _validator = new PawnMoveValidator(this);
            _movementSerivce = new MovementService(_validator);
        }

        /// <inheritdoc/>
        public override void Move(MovementType movementType, int newX, int newY)
        {
            if (movementType == MovementType.Move)
            {
                _movementSerivce.Move(newX, newY);
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
