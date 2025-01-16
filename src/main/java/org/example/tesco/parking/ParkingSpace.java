package org.example.tesco.parking;

import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.atomic.AtomicInteger;

@Getter
@Setter
public class ParkingSpace {
    private final int smallSpace;
    private final int largeSpace;
    private AtomicInteger filledSmallSpace;
    private AtomicInteger filledLargeSpace;

    public ParkingSpace(int smallSpace, int largeSpace){
        this.smallSpace = smallSpace;
        this.largeSpace = largeSpace;
        this.filledLargeSpace = new AtomicInteger();
        this.filledSmallSpace = new AtomicInteger();
    }

    public void parkVehicle(Vehicle vehicle) {
        if (vehicle instanceof Car) {
            if (largeSpace > filledLargeSpace.intValue()) {
                filledLargeSpace.getAndIncrement();
            } else {
                throw new SpaceFullException("Not available", SpaceType.LARGE);
            }
            System.out.println("Vehicle of type car parked");
        } else {
            if (smallSpace > filledSmallSpace.intValue()){
                filledSmallSpace.getAndIncrement();;
            }else {
                throw new SpaceFullException("Not available", SpaceType.SMALL);
            }
            System.out.println("Vehicle of type bike parked");
        }
    }

    public void unParkVehicle(Vehicle vehicle) {
        if (vehicle instanceof Car) {
            if (filledLargeSpace.intValue() > 0) {
                filledLargeSpace.getAndDecrement();
            } else {
                throw new NoParkingFoundException(SpaceType.LARGE);
            }
            System.out.println("Vehicle of type car un parked");
        } else {
            if (filledSmallSpace.intValue() > 0){
                filledSmallSpace.getAndDecrement();
            }else {
                throw new NoParkingFoundException(SpaceType.SMALL);
            }
            System.out.println("Vehicle of type bike un parked");
        }
    }
}
