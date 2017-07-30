/*
 * Copyright (c) 2017. [Author nitin_regati] [File LinkedListAddition.java]
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
public class LinkedListAddition {

    private static int carry = 0;
    private static Node result, current;
    private Node header;

    private LinkedListAddition(Node header) {
        this.header = header;
    }

    private static void addSameSize(Node head1, Node head2) {

        if (null == head1)
            return;

        addSameSize(head1.next, head2.next);

        int sum = head1.val + head2.val + carry;
        carry = sum / 10;
        sum %= 10;
        addToList(sum);
    }

    private static void propagateCarry(Node node) {
        if (node != current) {
            propagateCarry(node.next);
            int sum = carry + node.val;
            carry = sum / 10;
            sum %= 10;
            addToList(sum);
        }
    }

    private static void printList(Node head) {
        while (null != head) {
            System.out.println(head.val + "->");
            head = head.next;
        }
    }

    private static void addToList(int val) {
        Node node = new Node(val);
        node.next = result;
        result = node;
    }

    public static void main(String[] args) {
        LinkedListAddition list1 = new LinkedListAddition(new Node(1));
        list1.getHeader().next = new Node(2);
        list1.getHeader().next.next = new Node(3);
        list1.getHeader().next.next.next = new Node(7);
        LinkedListAddition list2 = new LinkedListAddition(new Node(2));
        list2.getHeader().next = new Node(9);

        int size1 = list1.getSize();
        int size2 = list2.getSize();
        if (size1 == size2) {
            addSameSize(list1.getHeader(), list2.getHeader());
        } else {
            if (size1 < size2) {
                LinkedListAddition temp = list1;
                list1 = list2;
                list2 = temp;
            }
            int sizeDiff = Math.abs(size1 - size2);
            Node temp = list1.getHeader();
            while (sizeDiff-- >= 0) {
                current = temp;
                temp = temp.next;
            }
            addSameSize(current, list2.getHeader());
            propagateCarry(list1.getHeader());

        }
        if (carry > 0) {
            addToList(carry);
        }
        printList(result);
    }

    private int getSize() {
        int size = 0;
        while (null != this.header) {
            size++;
            this.header = this.header.next;
        }
        return size;
    }

    private Node getHeader() {
        return header;
    }

    private static class Node {
        private int val;
        private Node next;

        Node(int val) {
            this.val = val;
        }
    }

}
