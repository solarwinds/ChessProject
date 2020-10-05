using System;

namespace SolarWinds.MSP.Chess
{
    public class ChessBoard
    {
        public static readonly int MaxBoardWidth = 7;
        public static readonly int MaxBoardHeight = 7;

        private object[,] pieces;

        private static AvailablePieces pieceCounts;

        public AvailablePieces PieceCounts
        {
            get { return pieceCounts; }
            private set { pieceCounts = value; }
        }

        public ChessBoard ()
        {
            this.pieces = new object[MaxBoardHeight + 1, MaxBoardWidth + 1];
            PieceCounts = new AvailablePieces();
        }
        public ChessBoard (object[,] board)
        {
            this.pieces = board;
            // NOTE: still need to adjust AvailablePieces___ and validate board to impliment this fully.
        }

        public void Add(ChessPiece piece, int xCoordinate, int yCoordinate)
        {
            Console.WriteLine("Attempting to Add() to ({0}, {1})...", xCoordinate, yCoordinate);
            if (!IsLegalBoardPosition(xCoordinate, yCoordinate))
            {
                piece.XCoordinate = -1;
                piece.YCoordinate = -1;
                throw new InvalidPositioningException("({0}, {1}) is not a valid position. Chessboard cannot Add() an invalid position.");
            }
            if (!IsPieceAvailable(piece)){
                piece.XCoordinate = -1;
                piece.YCoordinate = -1;
                throw new UnavailablePieceException(string.Format("The following Piece type could not be added as it is unavailabe: {0} {1}", piece.StrColor, piece.StrType));
            }
            
            object space = pieces[xCoordinate, yCoordinate];
            if (space is ChessPiece)
            {
                // Space is taken
                piece.XCoordinate = -1;
                piece.YCoordinate = -1;
                throw new DuplicatePositioningException("Position ({0},{1}) is already taken. Cannot Add a piece to a duplicate position.");
            } 
            else 
            {
                // Space is available
                Console.WriteLine("({0}, {1}) is available", xCoordinate, yCoordinate);
                piece.XCoordinate = xCoordinate;
                piece.YCoordinate = yCoordinate;
                pieces[xCoordinate, yCoordinate] = piece;
                AvailablePieces.PossiblePieces colorPieceCounts = (AvailablePieces.PossiblePieces)pieceCounts[piece.StrColor];
                int currCount;
                try
                {
                    currCount = (int)colorPieceCounts[piece.StrType];
                } 
                catch (NullReferenceException)
                {
                    throw new ArgumentException("Cannot determine availability of an unrecognized pieceType. Got: {0}", piece.StrType);
                }
                Console.WriteLine("Available {1} {2} before: {0}", currCount, piece.StrColor, piece.StrType);
                colorPieceCounts[piece.StrType] = --currCount;
                Console.WriteLine("Available {1} {2} after: {0}", currCount, piece.StrColor, piece.StrType);
            }
        }

        public bool IsLegalBoardPosition(int xCoordinate, int yCoordinate)
        {
            if (xCoordinate >= 0 && 
                xCoordinate <= MaxBoardWidth && 
                yCoordinate >= 0 && 
                yCoordinate <= MaxBoardHeight)
            {
                return true;
            } 
            else 
            {
                return false;
            }
        }

        public bool IsPieceAvailable(ChessPiece piece)
        {
            Console.WriteLine("Checking availability of {0} {1}s...", piece.StrColor, piece.StrType);
            AvailablePieces.PossiblePieces colorPieceCounts = (AvailablePieces.PossiblePieces)pieceCounts[piece.StrColor];
            int currCount;
            try
            {
                currCount = (int)colorPieceCounts[piece.StrType];
                Console.WriteLine("currCount {0}", currCount);
            } 
            catch (NullReferenceException)
            {
                throw new ArgumentException("Cannot determine availability of an unrecognized pieceType. Got: {0}", piece.StrType);
            }
            if (currCount > 0)
            {
                return true;
            }
            else
            {
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
