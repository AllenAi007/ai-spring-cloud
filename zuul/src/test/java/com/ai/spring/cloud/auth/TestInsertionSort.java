package com.ai.spring.cloud.auth;

import org.junit.Test;

import java.util.Arrays;

/**
 * Insertion sort is a simple sorting algorithm that works the way we sort playing cards in our hands.
 */
public class TestInsertionSort {

    private static final int[] NUMBERS =
            {49, 38, 65, 97, 76, 13, 27, 78, 34, 12, 64, 5, 4, 62, 99, 98, 54, 56, 17, 18, 23, 34, 15, 35, 25, 53, 51};

    /**
     * Time complexity O(n*n)
     */

    public void insertionSort() {
        int n = NUMBERS.length;
        for (int i = 1; i < n; i++) {
            int temp = NUMBERS[i];
            int j = i - 1;
            while (j >= 0 && NUMBERS[j] > temp) {
                NUMBERS[j + 1] = NUMBERS[j];
                j--;
            }
            NUMBERS[j + 1] = temp;
        }
    }

    @Test
    public void testInsertionSort() {
        insertionSort();
        System.out.println(Arrays.toString(NUMBERS));

    }
}
