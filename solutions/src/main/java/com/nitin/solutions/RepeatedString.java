/*
 * Copyright (c) 2017. [Author nitin_regati] [File RepeatedString.java]
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
public class RepeatedString {

    public static void main(String[] args) {
        solve();
    }

    public static void solve() {
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        long len = sc.nextLong();
        if (str.length() == 0) {
            System.out.println(0);
            return;
        }
        if (str.length() == 1) {
            System.out.println(str.charAt(0) == 'a' ? len : 0);
            return;
        }

        System.out.println(len / str.length() * count(str, 'a') + count(str.substring(0, (int) (len % str.length())), 'a'));
        sc.close();

    }

    public static int count(String str, char ch) {
        return (int) str.chars().filter(c -> c == ch).count();
    }

}
