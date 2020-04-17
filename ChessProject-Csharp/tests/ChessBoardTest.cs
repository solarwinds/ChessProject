using NUnit.Framework;

namespace SolarWinds.MSP.Chess
{
	[TestFixture]
	public class ChessBoardTest
	{
		private ChessBoard chessBoard;

		[SetUp]
		public void SetUp()
		{
			chessBoard = new ChessBoard();
		}

        // Offical chess board size of 8x8
		[Test]
		public void Has_MaxBoardWidth_of_8()
		{
			Assert.AreEqual(ChessBoard.MaxBoardWidth, 8);
		}

		[Test]
		public void Has_MaxBoardHeight_of_8()
		{
			Assert.AreEqual(ChessBoard.MaxBoardHeight, 8);
		}

		[TestCase(0,0)] // same as IsLegalBoardPosition_True_X_equals_0_Y_equals_0
		[TestCase(5,5)] // same as IsLegalBoardPosition_True_X_equals_5_Y_equals_5
		public void IsLegalBoardPosition_True(int x, int y)
		{
			var isValidPosition = chessBoard.IsLegalBoardPosition(x, y);
			Assert.IsTrue(isValidPosition);
		}

		[TestCase(11, 5)] // same as IsLegalBoardPosition_False_X_equals_11_Y_equals_5
		[TestCase(0, 9)]  // same as IsLegalBoardPosition_False_X_equals_0_Y_equals_9
		[TestCase(11, 0)] // same as IsLegalBoardPosition_False_X_equals_11_Y_equals_0
		[TestCase(-1, 5)] // same as IsLegalBoardPosition_False_For_Negative_X_Values
		[TestCase(5, -1)] // same as IsLegalBoardPosition_False_For_Negative_Y_Values
		public void IsLegalBoardPosition_False(int x, int y)
		{
			var isValidPosition = chessBoard.IsLegalBoardPosition(x, y);
			Assert.IsFalse(isValidPosition);
		}

		[Test]
		public void Avoids_Duplicate_Positioning()
		{
			Pawn firstPawn = new Pawn(PieceColor.Black);
			Pawn secondPawn = new Pawn(PieceColor.Black);
			chessBoard.Add(firstPawn, 6, 3, PieceColor.Black);
			chessBoard.Add(secondPawn, 6, 3, PieceColor.Black);
			Assert.AreEqual(firstPawn.XCoordinate, 6);
			Assert.AreEqual(firstPawn.YCoordinate, 3);
            // dup pawn coords updated
			Assert.AreEqual(secondPawn.XCoordinate, -1);
			Assert.AreEqual(secondPawn.YCoordinate, -1);
		}

		[Test]
		public void Limits_The_Number_Of_Pawns()
		{
			for (int i = 0; i < ChessBoard.MaxPawnCount + 2; i++) // number of pawns allowed is 16
			{
				Pawn pawn = new Pawn(PieceColor.Black);
				int row = i / ChessBoard.MaxBoardWidth; // (i / 8)
				chessBoard.Add(pawn, 6 - row, i % ChessBoard.MaxBoardWidth, PieceColor.Black); // black pawns start on row 6, build down to accomodate
				if (row < 2) // 0..15
				{
					Assert.AreEqual(pawn.XCoordinate, (6 - row));
					Assert.AreEqual(pawn.YCoordinate, (i % ChessBoard.MaxBoardWidth));
				}
				else // 16..17
				{
					Assert.AreEqual(pawn.XCoordinate, -1);
					Assert.AreEqual(pawn.YCoordinate, -1);
				}
			}
		}
	}
}