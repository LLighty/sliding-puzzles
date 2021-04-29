package com.liam.slidingpuzzlestest;

import com.liam.slidingpuzzles.InvalidPuzzleException;
import com.liam.slidingpuzzles.Pair;
import com.liam.slidingpuzzles.Puzzle;
import com.liam.slidingpuzzles.UnsolvablePuzzleException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * Created by Liam on 29/04/2021.
 */
public class PuzzleTest {

    @Test
    public void validPuzzleDimensionsLayout(){
        int[][] layout = {
                {0, 1, 2},
                {3, 4, 5},
                {6, 7, -1}
        };

        try {
            Puzzle one = new Puzzle(layout);
            Assertions.assertTrue(Puzzle.validPuzzle(one));
        } catch (InvalidPuzzleException e) {
            e.printStackTrace();
            Assertions.fail("Was not a valid puzzle.");
        } catch (UnsolvablePuzzleException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void invalidPuzzleDimensionsLayout() {
        int[][] layout = {
                {0, 1, 2},
                {3, 4, 5, 6},
                {6, 7, -1}
        };

        Assertions.assertThrows(InvalidPuzzleException.class, () -> {
            Puzzle one = new Puzzle(layout);
        });
    }

    @Test
    public void invalidPuzzleNumbers(){
        int[][] layout = {
                {0, 1, 2},
                {3, 4, 5},
                {6, -1, -1}
        };

        Assertions.assertThrows(InvalidPuzzleException.class, () -> {
           Puzzle one = new Puzzle(layout);
        });
    }

    @Test
    public void generateCorrectGoal() throws InvalidPuzzleException, UnsolvablePuzzleException {
        int[][] layout = {
                {0, 1, 2},
                {3, 4, 5},
                {6, -1, 7}
        };

        int[][] properGoal = {
                {0, 1, 2},
                {3, 4, 5},
                {6, 7, -1}
        };

        Puzzle one = new Puzzle(layout);
        Assertions.assertTrue(Arrays.deepEquals(properGoal, one.getGoal()));
    }

    @Test
    public void getEmptyTileCheck() throws InvalidPuzzleException, UnsolvablePuzzleException {
        Puzzle one = generateBasicPuzzle();
        Pair emptyTile = one.getEmptySpace();

        Assertions.assertTrue(emptyTile.getRow() == 2);
        Assertions.assertTrue(emptyTile.getCol() == 2);
    }


    @Test
    public void getPossibleMovesTwo() throws InvalidPuzzleException, UnsolvablePuzzleException {
        Puzzle one = generateBasicPuzzle();

        Assertions.assertTrue(one.getPossibleMoves().size() == 2);
    }

    @Test
    public void getPossibleMovesThree() throws InvalidPuzzleException, UnsolvablePuzzleException {
        int[][] layout = {
                {0, 1, 2},
                {3, 4, 5},
                {6, -1, 7}
        };
        Puzzle one = new Puzzle(layout);

        Assertions.assertTrue(one.getPossibleMoves().size() == 3);
    }

    @Test
    public void getPossibleMovesFour() throws InvalidPuzzleException, UnsolvablePuzzleException {
        int[][] layout = {
                {0, 1, 2},
                {3, -1, 5},
                {6, 4, 7}
        };
        Puzzle one = new Puzzle(layout);

        Assertions.assertTrue(one.getPossibleMoves().size() == 4);
    }


    public Puzzle generateBasicPuzzle() throws InvalidPuzzleException, UnsolvablePuzzleException {
        int[][] layout = {
                {0, 1, 2},
                {3, 4, 5},
                {6, 7, -1}
        };

        return new Puzzle(layout);
    }
}
