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

        [Test]
		public void Has_MaxBoardWidth_of_7()
		{
			Assert.AreEqual(ChessBoard.MaxBoardWidth, 7);
		}

        [Test]
		public void Has_MaxBoardHeight_of_7()
		{
			Assert.AreEqual(ChessBoard.MaxBoardHeight, 7);
		}

        [TestCase(0, 0)]
		[TestCase(5, 5)]
		public void IsLegalBoardPosition_True(int x, int y)
		{
			var isValidPosition = chessBoard.IsLegalBoardPosition(x, y);
			Assert.IsTrue(isValidPosition);
		}

		[TestCase(11, 5)]
		[TestCase(0, 9)]
		[TestCase(11, 0)]
		[TestCase(-1, 5)]
		[TestCase(5, -1)]
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
            Assert.AreEqual(secondPawn.XCoordinate, -1);
            Assert.AreEqual(secondPawn.YCoordinate, -1);
            // DONE set dup Pawn to (-1,-1)
		}

        [Test]
		public void Limits_The_Number_Of_Pawns()
		{
			for (int i = 0; i < 10; i++)
			{
				Pawn pawn = new Pawn(PieceColor.Black);
				int row = i / (ChessBoard.MaxBoardWidth + 1);
				chessBoard.Add(pawn, 6 + row, i % (ChessBoard.MaxBoardWidth + 1), PieceColor.Black);
				if (row < 1) // i = 0..7
				{
					Assert.AreEqual(pawn.XCoordinate, (6 + row));
					Assert.AreEqual(pawn.YCoordinate, (i % (ChessBoard.MaxBoardWidth + 1)));
				}
				else // i = 8..9
				{
					Assert.AreEqual(pawn.XCoordinate, -1);
                    Assert.AreEqual(pawn.YCoordinate, -1);
				}
			}
		}
	}
}
