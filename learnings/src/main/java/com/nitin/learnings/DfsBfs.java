/*
 * Copyright (c) 2017. [Author nitin_regati] [File DfsBfs.java]
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

/**
 * Created by nitin_regati on 16/06/17.
 */
public class DfsBfs {

    public static void main(String[] args) {
        Graph g = new Graph(7);
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 3);
        g.addEdge(1, 4);
        g.addEdge(4, 6);
        g.addEdge(2, 5);
        System.out.println("DFS: ");
        g.DFS(0);
        System.out.println("BFS: ");
        g.BFS(0);
    }
}
