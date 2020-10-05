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
	public class AvailablePiecesTest
	{
		private AvailablePieces availablePieces;
		private AvailablePieces availablePiecesCustom;

        [SetUp]
		public void SetUp()
		{
			availablePieces = new AvailablePieces();
			availablePiecesCustom = new AvailablePieces(blackPawn: 1);
		}

        [Test]
		public void Creates_Appropriate_Default_Piece_Count()
		{
			Assert.AreEqual(8, availablePieces.Black.Pawn);
			Assert.AreEqual(8, availablePieces.White.Pawn);
			Assert.AreEqual(2, availablePieces.Black.Rook);
			Assert.AreEqual(2, availablePieces.White.Rook);
			Assert.AreEqual(2, availablePieces.Black.Knight);
			Assert.AreEqual(2, availablePieces.White.Knight);
			Assert.AreEqual(2, availablePieces.Black.Bishop);
			Assert.AreEqual(2, availablePieces.White.Bishop);
			Assert.AreEqual(1, availablePieces.Black.Queen);
			Assert.AreEqual(1, availablePieces.White.Queen);
			Assert.AreEqual(1, availablePieces.Black.King);
			Assert.AreEqual(1, availablePieces.White.King);

		}

		[Test]
		public void Creates_Appropriate_Custom_Piece_Count()
		{
			Assert.AreEqual(1, availablePiecesCustom.Black.Pawn);
			Assert.AreEqual(8, availablePiecesCustom.White.Pawn);
			Assert.AreEqual(2, availablePiecesCustom.Black.Rook);
			Assert.AreEqual(2, availablePiecesCustom.White.Rook);
			Assert.AreEqual(2, availablePiecesCustom.Black.Knight);
			Assert.AreEqual(2, availablePiecesCustom.White.Knight);
			Assert.AreEqual(2, availablePiecesCustom.Black.Bishop);
			Assert.AreEqual(2, availablePiecesCustom.White.Bishop);
			Assert.AreEqual(1, availablePiecesCustom.Black.Queen);
			Assert.AreEqual(1, availablePiecesCustom.White.Queen);
			Assert.AreEqual(1, availablePiecesCustom.Black.King);
			Assert.AreEqual(1, availablePiecesCustom.White.King);

		}

        [Test]
		public void Decrement_True_Appropriately_Decriments_Piece_Count()
		{
			Assert.AreEqual(8, availablePieces.White.Pawn);
			Assert.IsTrue(availablePieces.Decrement("White", "Pawn"));
			Assert.AreEqual(7, availablePieces.White.Pawn);
			Assert.AreEqual(1, availablePiecesCustom.Black.Queen);
			Assert.IsTrue(availablePiecesCustom.Decrement("Black", "Queen"));
			Assert.AreEqual(0, availablePiecesCustom.Black.Queen);
		}

		[Test]
		public void Decrement_False_Will_Not_Pass_0()
		{
			Assert.AreEqual(1, availablePiecesCustom.Black.Pawn);
			Assert.IsTrue(availablePiecesCustom.Decrement("Black", "Pawn"));
			Assert.AreEqual(0, availablePiecesCustom.Black.Pawn);
			Assert.IsFalse(availablePiecesCustom.Decrement("Black", "Pawn"));
			Assert.AreEqual(0, availablePiecesCustom.Black.Pawn);
		}

		[Test]
		public void Decrement_Error_Bad_Color()
		{
			Assert.Throws<ArgumentException>(() => availablePieces.Decrement("Grey", "Pawn"));
		}

		[Test]
		public void Decrement_Error_Bad_Piece_Type()
		{
			Assert.Throws<ArgumentException>(() => availablePieces.Decrement("Black", "Prawn"));
		}
	}
}
