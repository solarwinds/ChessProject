using NUnit.Framework;
using System;

/*
Things to test:
- Cannot move past another piece (unless a Knight)
- Cannot move off the board
- Cannot Capture off the board
- Cannot perform invalid move
- Cannot perform invalid capture
- Can create custom board setup
- Cannot create a custom invalid board (size/dimensions)
- Can have multiple piece types on the board
- Can add multiple kinds of pieces to the board
*/

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
			Assert.AreEqual(7, ChessBoard.MaxBoardWidth);
		}

        [Test]
		public void Has_MaxBoardHeight_of_7()
		{
			Assert.AreEqual(7, ChessBoard.MaxBoardHeight);
		}

        [Test]
		public void IsLegalBoardPosition_True_X_equals_0_Y_equals_0()
		{
			var isValidPosition = chessBoard.IsLegalBoardPosition(0, 0);
			Assert.IsTrue(isValidPosition);
		}

        [Test]
		public void IsLegalBoardPosition_True_X_equals_5_Y_equals_5()
		{
			var isValidPosition = chessBoard.IsLegalBoardPosition(5, 5);
            Assert.IsTrue(isValidPosition);
		}

        [Test]
		public void IsLegalBoardPosition_False_X_equals_11_Y_equals_5()
		{
			var isValidPosition = chessBoard.IsLegalBoardPosition(11, 5);
            Assert.IsFalse(isValidPosition);
		}

        [Test]
		public void IsLegalBoardPosition_False_X_equals_0_Y_equals_9()
		{
			var isValidPosition = chessBoard.IsLegalBoardPosition(0, 9);
            Assert.IsFalse(isValidPosition);
		}

        [Test]
		public void IsLegalBoardPosition_False_X_equals_11_Y_equals_0()
		{
			var isValidPosition = chessBoard.IsLegalBoardPosition(11, 0);
            Assert.IsFalse(isValidPosition);
		}

        [Test]
		public void IsLegalBoardPosition_False_For_Negative_X_Values()
		{
			var isValidPosition = chessBoard.IsLegalBoardPosition(-1, 5);
            Assert.IsFalse(isValidPosition);
		}

        [Test]
		public void IsLegalBoardPosition_False_For_Negative_Y_Values()
		{
			var isValidPosition = chessBoard.IsLegalBoardPosition(5, -1);
            Assert.IsFalse(isValidPosition);
		}

        [Test]
		public void Avoids_Duplicate_Positioning()
		{
			Pawn firstPawn = new Pawn(PieceColor.Black, MovementDirection.Negative);
			Pawn secondPawn = new Pawn(PieceColor.Black, MovementDirection.Negative);
			chessBoard.Add(firstPawn, 6, 3);
			try 
			{
				chessBoard.Add(secondPawn, 6, 3);
				Assert.Fail("Chessboard was able to add a piece to an already-occupied position.");
			} 
			catch (DuplicatePositioningException)
			{/* pass */}
			Assert.AreEqual(6, firstPawn.XCoordinate);
            Assert.AreEqual(3, firstPawn.YCoordinate);
            Assert.AreEqual(-1, secondPawn.XCoordinate);
            Assert.AreEqual(-1, secondPawn.YCoordinate);
		}

		[Test]
		public void Avoids_Invalid_Positioning()
		{
			Pawn firstPawn = new Pawn(PieceColor.Black, MovementDirection.Negative);
			Pawn secondPawn = new Pawn(PieceColor.Black, MovementDirection.Negative);
			chessBoard.Add(firstPawn, 6, 3);
			try 
			{
				chessBoard.Add(secondPawn, 10, 3);
				Assert.Fail("Chessboard was able to add a piece to an invalid position.");
			} 
			catch (InvalidPositioningException)
			{/* pass */}
			Assert.AreEqual(6, firstPawn.XCoordinate);
            Assert.AreEqual(3, firstPawn.YCoordinate);
            Assert.AreEqual(-1, secondPawn.XCoordinate);
            Assert.AreEqual(-1, secondPawn.YCoordinate);
		}

		[Test]
		public void IsPieceAvailable_Fails_When_Unavailable()
		{
			Pawn pawn = new Pawn(PieceColor.Black, MovementDirection.Negative);
			for (int i = 0; i < 10; i++){
				if (i < 8)
				{
					Assert.IsTrue(chessBoard.IsPieceAvailable(pawn));
					chessBoard.PieceCounts.Decrement(pawn);
				} else {
					Assert.IsFalse(chessBoard.IsPieceAvailable(pawn));
				}
			}
		}

		[Test]
		public void Move_Updates_Board_And_Piece_Coordinates()
		{
			Pawn pawn = new Pawn(PieceColor.White, MovementDirection.Positive);
			chessBoard.Add(pawn, 1, 3);
			Assert.AreEqual(1, pawn.XCoordinate);
            Assert.AreEqual(3, pawn.YCoordinate);
			Assert.AreEqual(chessBoard.pieces[1,3], pawn);
            chessBoard.Move(pawn, 1, 4, MovementType.Move);
			Assert.AreEqual(chessBoard.pieces[1,4], pawn);
			Assert.AreEqual(1, pawn.XCoordinate);
            Assert.AreEqual(4, pawn.YCoordinate);
			
		}

		[Test]
		public void Move_True_When_Legal_Available_Position()
		{
			Pawn pawn = new Pawn(PieceColor.White, MovementDirection.Positive);
			chessBoard.Add(pawn, 1, 3);
			Assert.AreEqual(1, pawn.XCoordinate);
            Assert.AreEqual(3, pawn.YCoordinate);
			Assert.AreEqual(chessBoard.pieces[1,3], pawn);
            Assert.IsTrue(chessBoard.Move(pawn, 1, 4, MovementType.Move));
			Assert.AreEqual(chessBoard.pieces[1,4], pawn);
			Assert.AreEqual(1, pawn.XCoordinate);
            Assert.AreEqual(4, pawn.YCoordinate);
		}

		[Test]
		public void Move_False_When_Legal_Position_Occupied_By_Diff_Color_Move()
		{
			Pawn pawn = new Pawn(PieceColor.White, MovementDirection.Positive);
			Pawn oppPawn = new Pawn(PieceColor.Black, MovementDirection.Negative);
			chessBoard.Add(pawn, 1, 3);
			chessBoard.Add(oppPawn, 1, 4);
			Assert.AreEqual(1, pawn.XCoordinate);
            Assert.AreEqual(3, pawn.YCoordinate);
			Assert.AreEqual(chessBoard.pieces[1,3], pawn);
            Assert.IsFalse(chessBoard.Move(pawn, 1, 4, MovementType.Move));
			Assert.AreEqual(chessBoard.pieces[1,3], pawn);
			Assert.AreEqual(1, pawn.XCoordinate);
            Assert.AreEqual(3, pawn.YCoordinate);
		}

		[Test]
		public void Move_False_When_Legal_Occupied_Position_Same_Color()
		{
			Pawn pawn = new Pawn(PieceColor.White, MovementDirection.Positive);
			Pawn oppPawn = new Pawn(PieceColor.White, MovementDirection.Positive);
			chessBoard.Add(pawn, 1, 3);
			chessBoard.Add(oppPawn, 1, 4);
			Assert.AreEqual(1, pawn.XCoordinate);
            Assert.AreEqual(3, pawn.YCoordinate);
			Assert.AreEqual(chessBoard.pieces[1,3], pawn);
            Assert.IsFalse(chessBoard.Move(pawn, 1, 4, MovementType.Move));
			Assert.AreEqual(chessBoard.pieces[1,3], pawn);
			Assert.AreEqual(1, pawn.XCoordinate);
            Assert.AreEqual(3, pawn.YCoordinate);
		}

		[Test]
		public void Move_False_When_Illegal_Position()
		{
			Pawn pawn = new Pawn(PieceColor.White, MovementDirection.Positive);
			chessBoard.Add(pawn, 1, 7);
			Assert.AreEqual(1, pawn.XCoordinate);
            Assert.AreEqual(7, pawn.YCoordinate);
			Assert.AreEqual(chessBoard.pieces[1,7], pawn);
            Assert.IsFalse(chessBoard.Move(pawn, 1, 8, MovementType.Move));
			Assert.AreEqual(chessBoard.pieces[1,7], pawn);
			Assert.AreEqual(1, pawn.XCoordinate);
            Assert.AreEqual(7, pawn.YCoordinate);
			
		}

        [Test]
		public void Add_Limits_The_Number_Of_Pawns()
		{
			for (int i = 0; i < 10; i++)
			{
				Pawn pawn = new Pawn(PieceColor.Black, MovementDirection.Negative);
				int row = i / (ChessBoard.MaxBoardWidth + 1);
				int col = i % (ChessBoard.MaxBoardWidth + 1);
				Console.WriteLine("Row: {0}, Col: {1}", row, col);
				try 
				{
					chessBoard.Add(pawn, row, col);
					Assert.AreEqual(row, pawn.XCoordinate);
					Assert.AreEqual(col, pawn.YCoordinate);
				} 
				catch (UnavailablePieceException)
				{
					if (i > 7)
					{
						Assert.AreEqual(-1, pawn.XCoordinate);
						Assert.AreEqual(-1, pawn.YCoordinate);
					}
					else
					{
						throw new Exception("Chessboard unable to add correct number of pawns.");
					}
				}
			}
		}
	}
}
