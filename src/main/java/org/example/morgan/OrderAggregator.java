package org.example.morgan;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

public class OrderAggregator {

    public static List<AggregationInstruction> aggregateOrders(List<?> messsages) {
        Map<String, AggregationInstruction> map = new HashMap<>();
        List<AggregationInstruction> list = new ArrayList<>();
        Map<String, Integer> orderMap = new HashMap<>();
        for (Object message : messsages) {
            if (message instanceof Order order) {
                if (!map.containsKey(order.instrumentId + order.side.toString())) {
                    map.put(order.instrumentId + order.side.toString(), new AggregationInstruction());
                }
                AggregationInstruction aggregationInstruction = map.get(order.instrumentId + order.side);
                if (aggregationInstruction.orderIds == null) {
                    aggregationInstruction.orderIds = new ArrayList<>();
                }
                aggregationInstruction.quantity += order.quantity;
                aggregationInstruction.orderIds.add(order.orderId);
                aggregationInstruction.instrumentId = order.instrumentId;
                aggregationInstruction.side = order.side;
                orderMap.put(order.orderId, order.quantity);

            } else if (message instanceof Cancel cancel) {
                for (Map.Entry<String, AggregationInstruction> entry : map.entrySet()) {
                   String orderId = entry.getValue().orderIds.stream()
                            .filter(x -> x.equals(cancel.orderId)).findFirst()
                            .orElse("");
                    if (!orderId.isEmpty()){
                        entry.getValue().quantity -= orderMap.get(orderId);
                        entry.getValue().orderIds.remove(orderId);
                    }
                }
            }
        }

        for (Map.Entry<String, AggregationInstruction> entry : map.entrySet()) {
            Collections.addAll(list, entry.getValue());
        }

        return list;
    }
}
