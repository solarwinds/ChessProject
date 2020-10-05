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
            this.pieces = new object[MaxBoardHeight, MaxBoardWidth];
        }
        public ChessBoard (object[,] board)
        {
            this.pieces = board;
        }

        public void Add(ChessPiece piece, int xCoordinate, int yCoordinate)
        {
            var space = pieces[xCoordinate, yCoordinate];
            if (space is ChessPiece){
                piece.XCoordinate = -1;
                piece.YCoordinate = -1;
                throw new DuplicatePositioningException("Position ({0},{1}) is already taken. Cannot Add a piece to a duplicate position.");
            } else {
                // Space is available
                Console.WriteLine("space is available");
                piece.XCoordinate = xCoordinate;
                piece.YCoordinate = yCoordinate;
                pieces[xCoordinate, yCoordinate] = piece;
            }
            Console.WriteLine("Position contains: {0}", space != null ? space : "null" );
        }

        public bool IsLegalBoardPosition(int xCoordinate, int yCoordinate)
        {
            if (xCoordinate >= 0 && 
                xCoordinate <= MaxBoardWidth && 
                yCoordinate >= 0 && 
                yCoordinate <= MaxBoardHeight){
                return true;
            } else {
                return false;
            }
        }

        // public void Print()
        // {
        //     for (int row = 0; row < pieces.GetLength(0); row++){
        //         for (int col = 0; col < pieces.GetLength(1); col++){
        //             pieces[row, col].Print();
        //         }
        //     }
        // }

        // protected string CurrentBoardAsString()
        // {

        //     return string.Format("=========", Environment.NewLine, XCoordinate, YCoordinate, PieceColor);
        // }
    }
}
