/*
 * Copyright (c) 2017. [Author nitin_regati] [File LazyBartender.java]
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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nitin_regati on 18/07/17.
 */
public class LazyBartender {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {

        List<int[]> custDrinks = new ArrayList<>();
        custDrinks.add(new int[]{2, 3, 5, 7, 9});
        custDrinks.add(new int[]{5});
        custDrinks.add(new int[]{2, 3});
        custDrinks.add(new int[]{4});
        custDrinks.add(new int[]{3, 4, 3, 5, 7});
    }

    /**
     * Solve.
     *
     * @param custDrinks the cust drinks
     */
    public void solve(List<int[]> custDrinks) {

        // TODO: 18/07/17
        /*
        Perform drink to customer relation
        Sort by length of customers for a drink
        Loop through available drinks and add the drink if it increases customer length else leave
         */
    }
}
