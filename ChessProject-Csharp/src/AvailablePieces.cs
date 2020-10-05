using System;

namespace SolarWinds.MSP.Chess
{
    public class AvailablePieces
    {
        private PieceColor pieceColor;

        public PieceColor PieceColor
        {
            get { return pieceColor; }
            private set { pieceColor = value; }
        }

        public AvailablePieces(PieceColor color)
        {
            this.PieceColor = color;
            this.pawns = 8;
            this.rooks = 2;
            this.knights = 2;
            this.bishops = 2;
            this.queens = 1;
            this.kings = 1;
        }

        public AvailablePieces(PieceColor color, int pawns, int rooks, int knights, int bishops, int queens, int kings)
        {
            this.PieceColor = color;
            this.pawns = pawns;
            this.rooks = rooks;
            this.knights = knights;
            this.bishops = bishops;
            this.queens = queens;
            this.kings = kings;
        }

        private int pawns;

        public int Pawns
        {
            get { return pawns; }
            set { 
                if (value == -1){
                    pawns--;
                }
            }
        }

        private int rooks;

        public int Rooks
        {
            get { return rooks; }
            set { 
                if (value == -1){
                    rooks--;
                }
            }
        }

        private int knights;

        public int Knights
        {
            get { return knights; }
            set { 
                if (value == -1){
                    knights--;
                }
            }
        }

        private int bishops;

        public int Bishops
        {
            get { return bishops; }
            set { 
                if (value == -1){
                    bishops--;
                }
            }
        }

        private int queens;

        public int Queens
        {
            get { return queens; }
            set { 
                if (value == -1){
                    queens--;
                }
            }
        }

        private int kings;

        public int Kings
        {
            get { return kings; }
            set { 
                if (value == -1){
                    kings--;
                }
            }
        }
    }
}
