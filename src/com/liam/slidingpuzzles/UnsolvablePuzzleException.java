package com.liam.slidingpuzzles;

/**
 * Created by Liam on 28/04/2021.
 */
public class UnsolvablePuzzleException extends Exception{

    public UnsolvablePuzzleException(String message){
        super(message);
    }

    public UnsolvablePuzzleException(String message, Throwable cause) {
        super(message, cause);
    }
}
