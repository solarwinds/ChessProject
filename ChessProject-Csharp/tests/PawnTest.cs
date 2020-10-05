using NUnit.Framework;

/*
- Pawn backward move fails
- Pawn initial move of 2 spacess works
- Pawn subsequent move of 2 spaces fails
- Pawn move to the right fails
- Pawn move the the left fails
- Cannot capture two spaces up from initial move
*/

namespace SolarWinds.MSP.Chess
{
	[TestFixture]
	public class PawnTest
	{
		private ChessBoard chessBoard;
		private Pawn pawnPositive;
		private Pawn pawnNegative;

		[SetUp]
		public void SetUp()	
		{
			chessBoard = new ChessBoard();
			pawnPositive = new Pawn(PieceColor.White, MovementDirection.Positive);
			pawnNegative = new Pawn(PieceColor.Black, MovementDirection.Negative);
		}

		[Test]
		public void ChessBoard_Add_Sets_XCoordinate()
		{
			// Positive movement
			chessBoard.Add(pawnPositive, 1, 3);
			Assert.AreEqual(1, pawnPositive.XCoordinate);
			// Negative movement
			chessBoard.Add(pawnPositive, 6, 3);
			Assert.AreEqual(6, pawnPositive.XCoordinate);
		}

		[Test]
		public void ChessBoard_Add_Sets_YCoordinate()
		{
			// Positive movement
			chessBoard.Add(pawnPositive, 1, 3);
			Assert.AreEqual(3, pawnPositive.YCoordinate);
			// Negative movement
			chessBoard.Add(pawnNegative, 6, 3);
			Assert.AreEqual(3, pawnNegative.YCoordinate);
		}

		[Test]
		public void Pawn_Move_False_IllegalCoordinates_Right_DoesNotMove()
		{
			// Positive movement
			chessBoard.Add(pawnPositive, 1, 3);
			Assert.IsFalse(pawnPositive.Move(MovementType.Move, 7, 3));
			// Negative movement
			chessBoard.Add(pawnNegative, 6, 3);
			Assert.IsFalse(pawnNegative.Move(MovementType.Move, 7, 3));

		}

		[Test]
		public void Pawn_Move_False_IllegalCoordinates_Left_DoesNotMove()
		{
			// Positive movement
			chessBoard.Add(pawnPositive, 1, 3);
			Assert.IsFalse(pawnPositive.Move(MovementType.Move, 0, 3));
			// Negative movement
			chessBoard.Add(pawnNegative, 6, 3);
			Assert.IsFalse(pawnNegative.Move(MovementType.Move, 5, 3));
		}

		[Test]
		public void Pawn_Move_True_LegalCoordinates_Forward_UpdatesCoordinates()
		{
			// Positive movement
			chessBoard.Add(pawnPositive, 1, 3);
			Assert.IsTrue(pawnPositive.Move(MovementType.Move, 1, 4));
			// Negative movement
			chessBoard.Add(pawnNegative, 6, 3);
			Assert.IsTrue(pawnNegative.Move(MovementType.Move, 6, 2));
		}

		[Test]
		public void Pawn_Move_False_IllegalCoordinates_Backward_DoesNotMove()
		{
			// Positive movement
			chessBoard.Add(pawnPositive, 1, 3);
			Assert.IsFalse(pawnPositive.Move(MovementType.Move, 1, 2));
			// Negative movement
			chessBoard.Add(pawnNegative, 6, 3);
			Assert.IsFalse(pawnNegative.Move(MovementType.Move, 6, 4));
		}

	}
}
