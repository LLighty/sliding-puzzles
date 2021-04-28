package com.liam.puzzlereader;

import com.liam.slidingpuzzles.InvalidPuzzleException;
import com.liam.slidingpuzzles.Puzzle;
import com.liam.slidingpuzzles.UnsolvablePuzzleException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Liam on 28/04/2021.
 */
public class PuzzleInputReader {

    public Puzzle readPuzzleFromCSV(String pathToCSV) throws IOException, InvalidPuzzleException, UnsolvablePuzzleException {

        int[][] puzzleLayout = csvToLayout(pathToCSV);
        //System.out.println(array2DIntToString(puzzleLayout));
        Puzzle newPuzzle = new Puzzle(puzzleLayout);

        return newPuzzle;
    }

    private int[][] csvToLayout(String pathToCSV) throws IOException {
        int[][] layout;
        int[] dLayout = new int[0];
        int size = 0;
        int currentIndex = 0;
        String row;
        BufferedReader csvReader = new BufferedReader(new FileReader(pathToCSV));
        while((row = csvReader.readLine()) != null){
            String[] data = row.split(",");
            if(data.length < 2){
                size = Integer.parseInt(data[0]);
                dLayout = new int[size*size];
            } else {
                for(int i = 0; i < data.length; i++){
                    data[i] = data[i].trim();
                    dLayout[currentIndex] = Integer.parseInt(data[i]);
                    currentIndex++;
                }
            }
        }

        layout = convertSingleIntArrayToDouble(dLayout, size);
        csvReader.close();

        return layout;
    }

    private int[][] convertSingleIntArrayToDouble(int[] arrayToConvert, int size){
        int[][] doubleArray = new int[size][size];
        int currentRow = 0;
        int currentCol = 0;

        for(int i = 0; i < arrayToConvert.length; i++){
            doubleArray[currentRow][currentCol] = arrayToConvert[i];
            currentCol++;
            if(currentCol > size - 1){
                currentCol = 0;
                currentRow++;
            }
        }

        return doubleArray;
    }


    //For debugging 2D array
    public String array2DIntToString(int[][] arr){
        String returnString = "";
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < arr[0].length; i++){
            for(int j = 0; j < arr.length; j++) {
                sb.append(arr[i][j]);
            }
            sb.append("\n");
        }

        returnString = sb.toString();

        return returnString;
    }
}
