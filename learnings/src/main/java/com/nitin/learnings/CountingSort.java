/*
 * Copyright (c) 2017. [Author nitin_regati] [File CountingSort.java]
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
 * Created by nitin_regati on 23/06/17.
 * <p>
 * Counting Sort Algorithm
 * For simplicity, consider the data in the range 0 to 9.
 * Input data: 1, 4, 1, 2, 7, 5, 2
 * 1) Take a count array to store the count of each unique object.
 * Index:     0  1  2  3  4  5  6  7  8  9
 * Count:     0  2  2  0   1  1  0  1  0  0
 * <p>
 * 2) Modify the count array such that each element at each index
 * stores the sum of previous counts.
 * Index:     0  1  2  3  4  5  6  7  8  9
 * Count:     0  2  4  4  5  6  6  7  7  7
 * <p>
 * The modified count array indicates the position of each object in
 * the output sequence.
 * <p>
 * 3) Output each object from the input sequence followed by
 * decreasing its count by 1.
 * Process the input data: 1, 4, 1, 2, 7, 5, 2. Position of 1 is 2.
 * Put data 1 at index 2 in output. Decrease count by 1 to place
 * next data 1 at an index 1 smaller than this index.
 * <p>
 */
public class CountingSort {

    /**
     * Main.
     *
     * @param args the args
     */
// Driver method
    public static void main(String args[]) {
        CountingSort ob = new CountingSort();
        char arr[] = {'g', 'e', 'e', 'k', 's', 'f', 'o',
                'r', 'g', 'e', 'e', 'k', 's'
        };

        ob.sort(arr);

        System.out.print("Sorted character array is ");
        for (int i = 0; i < arr.length; ++i)
            System.out.print(arr[i]);
    }

    /**
     * Sort.
     *
     * @param arr the arr
     */
    void sort(char arr[]) {
        int n = arr.length;

        // The output character array that will have sorted arr
        char output[] = new char[n];

        // Create a count array to store count of inidividul
        // characters and initialize count array as 0
        int count[] = new int[256];
        for (int i = 0; i < 256; ++i)
            count[i] = 0;

        // store count of each character
        for (int i = 0; i < n; ++i)
            ++count[arr[i]];

        // Change count[i] so that count[i] now contains actual
        // position of this character in output array
        for (int i = 1; i <= 255; ++i)
            count[i] += count[i - 1];

        // Build the output character array
        for (int i = 0; i < n; ++i) {
            output[count[arr[i]] - 1] = arr[i];
            --count[arr[i]];
        }

        // Copy the output array to arr, so that arr now
        // contains sorted characters
        for (int i = 0; i < n; ++i)
            arr[i] = output[i];
    }
}
