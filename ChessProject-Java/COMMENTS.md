# ChessProject - Commentary

## Commit Summary

The initial commit focused purely on getting the unit tests passing. Further commits aimed to 
introduce a little more functionality around the move() operation. 

Refactoring was performed 
on a continuous basis in order to keep the production and test code fairly clean. 

An abstract class 
and a further (derived) piece (King) was added to suggest the way forward for the introduction of 
all other chess pieces and explore other aspects of the move() operation.
 
## Design Considerations

* We should use exceptions for invalid Piece placement rather than setting (-1,-1) coordinates. In retrospect, 
we should have fixed this a little earlier.
* Piece knows too much about ChessBoard. Consider removing/reducing references to the board from Piece
* The ChessBoard could query each piece in order to find out how it moves
* The ChessBoard could decide whether a proposed move is legal
* It may be better that we ask the board to move the piece
* Design might be cleaner if we have an 8x8 grid of Square objects, each of which potentially contains a Piece
* The PieceColor parameter in ChessBoard.add() seems redundant

## Suggestions for implementation of remaining features

* It's probably worth pulling the move/capture evaluation out into a separate class. This will 
make it less awkward to test and help reduce complexity in the ChessBoard class
* The ChessBoard will ultimately need a more generalised way to store and track the Pieces placed upon it. 
Maybe a set of objects, each of which contains a Piece and a count.
e.g. [Bishop, 2] and an associated maximum count for each Piece
* Consider a Factory pattern for making pieces
* Consider a Strategy pattern for move/capture logic
* Consider a Command pattern for recording/undoing moves

## Future Tests

* Need to handle check / discovered check / checkmate
* Need to handle en passant
* Need to handle pawn promotion
* Need to handle castling
* Future Pieces can move across multiple squares
* Knights can jump over other Pieces
* Feed in real historical games as a more rigorous (integration) test

## Other Thoughts

* We call a Knight a Horse to conveniently avoid a letter clash with King! :-)
* Refactor to more domain-specific (chess) terms. e.g. RANK and FILE rather than X,Y
* Also consider mapping (X,Y) coordinates to standard chess notation (E2-E4) 
* Refactor the slightly smelly PawnTest code
* Consider more test helper methods. e.g. to surround a piece with other pieces to present obstacles to movement
* The board should know what occupies each square in a normal game starting position
* Chess boards are 8x8, no need for width and height constants (if anything, SIZE=8)





