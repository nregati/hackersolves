/*
 * Copyright (c) 2017. [Author nitin_regati] [File BinarySearch.java]
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
public class BinarySearch {

  /**
   * Search int.
   *
   * @param arr the arr
   * @param valueToSearch the value to search
   * @return the int
   */
  public static int search(int[] arr, int valueToSearch) {

    return searchUtil(arr, valueToSearch, 0, arr.length - 1);
  }

  private static int searchUtil(int[] arr, int valueToSearch, int low, int high) {

    if (high >= low) {

      int mid = (low + high) / 2;
      if (arr[mid] == valueToSearch) {
        return mid;
      }

      if (valueToSearch > arr[mid]) {
        return searchUtil(arr, valueToSearch, mid + 1, high);
      } else {
        return searchUtil(arr, valueToSearch, low, mid - 1);
      }
    }
    return -1;
  }

  /**
   * The entry point of application.
   *
   * @param args the input arguments
   */
  public static void main(String[] args) {

  }
}
