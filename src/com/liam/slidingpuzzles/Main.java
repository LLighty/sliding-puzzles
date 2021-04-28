package com.liam.slidingpuzzles;

import com.liam.puzzlereader.PuzzleInputReader;
import com.liam.puzzlereader.PuzzleOutputWriter;

import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        try {

            Puzzle test = new Puzzle();
            Puzzle.printPuzzle(test);
            System.out.println();
            Scrambler.scramblePuzzle(test, 40);
            Puzzle.printPuzzle(test);
            System.out.println();
            System.out.println(test.printGoal());
            System.out.println();
            System.out.println("Hamming Distance: " + test.calculateHammingDistance());
            System.out.println("Manhattan Distance: " + test.calculateManhattanDistance());

            System.out.println();

            Solver solver = new Solver(test);
            solver.solvePuzzle();
            //System.out.println("Final puzzle solution: ");
            //Puzzle.printPuzzle(solver.getFinalPuzzle());
            System.out.println("Steps taken: " + solver.numberOfMoves());
            System.out.println("Steps: ");

            solver.solution().forEach( (element) -> {
                Puzzle.printPuzzle(element);
                System.out.println();
            });

            PuzzleInputReader inputReader = new PuzzleInputReader();
            PuzzleOutputWriter outputWriter = new PuzzleOutputWriter();
            String csvTestPathOne = new File("src/resources/puzzle1.csv").getAbsolutePath();
            Puzzle csvTest = inputReader.readPuzzleFromCSV(csvTestPathOne);

            Puzzle outputTest = new Puzzle();
            Scrambler.scramblePuzzle(outputTest, 40);
            String csvTestPathOutput = new File("src/resources/puzzle_output_test").getAbsolutePath();
            outputWriter.writePuzzleToCSV(outputTest, csvTestPathOutput);

        } catch (InvalidPuzzleException e){
            e.printStackTrace();
        } catch (UnsolvablePuzzleException e) {
            System.out.println("This puzzle cannot be solved!");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
