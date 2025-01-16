package org.example.tesco.parking;

public class ParkingTest {
    public static void main(String[] args) {
        ParkingSpace parkingSpace = new ParkingSpace(2, 1);
        parkingSpace.parkVehicle(new Car());
        parkingSpace.unParkVehicle(new Car());
        parkingSpace.parkVehicle(new Car());
    }
}
