/*
 * Copyright (c) 2017. [Author nitin_regati] [File Graph.java]
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

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by nitin_regati on 16/06/17.
 */
public class Graph {
    private int noVertices;
    private LinkedList<Integer> adj[];

    public Graph(int noVertices) {
        this.noVertices = noVertices;
        adj = new LinkedList[noVertices];
        for (int i = 0; i < noVertices; i++) {
            adj[i] = new LinkedList<>();
        }
    }

    public void addEdge(int source, int target) {
        adj[source].add(target);
    }

    void DFSUtil(int vertex, boolean[] visited) {
        visited[vertex] = true;
        System.out.println(vertex + " ");
        Iterator<Integer> itr = adj[vertex].iterator();
        while (itr.hasNext()) {
            int newVertex = itr.next();
            if (!visited[newVertex])
                DFSUtil(newVertex, visited);
        }
    }

    void DFS(int vertex) {
        boolean[] visited = new boolean[noVertices];
        DFSUtil(vertex, visited);
    }

    void BFS(int vertex) {
        Queue<Integer> bfsQueue = new LinkedList<>();
        boolean[] visited = new boolean[noVertices];
        bfsQueue.offer(vertex);
        while (!bfsQueue.isEmpty()) {
            int vtx = bfsQueue.poll();
            visited[vtx] = true;
            System.out.println(vtx);
            Iterator<Integer> itr = adj[vtx].iterator();
            while (itr.hasNext()) {
                int newVtx = itr.next();
                if (!visited[newVtx])
                    bfsQueue.offer(newVtx);
            }
        }
    }
}
