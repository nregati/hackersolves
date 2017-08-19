/*
 * Copyright (c) 2017. [Author nitin_regati] [File RadixSort.java]
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

import java.util.Arrays;

/**
 * Created by nitin_regati on 23/06/17. <p> The Radix Sort Algorithm 1) Do following for each digit
 * i where i varies from least significant digit to the most significant digit. ………….a) Sort input
 * array using counting sort (or any stable sort) according to the i’th digit. <p> Example:
 * Original, unsorted list: <p> 170, 45, 75, 90, 802, 24, 2, 66 Sorting by least significant digit
 * (1s place) gives: [*Notice that we keep 802 before 2, because 802 occurred before 2 in the
 * original list, and similarly for pairs 170 & 90 and 45 & 75.] <p> 170, 90, 802, 2, 24, 45, 75, 66
 * Sorting by next digit (10s place) gives: [*Notice that 802 again comes before 2 as 802 comes
 * before 2 in the previous list.] <p> 802, 2, 24, 45, 66, 170, 75, 90 Sorting by most significant
 * digit (100s place) gives: <p> 2, 24, 45, 66, 75, 90, 170, 802
 */
class RadixSort {

  /**
   * The entry point of application.
   *
   * @param args the input arguments
   */
/*Driver function to check for above function*/
  public static void main(String[] args) {
    int arr[] = {170, 45, 75, 90, 802, 24, 2, 66};
    int n = arr.length;
    radixsort(arr, n);
    print(arr, n);
  }

  /**
   * Radixsort.
   *
   * @param arr the arr
   * @param n the n
   */
// The main function to that sorts arr[] of size n using
  // RadixSort Sort
  static void radixsort(int arr[], int n) {
    // Find the maximum number to know number of digits
    int m = getMax(arr, n);

    // Do counting sort for every digit. Note that instead
    // of passing digit number, exp is passed. exp is 10^i
    // where i is current digit number
    for (int exp = 1; m / exp > 0; exp *= 10) {
      countSort(arr, n, exp);
    }
  }

  /**
   * Print.
   *
   * @param arr the arr
   * @param n the n
   */
// A utility function to print an array
  static void print(int arr[], int n) {
    for (int i = 0; i < n; i++) {
      System.out.print(arr[i]);
    }
  }

  /**
   * Gets max.
   *
   * @param arr the arr
   * @param n the n
   * @return the max
   */
// A utility function to get maximum value in arr[]
  static int getMax(int arr[], int n) {
    int mx = arr[0];
    for (int i = 1; i < n; i++) {
      if (arr[i] > mx) {
        mx = arr[i];
      }
    }
    return mx;
  }

  /**
   * Count sort.
   *
   * @param arr the arr
   * @param n the n
   * @param exp the exp
   */
// A function to do counting sort of arr[] according to
  // the digit represented by exp.
  static void countSort(int arr[], int n, int exp) {
    int output[] = new int[n]; // output array
    int i;
    int count[] = new int[10];
    Arrays.fill(count, 0);

    // Store count of occurrences in count[]
    for (i = 0; i < n; i++) {
      count[(arr[i] / exp) % 10]++;
    }

    // Change count[i] so that count[i] now contains
    // actual position of this digit in output[]
    for (i = 1; i < 10; i++) {
      count[i] += count[i - 1];
    }

    // Build the output array
    for (i = n - 1; i >= 0; i--) {
      output[count[(arr[i] / exp) % 10] - 1] = arr[i];
      count[(arr[i] / exp) % 10]--;
    }

    // Copy the output array to arr[], so that arr[] now
    // contains sorted numbers according to curent digit
    for (i = 0; i < n; i++) {
      arr[i] = output[i];
    }
  }
}
