/*
 * Copyright (c) 2017. [Author nitin_regati] [File KLargetElement.java]
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
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Created by nitin_regati on 02/08/17
 */
public class KLargetElement {

  /**
   * The entry point of application.
   *
   * @param args the input arguments
   */
  public static void main(String[] args) {

    List<Integer> intList = new ArrayList<>(10);
    intList.add(1);
    intList.add(2);
    intList.add(3);
    intList.add(4);
    intList.add(5);
    intList.add(6);
    intList.add(7);
    intList.add(8);
    intList.add(9);
    intList.add(10);
    getKLargest(intList, 5);
    getKSmallest(intList, 5);
  }

  private static void getKLargest(List<Integer> list, int k) {
    Queue<Integer> minHeap = new PriorityQueue<>(k);
    for (int i = 0; i < k; i++) {
      minHeap.offer(list.get(i));
    }
    for (int j = k; j < list.size(); j++) {
      int item = list.get(j);
      if (minHeap.peek() < item) {
        minHeap.poll();
        minHeap.offer(item);
      }
    }
    System.out.println(Arrays.toString(minHeap.toArray()));
  }

  private static void getKSmallest(List<Integer> list, int k) {
    Queue<Integer> maxHeap = new PriorityQueue<>(k, Collections.reverseOrder());
    for (int i = 0; i < k; i++) {
      maxHeap.offer(list.get(i));
    }
    for (int j = k; j < list.size(); j++) {
      int item = list.get(j);
      if (maxHeap.peek() > item) {
        maxHeap.poll();
        maxHeap.offer(item);
      }
    }
    System.out.println(Arrays.toString(maxHeap.toArray()));
  }

}
