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
			chessBoard.Add(pawn, 6, 3);
			Assert.AreEqual(pawn.XCoordinate, 6);
		}

		[Test]
		public void ChessBoard_Add_Sets_YCoordinate()
		{
			chessBoard.Add(pawn, 6, 3);
			Assert.AreEqual(pawn.YCoordinate, 3);
		}

		[Test]
		public void Pawn_Move_IllegalCoordinates_Right_DoesNotMove()
		{
			chessBoard.Add(pawn, 6, 3);
			pawn.Move(MovementType.Move, 7, 3);
            Assert.AreEqual(pawn.XCoordinate, 6);
            Assert.AreEqual(pawn.YCoordinate, 3);
		}

		[Test]
		public void Pawn_Move_IllegalCoordinates_Left_DoesNotMove()
		{
			chessBoard.Add(pawn, 6, 3);
			pawn.Move(MovementType.Move, 4, 3);
            Assert.AreEqual(pawn.XCoordinate, 6);
            Assert.AreEqual(pawn.YCoordinate, 3);
		}

		[Test]
		public void Pawn_Move_LegalCoordinates_Forward_UpdatesCoordinates()
		{
			chessBoard.Add(pawn, 6, 3);
			pawn.Move(MovementType.Move, 6, 2);
			Assert.AreEqual(pawn.XCoordinate, 6);
            Assert.AreEqual(pawn.YCoordinate, 2);
		}

		[Test]
		public void Pawn_Move_IllegalCoordinates_Backward_DoesNotMove()
        {
			chessBoard.Add(pawn, 6, 3);
			pawn.Move(MovementType.Move, 6, 4);
			Assert.AreEqual(pawn.XCoordinate, 6);
			Assert.AreEqual(pawn.YCoordinate, 3);
		}

		[Test]
		public void Pawn_Move_IllegalCoordinates_ForwardTwoSpaces_DoesNotMove()
        {
			chessBoard.Add(pawn, 6, 3);
			pawn.Move(MovementType.Move, 6, 1);
			Assert.AreEqual(pawn.XCoordinate, 6);
			Assert.AreEqual(pawn.YCoordinate, 3);
		}

		[Test]
		public void Pawn_Move_LegalCoordinates_Forward_SpaceOccupied_DoesNotMove()
        {
			Pawn pawn2 = new Pawn(PieceColor.Black);
			chessBoard.Add(pawn, 6, 3);
			chessBoard.Add(pawn2, 6, 2);
			pawn.Move(MovementType.Move, 6, 2);
			Assert.AreEqual(pawn.XCoordinate, 6);
			Assert.AreEqual(pawn.YCoordinate, 3);
		}
	}
}
