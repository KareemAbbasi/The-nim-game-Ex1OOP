kareem
318378213
Kareem Abbasi

========================
= RESUBMISSION CHANGES =
========================
1) Had two scanner objects initialised one for each player. Now it's only one.
2) Fixed an error that caused the competition to always display all the printed messages even when
none of the players are a human player.


====================
= File description =
====================
Board.java - Contains the implementation and the class of the game board.
Player.java - Contains the Player class and the functions that produce the moves according to the player type.
Competition.java - Contains the Competition class and it's implementation with the different functions that are
                   needed to run a number of Nim games to make a competition
Move.java - Contains the Move class and it's implementation and the functions needed to make the moves.
README


====================
=      DESIGN      =
====================
The classes were implemented in a way that doesn't reveal any unnecessary variables or functions to other classes.
Variables that are needed in other classes can be accessed by public functions that return their values. This
ensures that not everything in the class is public and shared with other classes.

====================================
=     IMPLEMENTATION DETAILS       =
====================================

The Smart Player:
=================
The smart player chooses it's move according to the number of unmarked sticks left on the game board.
The smart player works in this order:
The algorithm creates an array representation of the board where the board is an array of int arrays which holds
the values 1 for unmarked sticks and 0 for marked sticks.

If the number of unmarked sticks is 2 then it produces a move that marks the one the two sticks.

--- IF IT DOESN'T FIND A VALID MOVE ---

If the number of unmarked sticks is 3 then it tries to find 2 sticks that are next to each other and makes a move
that marks these two sticks.

--- IF IT DOESN'T FIND A VALID MOVE ---

It goes over each row starting from the last row (5th row) and checks it there is a sticks alone in a row (a row
that has only one stick left). It makes a move to mark that stick which makes the row empty.

--- IF IT DOESN'T FIND A VALID MOVE ---

It calls the function produceRandomMove to get a random move.
