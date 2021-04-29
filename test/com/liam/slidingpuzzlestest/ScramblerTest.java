package com.liam.slidingpuzzlestest;

import com.liam.slidingpuzzles.InvalidPuzzleException;
import com.liam.slidingpuzzles.Puzzle;
import com.liam.slidingpuzzles.Scrambler;
import com.liam.slidingpuzzles.UnsolvablePuzzleException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * Created by Liam on 29/04/2021.
 */
public class ScramblerTest {

    @Test
    public void testScramble() throws InvalidPuzzleException, UnsolvablePuzzleException {
        Puzzle test = new Puzzle();

        int[][] layoutPreScramble = test.copyLayout();
        Scrambler.scramblePuzzle(test, 50);

        Assertions.assertFalse(Arrays.deepEquals(layoutPreScramble, test.getLayout()));
    }
}
