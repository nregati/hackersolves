/*
 * Copyright (c) 2017. [Author nitin_regati] [File ArrayEqualize.java]
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

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by nitin_regati on 12/07/17.
 */
public class ArrayEqualize {

    public static void main(String[] args) {
        solve();
    }

    static void solve() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Map<Integer, Integer> frequencyMap = new HashMap<>(n);
        int maxVal = 0;
        for (int i = 0; i < n; i++) {
            int key = sc.nextInt();
            frequencyMap.compute(key, (k, v) -> (v == null) ? 1 : ++v);
            int newVal = frequencyMap.get(key);
            if (newVal > maxVal)
                maxVal = newVal;
        }
        System.out.println(n - maxVal);

    }

}
