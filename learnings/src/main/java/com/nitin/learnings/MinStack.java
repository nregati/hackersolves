/*
 * Copyright (c) 2017. [Author nitin_regati] [File MinStack.java]
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

import java.util.Stack;

/**
 * Created by nitin_regati on 02/08/17
 */
public class MinStack {

  public static void main(String[] args) {

    ExtendedStack<Integer> stack = new ExtendedStack<>();
    stack.push(18);
    System.out.println();
    System.out.println(stack.getMin() == 18);
    stack.push(21);
    System.out.println(stack.getMin() == 18);
    stack.push(15);
    System.out.println(stack.getMin() == 15);
    stack.pop();
    System.out.println(stack.getMin() == 18);
    stack.push(-1);
    System.out.println(stack.getMin() == -1);
    stack.push(80);
    System.out.println(stack.getMin() == 80);
  }

  private static class ExtendedStack<E extends Comparable<E>> extends Stack<E> {

    private Stack<E> minStack;

    public ExtendedStack() {
      minStack = new Stack<>();
    }

    @Override
    public E push(E item) {
      E e = super.push(item);
      if (minStack.empty()) {
        minStack.push(e);
      } else if (e.compareTo(minStack.peek()) <= 0) {
        minStack.push(e);
      }
      return e;
    }

    @Override
    public synchronized E pop() {
      E e = super.pop();
      if (!minStack.empty() && e.compareTo(minStack.peek()) == 0) {
        minStack.pop();
      }
      return e;
    }

    public E getMin() {
      return minStack.peek();
    }
  }
}
