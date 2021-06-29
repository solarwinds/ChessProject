using System;
using src;

namespace SolarWinds.MSP.Chess
{
    public class ChessBoard
    {
        public static readonly int MaxBoardWidth = 8;
        public static readonly int MaxBoardHeight = 8;
        private ChessPiece[,] pieces;

        public ChessBoard ()
        {
            pieces = new ChessPiece[MaxBoardWidth, MaxBoardHeight];
        }

        public void Add(ChessPiece piece, int xCoordinate, int yCoordinate)
        {
            if (IsLegalBoardPosition(xCoordinate, yCoordinate) && !ExceedsMaxNumberOfPieces(piece))
            {
                piece.XCoordinate = xCoordinate;
                piece.YCoordinate = yCoordinate;
                piece.ChessBoard = this;
                
                pieces[xCoordinate, yCoordinate] = piece;
            }
            else
            {
                piece.XCoordinate = -1;
                piece.YCoordinate = -1;
            }
        }
            

        public bool IsLegalBoardPosition(int xCoordinate, int yCoordinate)
        {
            if ((xCoordinate < 0 || xCoordinate >= MaxBoardWidth) || (yCoordinate < 0 || yCoordinate >= MaxBoardHeight))
                return false;

            if (IsPositionOccupied(xCoordinate, yCoordinate))
                return false;

            return true;
        }

        public bool IsPositionOccupied(int xCoordinate, int yCoordinate)
        {
            return pieces[xCoordinate, yCoordinate] != null;
        }

        private bool ExceedsMaxNumberOfPieces(ChessPiece piece)
        {
            int currentPieceTypeCount = 0;

            for (int i = 0; i < MaxBoardWidth; i++)
            {
                for (int j = 0; j < MaxBoardWidth; j++)
                {
                    ChessPiece currentPiece = pieces[i, j];

                    if (currentPiece != null && 
                        currentPiece.GetType() == piece.GetType() && 
                        currentPiece.PieceColor == piece.PieceColor)
                    {
                        currentPieceTypeCount++;

                        if (currentPieceTypeCount >= piece.MaxNumberOfPiecesPerColour)
                            return true;
                    }
                }
            }

            return false;
        }
    }
}
