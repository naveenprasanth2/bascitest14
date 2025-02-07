package org.example.chatgpt.dsa;

import java.util.HashMap;

public class TwoSum {
    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 15};
        int target = 9;
        HashMap<Integer, Integer> numIndexMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (numIndexMap.containsKey(target - nums[i])) {
                System.out.println(STR."\{numIndexMap.get(target - nums[i])}, \{i}");
                return;
            } else {
                numIndexMap.put(nums[i], i);
            }
        }
        System.out.println(STR."-1, -1");
    }
}
