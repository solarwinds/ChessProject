using NUnit.Framework;
using src;
using src.Enums;
using src.Pieces;

namespace tests
{
    [TestFixture]
    public class PawnTest
    {
        private ChessBoard _chessBoard;
        private Pawn _pawnBlack;
        private Pawn _pawnWhite;

        [SetUp]
        public void SetUp()
        {
            _chessBoard = new ChessBoard();
            _pawnBlack = new Pawn(PieceColor.Black);
            _pawnWhite = new Pawn(PieceColor.White);
        }

        [Test]
        public void ChessBoard_Add_Sets_XCoordinate()
        {
            _chessBoard.AddPiece(_pawnBlack, 6, 3);
            Assert.AreEqual(6, _pawnBlack.XCoordinate);
        }

        [Test]
        public void ChessBoard_Add_Sets_YCoordinate()
        {
            _chessBoard.AddPiece(_pawnBlack, 6, 3);
            Assert.AreEqual(3, _pawnBlack.YCoordinate);
        }

        [Test]
        public void PawnBlack_Move_IllegalCoordinates_Right_DoesNotMove()
        {
            _chessBoard.AddPiece(_pawnBlack, 6, 3);
            Assert.IsFalse(_pawnBlack.Move(MovementType.Move, 7, 3));
            Assert.AreEqual(6, _pawnBlack.XCoordinate);
            Assert.AreEqual(3, _pawnBlack.YCoordinate);
        }

        [Test]
        public void PawnBlack_Move_IllegalCoordinates_Left_DoesNotMove()
        {
            _chessBoard.AddPiece(_pawnBlack, 6, 3);
            Assert.IsFalse(_pawnBlack.Move(MovementType.Move, 4, 3));
            Assert.AreEqual(6, _pawnBlack.XCoordinate);
            Assert.AreEqual(3, _pawnBlack.YCoordinate);
        }

        [Test]
        public void PawnBlack_Move_LegalCoordinates_Forward_UpdatesCoordinates()
        {
            _chessBoard.AddPiece(_pawnBlack, 6, 3);
            Assert.IsTrue(_pawnBlack.Move(MovementType.Move, 6, 2));
            Assert.AreEqual(6, _pawnBlack.XCoordinate);
            Assert.AreEqual(2, _pawnBlack.YCoordinate);
        }

        [Test]
        public void PawnBlack_Move_IllegalCoordinates_Backwards_DoesNotMove()
        {
            _chessBoard.AddPiece(_pawnBlack, 6, 3);
            Assert.False(_pawnBlack.Move(MovementType.Move, 6, 4));
            Assert.AreEqual(6, _pawnBlack.XCoordinate);
            Assert.AreEqual(3, _pawnBlack.YCoordinate);
        }

        [Test]
        public void PawnWhite_Move_LegalCoordinates_Forward_UpdatesCoordinates()
        {
            _chessBoard.AddPiece(_pawnWhite, 6, 3);
            Assert.IsTrue(_pawnWhite.Move(MovementType.Move, 6, 4));
            Assert.AreEqual(6, _pawnWhite.XCoordinate);
            Assert.AreEqual(4, _pawnWhite.YCoordinate);
        }

        [Test]
        public void PawnWhite_Move_IllegalCoordinates_Backwards_DoesNotMove()
        {
            _chessBoard.AddPiece(_pawnWhite, 6, 3);
            Assert.False(_pawnWhite.Move(MovementType.Move, 6, 2));
            Assert.AreEqual(6, _pawnWhite.XCoordinate);
            Assert.AreEqual(3, _pawnWhite.YCoordinate);
        }

        [Test]
        public void PawnWhite_Move_IllegalCoordinates_Left_DoesNotMove()
        {
            _chessBoard.AddPiece(_pawnWhite, 6, 3);
            Assert.IsFalse(_pawnWhite.Move(MovementType.Move, 4, 3));
            Assert.AreEqual(6, _pawnWhite.XCoordinate);
            Assert.AreEqual(3, _pawnWhite.YCoordinate);
        }

        [Test]
        public void PawnWhite_Move_IllegalCoordinates_Right_DoesNotMove()
        {
            _chessBoard.AddPiece(_pawnWhite, 6, 3);
            Assert.IsFalse(_pawnWhite.Move(MovementType.Move, 7, 3));
            Assert.AreEqual(6, _pawnWhite.XCoordinate);
            Assert.AreEqual(3, _pawnWhite.YCoordinate);
        }
    }
}
