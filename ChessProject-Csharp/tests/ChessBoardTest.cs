﻿using NUnit.Framework;

namespace SolarWinds.MSP.Chess
{
	// These tests aren't using the IChessPieceContainer interface yet.
	// Should refactor to turn these into proper unit tests, rather than the current integration test feel
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
		public void Has_MaxBoardWidth_of_8()
		{
			Assert.AreEqual(ChessBoard.MaxBoardWidth, 8);
		}

        [Test]
		public void Has_MaxBoardHeight_of_8()
		{
			Assert.AreEqual(ChessBoard.MaxBoardHeight, 8);
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
			Pawn firstPawn = new Pawn(PieceColor.Black);
			Pawn secondPawn = new Pawn(PieceColor.Black);
			chessBoard.Add(firstPawn, 6, 6);
			chessBoard.Add(secondPawn, 6, 6);
			Assert.AreEqual(firstPawn.XCoordinate, 6);
            Assert.AreEqual(firstPawn.YCoordinate, 6);
            Assert.AreEqual(secondPawn.XCoordinate, -1);
            Assert.AreEqual(secondPawn.YCoordinate, -1);
		}

        [Test]
		public void Limits_The_Number_Of_Pawns()
		{
			for (int i = 0; i < 10; i++)
			{
				Pawn pawn = new Pawn(PieceColor.Black);
				int row = i / ChessBoard.MaxBoardWidth;
				chessBoard.Add(pawn, i % ChessBoard.MaxBoardWidth, 6 + row);
				if (row < 1)
				{
					Assert.AreEqual(pawn.XCoordinate, i % ChessBoard.MaxBoardWidth);
					Assert.AreEqual(pawn.YCoordinate, (6 + row));
				}
				else
				{
					Assert.AreEqual(pawn.XCoordinate, -1);
                    Assert.AreEqual(pawn.YCoordinate, -1);
				}
			}
		}
	}
}
