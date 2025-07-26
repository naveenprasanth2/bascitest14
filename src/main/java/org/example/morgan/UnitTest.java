package org.example.morgan;

import java.util.ArrayList;
import java.util.List;

public class UnitTest {

    public static void main(String[] args) {
        Order applBuy1 = new Order("1", "AAPL", 100, Side.BUY);
        Order applBuy2 = new Order("2", "AAPL", 50, Side.SELL);
        Order applBuy3 = new Order("3", "AAPL", 500, Side.SELL);
        Cancel cancel  = new Cancel("3");
        List<?> orders = List.of(applBuy1, applBuy2, applBuy3, cancel);
        System.out.println(OrderAggregator.aggregateOrders(orders));
    }
}
