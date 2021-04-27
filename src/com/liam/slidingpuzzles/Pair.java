package com.liam.slidingpuzzles;

/**
 * Created by Liam on 31/03/2021.
 */
public class Pair{
    int row, col;
    public Pair(int row, int col){
        this.row = row;
        this.col = col;
    }

    public String toString(){
        return "row: " + row + ", col: " + col;
    }
}
