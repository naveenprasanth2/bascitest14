package org.example.tesco.parking;

public class NoParkingFoundException extends RuntimeException{
    public NoParkingFoundException(SpaceType spaceType){
        super(STR."No parking found in the \{spaceType}");
    }
}
