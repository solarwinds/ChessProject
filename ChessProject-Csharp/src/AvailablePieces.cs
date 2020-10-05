using System;

namespace SolarWinds.MSP.Chess
{
    public class AvailablePieces
    {
        public object this[string propertyName] 
        {
            get {
                return typeof(AvailablePieces).GetProperty(propertyName).GetValue(this);
            }
            set {
                typeof(AvailablePieces).GetProperty(propertyName).SetValue(this, value);

            }

        }

        public AvailablePieces()
        {
            this.Black = new PossiblePieces();
            this.White = new PossiblePieces();
        }

        public AvailablePieces(
            int blackPawn = 8, int blackRook = 2, int blackKnight = 2, int blackBishop = 2, int blackQueen = 1, int blackKing = 1,
            int whitePawn = 8, int whiteRook = 2, int whiteKnight = 2, int whiteBishop = 2, int whiteQueen = 1, int whiteKing = 1
        )
        {
            this.Black = new PossiblePieces(blackPawn, blackRook, blackKnight, blackBishop, blackQueen, blackKing);
            this.White = new PossiblePieces(whitePawn, whiteRook, whiteKnight, whiteBishop, whiteQueen, whiteKing);
        }

        private PossiblePieces black;

        private PossiblePieces white;

        public PossiblePieces Black
        {
            get { return black; }
            private set { black = value; }
        }

        public PossiblePieces White
        {
            get { return white; }
            private set { white = value; }
        }

        public bool Decrement(string color, string pieceType)
        {
            if (color != "Black" && color != "White")
            {
                throw new ArgumentException("Color must be either 'Black' or 'White'");
            }
            PossiblePieces colorPieceCounts = (PossiblePieces)this[color];
            int curr;
            try
            {
                curr = (int)colorPieceCounts[pieceType];
            } catch (NullReferenceException)
            {
                throw new ArgumentException("Cannot decrement an unrecognized pieceType. Got: {0}", pieceType);
            }
            Console.WriteLine("Decrementing {0} {1} (Currently: {2})...", color, pieceType, curr);
            if (curr > 0)
            {
                colorPieceCounts[pieceType] = --curr;
                Console.WriteLine("Decremented {0}", colorPieceCounts[pieceType]);
                return true;
            }
            else
            {
                return false;
            }
        }

        public class PossiblePieces 
        {
            public object this[string propertyName] 
            {
                get {
                    return typeof(PossiblePieces).GetProperty(propertyName).GetValue(this);
                }
                set {
                    typeof(PossiblePieces).GetProperty(propertyName).SetValue(this, value);

                }

            }

            public PossiblePieces()
            {
                this.pawn = 8;
                this.rook = 2;
                this.knight = 2;
                this.bishop = 2;
                this.queen = 1;
                this.king = 1;
            }

            public PossiblePieces(int pawn, int rook, int knight, int bishop, int queen, int king)
            {
                this.pawn = pawn;
                this.rook = rook;
                this.knight = knight;
                this.bishop = bishop;
                this.queen = queen;
                this.king = king;
            }

            private int pawn;

            public int Pawn
            {
                get { return pawn; }
                private set { pawn = value; }
            }

            private int rook;

            public int Rook
            {
                get { return rook; }
                private set { rook = value; }
            }

            private int knight;

            public int Knight
            {
                get { return knight; }
                private set { knight = value; }
            }

            private int bishop;

            public int Bishop
            {
                get { return bishop; }
                private set { bishop = value; }
            }

            private int queen;

            public int Queen
            {
                get { return queen; }
                private set { queen = value; }
            }

            private int king;

            public int King
            {
                get { return king; }
                private set { king = value; }
            }
        }
    }

        
}
