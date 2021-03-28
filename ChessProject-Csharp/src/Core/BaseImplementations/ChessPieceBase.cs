using SolarWinds.MSP.Chess;
using SolarWinds.MSP.Chess.Core.Interfaces;
using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;

namespace src.Core.BaseImplementations
{
    /// <summary>
    /// Base class for implementations of chess pieces
    /// </summary>
    public abstract class ChessPieceBase : IChessPiece
    {
        public PieceColor PieceColor { get; protected set; }

        public int XCoordinate { get; set; }

        public int YCoordinate { get; set; }

        /// <summary>
        /// Returns the max number of pieces that are allowed on the board per side
        /// </summary>
        public virtual int NumberPiecesAllowedPerColour
        {
            get
            {
                if (ValidBlackStartPositions != null)
                    return ValidBlackStartPositions.Count();
                else if (ValidWhiteStartPositions != null)
                    return ValidWhiteStartPositions.Count();
                else
                    return -1;
            }
        }

        /// <summary>
        /// Returns a list of coordinates with the valid positions white pieces can start in
        /// </summary>
        public IEnumerable<Point> ValidWhiteStartPositions { get; protected set; }

        /// <summary>
        /// Returns a list of coordinates with the valid positions black pieces can start in
        /// </summary>
        public IEnumerable<Point> ValidBlackStartPositions { get; protected set; }

        /// <summary>
        /// Determines if the coordinates fall within the valid starting positions for this piece's colour
        /// </summary>
        /// <param name="xCoodrinate">The X coordinate</param>
        /// <param name="yCoordinate">The Y coordinate</param>
        /// <returns>True if the coordinates are valid</returns>
        public bool IsValidStartingPosition(int xCoodrinate, int yCoordinate)
        {
            if (PieceColor == PieceColor.Black)
                return ValidBlackStartPositions.Any(point => point.X == xCoodrinate && point.Y == yCoordinate);
            else
                return ValidWhiteStartPositions.Any(point => point.X == xCoodrinate && point.Y == yCoordinate);
        }

        /// <summary>
        /// Determines if the coordinates passed in consitute a valid change of location for the piece
        /// </summary>
        /// <param name="xCoordinate">The X coordinate</param>
        /// <param name="yCoordinate">The Y coordinate</param>
        /// <returns>True if the move is allowed by the piece</returns>
        // Consider injecting an IMoveValidator instance in constructor for use in this method and making it virtual
        public abstract bool IsValidMove(int xCoordinate, int yCoordinate);

        /// <summary>
        /// Attempts to move the piece to the designated coordinates
        /// </summary>
        /// <param name="movementType">The <see cref="MovementType"/> to use for the action</param>
        /// <param name="newX">The new X coordinate</param>
        /// <param name="newY">The new X coordinate</param>
        public abstract void Move(MovementType movementType, int newX, int newY);

        public override string ToString()
        {
            return CurrentPositionAsString();
        }

        protected string CurrentPositionAsString()
        {
            return $"Current X: {XCoordinate}{Environment.NewLine}Current Y: {YCoordinate}{Environment.NewLine}Piece Color: {PieceColor}";
        }
    }
}
