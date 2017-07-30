/*
 * Copyright (c) 2017. [Author nitin_regati] [File FrequencyItem.java]
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense,
 * and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do
 * so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.nitin.learnings;

/**
 * Created by nitin_regati on 30/07/17
 */
public class FrequencyItem {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {

        int b[] = {2, 2, 2, 2, 2, 2, 4, 4, 4, 4, 10, 10, 10, 18, 18, 20, 20, 20, 20, 20};

        System.out.println("First occurrence of 10: " + FrequencyItem.getFirstOccurrence(b, 10));
        System.out.println("Last occurrence of 10: " + FrequencyItem.getLastOccurrence(b, 10));
        System.out.println("Count total occurrences of 10: " + FrequencyItem.getFrequency(b, 10));

        System.out.println("First occurrence of 2: " + FrequencyItem.getFirstOccurrence(b, 2));
        System.out.println("Last occurrence of 2: " + FrequencyItem.getLastOccurrence(b, 2));
        System.out.println("Count total occurrences of 2: " + FrequencyItem.getFrequency(b, 2));
    }

    private static int getFirstOccurrence(int[] arr, int valueToSearch) {

        int low = 0;
        int high = arr.length - 1;
        int result = -1;


        while (low <= high) {
            int mid = (high + low) / 2;

            if (valueToSearch == arr[mid]) {
                result = mid;
                high = mid - 1;
            } else if (valueToSearch > arr[mid]) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return result;
    }

    private static int getLastOccurrence(int[] arr, int valueToSearch) {

        int low = 0;
        int high = arr.length - 1;
        int result = -1;


        while (low <= high) {
            int mid = (high + low) / 2;

            if (valueToSearch == arr[mid]) {
                result = mid;
                low = mid + 1;
            } else if (valueToSearch > arr[mid]) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return result;
    }

    private static int getFrequency(int[] arr, int value) {
        return getLastOccurrence(arr, value) - getFirstOccurrence(arr, value) + 1;
    }
}
