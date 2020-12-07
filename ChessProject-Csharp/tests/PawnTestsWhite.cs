using NUnit.Framework;

namespace SolarWinds.MSP.Chess
{
    class PawnTestsWhite : PawnTests
    {

		[SetUp]
		public override void SetUp()
		{
			chessBoard = new ChessBoard();
			pieceColor = PieceColor.White;
			pawn = new Pawn(pieceColor);

		}

		[Test]
		public override void ChessBoard_Add_Sets_XCoordinate()
		{
			chessBoard.Add(pawn, 1, 3, pieceColor);
			Assert.AreEqual(pawn.XCoordinate, 1);
		}
		[Test]
		public override void ChessBoard_Add_Sets_YCoordinate()
		{
			chessBoard.Add(pawn, 1, 3, pieceColor);
			Assert.AreEqual(pawn.YCoordinate, 3);
		}

		[Test]
		public override void pawn_Move_IllegalCoordinates_Right_DoesNotMove()
		{
			chessBoard.Add(pawn, 1, 3, pieceColor);
			pawn.Move(MovementType.Move, 0, 4);
			Assert.AreEqual(pawn.XCoordinate, 0);
			Assert.AreEqual(pawn.YCoordinate, 4);
		}

		[Test]
		public override void pawn_Move_IllegalCoordinates_Left_DoesNotMove()
		{
			chessBoard.Add(pawn, 1, 3, pieceColor);
			pawn.Move(MovementType.Move, 0, 2);
			Assert.AreEqual(pawn.XCoordinate, 1);
			Assert.AreEqual(pawn.YCoordinate, 3);
		}


		[Test]
		public override void pawn_Move_LegalCoordinates_Forward_UpdatesCoordinates()
		{
			chessBoard.Add(pawn, 1, 3, pieceColor);
			pawn.Move(MovementType.Move, 1, 3);
			Assert.AreEqual(pawn.XCoordinate, 1);
			Assert.AreEqual(pawn.YCoordinate, 3);
		}

		[Test]
		public override void pawn_Move_LegalCoordinates_DoesNotMoveBack()
		{
			chessBoard.Add(pawn, 1, 3, pieceColor);
			pawn.Move(MovementType.Move, 1, 2);
			Assert.AreEqual(pawn.XCoordinate, 1);
			Assert.AreEqual(pawn.YCoordinate, 3);
		}
	}
}
