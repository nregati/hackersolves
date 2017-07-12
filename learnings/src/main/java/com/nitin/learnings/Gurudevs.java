/*
package com.nitin.learnings;

import java.util.*;
import java.util.stream.Collectors;

*/
/**
 * Created by nitin_regati on 12/06/17.
 *//*

public class Gurudevs {

    public static void main(String[] args) {

    }

    public static int getMaxPaths(com.nitin.test.Graph g, int nodeId) {

        Stack<Integer> dfs = new Stack<>();
        dfs.push(nodeId);
        g.setVisited(nodeId);
        boolean flag = false;
        int sum = 0;
        while (!g.isAllVerticesVisited()) {
            int currNodeId = dfs.peek();
            if (g.getVertex(currNodeId).hasChildren()) {
                List<Node> childNodes = g.getVertex(currNodeId).getChildren();
                childNodes.forEach(child -> {
                    if (!g.isVisited(child.getID())) {
                        sum += g.getEdgeValue(currNodeId, child.getID());
                        dfs.push(child.getID());
                        g.setVisited(child.getID());
                    }
                });
            }
            else {
                dfs.pop();
            }
        }
    }

    public static class com.nitin.test.Graph {

        private int verticesSize;
        Map<Integer, Node> vertices;
        Map<String, Integer> edgeValues;
        Map<Integer, Boolean> visitedVertices;

        public com.nitin.test.Graph(int verticesSize) {
            this.verticesSize = verticesSize;
            this.vertices = new HashMap<>(verticesSize);
            this.edgeValues = new HashMap<>();
            this.visitedVertices = new HashMap<>();
        }

        public com.nitin.test.Graph() {
            this(0);
        }

        public void addEdge(int leftNodeId, int rightNodeId, int edgeValue) {
            Node leftNode = this.vertices.computeIfAbsent(leftNodeId, Node::new);
            Node rightNode = this.vertices.computeIfAbsent(rightNodeId, Node::new);
            this.edgeValues.put(leftNodeId+","+rightNodeId,edgeValue);
        }

        public void setVisited(int vertexId) {
            this.visitedVertices.put(vertexId, true);
        }

        public boolean isVisited(int vertexId) {
            this.visitedVertices.putIfAbsent(vertexId, false);
            return this.visitedVertices.get(vertexId);
        }

        public Node getVertex(int vertexId) {
            return this.vertices.get(vertexId);
        }

        public boolean isAllVerticesVisited() {
            return !this.visitedVertices.values().contains(false);
        }

        public int getEdgeValue(int leftNodeId, int rightNodeId) {
            return this.edgeValues.get(leftNodeId+","+rightNodeId);
        }
    }

    public static class Node {

        private int ID;
        List<Node> children;

        public Node(int ID) {
            this.ID = ID;
            this.children = new ArrayList<>();
        }

        public int getID() {
            return ID;
        }

        public boolean hasChildren() {
            return this.children.size() >= 1;
        }

        public List<Node> getChildren() {
            return children;
        }

    }

}
*public static void main(String[] args) throws IOException {
        // write your code here
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        int people = Integer.parseInt(tokenizer.nextToken());
        int relations = Integer.parseInt(tokenizer.nextToken());
        tokenizer = new StringTokenizer(br.readLine());
        long[] knowledgeLevels = new long[people];
        for (int i = 0; i < people; i++) {
            knowledgeLevels[i] = Long.parseLong(tokenizer.nextToken());
        }
        List<String> relationList = new ArrayList<>(relations);
        for (int j = 0; j < relations; j++) {
            relationList.add(br.readLine());
        }

        //create tree here
        boolean treeCreated = false;
        Tree nTree = null;

        for (String relationString : relationList) {
            Node currNode;
            int nodeId = Integer.parseInt(relationString.split(" ")[0]);
            if (!treeCreated) {
                currNode = new Node(nodeId);
                currNode.setKnowledgeLevel(knowledgeLevels[nodeId-1]);
                nTree = new Tree(currNode);
                treeCreated = true;
            } else {
                currNode = nTree.getNode(nodeId);
            }
            if (null == currNode) {
                currNode = new Node(nodeId);
                currNode.setKnowledgeLevel(knowledgeLevels[nodeId-1]);
            }
            int childNodeId = Integer.parseInt(relationString.split(" ")[1]);
            Node childNode = nTree.getNode(childNodeId);
            if (null == childNode) {
                childNode = new Node(childNodeId);
                childNode.setKnowledgeLevel(knowledgeLevels[childNodeId-1]);
            }
            currNode.addChild(childNode);
        }

        System.out.println(nTree.getPaths().size());

    }*/

    /*public static class Tree {

        private Node root;

        Tree(Node root) {
            this.root = root;
        }

        Node getNode(int id) {
            // start traversal
            Queue<Node> traversal = new LinkedList<>();
            traversal.offer(this.root);
            while (!traversal.isEmpty()) {
                Node currentNode = traversal.poll();
                if (currentNode.getId() == id)
                    return currentNode;
                if (currentNode.hasChildren()) {
                    List<Node> children = currentNode.getChildren();
                    children.forEach(traversal::offer);
                }
            }
            return null;
        }

        List<String> getPaths() {
            List<String> traversalPaths = new ArrayList<>();
            Queue<Node> traversal = new LinkedList<>();
            traversal.offer(this.root);
            StringBuilder builder = new StringBuilder();
            String parentPath = "";
            while (!traversal.isEmpty()) {
                Node currentNode = traversal.poll();
                builder.append(currentNode.getId()).append(" ");
                if (currentNode.hasChildren()) {
                    List<Node> children = currentNode.getChildren();
                    children.forEach(traversal::offer);
                    if (children.size() > 1)
                        parentPath = builder.toString();
                }
                else {
                    traversalPaths.add(builder.toString());
                    builder = new StringBuilder(parentPath);
                }
            }
            return traversalPaths;
        }

        public void addChild(int parentId, Node childId) {
            Node parent = getNode(parentId);
            if (null != parent) {
                parent.addChild(childId);
            }
        }
    }

    public static class Node {
        private int id;
        private long knowledgeLevel;
        private List<Node> children;

        Node(int id) {
            this.id = id;
            children = new ArrayList<>();
        }

        public long getKnowledgeLevel() {
            return knowledgeLevel;
        }

        int getId() {
            return id;
        }

        void setKnowledgeLevel(long knowledgeLevel) {
            this.knowledgeLevel = knowledgeLevel;
        }

        boolean hasChildren() {
            return this.getChildren().size() > 0;
        }

        List<Node> getChildren() {
            return children;
        }

        public void setChildren(List<Node> children) {
            this.children = children;
        }

        void addChild(Node child) {
            this.children.add(child);
        }
    }*/


//}

