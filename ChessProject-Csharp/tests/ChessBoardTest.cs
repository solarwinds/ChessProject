using NUnit.Framework;
using System;
using System.Collections.Generic;

namespace SolarWinds.MSP.Chess
{
    [TestFixture]
    public class ChessBoardTest
    {
        private ChessBoard chessBoard;
        private ChessPieceCreationFactory factory;

        [SetUp]
        public void SetUp()
        {
            factory = new ChessPieceCreationFactory();
            chessBoard = new ChessBoard(new EastSide(), factory);
        }

        [Test]
        public void Has_MaxBoardWidth_of_7()
        {
            Assert.AreEqual(ChessBoard.MaxBoardWidth, 7);
        }

        [Test]
        public void Has_MaxBoardHeight_of_7()
        {
            Assert.AreEqual(ChessBoard.MaxBoardHeight, 7);
        }

        [Test]
        public void IsLegalBoardPosition_True_X_equals_0_Y_equals_0()
        {
            var isValidPosition = chessBoard.IsLegalBoardPosition(new Position(0, 0));
            Assert.IsTrue(isValidPosition);
        }

        [Test]
        public void IsLegalBoardPosition_True_X_equals_5_Y_equals_5()
        {
            var isValidPosition = chessBoard.IsLegalBoardPosition(new Position(5, 5));
            Assert.IsTrue(isValidPosition);
        }

        [Test]
        public void IsLegalBoardPosition_False_X_equals_11_Y_equals_5()
        {
            var isValidPosition = chessBoard.IsLegalBoardPosition(new Position(11, 5));
            Assert.IsFalse(isValidPosition);
        }

        [Test]
        public void IsLegalBoardPosition_False_X_equals_0_Y_equals_9()
        {
            var isValidPosition = chessBoard.IsLegalBoardPosition(new Position(0, 9));
            Assert.IsFalse(isValidPosition);
        }

        [Test]
        public void IsLegalBoardPosition_False_X_equals_11_Y_equals_0()
        {
            var isValidPosition = chessBoard.IsLegalBoardPosition(new Position(11, 0));
            Assert.IsFalse(isValidPosition);
        }

        [Test]
        public void IsLegalBoardPosition_False_For_Negative_X_Values()
        {
            var isValidPosition = chessBoard.IsLegalBoardPosition(new Position(-1, 5));
            Assert.IsFalse(isValidPosition);
        }

        [Test]
        public void IsLegalBoardPosition_False_For_Negative_Y_Values()
        {
            var isValidPosition = chessBoard.IsLegalBoardPosition(new Position(5, -1));
            Assert.IsFalse(isValidPosition);
        }

        public static IEnumerable<TestCaseData> InvalidPositionTestCases
        {
            get
            {
                Direction north = new NorthSide();

                yield return new TestCaseData(north, new Position(-1, 1)).SetName("{m} (North Direction - Negative X-Coordinate)");
                yield return new TestCaseData(north, new Position(1, -1)).SetName("{m} (North Direction - Negative Y-Coordinate)");
                yield return new TestCaseData(north, new Position(8, 1)).SetName("{m} (North Direction - X-Coordinate Outside Bounds)");
                yield return new TestCaseData(north, new Position(4, 8)).SetName("{m} (North Direction - Y-Coordinate Outside Bounds)");
                yield return new TestCaseData(north, new Position(2, 6)).SetName("{m} (North Direction - Coordinates within bound but invalid)");

                Direction south = new SouthSide();

                yield return new TestCaseData(south, new Position(-4, 6)).SetName("{m} (South Direction - Negative X-Coordinate)");
                yield return new TestCaseData(south, new Position(3, -2)).SetName("{m} (South Direction - Negative Y-Coordinate)");
                yield return new TestCaseData(south, new Position(8, 6)).SetName("{m} (South Direction - X-Coordinate Outside Bounds)");
                yield return new TestCaseData(south, new Position(2, 8)).SetName("{m} (South Direction - Y-Coordinate Outside Bounds)");
                yield return new TestCaseData(south, new Position(6, 1)).SetName("{m} (South Direction - Coordinates within bound but invalid)");

                Direction east = new EastSide();

                yield return new TestCaseData(east, new Position(-1, 2)).SetName("{m} (East Direction - Negative X-Coordinate)");
                yield return new TestCaseData(east, new Position(1, -3)).SetName("{m} (East Direction - Negative Y-Coordinate)");
                yield return new TestCaseData(east, new Position(9, 4)).SetName("{m} (East Direction - X-Coordinate Outside Bounds)");
                yield return new TestCaseData(east, new Position(1, 8)).SetName("{m} (East Direction - Y-Coordinate Outside Bounds)");
                yield return new TestCaseData(east, new Position(6, 2)).SetName("{m} (East Direction - Coordinates within bound but invalid)");

                Direction west = new WestSide();

                yield return new TestCaseData(west, new Position(-1, 4)).SetName("{m} (West Direction - Negative X-Coordinate)");
                yield return new TestCaseData(west, new Position(6, -1)).SetName("{m} (West Direction - Negative Y-Coordinate)");
                yield return new TestCaseData(west, new Position(8, 2)).SetName("{m} (West Direction - X-Coordinate Outside Bounds)");
                yield return new TestCaseData(west, new Position(6, 8)).SetName("{m} (West Direction - Y-Coordinate Outside Bounds)");
                yield return new TestCaseData(west, new Position(1, 5)).SetName("{m} (West Direction - Coordinates within bound but invalid)");
            }
        }

        [Test, TestCaseSource(nameof(InvalidPositionTestCases))]
        public void PlacePawn_ThrowsException_For_Invalid_Positions(Direction direction, Position position)
        {
            chessBoard = new ChessBoard(direction, factory);
            Assert.Throws<InvalidOperationException>(() => chessBoard.PlacePawn(position, PieceColor.White));
        }

        [Test]
        public void PlacePawn_Avoids_Duplicate_Positioning()
        {
            Position position = new Position(6, 2);

            Assert.DoesNotThrow(() => chessBoard.PlacePawn(position, PieceColor.Black));
            Assert.Throws<InvalidOperationException>(() => chessBoard.PlacePawn(position, PieceColor.Black));
        }

        [Test]
        public void PlacePawn_Correctly_Updates_Position()
        {
            Assert.IsTrue(chessBoard.Cells[6, 2].IsEmpty);

            Position position = new Position(6, 2);
            IChessPiecePawn pawn = chessBoard.PlacePawn(position, PieceColor.Black);

            Assert.IsFalse(chessBoard.Cells[6, 2].IsEmpty);
            Assert.AreEqual(position, pawn.CurrentPosition);
        }

        //Note: This is already tested by other tests
        [Test]
        public void PlacePawn_Limits_The_Number_Of_Pawns()
        {
            for (int i = 0; i < 10; i++)
            {
                int row = i / ChessBoard.MaxBoardWidth;
                Position position = new Position(6 + row, i % ChessBoard.MaxBoardWidth);

                if (row < 1)
                {
                    IChessPiecePawn pawn = chessBoard.PlacePawn(position, PieceColor.Black);
                    Assert.AreEqual(position, pawn.CurrentPosition);
                }
                else
                {
                    Assert.Throws<InvalidOperationException>(() => chessBoard.PlacePawn(position, PieceColor.Black));
                }
            }
        }
    }
}
