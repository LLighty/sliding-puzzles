package com.liam.slidingpuzzles;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by Liam on 15/03/2021.
 */
public class Puzzle {

    private int[][] layout;
    private int[][] goal;

    //New puzzle which inherits test_puzzle
    public Puzzle() throws InvalidPuzzleException{
        this.layout = test_puzzle;
        if(!validPuzzle(this)){
            throw new InvalidPuzzleException("Invalid puzzle");
        }
        createGoal();
    }

    //Declare a new puzzle
    public Puzzle(int[][] layout) throws InvalidPuzzleException{
        this.layout = layout;
        if(!validPuzzle(this)){
            throw new InvalidPuzzleException("Invalid puzzle");
        }
        createGoal();
    }

    public Puzzle(int[][] layout, int[][] goal) throws InvalidPuzzleException{
        this.layout = layout;
        if(!validPuzzle(this)){
            throw new InvalidPuzzleException("Invalid puzzle");
        }
        this.goal = goal;
    }

    public static final int[][] test_puzzle = {
            {0,1,2},
            {3,4,5},
            {6,7,-1}
    };

    public static void printPuzzle(Puzzle puzzle){
        for (int row = 0; row < puzzle.layout.length; row++){
            System.out.println(getRowString(puzzle, row));
        }
    }

    /**
     * @param puzzle
     * @return Row of puzzle in String format
     */
    private static String getRowString(Puzzle puzzle, int row){
        String rowString = "";
        // We assume width = height for the puzzle i.e. 5x5, 4x4, 3x3, etc
        for(int col = 0; col < puzzle.layout.length; col++){
            if(rowString.length() > 0){
                rowString += ", ";
            }
            rowString += puzzle.layout[row][col];
        }
        return rowString;
    }

    public int[][] getLayout(){
        return layout;
    }

    public static boolean validPuzzle(Puzzle puzzle){
        //First check the dimensions n=m
        if(puzzle.layout.length != puzzle.layout[0].length){
            return false;
        }

        //Ensure that all numbers are accounted for (-1 : (length*length-2)
        int[] arrayCheck = layoutTo1D(puzzle.layout);
        Arrays.sort(arrayCheck);
        int currentNumber = -1;
        for(int i = 0; i < arrayCheck.length; i++){
            if(arrayCheck[i] != currentNumber){
                return false;
            }
            currentNumber++;
        }

        return true;
    }

    private void createGoal(){
        goal = new int[layout.length][layout[0].length];
        int currentNum = 0;
        for(int i = 0; i < layout.length; i++){
            for(int j = 0; j < layout[0].length; j++){
                if(i == j && i == layout.length-1){
                    goal[i][j] = -1;
                } else{
                    goal[i][j] = currentNum;
                    currentNum++;
                }
            }
        }
    }

    private static int[] layoutTo1D(int[][] layout){
        int[] array = new int[layout.length * layout[0].length];

        int k = 0;
        for(int i = 0; i < layout.length; i++){
            for(int j = 0; j < layout[0].length; j++){
                array[k++] = layout[i][j];
            }
        }
        return array;
    }

    public Pair selectMove(List<Pair> possibleMoves){
        Random rand = new Random();
        return possibleMoves.get(rand.nextInt(possibleMoves.size()));
    }

    public List<Pair> getPossibleMoves(){
        List<Pair> possibleMoves = new ArrayList<>();
        int puzzleBounds = this.getLayout().length;
        Pair emptySpaceCoordinate = getEmptySpace();
        if(emptySpaceCoordinate.row + 1 < puzzleBounds){
            possibleMoves.add(new Pair(emptySpaceCoordinate.row+1, emptySpaceCoordinate.col));
        }
        if(emptySpaceCoordinate.row - 1 >= 0){
            possibleMoves.add(new Pair(emptySpaceCoordinate.row-1, emptySpaceCoordinate.col));
        }
        if(emptySpaceCoordinate.col + 1 < puzzleBounds){
            possibleMoves.add(new Pair(emptySpaceCoordinate.row, emptySpaceCoordinate.col+1));
        }
        if(emptySpaceCoordinate.col - 1 >= 0){
            possibleMoves.add(new Pair(emptySpaceCoordinate.row, emptySpaceCoordinate.col-1));
        }
        return possibleMoves;
    }

    public Pair getEmptySpace(){
        int puzzleLength = this.getLayout().length;
        for(int row = 0; row < puzzleLength; row++){
            for(int col = 0; col < puzzleLength; col++){
                if(this.getLayout()[row][col] == -1){
                    return new Pair(row, col);
                }
            }
        }
        return new Pair(-1,-1);
    }

    public int getTile(int x, int y){
        try{
            return layout[x][y];
        } catch (ArrayIndexOutOfBoundsException e){
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    public int calculateHammingDistance(){
        int hammingDistance = 0;
        int size = layout.length;
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                if(layout[i][j] != goal[i][j]){
                    hammingDistance++;
                }
            }
        }

        return hammingDistance;
    }

    public int calculateManhattanDistance(){
        int currentManhattanDistance = 0;
        int size = layout.length;
        int goalCol, goalRow;
        int distX, distY;

        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                if(layout[i][j] != goal[i][j]){
                    Pair goalXYCord = findGoalRowCol(layout[i][j]);
                    goalCol = goalXYCord.col;
                    goalRow = goalXYCord.row;
                    distX = Math.abs(j - goalCol);
                    distY = Math.abs(i - goalRow);
                    currentManhattanDistance += distX + distY;
                }
            }
        }

        return currentManhattanDistance;
    }

    public Pair findGoalRowCol(int num){
        int row, col;

        if(num == -1){
            return new Pair(goal.length -1, goal.length - 1);
        }

        row = (int) Math.floor(num / goal.length);
        col = num % goal.length;

        return new Pair(row, col);
    }

    public Puzzle makeMove(Pair move, Pair emptySquare){
        Puzzle newPuzzle = this;

        newPuzzle.getLayout()[emptySquare.row][emptySquare.col] = newPuzzle.getLayout()[move.row][move.col];
        newPuzzle.getLayout()[move.row][move.col] = -1;

        return newPuzzle;
    }

    public int[][] getGoal(){
        return goal;
    }

    public boolean isGoal(){
        return calculateHammingDistance() == 0;
    }

    public String printGoal(){
        StringBuilder sb = new StringBuilder();

        int size = layout.length;

        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                sb.append(goal[i][j] + " ");
            }
            sb.append("\n");
        }

        return sb.toString();
    }

    public Boolean equals(Puzzle puzzle){
        int size = layout.length;
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                if(layout[i][j] != puzzle.layout[i][j]){
                    return false;
                }
            }
        }
        return true;
    }

    public Iterable<Puzzle> getNeighbours(){
        List<Puzzle> neighbours = new ArrayList<>();

        List<Pair> possibleMoves = getPossibleMoves();
        Pair emptySpace = getEmptySpace();

        for(int i = 0; i < possibleMoves.size(); i++){
            Pair p = possibleMoves.get(i);
            int[][] newLayout = copyLayout();
            newLayout[emptySpace.row][emptySpace.col] = layout[p.row][p.col];
            newLayout[p.row][p.col] = -1;
            try {
                neighbours.add(new Puzzle(newLayout, goal));
            } catch (InvalidPuzzleException e) {
                e.printStackTrace();
            }
        }

        return neighbours;
    }

    private int[][] copyLayout(){
        int[][] newLayout = new int[layout.length][layout.length];
        for (int i = 0; i < layout.length; i++) {
            for (int j = 0; j < layout.length; j++) {
                newLayout[i][j] = layout[i][j];
            }
        }
        return newLayout;
    }

}
