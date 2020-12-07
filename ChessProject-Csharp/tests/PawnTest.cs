using NUnit.Framework;

namespace SolarWinds.MSP.Chess
{
	[TestFixture]
	public class PawnTests
	{
		protected ChessBoard chessBoard;
		protected Pawn pawn;
		protected PieceColor pieceColor;

		[SetUp]
		public virtual void SetUp()	
		{
			chessBoard = new ChessBoard();
			pieceColor = PieceColor.Black;
			pawn = new Pawn(pieceColor);
			
		}

		[Test]
		public virtual void ChessBoard_Add_Sets_XCoordinate()
		{
			chessBoard.Add(pawn, 5, 3, pieceColor);
			Assert.AreEqual(pawn.  XCoordinate, 5);
		}
		[Test]
		public virtual void ChessBoard_Add_Sets_YCoordinate()
		{
			chessBoard.Add(pawn, 5, 3, pieceColor);
			Assert.AreEqual(pawn.YCoordinate, 3);
		}

		[Test]
		public virtual  void pawn_Move_IllegalCoordinates_Right_DoesNotMove()
		{
			chessBoard.Add(pawn, 5, 3, pieceColor);
			pawn.Move(MovementType.Move, 4, 3);
            Assert.AreEqual(pawn.XCoordinate, 5);
            Assert.AreEqual(pawn.YCoordinate, 3);
		}

		[Test]
		public virtual void pawn_Move_IllegalCoordinates_Left_DoesNotMove()
		{
			chessBoard.Add(pawn, 5, 3, pieceColor);
			pawn.Move(MovementType.Move, 4, 3);
            Assert.AreEqual(pawn.XCoordinate, 5);
            Assert.AreEqual(pawn.YCoordinate, 3);
		}

		[Test]
		public  virtual void pawn_Move_LegalCoordinates_Forward_UpdatesCoordinates()
		{
			chessBoard.Add(pawn, 5, 3, pieceColor);
			pawn.Move(MovementType.Move, 4, 2);
			Assert.AreEqual(pawn.XCoordinate, 4);
            Assert.AreEqual(pawn.YCoordinate, 2);
		}

		[Test]
		public virtual void pawn_Move_LegalCoordinates_DoesNotMoveBack()
		{
			chessBoard.Add(pawn, 5, 3, pieceColor);
			pawn.Move(MovementType.Move, 5, 4);
			Assert.AreEqual(pawn.XCoordinate, 5);
			Assert.AreEqual(pawn.YCoordinate, 3);
		}

		[Test]
		public void pawn_Check_Colour_Set_Correctly()
		{
			Assert.AreEqual(pawn.PieceColor, pieceColor);
		}
	}
}
