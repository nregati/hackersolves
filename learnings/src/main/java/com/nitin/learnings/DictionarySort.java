/*
 * Copyright (c) 2017. [Author nitin_regati] [File DictionarySort.java]
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
import java.util.Comparator;

/**
 * Created by nitin_regati on 02/08/17
 */
public class DictionarySort {

  public static void main(String[] args) {

    DictionaryList<String> dictionaryList = new DictionaryList<>();
    dictionaryList.add("abc");
    dictionaryList.add("zzz");
    dictionaryList.add("def");
    dictionaryList.add("abd");
    dictionaryList.add("abcd");
    dictionaryList.add("abyd");
    dictionaryList.add("abydx");
    dictionaryList.add("z");
    System.out.println("UnSorted: " + Arrays.toString(dictionaryList.toArray()));
    dictionaryList.sort();
    System.out.println("Sorted: " + Arrays.toString(dictionaryList.toArray()));
  }

  private static class DictionaryList<E> extends ArrayList<E> {

    void sort() {
      super.sort(new DictComparator<>());
    }

    private static class DictComparator<E> implements Comparator<E> {

      @Override
      public int compare(Object o1, Object o2) {
        int sum1 = o1.toString().chars().sum();
        int sum2 = o2.toString().chars().sum();
        return Integer.compare(sum1, sum2);
      }
    }
  }
}
