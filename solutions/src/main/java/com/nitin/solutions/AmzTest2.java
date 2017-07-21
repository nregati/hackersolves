/*
 * Copyright (c) 2017. [Author nitin_regati] [File AmzTest2.java]
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

package com.nitin.solutions;

import java.util.Scanner;

/**
 * Created by nitin_regati on 13/07/17.
 */
public class AmzTest2 {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int arrSize = sc.nextInt();
        int[] arr = new int[arrSize];
        for (int i = 0; i < arrSize; i++) {
            arr[i] = sc.nextInt();
        }
        int swaps = 0;
        int nonZero = 0;
        int f = 0;
        int l = arrSize - 1;
        while (f <= l) {
            if (arr[f] == 0 && arr[l] != 0) {
                nonZero++;
                swaps++;
                int swap = arr[f];
                arr[f] = arr[l];
                arr[l] = swap;
                f++;
                l--;
            } else if (arr[f] != 0) {
                f++;
                nonZero++;
            } else if (arr[l] == 0) {
                l--;
            }
        }
        System.out.println(nonZero);
        System.out.println(swaps);
    }


}
