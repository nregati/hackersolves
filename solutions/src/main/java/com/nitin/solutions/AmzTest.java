/*
 * Copyright (c) 2017. [Author nitin_regati] [File AmzTest.java]
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

package com.nitin.solutions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class AmzTest {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int noofWords = Integer.parseInt(br.readLine());
        List<String> words = new ArrayList<>();
        for (int i = 0; i < noofWords; i++) {
            words.add(br.readLine());
        }
        words.sort((o1, o2) -> {
            if (o1.length() > o2.length()) {
                return 1;
            } else if (o1.length() < o2.length()) {
                return -1;
            } else {
                return 0;
            }
        });
        Map<Integer, List<String>> map = new HashMap<>();
        for (String word : words) {
            int len = word.length();
            List<String> wordToLen = map.get(len);
            if (null == wordToLen) {
                wordToLen = new ArrayList<>();
            }
            wordToLen.add(word);
            map.put(len, wordToLen);
        }

        for (Map.Entry<Integer, List<String>> entry : map.entrySet()) {
            Set<String> setToPrint = new TreeSet<>(entry.getValue());
            setToPrint.forEach(System.out::println);
        }

    }

}
