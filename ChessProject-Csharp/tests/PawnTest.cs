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
		private Pawn pawn;

		[SetUp]
		public void SetUp()	
		{
			chessBoard = new ChessBoard();
			pawn = new Pawn(PieceColor.Black, MovementDirection.Negative);
		}

		[Test]
		public void ChessBoard_Add_Sets_XCoordinate()
		{
			chessBoard.Add(pawn, 6, 3);
			Assert.AreEqual(6, pawn.XCoordinate);
		}

		[Test]
		public void ChessBoard_Add_Sets_YCoordinate()
		{
			chessBoard.Add(pawn, 6, 3);
			Assert.AreEqual(3, pawn.YCoordinate);
		}

		[Test]
		public void Pawn_Move_IllegalCoordinates_Right_DoesNotMove()
		{
			chessBoard.Add(pawn, 6, 3);
			pawn.Move(MovementType.Move, 7, 3);
            Assert.AreEqual(6, pawn.XCoordinate);
            Assert.AreEqual(3, pawn.YCoordinate);
		}

		[Test]
		public void Pawn_Move_IllegalCoordinates_Left_DoesNotMove()
		{
			chessBoard.Add(pawn, 6, 3);
			pawn.Move(MovementType.Move, 4, 3);
            Assert.AreEqual(6, pawn.XCoordinate);
            Assert.AreEqual(3, pawn.YCoordinate);
		}

		[Test]
		public void Pawn_Move_LegalCoordinates_Forward_UpdatesCoordinates()
		{
			chessBoard.Add(pawn, 6, 3);
			pawn.Move(MovementType.Move, 6, 2);
			Assert.AreEqual(6, pawn.XCoordinate);
            Assert.AreEqual(2, pawn.YCoordinate);
		}
	}
}
