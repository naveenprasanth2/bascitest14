package org.example.morgan;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AggregationInstruction {
    String instrumentId;
    int quantity;
    Side side;
    List<String> orderIds;

    @Override
    public String toString() {
        return "AggregationInstruction{" +
                "instrumentId='" + instrumentId + '\'' +
                ", quantity=" + quantity +
                ", side=" + side +
                ", orderIds=" + orderIds +
                '}';
    }
}
