using System;
using System.Text.RegularExpressions;
using System.Collections;

namespace SolarWinds.MSP.Chess
{
    public class ChessBoard
    {
        // NOTE incremented by 1 instead of incrementing in methods and tests 
        public static readonly int MaxBoardWidth = 8;
        public static readonly int MaxBoardHeight = 8;

        public static readonly int MaxPawnCount = 16; //replace with Pawn modified Singleton

        // TODO true implementation for playable game
        // fen notation for standard game starting positions in six columns
        // 1. board-piece positions (ranks seperated by "/", numbers indicating horizontal spans of empty squares
        // 2. whose move (white moves first in a standard game)
        // 3. valid castle sides for white and black
        // 4. en passant square
        // 5. 50-move counter (half moves since last capture)
        // 6. full moves (white and black move) counter
        public static readonly string defaultFEN = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";

        private int pawnCount;

        // board array containing Pawn coords
        // TODO implement superclass Piece
        private Pawn[,] pieces;

        // counter for whose move
        public bool WhitesMove { get; set; }

        // ctor
        public ChessBoard ()
        {
            pieces = new Pawn[MaxBoardWidth, MaxBoardHeight];
            pawnCount = 0;
        }

        // ctor 2
        public ChessBoard(string fen)
        {
            pieces = new Pawn[MaxBoardWidth, MaxBoardHeight];
            pawnCount = 0;
            BoardHelper(fen);
        }

        // TODO add support for other chess configurations/"famous" games
        // TODO test
        // sets up the initial configuration of the board
        protected void BoardHelper(string fen)
        {
            if(fen.Equals(defaultFEN)) // default is for standard game
            {
                int blackRank = 6;
                int whiteOffset = 5;
                for(int file = 0; file < MaxPawnCount / 2; file++)
                {
                    Add(new Pawn(PieceColor.Black), file, blackRank, PieceColor.Black);
                    Add(new Pawn(PieceColor.White), file, blackRank - whiteOffset, PieceColor.White);
                }
                WhitesMove = true;
            }
        }

        public void Add(Pawn pawn, int xCoordinate, int yCoordinate, PieceColor pieceColor)
        {
            if (IsLegalBoardPosition(xCoordinate, yCoordinate))
            {
                if(pawn.XCoordinate == 0 && pawn.YCoordinate == 0) // new pawn placement
                {
                    if (pawnCount < MaxPawnCount)
                    {
                        pawn.ChessBoard = this; // associate new pawn
                        // update its coordinates
                        pawn.XCoordinate = xCoordinate;
                        pawn.YCoordinate = yCoordinate;
                        pieces[yCoordinate, xCoordinate] = pawn; // place the new pawn
                        pawnCount++;
                    }
                    else if (pawnCount == MaxPawnCount)
                    {
                        // don't place and set to out-of-bounds
                        pawn.XCoordinate = -1;
                        pawn.YCoordinate = -1;
                    }
                }
                else // a moved pawn
                {
                    pieces[pawn.YCoordinate, pawn.XCoordinate] = null; // remove the current pawn from the array first in order to place
                    pawnCount--; // allow more pawn placement
                    // update its coordinates
                    pawn.XCoordinate = xCoordinate;
                    pawn.YCoordinate = yCoordinate;
                    pieces[yCoordinate, xCoordinate] = pawn; // place the new pawn
                    pawnCount++;
                }
            }
            else // moving pawn failed
            {
                // restore old pawn
                pieces[pawn.XCoordinate, pawn.YCoordinate] = pawn;
                // don't place new pawn and set to out-of-bounds
                pawn.XCoordinate = -1;
                pawn.YCoordinate = -1;
            }
        }

        // TODO test
        // necessary for retrieving Pawn
        public Tuple<Pawn, int[]> GetPawn(string san)
        {
            int[] coords = NotationHelper(san);
            Pawn p;
            // TODO validate retrieval
            p = pieces[MaxBoardHeight - coords[1] - 1, coords[0]];
            return new Tuple<Pawn, int[]>(p, new int[] {MaxBoardHeight - coords[3] - 1, coords[2]});
        }

        // TODO validate string with full notation regex
        private int[] NotationHelper(string san)
        {
            Regex mvt = new Regex(@"[a-h][1-8]");
            ArrayList list = new ArrayList();
            foreach(Match match in mvt.Matches(san))
            {
                // subtract ascii int val for 0-base
                list.Add(match.Value[0] - 97); // file
                list.Add(match.Value[1] - 49); // rank
            }
            return (int[])list.ToArray(typeof(int));
        }

        public bool IsLegalBoardPosition(int xCoordinate, int yCoordinate)
        {
            if (xCoordinate >= 0 && xCoordinate < MaxBoardWidth
                && yCoordinate >= 0 && yCoordinate < MaxBoardHeight) // within board limits
            {
                if (pieces[yCoordinate, xCoordinate] == null) // space must be empty
                {
                    return true;
                }
                else if (MovementType.Capture.Equals(true)) // or else is capturing
                {
                    return true;
                }
                else
                {
                    return false;
                }
            }
            else
            {
                return false;
            }
        }

        // trivial no test
        public void PrintBoard()
        {
            for(int i = MaxBoardHeight; i >= 0; i--)
            {
                if (i != 0)
                {
                    Console.Write("{0} ", (char)(48 + i)); // prints the ascii chars 8-1
                }
                else
                {
                    Console.Write("  ");
                }
                for(int j = 0; j < MaxBoardWidth; j++)
                {
                    if (i == 0)
                    {
                        Console.Write("{0} ", (char)(j + 97)); // prints the ascii chars a-h below the board
                        continue;
                    }
                    Console.Write((pieces[i - 1, j] != null) ? $"{pieces[i - 1, j].PrintPiece()} " : ". "); // prints a piece or an "empty" space
                }
                Console.WriteLine();
            }
        }
    }
}
