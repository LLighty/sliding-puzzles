package com.liam.slidingpuzzles;

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
        } catch (InvalidPuzzleException e){
            e.printStackTrace();
        } catch (UnsolvablePuzzleException e) {
            System.out.println("This puzzle cannot be solved!");
            e.printStackTrace();
        }
    }
}
