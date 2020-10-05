using System;

namespace SolarWinds.MSP.Chess
{
    public class ChessBoard
    {
        public static readonly int MaxBoardWidth = 7;
        public static readonly int MaxBoardHeight = 7;
        private object[,] pieces;

        public ChessBoard ()
        {
            pieces = new object[MaxBoardHeight, MaxBoardWidth];
        }
        public ChessBoard (object[,] board)
        {
            pieces = board;
        }

        public void Add<T>(T piece, int xCoordinate, int yCoordinate, PieceColor pieceColor)
        {
            throw new NotImplementedException("Need to implement ChessBoard.Add()");
        }

        public bool IsLegalBoardPosition(int xCoordinate, int yCoordinate)
        {
            throw new NotImplementedException("Need to implement ChessBoard.IsLegalBoardPosition()");
        }

        // public void Print()
        // {
        //     for (int row = 0; row < pieces.GetLength(0); row++){
        //         for (int col = 0; col < pieces.GetLength(1); col++){
        //             pieces[row, col].Print();
        //         }
        //     }
        // }

        protected string CurrentBoardAsString()
        {

            return string.Format("=========", Environment.NewLine, XCoordinate, YCoordinate, PieceColor);
        }
    }
}
