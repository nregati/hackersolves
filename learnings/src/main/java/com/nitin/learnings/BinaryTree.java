/*
 * Copyright (c) 2017. [Author nitin_regati] [File BinaryTree.java]
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

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Objects;

/**
 * Created by nitin_regati on 21/07/17.
 */
public class BinaryTree {

  /**
   * The Root.
   */
  private Node root;

  /**
   * Instantiates a new Binary tree.
   *
   * @param root the root
   */
  private BinaryTree(Node root) {
    this.root = root;
  }

  /**
   * The entry point of application.
   *
   * @param args the input arguments
   */
  public static void main(String[] args) {

    Node root = new Node(1);
    root.left = new Node(2);
    root.right = new Node(3);
    root.left.left = new Node(4);
    root.left.right = new Node(5);
    root.right.left = new Node(6);
    root.right.right = new Node(7);
    root.right.left.left = new Node(8);

    BinaryTree tree = new BinaryTree(root);

//        lcaMain(tree);
//        distMain(tree);
    preOrderMain(tree);
//        inOredrMain(tree);
//        postOrderMain(tree);
//        morrisInOrder(tree);
    preOrderMain(deserializeBinaryTree(serializeBinaryTree(tree)));

  }

  /**
   * The distance between two nodes can be obtained in terms of lowest common ancestor. Following is
   * the formula. Dist(n1, n2) = Dist(root, n1) + Dist(root, n2) - 2*Dist(root, lca) 'n1' and 'n2'
   * are the two given keys 'root' is root of given Binary Tree. 'lca' is lowest common ancestor of
   * n1 and n2 Dist(n1, n2) is the distance between n1 and n2.
   */
  private static void distMain(BinaryTree tree) {
    System.out.println(String.format("Distance(%d, %d) = %d %n", 4, 5, tree.getDistance(4, 5)));
    System.out.println(String.format("Distance(%d, %d) = %d %n", 4, 6, tree.getDistance(4, 6)));
    System.out.println(String.format("Distance(%d, %d) = %d %n", 3, 4, tree.getDistance(3, 4)));
    System.out.println(String.format("Distance(%d, %d) = %d %n", 2, 4, tree.getDistance(2, 4)));
    System.out.println(String.format("Distance(%d, %d) = %d %n", 8, 5, tree.getDistance(8, 5)));
  }

  /**
   * Lowest Common Ancestor in a Binary Tree: Given a binary tree (not a binary search tree) Let T
   * be a rooted tree. The lowest common ancestor between two nodes n1 and n2 is defined as the
   * lowest node in T that has both n1 and n2 as descendants (where we allow a node to be a
   * descendant of itself). <p> The LCA of n1 and n2 in T is the shared ancestor of n1 and n2 that
   * is located farthest from the root. Computation of lowest common ancestors may be useful, for
   * instance, as part of a procedure for determining the distance between pairs of nodes in a tree:
   * the distance from n1 to n2 can be computed as the distance from the root to n1, plus the
   * distance from the root to n2, minus twice the distance from the root to their lowest common
   * ancestor.
   */
  private static void lcaMain(BinaryTree tree) {
    Node lca = tree.findLCA(4, 5);
    if (null != lca) {
      System.out.println("LCA(4,5) = " + lca.data);
    }
    if (null != lca) {
      lca = tree.findLCA(6, 7);
    }
    if (null != lca) {
      System.out.println("LCA(6,7) = " + lca.data);
    }
    lca = tree.findLCA(4, 3);
    if (null != lca) {
      System.out.println("LCA(4,3) = " + lca.data);
    }
    lca = tree.findLCA(4, 2);
    if (null != lca) {
      System.out.println("LCA(4,2) = " + lca.data);
    }
    lca = tree.findLCA(1, 3);
    if (null != lca) {
      System.out.println("LCA(1,3) = " + lca.data);
    }
  }

  private static void morrisInOrder(BinaryTree tree) {
    Node current = tree.root;

    while (null != current) {
      if (null == current.left) {
        System.out.println(current.data);
        current = current.right;
      } else {

        Node temp = current.left;
        while (null != temp.right && current != temp.right) { //Inorder predecessor
          temp = temp.right;
        }
        if (temp.right == current) {
          System.out.println(current.data);
          temp.right = null;
          current = current.right;
        } else {
          temp.right = current;
          current = current.left;
        }

      }
    }
  }

  private static void preOrderMain(BinaryTree tree) {
    preOrderUtil(tree.root);
//        preOrderRecursive(tree.root);
  }

  private static void preOrderUtil(Node rootNode) {

    Deque<Node> stack = new ArrayDeque<>();
    stack.push(rootNode);
    while (!stack.isEmpty()) {
      Node newNode = stack.pop();
      System.out.println(newNode.data);
      if (null != newNode.right) {
        stack.push(newNode.right);
      }
      if (null != newNode.left) {
        stack.push(newNode.left);
      }
    }

  }

  private static void preOrderRecursive(Node rootNode) {

    if (null == rootNode) {
      return;
    }

    System.out.println(rootNode.data);
    preOrderRecursive(rootNode.left);
    preOrderRecursive(rootNode.right);
  }

  private static void inOrderRecursive(Node rootNode) {
    if (null == rootNode) {
      return;
    }
    inOrderRecursive(rootNode.left);
    System.out.println(rootNode.data);
    inOrderRecursive(rootNode.right);
  }

  private static void postOrderRecursive(Node rootNode) {
    if (null == rootNode) {
      return;
    }
    postOrderRecursive(rootNode.left);
    postOrderRecursive(rootNode.right);
    System.out.println(rootNode.data);
  }

  private static void inOredrMain(BinaryTree tree) {
    inOrderUtil(tree.root);
  }

  private static void inOrderUtil(Node rootNode) {

    Deque<Node> stack = new ArrayDeque<>();

    Node currentNode = rootNode;
    stack.push(currentNode);
    currentNode = currentNode.left;

    while (!stack.isEmpty() || null != currentNode) {
      while (null != currentNode) {
        stack.push(currentNode);
        currentNode = currentNode.left;
      }
      currentNode = stack.pop();
      System.out.println(currentNode.data);
      currentNode = currentNode.right;
    }

  }

  private static void postOrderMain(BinaryTree tree) {
    postOrderUtil(tree.root);
  }

  private static void postOrderUtil(Node rootNode) {

    Deque<Node> stack = new ArrayDeque<>();

    Node currentNode = rootNode;
    if (null != currentNode.right) {
      stack.push(currentNode.right);
    }
    stack.push(currentNode);
    currentNode = currentNode.left;

    while (!stack.isEmpty()) {

      while (null != currentNode) {
        if (null != currentNode.right) {
          stack.push(currentNode.right);
        }
        stack.push(currentNode);
        currentNode = currentNode.left;
      }

      currentNode = stack.pop();
      if (currentNode.right == stack.peek()) {
        Node temp = stack.pop();
        stack.push(currentNode);
        currentNode = temp;
      } else {
        System.out.println(currentNode.data);
        currentNode = null;
      }

    }
  }

  private static String serializeBinaryTree(BinaryTree tree) {

    StringBuilder builder = new StringBuilder();
    Node currentNode = tree.root;
    return serializeBinaryTreeHelper(currentNode, builder);
  }

  private static String serializeBinaryTreeHelper(Node node, StringBuilder builder) {

    if (node == null) {
      builder.append("# ");
      return builder.toString();
    }
    builder.append(node.data + " ");
    serializeBinaryTreeHelper(node.left, builder);
    serializeBinaryTreeHelper(node.right, builder);
    return builder.toString().trim();
  }

  private static BinaryTree deserializeBinaryTree(String preOrder) {
    // 1 2 4 # # 5 # # 3 6 8 # # # 7 # #
    if (null == preOrder) {
      return null;
    }
    String[] pre = preOrder.split(" ");
    int[] t = {0};
    Node root = deserializeBinaryTreeHelper(pre, t);
    return new BinaryTree(root);
  }

  private static Node deserializeBinaryTreeHelper(String[] data, int[] index) {
    if (index[0] >= data.length || Objects.equals(data[index[0]], "#")) {
      return null;
    }
    Node node = new Node(Integer.parseInt(data[index[0]]));
    index[0] += 1;
    node.left = deserializeBinaryTreeHelper(data, index);
    index[0] += 1;
    node.right = deserializeBinaryTreeHelper(data, index);
    return node;
  }

  private int getDistance(int n1, int n2) {

    Node lca = findLCA(n1, n2);
    return getHeight(root, n1, 0) + getHeight(root, n2, 0) - 2 * (getHeight(root,
        lca != null ? lca.data : root.data, 0));
  }

  private int getHeight(Node node, int n1, int currentHeight) {

    if (null == node) {
      return -1; // Reached leaf node
    }

    if (node.data == n1) {
      return currentHeight;
    }

    int l_level = getHeight(node.left, n1, currentHeight + 1);
    return l_level != -1 ? l_level : getHeight(node.right, n1, currentHeight + 1);
  }

  /**
   * @param n1
   * @param n2
   * @return
   */
  private Node findLCA(int n1, int n2) {
    return findLCAUtil(this.root, n1, n2);
  }

  /**
   * @param node
   * @param n1
   * @param n2
   * @return
   */
  private Node findLCAUtil(Node node, int n1, int n2) {

    if (null == node) {
      return null;
    }

    if (node.data == n1 || node.data == n2) {
      return node;
    }

    Node l_LCA = findLCAUtil(node.left, n1, n2);
    Node r_LCA = findLCAUtil(node.right, n1, n2);

    if (l_LCA != null && r_LCA != null) {
      return node;
    }

    return l_LCA != null ? l_LCA : r_LCA;
  }

  /**
   * The type Node.
   */
  static class Node {

    private int data;
    private Node left, right;

    /**
     * Instantiates a new Node.
     *
     * @param data the data
     */
    Node(int data) {
      this.data = data;
      left = right = null;
    }


  }
}
