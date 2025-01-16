package org.example.tesco.parking;

public class SpaceFullException extends RuntimeException{

    public SpaceFullException(String message, SpaceType spaceType){
        super(STR."\{spaceType.toString()} is \{message}");
    }
}
