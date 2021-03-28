using NUnit.Framework;

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
			pawn = new Pawn(PieceColor.Black);
		}

		[Test]
		public void ChessBoard_Add_Sets_XCoordinate()
		{
			chessBoard.Add(pawn, 3, 6);
			Assert.AreEqual(pawn.XCoordinate, 3);
		}

		[Test]
		public void ChessBoard_Add_Sets_YCoordinate()
		{
			chessBoard.Add(pawn, 3, 6);
			Assert.AreEqual(pawn.YCoordinate, 6);
		}

		[Test]
		public void Pawn_Move_IllegalCoordinates_Right_DoesNotMove()
		{
			chessBoard.Add(pawn, 3, 6);
			pawn.Move(MovementType.Move, 7, 6);
            Assert.AreEqual(pawn.XCoordinate, 3);
            Assert.AreEqual(pawn.YCoordinate, 6);
		}

		[Test]
		public void Pawn_Move_IllegalCoordinates_Left_DoesNotMove()
		{
			chessBoard.Add(pawn, 3, 6);
			pawn.Move(MovementType.Move, 1, 3);
            Assert.AreEqual(pawn.XCoordinate, 3);
            Assert.AreEqual(pawn.YCoordinate, 6);
		}

		[Test]
		public void Pawn_Move_LegalCoordinates_Forward_UpdatesCoordinates()
		{
			chessBoard.Add(pawn, 3, 6);
			pawn.Move(MovementType.Move, 3, 4);
			Assert.AreEqual(pawn.XCoordinate, 3);
            Assert.AreEqual(pawn.YCoordinate, 4);
		}
	}
}
