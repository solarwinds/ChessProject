# ChessProject - Java Solution Notes

## Modifications to `Pawn` Class
* `Pawn` class has been subclassed from a new abstract `ChessPiece` base class, to facilitate future addition of other piece types.
*  Reference to `ChessBoard` from pawn/piece class has been removed to avoid circular dependency between these. (If knowledge of the board is necessary for a piece in future for some reason, could use some kind of observer/callback interface to avoid circular dependency)
* Helper method added to base class to translate absolute co-ords to left/forward relative moves to allow identical checking for black and white pieces.

## Modifications to `ChessBoard` Class
*  An overloaded `Add` method has been added, making use of piece codes and the new `ChessPieceFactory` to allow addition of pieces to the board without caller creating the piece first. This is intended as a convenience for callers such as UIs or setting up initial board state (e.g. to recreate famous games as per spec).
*  `Move` method has been added, which takes player colour and current and new board co-ordinates, and forwards the move to the appropriate piece as either a move or capture depending on the current board state.
`toString` method has been added to render a board as an 8x8 grid, with character codes for pieces. This aids visualization for debugging and checking of board states for testing.
*  These three methods are intended as the start of an API for a caller, allowing boards to be set up, played, and rendered, at least in some fashion, for pawns. Future additions could include parsing board set up from a text representation and parsing a list of moves in chess algebraic notation.
*  The constants indicating board height and width have been changed to 8 for more idiomatic Java usage of array allocation and indexing (0 <= x < n)
*  The element type of the board is now `ChessPiece` rather than `Pawn`

## Modifications to tests and existing code
  * These are detailed in the code



# ChessProject - Java

Pretend that you taking over a software project that has only just started and may be on the wrong track. The project’s long-term goals are to build a fully functional chess game and you have been hired to pick up where others have left off.  You won’t be writing it all at once and the first sprint will have a narrow focus on some basic functionality of the Chessboard and some simple movements of a Pawn.

All simulations take place on a chess board (class name: `ChessBoard`) that is a grid consisting of length X, and height Y – both of which are integers.  Chess pieces can be placed on the board at a given (x,y) coordinate pair with (0, 0) being in the lower left-hand corner of the board, and (7, 7) being in the top right hand corner of the board, as seen in the following illustration:

![alt text](http://www.chessvariants.org/d.chess/startup.gif)

Pieces are either Black or White.  Black pieces typically start at row x=7 and x=6, whereas white pieces typically start at rows x=0 and x=1.  That said, you can set up a board with many initial configurations to replay famous chess games (that last bit might be a paradox).  
Additionally, Pieces can be given two commands: move and capture (we will ignore capture for this exercise).  Each piece has unique movements, but we are going to focus on commands for pawns.  For our limited implementation, Pawns can only more forward one space (toward their opponents side of the board) and can only capture in a forward and diagonal direction as seen in the next illustration.

![alt text](http://www.chessvariants.org/d.chess/pawnmove.gif)

Your task is to get all unit tests found under the Tests folder passing. Since you plan to be on the project long term, think about how you would implement the solution, what other test coverage might be necessary and what you would do to make future features easier to implement.

Good luck, and please reach out to us if you have any questions!
