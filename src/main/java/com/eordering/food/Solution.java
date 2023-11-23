package com.eordering.food;

import java.util.*;

public class Solution {
    public static void main(String[] args) {
        List list = new ArrayList<>();
        list = Collections.checkedList(list, String.class);

        list.add(1);
    }

    public static int twoSum(int[] nums) {
        int l = 0, r = nums.length - 1, mid = 0;

        if (nums[r] > nums[l]) return nums[l];

        while (l < r) {
            mid = l + (r - l) / 2;

            if (nums[mid] > nums[r]) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }

        return nums[r];
    }
}

enum Day {
    SUNDAY,
    MONDAY,
    TUESDAY,
    WEDNESDAY,
    THURSDAY,
    FRIDAY,
    SATURDAY;

    public static boolean isHolidays(Day day) {
        return switch (day) {
            case SUNDAY, SATURDAY -> true;
            default -> false;
        };
    }
}