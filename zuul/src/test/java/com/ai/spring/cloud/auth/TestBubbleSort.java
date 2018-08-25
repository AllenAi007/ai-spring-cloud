package com.ai.spring.cloud.auth;

import org.junit.Test;

import java.util.Arrays;

/**
 * Bubble Sort is the simplest sorting algorithm that works by repeatedly swapping the adjacent elements if they are in wrong order.
 */
public class TestBubbleSort {

    private static final int[] NUMBERS =
            {49, 38, 65, 97, 76, 13, 27, 78, 34, 12, 64, 5, 4, 62, 99, 98, 54, 56, 17, 18, 23, 34, 15, 35, 25, 53, 51};


    /**
     * Sort 1,  bubble sort
     * <p>
     * Time Complexity, worse case: O(n*n), Best Case O(n)
     */
    public void bubbleSort() {
        int i, j, n = NUMBERS.length;
        for (i = 0; i < n - 1; i++) {
            for (j = 0; j < n - 1 - i; j++) {
                if (NUMBERS[j] > NUMBERS[j + 1]) {
                    // swap
                    int temp = NUMBERS[j];
                    NUMBERS[j] = NUMBERS[j + 1];
                    NUMBERS[j + 1] = temp;
                }
            }
        }
    }

    @Test
    public void testBubbleSort() {
        bubbleSort();
        printNumber();
    }


    void printNumber() {
        System.out.println(Arrays.toString(NUMBERS));
    }
}
