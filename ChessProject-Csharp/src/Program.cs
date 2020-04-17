using System;
using System.Threading;

namespace SolarWinds.MSP.Chess
{
    public class Program
    {
        public static void Main(string[] args)
        {
            ChessBoard board = new ChessBoard(ChessBoard.defaultFEN);
            Console.WriteLine();
            board.PrintBoard();
            bool moves = true;
            while (moves)
            {
                Console.Write((board.WhitesMove) ? "White's move. " : "Black's move. ");
                Console.Write("Type move and press enter or 'bye' to exit: ");
                string turn = Console.ReadLine();
                if (turn.Equals("bye"))
                {
                    moves = false;
                }
                else
                {
                    Tuple<Pawn, int[]> tuple = board.GetPawn(turn);
                    Pawn p = tuple.Item1;
                    int[] coord = tuple.Item2;
                    p.Move(MovementType.Move, coord[0], coord[1]);
                    board.PrintBoard();
                }
            }
        }
    }
}
