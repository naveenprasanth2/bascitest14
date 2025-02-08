package org.example.chatgpt.dsa;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CountingElements {
    public static void main(String[] args) {
        int[] array = {1, 1, 2, 2, 2, 3, 4, 5};
        Map<Integer, Integer> map = new HashMap<>();
        int total = 0;
        for (int value : array) {
            map.put(value, map.getOrDefault(value, 0) + 1);
        }
        System.out.println(map);
        Set<Map.Entry<Integer, Integer>> set = map.entrySet();
        for (Map.Entry<Integer, Integer> entry : set) {
            if (map.containsKey(entry.getKey() + 1)) {
                total += map.get(entry.getKey());
            }
        }

        System.out.println(total);
    }
}
