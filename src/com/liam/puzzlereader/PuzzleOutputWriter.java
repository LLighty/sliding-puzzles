package com.liam.puzzlereader;

import com.liam.slidingpuzzles.Puzzle;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Liam on 28/04/2021.
 */
public class PuzzleOutputWriter {



    public void writePuzzleToCSV(Puzzle puzzle, String outputPath) throws IOException {
        String outputPathCSV = outputPath + ".csv";
        List<String> layout = convertLayoutToList(puzzle.getLayout());
        int size = puzzle.getLayout().length;



        FileWriter fileWriter = new FileWriter(outputPathCSV);
        BufferedWriter csvWriter = new BufferedWriter(fileWriter);
        csvWriter.append(Integer.toString(size));
        csvWriter.append("\n");

        for(String row : layout){
            csvWriter.append(row);
            csvWriter.append("\n");
        }

        csvWriter.close();
    }

    private List<String> convertLayoutToList(int[][] layout){
        List<String> listLayout = new ArrayList<>();
        String row = "";

        for(int i = 0; i < layout.length; i++){
            for(int j = 0; j < layout[0].length; j++){
                row += Integer.toString(layout[i][j]);
                if(j < layout[0].length - 1){
                    row += ", ";
                }
            }
            listLayout.add(row);
            row = "";
        }

        return listLayout;
    }
}
