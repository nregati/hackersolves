/*
 * Copyright (c) 2017. [Author nitin_regati] [File QueenAttack.java]
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
public class QueenAttack {

    public static void main(String[] args) {
        solve();
    }

    static void solve() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[][] board = new int[n][n];
        int rQueen = sc.nextInt() - 1;
        int cQueen = sc.nextInt() - 1;
        board[rQueen][cQueen] = 1; //Queens position
        for (int i = 0; i < k; i++) {
            board[sc.nextInt() - 1][sc.nextInt() - 1] = -1; //Obstacles
        }
        int steps = 0;
        for (int r = rQueen, c = cQueen; c < n; c++) {
            if (board[r][c] == -1)
                break;
            steps++;
        }
        for (int r = rQueen, c = cQueen; c >= 0; c--) {
            if (board[r][c] == -1)
                break;
            steps++;
        }
        for (int r = rQueen, c = cQueen; r < n; r++) {
            if (board[r][c] == -1)
                break;
            steps++;
        }
        for (int r = rQueen, c = cQueen; r >= 0; r--) {
            if (board[r][c] == -1)
                break;
            steps++;
        }
        for (int r = rQueen, c = cQueen; r < n && c < n; r++, c++) {
            if (board[r][c] == -1)
                break;
            steps++;
        }
        for (int r = rQueen, c = cQueen; r >= 0 && c >= 0; r--, c--) {
            if (board[r][c] == -1)
                break;
            steps++;
        }
        for (int r = rQueen, c = cQueen; r >= 0 && c < n; r--, c++) {
            if (board[r][c] == -1)
                break;
            steps++;
        }
        for (int r = rQueen, c = cQueen; r < n && c >= 0; r++, c--) {
            if (board[r][c] == -1)
                break;
            steps++;
        }

        System.out.println(steps - 8);

    }
}
