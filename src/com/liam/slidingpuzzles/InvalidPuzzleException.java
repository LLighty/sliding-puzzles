package com.liam.slidingpuzzles;

/**
 * Created by Liam on 27/04/2021.
 */
public class InvalidPuzzleException extends Exception {

    public InvalidPuzzleException(String message){
        super(message);
    }

    public InvalidPuzzleException(String message, Throwable cause) {
        super(message, cause);
    }
}
