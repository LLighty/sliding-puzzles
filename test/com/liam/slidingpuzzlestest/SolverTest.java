package com.liam.slidingpuzzlestest;

import com.liam.slidingpuzzles.InvalidPuzzleException;
import com.liam.slidingpuzzles.Puzzle;
import com.liam.slidingpuzzles.Solver;
import com.liam.slidingpuzzles.UnsolvablePuzzleException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * Created by Liam on 29/04/2021.
 */
public class SolverTest {

    @Test
    public void correctSolution() throws InvalidPuzzleException, UnsolvablePuzzleException {
        int[][] layout = {
                {3, 0, 2},
                {1, 4, 5},
                {6, 7, -1}
        };

        int[][] goal = {
                {0, 1, 2},
                {3, 4, 5},
                {6, 7, -1}
        };

        Puzzle one = new Puzzle(layout);
        Solver solver = new Solver(one);
        solver.solvePuzzle();

        Assertions.assertTrue(Arrays.deepEquals(solver.getFinalPuzzle().getLayout(), goal));
    }
}
