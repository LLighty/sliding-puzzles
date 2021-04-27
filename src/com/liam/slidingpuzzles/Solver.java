package com.liam.slidingpuzzles;

import java.util.*;

/**
 * Created by Liam on 15/03/2021.
 */
public class Solver {

    private PriorityQueue<PuzzleSearchNode> priorityQueue = new PriorityQueue<>(1, new SortByManhattanDistance());
    private List<Puzzle> solutionPuzzleList;
    private List<PuzzleSearchNode> possibleSolutionList;
    private Puzzle finalPuzzle;
    private int numMoves;

    public Solver(Puzzle initialPuzzle){
        priorityQueue.add(new PuzzleSearchNode(initialPuzzle, null, 0, null));
        solutionPuzzleList = new ArrayList<>();
        numMoves = 0;
    }

    public void solvePuzzle(){
        boolean solved = false;
        int searched = 0;
        PuzzleSearchNode finalPuzzleSearchNode = null;
        possibleSolutionList = new ArrayList<>();

        while(!solved || searched < 400){
            PuzzleSearchNode currentPuzzleSearchNode = priorityQueue.poll();
            if(currentPuzzleSearchNode.thisPuzzle.isGoal()){
                solved = true;
                finalPuzzleSearchNode = currentPuzzleSearchNode;
                possibleSolutionList.add(currentPuzzleSearchNode);
                break;
            }

            possibleSolutionList.add(currentPuzzleSearchNode);

            for (Puzzle puzzle : currentPuzzleSearchNode.thisPuzzle.getNeighbours()) {
                //Don't want to go back and forwards in puzzle movement (i.e. return to the same state we were previously in.)
                if(!currentPuzzleSearchNode.thisPuzzle.equals(puzzle)){
                    priorityQueue.add(new PuzzleSearchNode(puzzle, currentPuzzleSearchNode.thisPuzzle, currentPuzzleSearchNode.moveCount + 1, currentPuzzleSearchNode));
                }
            }
        }

        if(searched >= 400){
            System.out.println("Could not find the solution within 400 steps.");
            return;
        }

        System.out.println("Solved!");
        numMoves = finalPuzzleSearchNode.moveCount;
        finalPuzzle = finalPuzzleSearchNode.thisPuzzle;

        PuzzleSearchNode solutionBuilder = finalPuzzleSearchNode;
        while(solutionBuilder != null){
            solutionPuzzleList.add(0, solutionBuilder.thisPuzzle);
            solutionBuilder = solutionBuilder.previousSearchNode;
        }
    }

    public int numberOfMoves(){
        return numMoves;
    }

    public Iterable<Puzzle> solution(){
        return solutionPuzzleList;
    }

    public Puzzle getFinalPuzzle(){
        return finalPuzzle;
    }

    public class PuzzleSearchNode{

        Puzzle thisPuzzle;
        Puzzle previousPuzzle;
        PuzzleSearchNode previousSearchNode;
        int moveCount;

        public PuzzleSearchNode(Puzzle puzzle, Puzzle prevPuzzle, int moveCount, PuzzleSearchNode puzzleSearchNode){
            thisPuzzle = puzzle;
            previousPuzzle = prevPuzzle;
            this.moveCount = moveCount;
            previousSearchNode = puzzleSearchNode;
        }

    }

    class SortByManhattanDistance implements Comparator<PuzzleSearchNode>{
        @Override
        public int compare(PuzzleSearchNode o1, PuzzleSearchNode o2) {
            return (o1.moveCount + o1.thisPuzzle.calculateManhattanDistance()) - (o2.moveCount + o2.thisPuzzle.calculateManhattanDistance());
        }
    }

}
