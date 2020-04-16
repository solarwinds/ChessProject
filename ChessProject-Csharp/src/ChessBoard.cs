using System;

namespace SolarWinds.MSP.Chess
{
    public class ChessBoard // DONE pass all SolarWinds unit tests
    {
        public static readonly int MaxBoardWidth = 7;
        public static readonly int MaxBoardHeight = 7;
        public static readonly int MaxPawnCount = 16; //replace with Pawn Singleton

        private int pawnCount;

        // board array containing piece coords
        private Pawn[,] pieces;

        public ChessBoard ()
        {
            pieces = new Pawn[MaxBoardWidth + 1, MaxBoardHeight + 1];
            pawnCount = 0;
        }

        // TODO test
        // sets up the initial configuration of the board
        protected void BoardHelper(string fes)
        {
            
        }

        // TODO add support for other chess configurations/"famous" games

        public void Add(Pawn pawn, int xCoordinate, int yCoordinate, PieceColor pieceColor)
        {
            if (pawnCount < MaxPawnCount && IsLegalBoardPosition(xCoordinate, yCoordinate))
            {
                if (pawn.XCoordinate == 0 && pawn.YCoordinate == 0)
                {
                    pawn.ChessBoard = this;
                }
                //else if (pieces[pawn.XCoordinate, pawn.YCoordinate] != null)
                //{
                //    pieces[pawn.XCoordinate, pawn.YCoordinate] = null;
                //    pawnCount--;
                //    pieces[xCoordinate, yCoordinate] = pawn;
                //}
                pieces[xCoordinate, yCoordinate] = pawn;
                pawn.XCoordinate = xCoordinate;
                pawn.YCoordinate = yCoordinate;
                pawnCount++;
            }
            else
            {
                pawn.XCoordinate = -1;
                pawn.YCoordinate = -1;
                try
                {
                    throw new Exception("Too many pawns!");
                }
                catch(Exception e)
                {
                    e.ToString();
                }
            }
        }

        // TODO test
        // necessary for retrieving Pawn
        public Pawn getPawn(int x, int y)
        {
            Pawn p = pieces[x, y];
            pieces[x, y] = null;
            pawnCount--;
            return p;
        }

        public bool IsLegalBoardPosition(int xCoordinate, int yCoordinate)
        {
            if (xCoordinate >= 0 && xCoordinate <= MaxBoardWidth
                && yCoordinate >= 0 && yCoordinate <= MaxBoardHeight
                && pieces[xCoordinate, yCoordinate] == null)
            {
                return true;
            }
            else if(xCoordinate >= 0 && xCoordinate <= MaxBoardWidth
                    && yCoordinate >= 0 && yCoordinate <= MaxBoardHeight
                    && pieces[xCoordinate, yCoordinate] != null
                    && MovementType.Capture.Equals(true))
            {
                return true;
            }
            else
            {
                return false;
            }
        }

        // TODO test
        // necessary for visual aid
        public void printBoard()
        {
            for(int i = 0; i <= MaxBoardWidth; i++)
            {
                for(int j = 0; j <= MaxBoardHeight; j++)
                {
                    Console.Write((pieces[i, j] != null) ? pieces[i, j].PrintPiece() + " " : ". ");
                }
                Console.WriteLine();
            }
        }
    }
}
