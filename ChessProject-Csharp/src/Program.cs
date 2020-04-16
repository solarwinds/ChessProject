using System;
using System.Threading;

namespace SolarWinds.MSP.Chess
{
    public class Program
    {
        public static void Main(string[] args)
        {
            ChessBoard board = new ChessBoard();
            int x = 6, y = 1, r = 1, c = 6;
            board.Add(new Pawn(PieceColor.White), x, y, PieceColor.White); // row, col from top
            board.Add(new Pawn(PieceColor.Black), r, c, PieceColor.Black); // row, col from top
            board.printBoard();
            Console.WriteLine();
            Thread.Sleep(500);
            Pawn wp = board.getPawn(x, y);
            wp.Move(MovementType.Move, --x, y);
            Pawn bp = board.getPawn(r, c);
            bp.Move(MovementType.Move, ++r, c);
            board.printBoard();
        }
    }
}
