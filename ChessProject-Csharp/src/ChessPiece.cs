using System;
using System.Collections.Generic;
using System.Text;

namespace SolarWinds.MSP.Chess
{
    public class ChessPiece
    {
      
        protected int xCoordinate { get; set; } = -1;

        protected int yCoordinate { get; set; } = -1;

        protected PieceColor pieceColor { get; set; }

        protected int maxPieces { get; set; }

        public event EventHandler<bool> pieceUpdated;


        public int XCoordinate
        {
            get { return xCoordinate; }
            set { xCoordinate = value; }
        }

        public int YCoordinate
        {
            get { return yCoordinate; }
            set { yCoordinate = value; }
        }

        public PieceColor PieceColor
        {
            get { return pieceColor; }
          
        }

        protected void SetPieceColour(PieceColor pieceColor)
        {
            this.pieceColor = pieceColor;
        }

        protected virtual void OnUpdateCompleted(bool IsSuccessful)
        {
            pieceUpdated?.Invoke(this, IsSuccessful);
        }

        public virtual bool IsValidPlacement(int newX, int newY)
        {
            switch (pieceColor)
            {
                case PieceColor.Black:
                    if (newX == 5 )
                    {
                        return true;
                    }
                    break;
                case PieceColor.White:
                    if (newX == 1 )
                    {
                        return true;
                    }
                    break;
            }
            return false ;
        }

        protected virtual bool IsValidMovement(int newX, int newY)
        {
            if (XCoordinate == newX && YCoordinate == newY)
            {
                return false; 
            }

            return true;
        }

        public void Move(MovementType movementType, int newX, int newY)
        {
            try
            {
                if (IsValidMovement(newX, newY))
                {
                    XCoordinate = newX;
                    YCoordinate = newY;
                }

                OnUpdateCompleted(true);
            }
            catch
            {
                OnUpdateCompleted(false);
            }

          
        }

    }
}
