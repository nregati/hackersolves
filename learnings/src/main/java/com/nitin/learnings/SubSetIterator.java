/*
 * Copyright (c) 2017. [Author nitin_regati] [File SubSetIterator.java]
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 */

package com.nitin.learnings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SubSetIterator<E> {

    private List<E> list;
    private int index, max;

    public SubSetIterator(List<E> list) {
        this.list = list;
        max = 1 << list.size();
        this.index = 0;
    }

    public static void main(String[] args) {

        List<Integer> intList = Arrays.asList(1, 2, 3, 4, 5);
        List<Character> charList = Arrays.asList('a', 'b', 'c');

        SubSetIterator<Integer> intIterator = new SubSetIterator<>(intList);
        while (intIterator.hasNext()) {
            System.out.println(intIterator.next());
        }

        SubSetIterator<Character> charIterator = new SubSetIterator<>(charList);
        while (charIterator.hasNext()) {
            System.out.println(charIterator.next());
        }

    }

    public boolean hasNext() {
        return index < max;
    }

    public List<E> next() {
        List<E> tempList = new ArrayList<>();
        int flag = 1;
        for (E element : list) {
            if ((index & flag) != 0) {
                tempList.add(element);
            }
            flag <<= 1;
        }
        ++index;
        return tempList;
    }

}
