using System;



namespace SolarWinds.MSP.Chess
{

    public class ChessBoard
    {
        public static readonly int MaxBoardWidth = 7;
        public static readonly int MaxBoardHeight = 7;
       
        private Pawn[,] pieces;

        public ChessBoard()
        {
            pieces = new Pawn[MaxBoardWidth, MaxBoardHeight];
        }

        public void Add(Pawn pawn, int xCoordinate, int yCoordinate, PieceColor pieceColor)
        {
            if (pawn.IsValidPlacement(xCoordinate, yCoordinate) && IsLegalBoardPosition(xCoordinate, yCoordinate) && pieces[xCoordinate,yCoordinate] == null)
            {
                pawn.pieceUpdated += PieceUpdate;
                pawn.XCoordinate = xCoordinate;
                pawn.YCoordinate = yCoordinate;
                pieces[xCoordinate, yCoordinate] = pawn;
            }
           
        }

        public bool IsLegalBoardPosition(int xCoordinate, int yCoordinate)
        {
            if (xCoordinate < MaxBoardWidth && xCoordinate  >=0 && yCoordinate < MaxBoardWidth && yCoordinate >=0)
            {
                return true;
            }
          
            return false;
        }

        public static void PieceUpdate(Object sender, bool IsSuccessful)
        {
            Console.WriteLine("Process " + (IsSuccessful ? "Completed Successfully" : "failed"));
                      
        }
    }
}
