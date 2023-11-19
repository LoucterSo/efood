package com.eordering.food;

import java.util.*;

public class Solution {
    public static void main(String[] args) {

//        int[] nums = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
//        System.out.println(findIndexOfElement(nums, 3));
        Deque<String> deque = new LinkedList<>();
        deque.add("123");
        deque.add("124");
        deque.add("122");

        System.out.println(deque);
        System.out.println(deque.peekLast());
        System.out.println(deque);
    }

    public static int findIndexOfElement(int[] array, int element) {
        return findIndexOfElementRec(array, 0, array.length - 1, element);
    }

    public static int findIndexOfElementRec(int[] array, int start, int end, int element) {
        if (start > end) {
            return -1;
        }
        int mid = start + (end - start) / 2;
        int midElement = array[mid];

        if (midElement == element) return mid;
        if (midElement < element) return findIndexOfElementRec(array, mid + 1, end, element);
        else return findIndexOfElementRec(array, start, mid - 1, element);
    }
}
