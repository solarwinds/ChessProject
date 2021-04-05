using NUnit.Framework;
using System;

namespace SolarWinds.MSP.Chess
{
	[TestFixture]
	public class PawnTest
	{
		private ChessBoard chessBoard;

		private IChessPiecePawn blackPawn1; // At position 6,3  Faces west side of the chessboard
		private IChessPiecePawn blackPawn2; // At position 6,4  Faces west side of the chessboard
		private IChessPiecePawn whitePawn1; // At position 1,3  Faces east side of the chessboard
		private IChessPiecePawn whitePawn2; // At position 1,4  Faces east side of the chessboard

		[SetUp]
		public void SetUp()
		{
			chessBoard = new ChessBoard(new EastSide(), new ChessPieceCreationFactory());

			blackPawn1 = chessBoard.PlacePawn(new Position(6, 3), PieceColor.Black);
			blackPawn2 = chessBoard.PlacePawn(new Position(6, 4), PieceColor.Black);

			whitePawn1 = chessBoard.PlacePawn(new Position(1, 3), PieceColor.White);
			whitePawn2 = chessBoard.PlacePawn(new Position(1, 4), PieceColor.White);
		}

		[Test]
		public void ChessBoard_PlacePawn_Sets_InitialProperties()
		{
			Assert.AreEqual(new Position(6, 3), blackPawn1.CurrentPosition);
			Assert.AreEqual(PieceColor.Black, blackPawn1.Color);
			Assert.IsFalse(blackPawn1.IsCaptured);

			Assert.AreEqual(new Position(6, 4), blackPawn2.CurrentPosition);
			Assert.AreEqual(PieceColor.Black, blackPawn2.Color);
			Assert.IsFalse(blackPawn2.IsCaptured);

			Assert.AreEqual(new Position(1, 3), whitePawn1.CurrentPosition);
			Assert.AreEqual(PieceColor.White, whitePawn1.Color);
			Assert.IsFalse(whitePawn1.IsCaptured);

			Assert.AreEqual(new Position(1, 4), whitePawn2.CurrentPosition);
			Assert.AreEqual(PieceColor.White, whitePawn2.Color);
			Assert.IsFalse(whitePawn2.IsCaptured);

			Assert.IsFalse(chessBoard.Cells[6, 3].IsEmpty);
			Assert.IsFalse(chessBoard.Cells[6, 4].IsEmpty);
			Assert.IsFalse(chessBoard.Cells[1, 3].IsEmpty);
			Assert.IsFalse(chessBoard.Cells[1, 4].IsEmpty);
		}

		[TestCase(0)]
		[TestCase(-1)]
		[TestCase(2)]
		public void Pawn_MoveForward_Throws_Exception_For_InvalidSteps(int steps)
		{
			Assert.Throws<InvalidOperationException>(() => blackPawn1.TryMoveForward(steps));
		}

		[Test]
		public void Pawn_MoveForward_LegalCoordinates_UpdatesCoordinates()
		{
			Assert.IsTrue(blackPawn1.TryMoveForward(1));
			Assert.AreEqual(new Position(5, 3), blackPawn1.CurrentPosition);

			Assert.IsTrue(chessBoard.Cells[6, 3].IsEmpty);
			Assert.IsFalse(chessBoard.Cells[5, 3].IsEmpty);
		}

		[Test]
		public void Pawn_MoveForward_CoordinatesOutsideBounds_DoesNotUpdateCoordinates()
		{
			int i = 1;
			while (i < 7)
			{
				blackPawn1.TryMoveForward(1);
				i++;
			}

			Assert.AreEqual(new Position(0, 3), blackPawn1.CurrentPosition);
			Assert.IsFalse(chessBoard.Cells[0, 3].IsEmpty);

			Assert.IsFalse(blackPawn1.TryMoveForward(1));

			Assert.AreEqual(new Position(0, 3), blackPawn1.CurrentPosition);
			Assert.IsFalse(chessBoard.Cells[0, 3].IsEmpty);
		}

		[Test]
		public void Pawn_MoveForward_Captures_Pawn_With_DifferentColor()
		{
			int i = 1;
			while (i < 5)
			{
				blackPawn1.TryMoveForward(1);
				i++;
			}

			//Precondition
			Assert.AreEqual(new Position(2, 3), blackPawn1.CurrentPosition);
			Assert.IsFalse(chessBoard.Cells[2, 3].IsEmpty);

			Assert.AreEqual(new Position(1, 3), whitePawn1.CurrentPosition);
			Assert.IsFalse(whitePawn1.IsCaptured);
			Assert.IsFalse(chessBoard.Cells[1, 3].IsEmpty);

			Assert.IsTrue(blackPawn1.TryMoveForward(1));

			Assert.AreEqual(new Position(1, 3), blackPawn1.CurrentPosition);
			Assert.IsFalse(chessBoard.Cells[1, 3].IsEmpty);

			Assert.AreEqual(new Position(-1, -1), whitePawn1.CurrentPosition);
			Assert.IsTrue(whitePawn1.IsCaptured);

			Assert.IsTrue(chessBoard.Cells[2, 3].IsEmpty);
		}

		[Test]
		public void Pawn_MoveForward_Encounters_Pawn_With_SameColor_DoesNotUpdateCoordinates()
		{
			int i = 1;
			while (i < 5)
			{
				blackPawn1.TryMoveForward(1);
				blackPawn2.TryMoveForward(1);
				i++;
			}

			blackPawn1.TryMoveDiagonalRightForward(1);

			//Precondition
			Assert.AreEqual(new Position(1, 4), blackPawn1.CurrentPosition);
			Assert.IsFalse(chessBoard.Cells[1, 4].IsEmpty);

			Assert.AreEqual(new Position(2, 4), blackPawn2.CurrentPosition);
			Assert.IsFalse(chessBoard.Cells[2, 4].IsEmpty);

			Assert.IsFalse(blackPawn2.TryMoveForward(1));

			Assert.AreEqual(new Position(1, 4), blackPawn1.CurrentPosition);
			Assert.IsFalse(chessBoard.Cells[1, 4].IsEmpty);

			Assert.AreEqual(new Position(2, 4), blackPawn2.CurrentPosition);
			Assert.IsFalse(chessBoard.Cells[2, 4].IsEmpty);
		}

		[Test]
		public void Pawn_MoveForward_Throws_Exception_If_Already_Captured()
		{
			int i = 1;
			while (i < 5)
			{
				blackPawn1.TryMoveForward(1);
				i++;
			}

			Assert.IsTrue(blackPawn1.TryMoveForward(1));
			Assert.IsTrue(whitePawn1.IsCaptured);

			Assert.Throws<InvalidOperationException>(() => whitePawn1.TryMoveForward(1));
		}

		[TestCase(0)]
		[TestCase(-1)]
		[TestCase(2)]
		public void Pawn_Move_Diagonal_Left_Throws_Exception_For_InvalidSteps(int steps)
		{
			Assert.Throws<InvalidOperationException>(() => blackPawn1.TryMoveDiagonalLeftForward(steps));
		}

		[Test]
		public void Pawn_Move_Diagonal_Left_CoordinatesOutsideBounds_DoesNotUpdateCoordinates()
		{
			int i = 1;
			while (i < 7)
			{
				blackPawn1.TryMoveForward(1);
				i++;
			}

			Assert.AreEqual(new Position(0, 3), blackPawn1.CurrentPosition);
			Assert.IsFalse(chessBoard.Cells[0, 3].IsEmpty);

			Assert.IsFalse(blackPawn1.TryMoveDiagonalLeftForward(1));

			Assert.AreEqual(new Position(0, 3), blackPawn1.CurrentPosition);
			Assert.IsFalse(chessBoard.Cells[0, 3].IsEmpty);
		}

		[Test]
		public void Pawn_Move_Diagonal_Left_NothingToCapture_DoesNotUpdateCoordinates()
		{
			Assert.IsFalse(blackPawn1.TryMoveDiagonalLeftForward(1));

			Assert.AreEqual(new Position(6, 3), blackPawn1.CurrentPosition);
			Assert.IsFalse(chessBoard.Cells[6, 3].IsEmpty);
		}

		[Test]
		public void Pawn_Move_Diagonal_Left_Encounters_Pawn_With_SameColor_DoesNotUpdateCoordinates()
		{
			blackPawn1.TryMoveForward(1);

			Assert.AreEqual(new Position(5, 3), blackPawn1.CurrentPosition);
			Assert.IsFalse(chessBoard.Cells[5, 3].IsEmpty);

			Assert.AreEqual(new Position(6, 4), blackPawn2.CurrentPosition);
			Assert.IsFalse(chessBoard.Cells[6, 4].IsEmpty);

			Assert.IsFalse(blackPawn2.TryMoveDiagonalLeftForward(1));

			Assert.AreEqual(new Position(5, 3), blackPawn1.CurrentPosition);
			Assert.IsFalse(chessBoard.Cells[5, 3].IsEmpty);

			Assert.AreEqual(new Position(6, 4), blackPawn2.CurrentPosition);
			Assert.IsFalse(chessBoard.Cells[6, 4].IsEmpty);
		}

		[Test]
		public void Pawn_Move_Diagonal_Left_Captures_Pawn_With_DifferentColor()
		{
			int i = 1;
			while (i < 5)
			{
				blackPawn2.TryMoveForward(1);
				i++;
			}

			//Precondition
			Assert.AreEqual(new Position(2, 4), blackPawn2.CurrentPosition);
			Assert.IsFalse(chessBoard.Cells[2, 4].IsEmpty);

			Assert.AreEqual(new Position(1, 3), whitePawn1.CurrentPosition);
			Assert.IsFalse(whitePawn1.IsCaptured);
			Assert.IsFalse(chessBoard.Cells[1, 3].IsEmpty);

			Assert.IsTrue(blackPawn2.TryMoveDiagonalLeftForward(1));

			Assert.AreEqual(new Position(1, 3), blackPawn2.CurrentPosition);
			Assert.IsFalse(chessBoard.Cells[1, 3].IsEmpty);

			Assert.AreEqual(new Position(-1, -1), whitePawn1.CurrentPosition);
			Assert.IsTrue(whitePawn1.IsCaptured);

			Assert.IsTrue(chessBoard.Cells[2, 4].IsEmpty);
		}

		[Test]
		public void Pawn_Move_Diagonal_Left_Throws_Exception_If_Already_Captured()
		{
			int i = 1;
			while (i < 5)
			{
				blackPawn2.TryMoveForward(1);
				i++;
			}

			Assert.IsTrue(blackPawn2.TryMoveDiagonalLeftForward(1));
			Assert.IsTrue(whitePawn1.IsCaptured);

			Assert.Throws<InvalidOperationException>(() => whitePawn1.TryMoveDiagonalLeftForward(1));
		}

		[TestCase(0)]
		[TestCase(-1)]
		[TestCase(2)]
		public void Pawn_Move_Diagonal_Right_Throws_Exception_For_InvalidSteps(int steps)
		{
			Assert.Throws<InvalidOperationException>(() => blackPawn1.TryMoveDiagonalRightForward(steps));
		}

		[Test]
		public void Pawn_Move_Diagonal_Right_CoordinatesOutsideBounds_DoesNotUpdateCoordinates()
		{
			int i = 1;
			while (i < 7)
			{
				blackPawn1.TryMoveForward(1);
				i++;
			}

			Assert.AreEqual(new Position(0, 3), blackPawn1.CurrentPosition);
			Assert.IsFalse(chessBoard.Cells[0, 3].IsEmpty);

			Assert.IsFalse(blackPawn1.TryMoveDiagonalRightForward(1));

			Assert.AreEqual(new Position(0, 3), blackPawn1.CurrentPosition);
			Assert.IsFalse(chessBoard.Cells[0, 3].IsEmpty);
		}

		[Test]
		public void Pawn_Move_Diagonal_Right_NothingToCapture_DoesNotUpdateCoordinates()
		{
			Assert.IsFalse(blackPawn1.TryMoveDiagonalRightForward(1));

			Assert.AreEqual(new Position(6, 3), blackPawn1.CurrentPosition);
			Assert.IsFalse(chessBoard.Cells[6, 3].IsEmpty);
		}

		[Test]
		public void Pawn_Move_Diagonal_Right_Encounters_Pawn_With_SameColor_DoesNotUpdateCoordinates()
		{
			blackPawn2.TryMoveForward(1);

			Assert.AreEqual(new Position(6, 3), blackPawn1.CurrentPosition);
			Assert.IsFalse(chessBoard.Cells[6, 3].IsEmpty);

			Assert.AreEqual(new Position(5, 4), blackPawn2.CurrentPosition);
			Assert.IsFalse(chessBoard.Cells[5, 4].IsEmpty);

			Assert.IsFalse(blackPawn1.TryMoveDiagonalRightForward(1));

			Assert.AreEqual(new Position(6, 3), blackPawn1.CurrentPosition);
			Assert.IsFalse(chessBoard.Cells[6, 3].IsEmpty);

			Assert.AreEqual(new Position(5, 4), blackPawn2.CurrentPosition);
			Assert.IsFalse(chessBoard.Cells[5, 4].IsEmpty);
		}

		[Test]
		public void Pawn_Move_Diagonal_Right_Captures_Pawn_With_DifferentColor()
		{
			int i = 1;
			while (i < 5)
			{
				blackPawn1.TryMoveForward(1);
				i++;
			}

			//Precondition
			Assert.AreEqual(new Position(2, 3), blackPawn1.CurrentPosition);
			Assert.IsFalse(chessBoard.Cells[2, 3].IsEmpty);

			Assert.AreEqual(new Position(1, 4), whitePawn2.CurrentPosition);
			Assert.IsFalse(whitePawn2.IsCaptured);
			Assert.IsFalse(chessBoard.Cells[1, 4].IsEmpty);

			Assert.IsTrue(blackPawn1.TryMoveDiagonalRightForward(1));

			Assert.AreEqual(new Position(1, 4), blackPawn1.CurrentPosition);
			Assert.IsFalse(chessBoard.Cells[1, 4].IsEmpty);

			Assert.AreEqual(new Position(-1, -1), whitePawn2.CurrentPosition);
			Assert.IsTrue(whitePawn2.IsCaptured);

			Assert.IsTrue(chessBoard.Cells[2, 3].IsEmpty);
		}

		[Test]
		public void Pawn_Move_Diagonal_Right_Throws_Exception_If_Already_Captured()
		{
			int i = 1;
			while (i < 5)
			{
				blackPawn1.TryMoveForward(1);
				i++;
			}

			Assert.IsTrue(blackPawn1.TryMoveDiagonalRightForward(1));
			Assert.IsTrue(whitePawn2.IsCaptured);

			Assert.Throws<InvalidOperationException>(() => whitePawn2.TryMoveDiagonalRightForward(1));
		}
	}
}
