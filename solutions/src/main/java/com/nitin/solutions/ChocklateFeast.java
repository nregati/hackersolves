/*
 * Copyright (c) 2017. [Author nitin_regati] [File ChocklateFeast.java]
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
public class ChocklateFeast {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        solve();
    }

    /**
     * Solve.
     */
    public static void solve() {

        Scanner sc = new Scanner(System.in);
        int trips = sc.nextInt();
        int[] chocklates = new int[trips];
        for (int i = 0; i < trips; i++) {
            int n = sc.nextInt();
            int c = sc.nextInt();
            int m = sc.nextInt();
            chocklates[i] = n / c;
            int wrapers = chocklates[i];
            while (wrapers >= m) {
                int exchange = wrapers / m;
                chocklates[i] += exchange;
                wrapers = wrapers % m + exchange;
            }
        }
        for (int i = 0; i < trips; i++)
            System.out.println(chocklates[i]);
    }
}
