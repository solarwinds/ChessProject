# This is the plan on how to complete this project

- [x] 1. Pass all the tests
- [x] 2. Check the tests
- [ ] 3. Add missing tests
- [ ] 4. Add new features
- [ ] 5. Add missing documentation

## 1. Pass all the tests
We need to pass all the tests that are here, implementing the features that may be required for that.

## 2. Check the tests
We need to check that the tests that currently exist:

- actually make sense
- are actually doing what they claim they are doing

## 3. Add missing tests
There may be normal cases or corner cases that should be tested but currently aren't.

Find those and create the missing tests.

## 4. Add new features
Now that we have fixed what we have, we still have to complete the project.

So we'll have to write the new tests and features in order to honour the requirements.

## 5. Add missing documentation
The original project wasn't well documented, if at all.

We should add as much documentation as possible.

# Movement
Movement is by far the most complex part of this project, so it's better to have a specific plan for it.

There's a minimum viable product, which fully implements the requests, allowing for improvements to be built upon it organically.

Then, there will be future features, which are beyond the scope now, but it's still better to plan for them at this stage.

## Checklist

Minimum viable product:

- [x] Forward movement
- [x] Piece class
- [ ] validMove()
- [ ] move()

---

Future features, if needed:

- [ ] BoardSetup class
- [ ] ChessBoard using BoardSetup
- [ ] Facing class
- [ ] validMove() using Facing

## MVP

### Forward movement
The first step is to allow Pawns to move forward one step.

This is obviously not complete, nor correct for the black pieces: it's just a start.

It's easy, no reason to go into more details.

### Piece
We'll need to have an abstract Piece class, from with Pawn will derive.

The move() method will be abstract, while other things might end up here (coordinates, facing, etc.)

### validMove()
Will need to check if the move is valid.

So far we'll only care about theoretical moves, without involving the "if I do this, my King will be captured".

Will have to add it later, though.

### move()
The move() method will be specific for every Piece derived class. Will call validMove() before performing the move itself.
Will actually be quite simple: the complex function is validMove().

## Future features

The BoardSetup and Facing are not really necessary now.

With "now" I mean "with a normal chess game".

The only Piece that needs facing is the Pawn, and it can just face upwards or downwards.

The setup for a new game is only one.

Being extensible is a nice thing; overengineering, adding complexity in order to support features that might or might not ever be used, is not great.

So, while I designed those at first, I don't really think it makes business sense to actually support them for now.

### Board setup
The amount of colours and their behaviours is strictly dependant on the board itself.

Think about it: if we were to play an alternate version, we would likely have pieces moving the same way, but the board would be setup differently.

So the board will have to include a setup array, one value for every player. Said values will have to include both the pieces setup, and the direction they will be facing.

### Facing
Every piece will face a specific direction at any given time.

This information will have to be included in Piece, and will have to affect its allowed movement.

Will likely be a tuple that can have values 1 or -1, that will get multiplied to the allowed movements.
