/*
 * Copyright (c) 2017. [Author nitin_regati] [File Graph.java]
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense,
 * and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do
 * so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.nitin.learnings;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by nitin_regati on 16/06/17.
 */
public class Graph {

    private int noVertices;
    private LinkedList<Integer> adj[];

    /**
     * Instantiates a new Graph.
     *
     * @param noVertices the no vertices
     */
    public Graph(int noVertices) {
        this.noVertices = noVertices;
        adj = new LinkedList[noVertices];
        for (int i = 0; i < noVertices; i++) {
            adj[i] = new LinkedList<>();
        }
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
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
        g.topologicalSort();
    }

    /**
     * Add edge.
     *
     * @param source the source
     * @param target the target
     */
    public void addEdge(int source, int target) {
        adj[source].add(target);
    }

    /**
     * Dfs util.
     *
     * @param vertex  the vertex
     * @param visited the visited
     */
    void DFSUtil(int vertex, boolean[] visited) {
        visited[vertex] = true;
        System.out.println(vertex);
        for (Integer newVertex : adj[vertex]) {
            if (!visited[newVertex])
                DFSUtil(newVertex, visited);
        }
    }

    /**
     * Dfs.
     *
     * @param vertex the vertex
     */
    void DFS(int vertex) {
        boolean[] visited = new boolean[noVertices];
        DFSUtil(vertex, visited);
    }

    /**
     * Bfs.
     *
     * @param vertex the vertex
     */
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

    /**
     * Topological sort util.
     *
     * @param vertex  the vertex
     * @param visited the visited
     * @param stack   the stack
     */
    void topologicalSortUtil(int vertex, boolean[] visited, Stack<Integer> stack) {

        visited[vertex] = true;
        System.out.println(vertex);
        for (Integer vtx : adj[vertex]) {
            if (!visited[vtx])
                topologicalSortUtil(vertex, visited, stack);
        }
        stack.push(vertex);
    }

    /**
     * Topological sort.
     * <p>
     * Topological sorting for Directed Acyclic Graph (DAG) is a linear ordering of vertices such that
     * for every directed edge uv, vertex u comes before v in the ordering. Topological Sorting for a
     * graph is not possible if the graph is not a DAG.
     * For example, a topological sorting of the following graph is “5 4 2 3 1 0”. There can be more than
     * one topological sorting for a graph. For example, another topological sorting of the following graph
     * is “4 5 2 3 1 0”. The first vertex in topological sorting is always a vertex with in-degree as 0 (a vertex with no in-coming edges).
     * <p>
     * Topological Sorting vs Depth First Traversal (DFS):
     * In DFS, we print a vertex and then recursively call DFS for its adjacent vertices. In topological
     * sorting, we need to print a vertex before its adjacent vertices. For example, in the given graph,
     * the vertex ‘5’ should be printed before vertex ‘0’, but unlike DFS, the vertex ‘4’ should also be
     * printed before vertex ‘0’. So Topological sorting is different from DFS. For example, a DFS of the
     * shown graph is “5 2 3 1 0 4”, but it is not a topological sorting
     * <p>
     * Algorithm to find Topological Sorting:
     * We recommend to first see implementation of DFS here. We can modify DFS to find Topological Sorting of
     * a graph. In DFS, we start from a vertex, we first print it and then recursively call DFS for its
     * adjacent vertices. In topological sorting, we use a temporary stack. We don’t print the vertex immediately,
     * we first recursively call topological sorting for all its adjacent vertices, then push it to a stack. Finally,
     * print contents of stack. Note that a vertex is pushed to stack only when all of its adjacent vertices (and their
     * adjacent vertices and so on) are already in stack.
     */
    void topologicalSort() {
        boolean[] visited = new boolean[noVertices];
        Stack<Integer> stack = new Stack<>();
        topologicalSortUtil(0, visited, stack);
        while (!stack.empty())
            System.out.println(" Topological sort: " + stack.pop());
    }
}
