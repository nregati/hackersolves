/*
 * Copyright (c) 2017. [Author nitin_regati] [File BitMask.java]
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


import static com.nitin.learnings.BitMask.Smallest0And9DivisibleNumber.find;

/**
 * Created by nitin_regati on 23/06/17.
 */
public class BitMask {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {

        System.out.println("10 -> 90 " + find(10, 90));
        System.out.println("99 -> 99 " + find(99, 99));
        System.out.println("33 -> 99 " + find(33, 99));
        System.out.println("3 -> 9 " + find(3, 9));
        System.out.println("333 -> 999 " + find(333, 999));
        System.out.println("300 -> 900 " + find(300, 900));
        System.out.println("303 -> 909 " + find(303, 909));
        System.out.println("3033 -> 9099 " + find(3033, 9099));
        System.out.println("3303 -> 9909 " + find(3303, 9909));
        System.out.println("20 -> 900 " + find(20, 900));
    }

    /**
     * The type Smallest 0 and 9 divisible number.
     */
    static class Smallest0And9DivisibleNumber {
        /**
         * Find boolean.
         *
         * @param divisible the divisible
         * @param expected  the expected
         * @return the boolean
         */
        static boolean find(int divisible, int expected) {
            int bin = 1;
            while (true) {
                int res = translate(bin);
                if (res % divisible == 0) {
                    return res == expected;
                }
                bin += 1;
            }
        }

        private static int translate(int bin) {
            int result = 0;
            int flag = 1;
            int size = Integer.toBinaryString(bin).length();
            for (int i = 1; i <= size; i++) {
                result += ((bin & flag) != 0 ? 9 : 0) * Math.pow(10, i - 1);
                flag <<= 1;
            }
            return result;
        }

    }
}
