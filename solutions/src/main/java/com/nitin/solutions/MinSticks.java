/*
 * Copyright (c) 2017. [Author nitin_regati] [File MinSticks.java]
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
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by nitin_regati on 24/06/17.
 */
public class MinSticks {

    public static void main(String[] args) {

        try {
            solve();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    public static void solve() throws IOException, ParseException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int nofSticks = Integer.parseInt(br.readLine());
        List<Integer> sticks = new ArrayList<>();
        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        for (int i = 0; i < nofSticks; i++) {
            sticks.add(Integer.valueOf(tokenizer.nextToken()));
        }
        while (sticks.size() != 0) {
            System.out.println(sticks.size());
            Collections.sort(sticks);
            int minLen = sticks.get(0);
            List<Integer> newSticks = new ArrayList<>();
            for (int stick : sticks) {
                stick -= minLen;
                if (stick > 0)
                    newSticks.add(stick);
            }
            sticks.clear();
            sticks.addAll(newSticks);
        }
    }
}


