README

Preliminary Notes:

I am using the libraries org.junit.jupiter:junit-jupiter-api:5.6.0 and org.opentest4j:opentest4j:1.0.0-M1 for testing. I mainly did this to be able to use Assertions.assertThrow(), as this allowed me to see if the appropriate exceptions had been caught.

Design Choices:

- chess board of 8 by 8 (now using 1-8 rather than 0-7)
- replaced two dimensional Array with a HashMap: modern data structure that only stores pieces that are on the board, and no nulls
- board is set up with a default layout of pawns, but in future we can allow other, more custom setups to be implemented
- refactored the code to conform to standard Java coding standards, such as proper camel casing, variable naming, unit test method naming etc.	
- Piece class as superclass which is extended by all chess pieces; this allows for a structured future implementation of remaining chess pieces
- Piece class contains all logic pertaining to all chess pieces on the board
- Piece object is constructed with a colour, coordinates, and the chess board; in my take, a piece cannot exist without a set position on the chess board
- Chess Board contains all necessary information for each chess game, and can be readily replicated for multiple games
- white pawns are denoted using a lower case 'p', and black pawns use an uppcercase 'P'
- toString() and move() extended from Piece superclass and called polymorphically to allow other pieces to be implemented easily, with their own implementations of these methods
- static fields used for enforcing the number of pawns per colour would have to be modified to support multiple chess boards in the future

Testing:

- all 25 tests are passing
- 85% method coverage according to IntelliJ IDEA coverage runner; this would be extended given the scope of the objectives of the program
- equivalence partitioning to gain coverage of the test space
- additional tests have been added, such as tests checking if a pawn can be moved two squares forward or not, if a pawn can teleport across the board, and an array of other moves considered illegal
- some existing tests have been modified to conform to the new design choices, but the spirit of the tests has been maintained; the test "testLimits_The_Number_Of_Pawns()" in particular has been rewritten to work with my implementation, but also to generally simplify the test
- add() method throws exceptions, as these errors are regarded to be illegal board states
- move() method gives error messages that are designed to allow the user to change their actions
- tests relying on negative 1 (-1) have been replaced with assertFalse() assertions, taking in the respective method; pieces cannot be added to the chess board in illegal positions

Debugging Purposes:

- chess board is printed to the console for visual aid
- error messages printed to the console in order to better follow why certain actions cannot be performed
- example moves implemented within the main() method to visualise the movement of pieces



++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

# ChessProject - Java

Pretend that you taking over a software project that has only just started and may be on the wrong track. The project’s long-term goals are to build a fully functional chess game and you have been hired to pick up where others have left off.  You won’t be writing it all at once and the first sprint will have a narrow focus on some basic functionality of the Chessboard and some simple movements of a Pawn.

All simulations take place on a chess board (class name: `ChessBoard`) that is a grid consisting of length X, and height Y – both of which are integers.  Chess pieces can be placed on the board at a given (x,y) coordinate pair with (0, 0) being in the lower left-hand corner of the board, and (7, 7) being in the top right hand corner of the board, as seen in the following illustration:

![alt text](http://www.chessvariants.org/d.chess/startup.gif)

Pieces are either Black or White.  Black pieces typically start at row x=7 and x=6, whereas white pieces typically start at rows x=0 and x=1.  That said, you can set up a board with many initial configurations to replay famous chess games (that last bit might be a paradox).  
Additionally, Pieces can be given two commands: move and capture (we will ignore capture for this exercise).  Each piece has unique movements, but we are going to focus on commands for pawns.  For our limited implementation, Pawns can only more forward one space (toward their opponents side of the board) and can only capture in a forward and diagonal direction as seen in the next illustration.

![alt text](http://www.chessvariants.org/d.chess/pawnmove.gif)

Your task is to get all unit tests found under the Tests folder passing. Since you plan to be on the project long term, think about how you would implement the solution, what other test coverage might be necessary and what you would do to make future features easier to implement.

Good luck, and please reach out to us if you have any questions!