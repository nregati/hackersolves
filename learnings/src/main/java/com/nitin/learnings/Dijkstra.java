/*
 * Copyright (c) 2017. [Author nitin_regati] [File Dijkstra.java]
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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Created by nitin_regati on 15/06/17.
 */
public class Dijkstra {

    public static void main(String[] args) {

    }

    public void computePaths(Vertex source) {

        PriorityQueue<Vertex> queue = new PriorityQueue<>();
        queue.offer(source);

        while (!queue.isEmpty()) {
            Vertex s = queue.poll();
            if (s.getAdjacencies().length == 0)
                continue;
            for (Edge e : s.getAdjacencies()) {
                Vertex t = e.getTarget();
                int wt = e.getWeight();
                int throughS = s.getMinValue() + wt;
                if (throughS < t.getMinValue()) {
                    queue.remove(t);
                    t.setMinValue(throughS);
                    t.setPrevious(s);
                    queue.add(t);
                }
            }
        }

    }

    public List<Vertex> getShortestPath(Vertex target) {

        List<Vertex> path = new ArrayList<>();
        for (Vertex v = target; v != null; v = v.getPrevious()) {
            path.add(v);
        }
        Collections.reverse(path);
        return path;
    }

    public class Edge {

        private Vertex target;
        private int weight;

        public Edge(Vertex target, int weight) {
            this.target = target;
            this.weight = weight;
        }

        public Vertex getTarget() {
            return target;
        }

        public void setTarget(Vertex target) {
            this.target = target;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }
    }

    public class Vertex {

        private int vertexID;
        private int minValue;
        private Edge[] adjacencies;
        private Vertex previous;

        public Vertex(int vertexID) {
            this.vertexID = vertexID;
            this.minValue = 0;
        }

        public int getMinValue() {
            return minValue;
        }

        public void setMinValue(int minValue) {
            this.minValue = minValue;
        }

        public Edge[] getAdjacencies() {
            return adjacencies;
        }

        public void setAdjacencies(Edge[] adjacencies) {
            this.adjacencies = adjacencies;
        }

        public Vertex getPrevious() {
            return previous;
        }

        public void setPrevious(Vertex previous) {
            this.previous = previous;
        }
    }

}
