using src.Interfaces;
using System;

namespace src
{
    /// <summary>
    /// Service class for handling the movement of chess pieces
    /// </summary>
    /// <remarks>
    /// Could possibly add an interface for this to improve testability and 
    /// inject it into different chess piece objects
    /// </remarks>
    public class MovementService
    {
        private IMoveValidator _validator;
        private IChessPiece _chessPiece;

        /// <summary>
        /// Constructor
        /// </summary>
        /// <param name="validator"><see cref="IMoveValidator"/></param>
        /// <param name="chessPiece"><see cref="IChessPiece"/></param>
        public MovementService(IMoveValidator validator)
        {
            _validator = validator;
            _chessPiece = validator.ChessPiece;
        }

        /// <summary>
        /// Handle chess piece move if valid
        /// </summary>
        /// <param name="newX">X coordinate to move to</param>
        /// <param name="newY">Y coordinate to move to</param>
        /// <remarks>
        /// Would ideally have separate unit tests for this but code is covered in current PawnTests
        /// </remarks>
        public void Move(int newX, int newY)
        {
            if (_validator.IsValidMove(newX, newY))
            {
                _chessPiece.XCoordinate = newX;
                _chessPiece.YCoordinate = newY;
            }
        }
    }
}
