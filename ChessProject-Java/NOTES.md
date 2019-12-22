# Notes

I'm tracking my thought-process here, to capture all of the "conversations" that would have
taken place with other people if this had been a real project.

I'm doing the Java version of the project.


## Initial Thoughts

  - I've read through the source and test code, and have attempted to run the existing tests
    to see what happens.

  - I can immediately see various refactorings and restructurings that I would like to do, but
    the very first priority is to get the existing tests passing (partly because that is the
    scope of this sprint! - and partly because I will feel a whole lot happier about changing
    things subsequently if I've got a working test suite to give me confidence).

  - I'll need to make various decisions about things. In a real project, we'd be having
    conversations, some pair-programming, code-reviews, etc. In lieu of all that, I'll just
    capture them here in this NOTES file.

  - I'm going to consider these classes as **NOT** being a fixed externally-specified API. So I'm
    going to feel free to change that API, and the semantics exposed through it, if I feel it's
    justified.

  - The very first thing I'm going to is definitely going to change that API, but I think it's
    something so crucially wrong (conceptually) in the existing code, that it needs to be tackled
    first. And that's that the board width and height are given as constants with the value **7**.
    That's confusing the *number of squares* abstraction with the low-level internal *indexing*
    abstraction (there are 8 squares, indexed from 0 to 7 in Java). So the first thing I'll do will
    be to change those numbers from 7 to 8. I know the board is supposed to be an 8x8 one, as
    that's how it looks in the supporting "specification" (from http://www.chessvariants.org/d.chess/),
    though I guess it's open to being able to support different configurations in the future.

## First Task - Getting the existing tests passing

  - I'm running the tests with **mvn test**, and they're starting off with 14 tests and
    12 failures. They're all *UnsupportedOperationException* errors, because the problem is
    that the method implementations are currently just stubs.

  - I'll change the *MAX_BOARD_WIDTH* and *MAX_BOARD_HEIGHT* constants in *ChessBoard.java*
    from 7 to 8. That'll actually break two of the existing tests so I'll be starting off in
    what will look like the wrong direction (increasing the number of failing tests), but I'll
    immediately change that test to match. This is an API change, of course, not just an
    internal implementation detail. But I'm happy with that.

  - I now get 2 additional failures. But I'll immediately fix that by changing the two tests
    in *ChessBoardTest.java* that check the board size (to check for 8, rather than 7).

  - Now I'll start implementing the internals of the methods that are currently just stubs.
    I'll start with *ChessBoard.IsLegalBoardPosition*, which I'll implement in the simplest
    possible way, just checking that both the *x* and *y* coordinates are in range (and just
    doing that in the simplest way, not using anything complicated). But I'll split the logic
    into separate cases for the *x* and *y* coordinates, so as to avoid having a potentially
    hard-to-read complicated boolean expression with lots of terms in it. Now I get 1 failure
    and 6 errors from my 14 tests.

  - It feels odd to get a failure, so next, I'll read through all the tests in some detail and
    check that they are all make sense. On my initial quick scan, I saw at least one that looked a
    bit dodgy! Ah yes, the test that [11, 5] is not a legal board position appears to have a small
    typo that it is expecting it to be *true* rather than *false*. This is what was causing that
    one failure above, So I'll change that. Now I get 0 failures and 6 UnsupportOperationException
    errors.

  - Looking through the tests, the logic in *ChessBoardTest.testLimits_The_Number_Of_Pawns*
    feels a bit strange, but I'll come back to that later.

  - Now I'll implement the *ChessBoard.Add* method. I'll initially do this in the very simplest
    way I can (by just inserting the pawn into *pieces* 2-d array). From reading the other tests,
    and the *Pawn.java* code, I see that it is expected to set colour and the X and Y coordinates,
    as properties of the pawn object itself too (and to set coordinates to -1 if not valid).
    I'm not sure I'm very happy with those semantics, but for now, I'll just do it that way. So
    now it's implementation does the following:

      - check (using *IsLegalBoardPosition*) the coordinates are in range, and reset them to -1 if not.
      - set the chessBoard property on the pawn
      - set the piece colour on the pawn
      - set the X coordinate on the pawn
      - set the Y coordinate on the pawn
      - assign the pawn to the appropriate slot in the *pieces* property.

    In setting the piece colour, I see that the *Pawn.setPieceColor* method is private, unlike
    all the rest of them which are public. There's no reason for it to be private, and it means
    I can't call it from the *ChessBoard* class, so I'll change that to public. (I'm actually
    a bit unhappy with the idea that a pawn can change its colour; it feels like it would be
    much better for that to be an unmutable property of the object, just set as final when it
    ia constructed, but I'll leave that to a refactoring stage, since my initial priority is to
    change as little as possible until I've got a full set of tests running).

    Now I get 1 failure and 3 errors, from my 14 tests. 

  - The one failure I'm getting is from that *testLimits_The_Number_Of_Pawns* test that I was
    concerned about earlier. So I'll look at that in more detail now.

    First thing I can see is that when it's placing pawns, it's using *MAX_BOARD_WIDTH* to
    control the Y coordinate. But that's wrong, since the Y coordinate is height. So I'll change
    that to *MAX_BOARD_HEIGHT*. It's a conceptual error, not one that would be picked up from
    the existing tests (since, for now, both the width and the height have the same value - but
    maybe they won't in the future, which is presumably why there are two separate constants
    rather than just a single "board size" value).

  - But that (of course) doesn't fix the failing test. Looking at the test in more detail, it's
    trying to place 10 pawns - 8 on the penultimate row ("row 6"), and the remaining 2 on the last
    row ("row 7"). Then it's expecting all of the pawns placed on "row 6" to have the
    assigned coordinates, and all of the pawns placed on "row 7" to be invalid (with coordinates of
    -1). I've changed the width and height to 8, so these expectations don't work any more.

    Actually I'm not sure what value this test brings, if all its really doing is checking
    legality of positions (which there are already other tests for). Its name implies that
    its trying to test that once its placed 8 pawns, it's not allowed to place any more. I don't
    know how chess works, but that sounds like a plausible thing to want to be able to test
    (in terms of checking for valid start positions). So I think that means that the *Add()*
    method needs to have an extra check to impose a limit on the number of pawns allowed.

    So I'll do that. For now I'll do it really explicitly inside the *ChessBoard* class, as a check
    in its *Add()* method. Eventually we'll need to handle different types of pieces, and this
    limit on pawns is very specific to it being a pawn, but I won't try (yet) to abstract this into
    the *Pawn* class itself.

    An alternative thing that the test might be trying to do (though I don't think so, because
    of its name) is to test that BLACK isn't allowed to have any pawns on "row 7" and WHITE isn't
    allowed to have any pawns on "row 0". The movement rules (and initial-placement rules) should
    prohibit that. That would be a good thing to test too, but I think I'll add that later as
    an additional test, once I've got this initial set all passing.

  - Now I get 3 errors from my 14 tests, all because of the unimplemented *Move() method  in the
    *Pawn* class. According to the spec in thw README file, pawns can only move forward one space
    (in the same column). But the diagram shows that a pawn can also move forward two spaces (which,
    after a quick google to check, is allowed if the pawn is still in its "start position" column).
    I think I'll use the diagram to guide me (though in real life this would have sparked off a
    conversation about the requirements).

  - I'll implement *Pawn.Move()* in the simplest way I can think of, where all it needs to do is:

      - do nothing (for now) if it's a CAPTURE move rather than a MOVE one.
      - check that the pawn is moving forward by exactly one square (or possibly two squares if
        its on its "starter" column), taking account of the colour to determine direction of movement.
      - check (via a call to the *IsLegalBoardPosition method on the associated board object)
        that the new coordinates are allowed (and leave the pawn unmoved if not).
      - Update the board object so that it knows the pawn is in the new position (and is no
        longer in the old position).
      - Assign the new coordinates in the pawn object

    I'm not super-happy that the current representation means coordinates are being stored
    separately in two different places (in the board object, and also within each pawn object),
    as that introduces risk that some bug might get them out of sync. But I won't worry about
    that just now; I'm just trying to get the tests to pass right now!

  - Oh look! The *Pawn.getChessBoard* method isn't actually called that at all. It's mis-spelled
    as *getChesssBoard* (three S's). I'll fix that. Looks like an extra test would have detected
    that. I probably need to add some additional tests for a whole lot of stuff, including that,
    which I'll do as soon as I've finished this initial task.

  - Now all 14 tests are passing.



## Refactoring



