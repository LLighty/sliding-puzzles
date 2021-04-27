package com.liam.slidingpuzzles;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Liam on 15/03/2021.
 */
public class Scrambler {
    public static Puzzle scramblePuzzle(Puzzle puzzle, int numMoves){
        Scrambler scrambler = new Scrambler();
        List<Pair> possibleMoves;
        Pair move;
        for(int i = 0; i < numMoves; i++){
            possibleMoves = puzzle.getPossibleMoves();
            move = puzzle.selectMove(possibleMoves);
            puzzle = puzzle.makeMove(move, puzzle.getEmptySpace());
        }
        return puzzle;
    }
}
