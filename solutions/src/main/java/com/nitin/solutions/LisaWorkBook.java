/*
 * Copyright (c) 2017. [Author nitin_regati] [File LisaWorkBook.java]
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
 * Created by nitin_regati on 13/07/17.
 */
public class LisaWorkBook {

    public static void main(String[] args) {
        solve();
    }

    public static void solve() {
        Scanner sc = new Scanner(System.in);
        int chap = sc.nextInt();
        int probPerPage = sc.nextInt();
        int page = 1, specialProb = 0;
        for (int i = 1; i <= chap; i++) {
            int probs = sc.nextInt();
            for (int j = 1; j <= probs; j++) {
                if (j == page)
                    specialProb++;
                if (j % probPerPage == 0)
                    page++;
            }
        }
        System.out.println(specialProb);
    }
}