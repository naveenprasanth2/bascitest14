package org.example.tesco.parking;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class ParkingSpace {
    private final int smallSpace;
    private final int largeSpace;
    private int filledSmallSpace;
    private int filledLargeSpace;

    public void parkVehicle(Vehicle vehicle) {
        if (vehicle instanceof Car) {
            if (largeSpace > filledLargeSpace) {
                filledLargeSpace++;
            } else {
                throw new SpaceFullException("Not available", SpaceType.LARGE);
            }
            System.out.println("Vehicle of type car parked");
        } else {
            if (smallSpace > filledSmallSpace){
                filledSmallSpace++;
            }else {
                throw new SpaceFullException("Not available", SpaceType.SMALL);
            }
            System.out.println("Vehicle of type bike parked");
        }
    }

    public void unParkVehicle(Vehicle vehicle) {
        if (vehicle instanceof Car) {
            if (filledLargeSpace > 0) {
                filledLargeSpace--;
            } else {
                throw new NoParkingFoundException(SpaceType.LARGE);
            }
            System.out.println("Vehicle of type car un parked");
        } else {
            if (filledSmallSpace > 0){
                filledSmallSpace--;
            }else {
                throw new NoParkingFoundException(SpaceType.SMALL);
            }
            System.out.println("Vehicle of type bike un parked");
        }
    }
}
