using System;
using System.Collections.Generic;
using System.Text;

namespace SolarWinds.MSP.Chess
{
    public class Piece
    {
            private ChessBoard chessBoard;
            private int xCoordinate;
            private int yCoordinate;
            private PieceColor pieceColor;

            public ChessBoard ChessBoard
            {
                get { return chessBoard; }
                set { chessBoard = value; }
            }

            public int XCoordinate
            {
                get { return xCoordinate; }
                set { xCoordinate = value; }
            }

            public int YCoordinate
            {
                get { return yCoordinate; }
                set { yCoordinate = value; }
            }

            public PieceColor PieceColor
            {
                get { return pieceColor; }
                private set { pieceColor = value; }
            }

            public Piece(PieceColor pieceColor)
            {
                this.pieceColor = pieceColor;
            }

            public override string ToString()
            {
                return CurrentPositionAsString();
            }

            protected string CurrentPositionAsString()
            {
                return string.Format("Current X: {1}{0}Current Y: {2}{0}Piece Color: {3}", Environment.NewLine, XCoordinate, YCoordinate, PieceColor);
            }

        }
    }