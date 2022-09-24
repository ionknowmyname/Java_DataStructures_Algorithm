package com.faithfulolaleru;

import com.faithfulolaleru.base.GraphNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

public class GraphWithAdjacencyMatrix {

    ArrayList<GraphNode> nodeList = new ArrayList<GraphNode>();
    int[][] adjacencyMatrix;



    public GraphWithAdjacencyMatrix(ArrayList<GraphNode> nodeList) {
        this.nodeList = nodeList;
        adjacencyMatrix = new int[nodeList.size()][nodeList.size()];   // make a 2D array with the number of nodes
    }




    public static void main(String[] args) {
        ArrayList<GraphNode> nodeList = new ArrayList<GraphNode>();
        nodeList.add(new GraphNode("A", 0));
        nodeList.add(new GraphNode("B", 1));
        nodeList.add(new GraphNode("C", 2));
        nodeList.add(new GraphNode("D", 3));
        nodeList.add(new GraphNode("E", 4));

        GraphWithAdjacencyMatrix g = new GraphWithAdjacencyMatrix(nodeList);
        g.addUndirectedEdge(0, 1);
        g.addUndirectedEdge(0, 2);
        g.addUndirectedEdge(0, 3);
        g.addUndirectedEdge(1, 4);
        g.addUndirectedEdge(2, 3);
        g.addUndirectedEdge(3, 4);
        System.out.print(g.toString());

        // g.BFS();
        g.DFS();

    }



    public void addUndirectedEdge(int i, int j) {
        adjacencyMatrix[i][j] = 1;  // set 1 when there's a connection between nodes
        adjacencyMatrix[j][i] = 1;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("   ");
        for (int i = 0; i < nodeList.size(); i++) {
            s.append(nodeList.get(i).name + " ");
        }
        s.append("\n");

        for (int i = 0; i < nodeList.size(); i++) {
            s.append(nodeList.get(i).name + ": ");
            for(int j : adjacencyMatrix[i]) {
                s.append((j) + " ");
            }
            s.append("\n");
        }

        return s.toString();
    }


    ////////////////////////////////////
    ////  FOR BREADTH-FIRST SEARCH  ////
    ////////////////////////////////////


    public ArrayList<GraphNode> getNeighbors(GraphNode node) {
        ArrayList<GraphNode> neighbors = new ArrayList<GraphNode>();
        int nodeIndex = node.index;

        for (int i = 0; i < adjacencyMatrix.length; i++) {
            if(adjacencyMatrix[nodeIndex][i] == 1) {   // that means they adjacent i.e there is an edge connecting the nodes
                neighbors.add(nodeList.get(i));
            }
        }

        return neighbors;
    }

    void bfsVisit(GraphNode node) {
        LinkedList<GraphNode> queue = new LinkedList<>();
        queue.add(node);

        while(!queue.isEmpty()) {
            GraphNode currentNode = queue.remove(0);
            currentNode.isVisited = true;
            System.out.print(currentNode.name + " ");

            ArrayList<GraphNode> neighbors = getNeighbors(currentNode);
            for (GraphNode neighbor : neighbors) {
                if (!neighbor.isVisited) {  // add not visited nodes to the list
                    queue.add(neighbor);
                    neighbor.isVisited = true;
                }
            }
        }
    }

    public void BFS() {
        for (GraphNode node : nodeList) {
            if(!node.isVisited) {
                bfsVisit(node);
            }
        }
    }



    ////////////////////////////////////
    ////  FOR DEPTH-FIRST SEARCH  /////
    ///////////////////////////////////



    void dfsVisit(GraphNode node) {
        Stack<GraphNode> stack = new Stack<>();
        stack.push(node);

        while(!stack.isEmpty()) {
            GraphNode currentNode = stack.pop();
            currentNode.isVisited = true;
            System.out.print(currentNode.name + " ");

            ArrayList<GraphNode> neighbors = getNeighbors(currentNode);
            for (GraphNode neighbor : neighbors) {
                if (!neighbor.isVisited) {  // add not visited nodes to the list
                    stack.push(neighbor);
                    neighbor.isVisited = true;
                }
            }
        }
    }

    public void DFS() {
        for (GraphNode node : nodeList) {
            if(!node.isVisited) {
                dfsVisit(node);
            }
        }
    }

}
