package com.faithfulolaleru.Graphs;

import com.faithfulolaleru.base.GraphNodeAdjacencyMatrix;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

public class GraphWithAdjacencyMatrix {

    ArrayList<GraphNodeAdjacencyMatrix> nodeList = new ArrayList<GraphNodeAdjacencyMatrix>();
    int[][] adjacencyMatrix;



    public GraphWithAdjacencyMatrix(ArrayList<GraphNodeAdjacencyMatrix> nodeList) {
        this.nodeList = nodeList;
        adjacencyMatrix = new int[nodeList.size()][nodeList.size()];   // make a 2D array with the number of nodes
    }




    public static void main(String[] args) {
        /*

        ArrayList<GraphNodeAdjacencyMatrix> nodeList = new ArrayList<GraphNodeAdjacencyMatrix>();
        nodeList.add(new GraphNodeAdjacencyMatrix("A", 0));
        nodeList.add(new GraphNodeAdjacencyMatrix("B", 1));
        nodeList.add(new GraphNodeAdjacencyMatrix("C", 2));
        nodeList.add(new GraphNodeAdjacencyMatrix("D", 3));
        nodeList.add(new GraphNodeAdjacencyMatrix("E", 4));

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

        */


        // Graph for Topological Sort

        ArrayList<GraphNodeAdjacencyMatrix> nodeList = new ArrayList<GraphNodeAdjacencyMatrix>();
        nodeList.add(new GraphNodeAdjacencyMatrix("A", 0));
        nodeList.add(new GraphNodeAdjacencyMatrix("B", 1));
        nodeList.add(new GraphNodeAdjacencyMatrix("C", 2));
        nodeList.add(new GraphNodeAdjacencyMatrix("D", 3));
        nodeList.add(new GraphNodeAdjacencyMatrix("E", 4));
        nodeList.add(new GraphNodeAdjacencyMatrix("F", 5));
        nodeList.add(new GraphNodeAdjacencyMatrix("G", 6));
        nodeList.add(new GraphNodeAdjacencyMatrix("H", 7));

        GraphWithAdjacencyMatrix g = new GraphWithAdjacencyMatrix(nodeList);
        g.addDirectedEdge(0, 2);
        g.addDirectedEdge(2, 4);
        g.addDirectedEdge(4, 7);
        g.addDirectedEdge(4, 5);
        g.addDirectedEdge(5, 6);
        g.addDirectedEdge(1, 2);
        g.addDirectedEdge(1, 3);
        g.addDirectedEdge(3, 5);
        System.out.print(g.toString());

        g.topologicalSort();
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


    public ArrayList<GraphNodeAdjacencyMatrix> getNeighbors(GraphNodeAdjacencyMatrix node) {
        ArrayList<GraphNodeAdjacencyMatrix> neighbors = new ArrayList<GraphNodeAdjacencyMatrix>();
        int nodeIndex = node.index;

        for (int i = 0; i < adjacencyMatrix.length; i++) {
            if(adjacencyMatrix[nodeIndex][i] == 1) {   // that means they adjacent i.e there is an edge connecting the nodes
                neighbors.add(nodeList.get(i));
            }
        }

        return neighbors;
    }

    void bfsVisit(GraphNodeAdjacencyMatrix node) {
        LinkedList<GraphNodeAdjacencyMatrix> queue = new LinkedList<>();
        queue.add(node);

        while(!queue.isEmpty()) {
            GraphNodeAdjacencyMatrix currentNode = queue.remove(0);
            currentNode.isVisited = true;
            System.out.print(currentNode.name + " ");

            ArrayList<GraphNodeAdjacencyMatrix> neighbors = getNeighbors(currentNode);
            for (GraphNodeAdjacencyMatrix neighbor : neighbors) {
                if (!neighbor.isVisited) {  // add not visited nodes to the list
                    queue.add(neighbor);
                    neighbor.isVisited = true;
                }
            }
        }
    }

    public void BFS() {
        for (GraphNodeAdjacencyMatrix node : nodeList) {
            if(!node.isVisited) {
                bfsVisit(node);
            }
        }
    }



    ////////////////////////////////////
    ////  FOR DEPTH-FIRST SEARCH  /////
    ///////////////////////////////////



    void dfsVisit(GraphNodeAdjacencyMatrix node) {
        Stack<GraphNodeAdjacencyMatrix> stack = new Stack<>();
        stack.push(node);

        while(!stack.isEmpty()) {
            GraphNodeAdjacencyMatrix currentNode = stack.pop();
            currentNode.isVisited = true;
            System.out.print(currentNode.name + " ");

            ArrayList<GraphNodeAdjacencyMatrix> neighbors = getNeighbors(currentNode);
            for (GraphNodeAdjacencyMatrix neighbor : neighbors) {
                if (!neighbor.isVisited) {  // add not visited nodes to the list
                    stack.push(neighbor);
                    neighbor.isVisited = true;
                }
            }
        }
    }

    public void DFS() {
        for (GraphNodeAdjacencyMatrix node : nodeList) {
            if(!node.isVisited) {
                dfsVisit(node);
            }
        }
    }




    ////////////////////////////////////
    /////  FOR TOPOLOGICAL SORT  //////
    ///////////////////////////////////



    public void addDirectedEdge(int i, int j) {
        adjacencyMatrix[i][j] = 1;  // set 1 when there's a connection between nodes
    }

    void topologicalVisit(GraphNodeAdjacencyMatrix node, Stack<GraphNodeAdjacencyMatrix> stack) {
        ArrayList<GraphNodeAdjacencyMatrix> neighbors = getNeighbors(node);

        // check if neighbor node is visited, if yes, push to stack, if no, call topologicalVisit
        // recursively to going down the dependent nodes

        for (GraphNodeAdjacencyMatrix neighbor : neighbors) {
            if(!neighbor.isVisited) {
                topologicalVisit(neighbor, stack);
            }
        }

        node.isVisited = true;
        stack.push(node);
    }

    void topologicalSort() {
        Stack<GraphNodeAdjacencyMatrix> stack = new Stack<>();

        for (GraphNodeAdjacencyMatrix node : nodeList) {
            if(!node.isVisited) {
                topologicalVisit(node, stack);
            }
        }

        while(!stack.isEmpty()) {
            System.out.print(stack.pop().name + " ");
        }
    }

}
