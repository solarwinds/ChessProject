using SolarWinds.MSP.Chess;
using SolarWinds.MSP.Chess.Core.Interfaces;
using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;

namespace src.Core.BaseImplementations
{
    public abstract class ChessPieceBase : IChessPiece
    {
        public PieceColor PieceColor { get; protected set; }

        public int XCoordinate { get; set; }

        public int YCoordinate { get; set; }

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

        public IEnumerable<Point> ValidWhiteStartPositions { get; protected set; }

        public IEnumerable<Point> ValidBlackStartPositions { get; protected set; }

        public bool IsValidStartingPosition(int xCoodrinate, int yCoordinate)
        {
            if (PieceColor == PieceColor.Black)
                return ValidBlackStartPositions.Any(point => point.X == xCoodrinate && point.Y == yCoordinate);
            else
                return ValidWhiteStartPositions.Any(point => point.X == xCoodrinate && point.Y == yCoordinate);
        }

        // Consider injecting an IMoveValidator instance in constructor for use in this method and making it virtual
        public abstract bool IsValidMove(int xCoordinate, int yCoordinate);

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
