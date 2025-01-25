package org.example.dsa;

import java.util.HashMap;
import java.util.Map;

public class TwoSum {
    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 15};
        int target = 9;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                System.out.println(STR."\{map.get(target - nums[i])}, \{i}");
            } else {
                map.put(nums[i], i);
            }
        }
    }
}
