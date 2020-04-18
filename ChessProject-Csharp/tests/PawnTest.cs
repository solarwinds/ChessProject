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
			chessBoard.Add(pawn, 6, 3, PieceColor.Black);
			Assert.AreEqual(pawn.XCoordinate, 6);
		}

		[Test]
		public void ChessBoard_Add_Sets_YCoordinate()
		{
			chessBoard.Add(pawn, 6, 3, PieceColor.Black);
			Assert.AreEqual(pawn.YCoordinate, 3);
		}

		[Test]
		public void Pawn_Move_IllegalCoordinates_Right_DoesNotMove()
		{
			chessBoard.Add(pawn, 6, 3, PieceColor.Black);
			pawn.Move(MovementType.Move, 7, 3);
			Assert.AreEqual(pawn.XCoordinate, 6);
			Assert.AreEqual(pawn.YCoordinate, 3);
		}

		[Test]
		public void Pawn_Move_IllegalCoordinates_Left_DoesNotMove()
		{
			chessBoard.Add(pawn, 6, 3, PieceColor.Black);
			pawn.Move(MovementType.Move, 4, 3);
			Assert.AreEqual(pawn.XCoordinate, 6);
			Assert.AreEqual(pawn.YCoordinate, 3);
		}

		[Test]
		public void Pawn_Move_LegalCoordinates_Forward_UpdatesCoordinates()
		{
			chessBoard.Add(pawn, 6, 3, PieceColor.Black);
			pawn.Move(MovementType.Move, 6, 2);
			Assert.AreEqual(pawn.XCoordinate, 6);
			Assert.AreEqual(pawn.YCoordinate, 2);
		}
	}
}