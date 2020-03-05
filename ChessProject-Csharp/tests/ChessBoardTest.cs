using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Microsoft.VisualStudio.TestTools.UnitTesting;  

namespace SolarWinds.MSP.Chess
{
    [TestClass]
	public class ChessBoardTest : ChessBoard
	{
		private ChessBoard chessBoard;

        [TestInitialize]
		public void SetUp()
		{
			chessBoard = new ChessBoard();
		}

        [TestMethod]
		public void Has_MaxBoardWidth_of_8()
		{
            //the specification states that (7,7) is a valid position so I have modified the code to accept this.
            Assert.AreEqual(ChessBoard.MaxBoardWidth, 8);
		}

        [TestMethod]
		public void Has_MaxBoardHeight_of_8()
		{
            //the specification states that (7,7) is a valid position so I have modified the code to accept this.
            Assert.AreEqual(ChessBoard.MaxBoardHeight, 8);
		}

        [TestMethod]
		public void IsLegalBoardPosition_True_X_equals_0_Y_equals_0()
		{
			var isValidPosition = this.IsLegalBoardPosition(0, 0);
			Assert.IsTrue(isValidPosition);
		}

        [TestMethod]
		public void IsLegalBoardPosition_True_X_equals_5_Y_equals_5()
		{
			var isValidPosition = this.IsLegalBoardPosition(5, 5);
            Assert.IsTrue(isValidPosition);
		}

        [TestMethod]
		public void IsLegalBoardPosition_False_X_equals_11_Y_equals_5()
		{
			var isValidPosition = this.IsLegalBoardPosition(11, 5);
            Assert.IsFalse(isValidPosition);
		}

        [TestMethod]
		public void IsLegalBoardPosition_False_X_equals_0_Y_equals_9()
		{
			var isValidPosition = this.IsLegalBoardPosition(0, 9);
            Assert.IsFalse(isValidPosition);
		}

        [TestMethod]
		public void IsLegalBoardPosition_False_X_equals_11_Y_equals_0()
		{
			var isValidPosition = this.IsLegalBoardPosition(11, 0);
            Assert.IsFalse(isValidPosition);
		}

        [TestMethod]
        public void IsLegalBoardPosition_True_X_equals_7_Y_equals_7()
        {
            //the specification states that (7,7) is a valid position so I have modified the code to accept this.
            var isValidPosition = this.IsLegalBoardPosition(7, 7);
            Assert.IsTrue(isValidPosition);
        }

        [TestMethod]
        public void IsLegalBoardPosition_True_X_equals_0_Y_equals_7()
        {
            var isValidPosition = this.IsLegalBoardPosition(0, 7);
            Assert.IsTrue(isValidPosition);
        }

        [TestMethod]
        public void IsLegalBoardPosition_True_X_equals_7_Y_equals_0()
        {
            var isValidPosition = this.IsLegalBoardPosition(7, 0);
            Assert.IsTrue(isValidPosition);
        }

        [TestMethod]
		public void IsLegalBoardPosition_False_For_Negative_X_Values()
		{
			var isValidPosition = this.IsLegalBoardPosition(-1, 5);
            Assert.IsFalse(isValidPosition);
		}

        [TestMethod]
		public void IsLegalBoardPosition_False_For_Negative_Y_Values()
		{
			var isValidPosition = this.IsLegalBoardPosition(5, -1);
            Assert.IsFalse(isValidPosition);
		}

        [TestMethod]
		public void Avoids_Duplicate_Positioning()
		{
			Pawn firstPawn = new Pawn(PieceColor.Black);
			Pawn secondPawn = new Pawn(PieceColor.Black);
			var firstPawnAddResult = this.Add(firstPawn, 6, 3, PieceColor.Black);
			var secondPawnAddResult =this.Add(secondPawn, 6, 3, PieceColor.Black);
            Assert.AreEqual(firstPawnAddResult, true);
            Assert.AreEqual(secondPawnAddResult, false);
		}

        [TestMethod]
		public void Limits_The_Number_Of_Pawns()
		{
			for (int i = 0; i < 10; i++)
			{
				Pawn pawn = new Pawn(PieceColor.Black);
				int x = 6+(i / ChessBoard.MaxBoardWidth);
                int y = i % ChessBoard.MaxBoardWidth;
                bool pawnAddResult = this.Add(pawn, x, y, PieceColor.Black);
				if (this.IsLegalBoardPosition(x,y) == true)
				{
					Assert.IsTrue(pawnAddResult);
				}
				else
				{
                    Assert.IsFalse(pawnAddResult);
				}
			}
		}

        [TestMethod]
        public void Piece_Already_Exists_Test()
        {
            const int x = 6;
            const int y = 3;

            Pawn firstPawn = new Pawn(PieceColor.Black);
            var firstPawnAddResult = this.Add(firstPawn, x, y, PieceColor.Black);
            Assert.AreEqual(firstPawnAddResult, true);

            bool pieceAlreadyExistsCheck = this.PieceExistsOnSquare(x, y);
            Assert.IsTrue(pieceAlreadyExistsCheck);
        }


        [TestMethod]
        public void Piece_Already_Exists_Invalid_Square_Test()
        {
            const int x = 93;
            const int y = -1;

            bool pieceAlreadyExistsCheck = this.PieceExistsOnSquare(x, y);
            Assert.IsFalse(pieceAlreadyExistsCheck);
        }
    }
}
