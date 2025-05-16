package org.example.morgan;

public class Order {
    String orderId;
    String instrumentId;
    int quantity;
    Side side;

    public Order(String orderId, String instrumentId, int quantity, Side side) {
        this.orderId = orderId;
        this.instrumentId = instrumentId;
        this.quantity = quantity;
        this.side = side;
    }
}
