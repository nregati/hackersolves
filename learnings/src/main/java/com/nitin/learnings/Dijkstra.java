/*
 * Copyright (c) 2017. [Author nitin_regati] [File Dijkstra.java]
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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Created by nitin_regati on 15/06/17.
 */
public class Dijkstra {

  /**
   * The entry point of application.
   *
   * @param args the input arguments
   */
  public static void main(String[] args) {

  }

  /**
   * Compute paths.
   *
   * @param source the source
   */
  public void computePaths(Vertex source) {

    PriorityQueue<Vertex> queue = new PriorityQueue<>();
    queue.offer(source);

    while (!queue.isEmpty()) {
      Vertex s = queue.poll();
      if (s.getAdjacencies().length == 0) {
        continue;
      }
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

  /**
   * Gets shortest path.
   *
   * @param target the target
   * @return the shortest path
   */
  public List<Vertex> getShortestPath(Vertex target) {

    List<Vertex> path = new ArrayList<>();
    for (Vertex v = target; v != null; v = v.getPrevious()) {
      path.add(v);
    }
    Collections.reverse(path);
    return path;
  }

  /**
   * The type Edge.
   */
  public static class Edge {

    private Vertex target;
    private int weight;

    /**
     * Instantiates a new Edge.
     *
     * @param target the target
     * @param weight the weight
     */
    public Edge(Vertex target, int weight) {
      this.target = target;
      this.weight = weight;
    }

    /**
     * Gets target.
     *
     * @return the target
     */
    public Vertex getTarget() {
      return target;
    }

    /**
     * Sets target.
     *
     * @param target the target
     */
    public void setTarget(Vertex target) {
      this.target = target;
    }

    /**
     * Gets weight.
     *
     * @return the weight
     */
    public int getWeight() {
      return weight;
    }

    /**
     * Sets weight.
     *
     * @param weight the weight
     */
    public void setWeight(int weight) {
      this.weight = weight;
    }
  }

  /**
   * The type Vertex.
   */
  public static class Vertex {

    private int minValue;
    private Edge[] adjacencies;
    private Vertex previous;

    /**
     * Instantiates a new Vertex.
     */
    public Vertex() {
      this.minValue = 0;
    }

    /**
     * Gets min value.
     *
     * @return the min value
     */
    public int getMinValue() {
      return minValue;
    }

    /**
     * Sets min value.
     *
     * @param minValue the min value
     */
    public void setMinValue(int minValue) {
      this.minValue = minValue;
    }

    /**
     * Get adjacencies edge [ ].
     *
     * @return the edge [ ]
     */
    public Edge[] getAdjacencies() {
      return adjacencies;
    }

    /**
     * Sets adjacencies.
     *
     * @param adjacencies the adjacencies
     */
    public void setAdjacencies(Edge[] adjacencies) {
      this.adjacencies = adjacencies;
    }

    /**
     * Gets previous.
     *
     * @return the previous
     */
    public Vertex getPrevious() {
      return previous;
    }

    /**
     * Sets previous.
     *
     * @param previous the previous
     */
    public void setPrevious(Vertex previous) {
      this.previous = previous;
    }
  }

}
