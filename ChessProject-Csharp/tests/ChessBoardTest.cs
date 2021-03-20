using NUnit.Framework;
using src;
using src.Enums;
using src.Pieces;
using System;

namespace tests
{
    [TestFixture]
    public class ChessBoardTest
    {
        private ChessBoard _chessBoard;

        [SetUp]
        public void SetUp()
        {
            _chessBoard = new ChessBoard();
        }

        [Test]
        public void Has_MaxBoardWidth_of_8()
        {
            Assert.AreEqual(8, ChessBoard.MaxBoardWidth);
        }

        [Test]
        public void Has_MaxBoardHeight_of_8()
        {
            Assert.AreEqual(8, ChessBoard.MaxBoardHeight);
        }

        [Test]
        public void IsLegalBoardPosition_True()
        {
            Assert.IsTrue(_chessBoard.IsLegalBoardPosition(0, 0));
            Assert.IsTrue(_chessBoard.IsLegalBoardPosition(5, 5));
        }


        [Test]
        public void IsLegalBoardPosition_False()
        {
            Assert.IsFalse(_chessBoard.IsLegalBoardPosition(11, 5));
            Assert.IsFalse(_chessBoard.IsLegalBoardPosition(0, 9));
            Assert.IsFalse(_chessBoard.IsLegalBoardPosition(11, 0));
        }


        [Test]
        public void IsLegalBoardPosition_False_For_Negative_Values()
        {
            Assert.IsFalse(_chessBoard.IsLegalBoardPosition(-1, 5));
            Assert.IsFalse(_chessBoard.IsLegalBoardPosition(5, -1));
        }


        [Test]
        public void IsEmptySquare_True()
        {
            Assert.IsTrue(_chessBoard.IsSquareEmpty(5, 5));
            Assert.IsTrue(_chessBoard.IsSquareEmpty(1, 7));
        }

        [Test]
        public void IsEmptySquare_False()
        {
            Pawn pawn = new Pawn(PieceColor.Black);
            Assert.IsTrue(_chessBoard.AddPiece(pawn, 5, 5));
            Assert.IsFalse(_chessBoard.IsSquareEmpty(5, 5));
        }

        [Test]
        public void Chessboard_Pieces_Count_Set_To_Zero()
        {
            foreach (PieceType type in Enum.GetValues(typeof(PieceType)))
            {
                foreach (PieceColor color in Enum.GetValues(typeof(PieceColor)))
                {
                    Assert.AreEqual(0, _chessBoard.PieceCount[(type, color)]);
                }
            }
        }

        [Test]
        public void Avoids_Duplicate_Positioning()
        {
            var firstPawn = new Pawn(PieceColor.Black);
            var secondPawn = new Pawn(PieceColor.Black);
            Assert.IsTrue(_chessBoard.AddPiece(firstPawn, 6, 3));
            Assert.IsFalse(_chessBoard.AddPiece(secondPawn, 6, 3));
        }


        [Test]
        public void Increments_And_Limits_The_Number_Of_Pawns()
        {
            for (var i = 0; i < 10; i++)
            {
                var pawn = new Pawn(PieceColor.Black);
                var row = i / ChessBoard.MaxBoardWidth;

                if (row < 1)
                {
                    Assert.IsTrue(_chessBoard.AddPiece(pawn, 6 + row, i % ChessBoard.MaxBoardWidth));
                    Assert.AreEqual(i + 1, _chessBoard.PieceCount[(pawn.PieceType, pawn.PieceColor)]);
                }
                else
                {
                    Assert.IsFalse(_chessBoard.AddPiece(pawn, 6 + row, i % ChessBoard.MaxBoardWidth));
                    Assert.AreEqual(8, _chessBoard.PieceCount[(pawn.PieceType, pawn.PieceColor)]);
                }
            }
        }

        [Test]
        public void Capture_Move_Removes_Existing_Piece()
        {
            var firstPawn = new Pawn(PieceColor.Black);
            var secondPawn = new Pawn(PieceColor.White);
            Assert.IsTrue(_chessBoard.AddPiece(firstPawn, 6, 4));
            Assert.IsTrue(_chessBoard.AddPiece(secondPawn, 6, 3));
            Assert.IsTrue(_chessBoard.MovePiece(firstPawn, 6, 3, MovementType.Capture));
            Assert.AreEqual(0, _chessBoard.PieceCount[(PieceType.Pawn, PieceColor.White)]);
        }

        [Test]
        public void Capture_Move_Does_Not_Removes_Existing_Piece()
        {
            var firstPawn = new Pawn(PieceColor.Black);
            var secondPawn = new Pawn(PieceColor.White);
            Assert.IsTrue(_chessBoard.AddPiece(firstPawn, 6, 4));
            Assert.IsTrue(_chessBoard.AddPiece(secondPawn, 6, 2));
            Assert.IsTrue(_chessBoard.MovePiece(firstPawn, 6, 3, MovementType.Capture));
            Assert.AreEqual(1, _chessBoard.PieceCount[(PieceType.Pawn, PieceColor.White)]);
        }
    }
}
