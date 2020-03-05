using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace SolarWinds.MSP.Chess
{
    /// <summary>
    /// Class representing a piece. This class is to be override by all other piece types(Pawn,Rook,Knight etc.)
    /// </summary>
    public abstract class Piece
    {
        ChessBoard chessBoard;

        protected PieceColor pieceColor;

        protected int xCoordinate;
        protected int yCoordinate;

        public ChessBoard ChessBoard
        {
            get { return chessBoard; }
            set { chessBoard = value; }
        }

        /// <summary>
        /// Current x coordinate of the piece on the board
        /// </summary>
        public int XCoordinate
        {
            get { return xCoordinate; }
            set { xCoordinate = value; }
        }

        /// <summary>
        /// Current y coordinate of the piece on the board
        /// </summary>
        public int YCoordinate
        {
            get { return yCoordinate; }
            set { yCoordinate = value; }
        }

        /// <summary>
        /// Which colour the piece belongs to (black/white)
        /// </summary>
        public PieceColor PieceColor
        {
            get { return pieceColor; }
            private set { pieceColor = value; }
        }

        public Piece(PieceColor pieceColor)
        {
            this.pieceColor = pieceColor;
        }

        /// <summary>
        /// Tries to move the piece to the designated square. Abstract as all pieces have different ways to move.
        /// </summary>
        /// <returns>
        /// Return true when the piece has been successfully moved, returns false otherwise.
        /// </returns>
        /// <param name="movementType"> This determines whether we are moving or capturing</param>
        /// <param name="newX"> The x coordinate of the square we are trying to move to.</param>
        /// <param name="newY"> The y coordinate of the square we are trying to move to.</param>
        public abstract bool Move(MovementType movementType, int newX, int newY);
    }
}
