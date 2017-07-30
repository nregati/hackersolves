/*
 * Copyright (c) 2017. [Author nitin_regati] [File KWayPartitionArray.java]
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by nitin_regati on 30/07/17
 */
public class KWayPartitionArray {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        int[] arr = {2, 1, 4, 5, 6};
        printEqualSumPartitions(arr, 3);
        printEqualSumPartitions(arr, 2);
    }

    private static void printEqualSumPartitions(int[] arr, int part) {

        if (part == 1)
            System.out.println(Arrays.toString(arr));
        else if (part > arr.length || Arrays.stream(arr).sum() % part != 0)
            System.out.println("Cannot partition");
        else {
            List<List<Integer>> partitons = new ArrayList<>();
            Arrays.sort(arr);
            int i = 0;
            for (i = arr.length - 1; i >= arr.length - part; i--) {
                List<Integer> temp = new ArrayList<>();
                temp.add(arr[i]);
                partitons.add(temp);
            }
            while (i >= 0) {
                partitons.get(minSumIndex(partitons)).add(arr[i]);
                i--;
            }
            partitons.forEach(System.out::println);
        }
    }

    private static int minSumIndex(List<List<Integer>> list) {
        int index = 0, sum = Integer.MAX_VALUE;
        for (int k = 0; k < list.size(); k++) {
            int tempSum = list.get(k).stream().mapToInt(Integer::intValue).sum();
            if (sum > tempSum) {
                sum = tempSum;
                index = k;
            }
        }
        return index;
    }
}
