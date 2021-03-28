using NUnit.Framework;

namespace SolarWinds.MSP.Chess
{
	// These tests aren't using the IChessPieceContainer interface yet.
	// Should refactor to turn these into proper unit tests, rather than the current integration test feel
	[TestFixture]
	public class PawnTest
	{
		private ChessBoard m_chessBoard;
		private Pawn m_blackPawn;
		private Pawn m_whitePawn;

		[SetUp]
		public void SetUp()	
		{
			m_chessBoard = new ChessBoard();
			m_blackPawn = new Pawn(PieceColor.Black);
            m_whitePawn = new Pawn(PieceColor.White);
		}

		[Test]
		public void ChessBoard_Add_Sets_XCoordinate()
		{
			m_chessBoard.Add(m_blackPawn, 3, 6);

			Assert.AreEqual(m_blackPawn.XCoordinate, 3);
			Assert.IsTrue(m_chessBoard.HasPieces);
		}

		[Test]
		public void ChessBoard_Add_Sets_YCoordinate()
		{
			m_chessBoard.Add(m_blackPawn, 3, 6);

			Assert.AreEqual(m_blackPawn.YCoordinate, 6);
			Assert.IsTrue(m_chessBoard.HasPieces);
		}

		[Test]
		public void ChessBoard_Add_IllegalXCoodrinate_DoesNotAddPieceToBoard()
		{
			m_chessBoard.Add(m_blackPawn, 8, 6);

			Assert.IsFalse(m_chessBoard.HasPieces);
		}

		[Test]
		public void ChessBoard_Add_IllegalYCoodrinate_DoesNotAddPieceToBoard()
		{
			m_chessBoard.Add(m_blackPawn, 3, 8);

			Assert.IsFalse(m_chessBoard.HasPieces);
		}

		[Test]
		public void Pawn_Move_IllegalCoordinates_Right_DoesNotMove()
		{
			m_chessBoard.Add(m_blackPawn, 3, 6);
			m_blackPawn.Move(MovementType.Move, 7, 6);
            Assert.AreEqual(m_blackPawn.XCoordinate, 3);
            Assert.AreEqual(m_blackPawn.YCoordinate, 6);
		}

		[Test]
		public void Pawn_Move_IllegalCoordinates_Left_DoesNotMove()
		{
			m_chessBoard.Add(m_blackPawn, 3, 6);
			m_blackPawn.Move(MovementType.Move, 1, 3);

            Assert.AreEqual(m_blackPawn.XCoordinate, 3);
            Assert.AreEqual(m_blackPawn.YCoordinate, 6);
		}

		[Test]
		public void BlackPawn_MoveTwoSpacesForward_FromStart_UpdatesCoordinates()
		{
			m_chessBoard.Add(m_blackPawn, 3, 6);
			m_blackPawn.Move(MovementType.Move, 3, 4);
			Assert.AreEqual(m_blackPawn.XCoordinate, 3);
            Assert.AreEqual(m_blackPawn.YCoordinate, 4);
		}

		[Test]
		public void WhitePawn_MoveTwoSpacesForward_FromStart_UpdatesCoordinates()
		{
			m_chessBoard.Add(m_whitePawn, 3, 1);
			m_whitePawn.Move(MovementType.Move, 3, 3);

			Assert.AreEqual(m_whitePawn.XCoordinate, 3);
			Assert.AreEqual(m_whitePawn.YCoordinate, 3);
		}

		[Test]
		public void BlackPawn_MoveThreeSpacesForward_FromStart_DoesNotUpdateCoordinates()
		{
			m_chessBoard.Add(m_blackPawn, 3, 6);
			m_blackPawn.Move(MovementType.Move, 3, 3);

			Assert.AreEqual(m_blackPawn.XCoordinate, 3);
			Assert.AreEqual(m_blackPawn.YCoordinate, 6);
		}

		[Test]
		public void WhitePawn_MoveThreeSpacesForward_FromStart_DoesNotUpdateCoordinates()
		{
			m_chessBoard.Add(m_whitePawn, 3, 1);
			m_whitePawn.Move(MovementType.Move, 3, 4);

			Assert.AreEqual(m_whitePawn.XCoordinate, 3);
			Assert.AreEqual(m_whitePawn.YCoordinate, 1);
		}

		[Test]
		public void BlackPawn_MoveBack_DoesNotUpdateCoordinates()
		{
			m_chessBoard.Add(m_blackPawn, 3, 6);
			// Move forward once
			m_blackPawn.Move(MovementType.Move, 3, 4);
			// Try to move back
			m_blackPawn.Move(MovementType.Move, 3, 5);

			Assert.AreEqual(m_blackPawn.XCoordinate, 3);
			Assert.AreEqual(m_blackPawn.YCoordinate, 4);
		}

		[Test]
		public void WhitePawn_MoveBack_DoesNotUpdateCoordinates()
		{
			m_chessBoard.Add(m_whitePawn, 3, 1);
			// Move forward once
			m_whitePawn.Move(MovementType.Move, 3, 3);
			// Try to move back
			m_whitePawn.Move(MovementType.Move, 3, 2);

			Assert.AreEqual(m_whitePawn.XCoordinate, 3);
			Assert.AreEqual(m_whitePawn.YCoordinate, 3);
		}

		[Test]
		public void BlackPawn_MoveOneForward_SecondMove_UpdatesCoordinates()
		{
			m_chessBoard.Add(m_blackPawn, 3, 6);
			// Move forward once
			m_blackPawn.Move(MovementType.Move, 3, 4);
			// Try to move back
			m_blackPawn.Move(MovementType.Move, 3, 3);

			Assert.AreEqual(m_blackPawn.XCoordinate, 3);
			Assert.AreEqual(m_blackPawn.YCoordinate, 3);
		}

		[Test]
		public void WhitePawn_MoveOneForward_SecondMove_UpdatesCoordinates()
		{
			m_chessBoard.Add(m_whitePawn, 3, 1);
			// Move forward once
			m_whitePawn.Move(MovementType.Move, 3, 3);
			// Try to move back
			m_whitePawn.Move(MovementType.Move, 3, 4);

			Assert.AreEqual(m_whitePawn.XCoordinate, 3);
			Assert.AreEqual(m_whitePawn.YCoordinate, 4);
		}

		[Test]
		public void BlackPawn_MoveTwoForward_SecondMove_DoesNotUpdateCoordinates()
		{
			m_chessBoard.Add(m_blackPawn, 3, 6);
			// Move forward once
			m_blackPawn.Move(MovementType.Move, 3, 4);
			// Try to move back
			m_blackPawn.Move(MovementType.Move, 3, 2);

			Assert.AreEqual(m_blackPawn.XCoordinate, 3);
			Assert.AreEqual(m_blackPawn.YCoordinate, 4);
		}

		[Test]
		public void WhitePawn_MoveTwoForward_SecondMove_DoesNotUpdateCoordinates()
		{
			m_chessBoard.Add(m_whitePawn, 3, 1);
			// Move forward once
			m_whitePawn.Move(MovementType.Move, 3, 3);
			// Try to move back
			m_whitePawn.Move(MovementType.Move, 3, 5);

			Assert.AreEqual(m_whitePawn.XCoordinate, 3);
			Assert.AreEqual(m_whitePawn.YCoordinate, 3);
		}

		[Test]
		public void BlackPawn_MoveBack_FromStart_DoesNotUpdateCoordinates()
		{
			m_chessBoard.Add(m_blackPawn, 3, 6);
			m_blackPawn.Move(MovementType.Move, 3, 7);

			Assert.AreEqual(m_blackPawn.XCoordinate, 3);
			Assert.AreEqual(m_blackPawn.YCoordinate, 6);
		}

		[Test]
		public void WhitePawn_MoveBack_FromStart_DoesNotUpdateCoordinates()
		{
			m_chessBoard.Add(m_whitePawn, 3, 1);
			m_whitePawn.Move(MovementType.Move, 3, 0);

			Assert.AreEqual(m_whitePawn.XCoordinate, 3);
			Assert.AreEqual(m_whitePawn.YCoordinate, 1);
		}
	}
}
