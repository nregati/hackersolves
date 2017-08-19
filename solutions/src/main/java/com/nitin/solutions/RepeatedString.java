/*
 * Copyright (c) 2017. [Author nitin_regati] [File RepeatedString.java]
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
 * Created by nitin_regati on 12/07/17.
 */
public class RepeatedString {

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
    String str = sc.next();
    long len = sc.nextLong();
    if (str.length() == 0) {
      System.out.println(0);
      return;
    }
    if (str.length() == 1) {
      System.out.println(str.charAt(0) == 'a' ? len : 0);
      return;
    }

    System.out.println(
        len / str.length() * count(str, 'a') + count(str.substring(0, (int) (len % str.length())),
            'a'));
    sc.close();

  }

  /**
   * Count int.
   *
   * @param str the str
   * @param ch the ch
   * @return the int
   */
  public static int count(String str, char ch) {
    return (int) str.chars().filter(c -> c == ch).count();
  }

}
