/*
 * Copyright (c) 2017. [Author nitin_regati] [File JumpingClouds.java]
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

import java.util.Scanner;

/**
 * Created by nitin_regati on 12/07/17.
 */
public class JumpingClouds {

    public static void main(String[] args) {
        solve();
    }

    static void solve() {
        Scanner sc = new Scanner(System.in);
        int nofClouds = sc.nextInt();
        int[] clouds = new int[nofClouds];
        for (int i = 0; i < nofClouds; i++) {
            clouds[i] = sc.nextInt();
        }
        int count = -1;
        for (int i = 0; i < nofClouds; i++, count++) {
            if (i < nofClouds - 2 && clouds[i + 2] == 0) i++;
        }
        System.out.println(count);
    }
}
