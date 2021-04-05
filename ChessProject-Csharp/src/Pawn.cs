using System;

namespace SolarWinds.MSP.Chess
{
    /// <summary>
    /// Class represnting pawn chess piece
    /// </summary>
    public class Pawn : ChessPiece<IChessPiecePawn>, IChessPiecePawn
    {
        /// <summary>
        /// Initializes pawn chess piece
        /// </summary>
        /// <param name="chessBoard">Chess board holding the piece</param>
        /// <param name="direction">Direction towards which chess piece can move.</param>
        /// <param name="color">Color of chess piece</param>
        /// <exception cref="ArgumentNullException">chessBoard or direction</exception>
        public Pawn(ChessBoard chessBoard, Direction direction, PieceColor color) : base(chessBoard, direction, color)
        {
        }

        protected override IChessPiecePawn GetInstance() => this;

        /// <see cref="IForwardMoveCommand.TryMoveForward(int)"/>
        public bool TryMoveForward(int steps)
        {
            PerformValidationBeforeMove(steps);

            Position newPosition = CurrentDirection.MoveForward(CurrentPosition);
            return TryMoveChessPiece(newPosition);
        }

        /// <see cref="IDiagonalForwardMoveCommand.TryMoveDiagonalLeftForward(int)"/>
        public bool TryMoveDiagonalLeftForward(int steps)
        {
            PerformValidationBeforeMove(steps);

            Position newPosition = CurrentDirection.MoveForward(CurrentPosition);
            newPosition = CurrentDirection.MoveLeft(newPosition);

            return Capture(newPosition);
        }

        /// <see cref="IDiagonalForwardMoveCommand.TryMoveDiagonalRightForward(int)"/>
        public bool TryMoveDiagonalRightForward(int steps)
        {
            PerformValidationBeforeMove(steps);

            Position newPosition = CurrentDirection.MoveForward(CurrentPosition);
            newPosition = CurrentDirection.MoveRight(newPosition);

            return Capture(newPosition);
        }

        private void PerformValidationBeforeMove(int steps)
        {
            if (steps != 1)
                throw new InvalidOperationException("Pawn can only move one step at a time");

            if (IsCaptured)
                throw new InvalidOperationException("Pawn is already captured and cannot move.");
        }

        private bool Capture(Position position)
        {
            if (!ChessBoard.IsLegalBoardPosition(position))
                return false;

            //Do not allow to move if no piece to capture
            if (ChessBoard.Cells[position.XCoordinate, position.YCoordinate].IsEmpty)
                return false;

            return TryMoveChessPiece(position);
        }
    }
}
