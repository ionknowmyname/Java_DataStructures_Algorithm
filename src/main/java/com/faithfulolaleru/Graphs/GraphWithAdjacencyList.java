package com.faithfulolaleru.Graphs;

import com.faithfulolaleru.base.GraphNodeAdjacencyList;
import com.faithfulolaleru.base.GraphNodeSSSPP;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

public class GraphWithAdjacencyList {


    ArrayList<GraphNodeAdjacencyList> nodeList = new ArrayList<GraphNodeAdjacencyList>();
    ArrayList<GraphNodeSSSPP> nodeList2 = new ArrayList<GraphNodeSSSPP>();



    /*
    public GraphWithAdjacencyList(ArrayList<GraphNodeAdjacencyList> nodeList) {
        this.nodeList = nodeList;
    }
    */

    public GraphWithAdjacencyList(ArrayList<GraphNodeSSSPP> nodeList2) {
        this.nodeList2 = nodeList2;
    }


    public static void main(String[] args) {
        /*

        ArrayList<GraphNodeAdjacencyList> nodeList = new ArrayList<GraphNodeAdjacencyList>();
        nodeList.add(new GraphNodeAdjacencyList("A", 0));
        nodeList.add(new GraphNodeAdjacencyList("B", 1));
        nodeList.add(new GraphNodeAdjacencyList("C", 2));
        nodeList.add(new GraphNodeAdjacencyList("D", 3));
        nodeList.add(new GraphNodeAdjacencyList("E", 4));

        GraphWithAdjacencyList g = new GraphWithAdjacencyList(nodeList);
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

        /*

        ArrayList<GraphNodeAdjacencyList> nodeList = new ArrayList<GraphNodeAdjacencyList>();
        nodeList.add(new GraphNodeAdjacencyList("A", 0));
        nodeList.add(new GraphNodeAdjacencyList("B", 1));
        nodeList.add(new GraphNodeAdjacencyList("C", 2));
        nodeList.add(new GraphNodeAdjacencyList("D", 3));
        nodeList.add(new GraphNodeAdjacencyList("E", 4));
        nodeList.add(new GraphNodeAdjacencyList("F", 5));
        nodeList.add(new GraphNodeAdjacencyList("G", 6));
        nodeList.add(new GraphNodeAdjacencyList("H", 7));

        GraphWithAdjacencyList g = new GraphWithAdjacencyList(nodeList);
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

        */


        // Graph for SSSPP

        ArrayList<GraphNodeSSSPP> nodeList = new ArrayList<GraphNodeSSSPP>();
        nodeList.add(new GraphNodeSSSPP("A", 0));
        nodeList.add(new GraphNodeSSSPP("B", 1));
        nodeList.add(new GraphNodeSSSPP("C", 2));
        nodeList.add(new GraphNodeSSSPP("D", 3));
        nodeList.add(new GraphNodeSSSPP("E", 4));
        nodeList.add(new GraphNodeSSSPP("F", 5));
        nodeList.add(new GraphNodeSSSPP("G", 6));

        GraphWithAdjacencyList g = new GraphWithAdjacencyList(nodeList);
        g.addUndirectedEdgeSSSPP(0, 1);
        g.addUndirectedEdgeSSSPP(0, 2);
        g.addUndirectedEdgeSSSPP(1, 3);
        g.addUndirectedEdgeSSSPP(1, 6);
        g.addUndirectedEdgeSSSPP(2, 3);
        g.addUndirectedEdgeSSSPP(2, 4);
        g.addUndirectedEdgeSSSPP(3, 5);
        g.addUndirectedEdgeSSSPP(4, 5);
        g.addUndirectedEdgeSSSPP(5, 6);
        System.out.print(g.toString());

        g.BFSForSSSPP(nodeList.get(0));
    }



    public void addUndirectedEdge(int i, int j) {
        GraphNodeAdjacencyList first = nodeList.get(i);
        GraphNodeAdjacencyList second = nodeList.get(j);

        first.neighbors.add(second);
        second.neighbors.add(first);

        // adding them to the list of neighbors creates edge between the nodes
        // so everyone on a node's neighbor list, it means there's an edge/connection between that node and its neighbor
        // therefore all elements in the list of a node's neighbor has a connection to that node
    }


    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();

        for (int i = 0; i < nodeList.size(); i++) {
            s.append(nodeList.get(i).name + ": ");

            for (int j = 0; j < nodeList.get(i).neighbors.size(); j++) {
                if(j == nodeList.get(i).neighbors.size() - 1) {
                    s.append((nodeList.get(i).neighbors.get(j).name) );
                } else {
                    s.append((nodeList.get(i).neighbors.get(j).name) + " --> ");
                }
            }

            s.append("\n");
        }

        return s.toString();
    }



    ////////////////////////////////////
    ////  FOR BREADTH-FIRST SEARCH  ////
    ////////////////////////////////////


    void bfsVisit(GraphNodeAdjacencyList node) {
        LinkedList<GraphNodeAdjacencyList> queue = new LinkedList<>();
        queue.add(node);

        while(!queue.isEmpty()) {
            GraphNodeAdjacencyList currentNode = queue.remove(0);
            currentNode.isVisited = true;
            System.out.print(currentNode.name + " ");

            for (GraphNodeAdjacencyList neighbor : currentNode.neighbors) {
                if (!neighbor.isVisited) {  // add not visited nodes to the list
                    queue.add(neighbor);
                    neighbor.isVisited = true;
                }
            }
        }
    }

    public void BFS() {
        for (GraphNodeAdjacencyList node : nodeList) {
            if(!node.isVisited) {
                bfsVisit(node);
            }
        }
    }



    ////////////////////////////////////
    ////  FOR DEPTH-FIRST SEARCH  /////
    ///////////////////////////////////


    void dfsVisit(GraphNodeAdjacencyList node) {
        Stack<GraphNodeAdjacencyList> stack = new Stack<>();
        stack.push(node);

        while(!stack.isEmpty()) {
            GraphNodeAdjacencyList currentNode = stack.pop();
            currentNode.isVisited = true;
            System.out.print(currentNode.name + " ");

            for (GraphNodeAdjacencyList neighbor : currentNode.neighbors) {
                if(!neighbor.isVisited) {  // if adjacent nodes/neighbors arent visited, then add to stack
                    stack.push(neighbor);
                    neighbor.isVisited = true;
                }
            }
        }
    }

    public void DFS() {
        for (GraphNodeAdjacencyList node : nodeList) {
            if(!node.isVisited) {
                dfsVisit(node);
            }
        }
    }



    ////////////////////////////////////
    /////  FOR TOPOLOGICAL SORT  //////
    ///////////////////////////////////



    public void addDirectedEdge(int i, int j) {
        GraphNodeAdjacencyList first = nodeList.get(i);
        GraphNodeAdjacencyList second = nodeList.get(j);

        first.neighbors.add(second);  // i.e going only from 1st to 2nd
    }

    void topologicalVisit(GraphNodeAdjacencyList node, Stack<GraphNodeAdjacencyList> stack) {
        // check if neighbor node is visited, if yes, push to stack, if no, call topologicalVisit
        // recursively to going down the dependent nodes

        for (GraphNodeAdjacencyList neighbor : node.neighbors){
            if (!neighbor.isVisited) {
                topologicalVisit(neighbor, stack);
            }
        }

        node.isVisited = true;
        stack.push(node);
    }

    void topologicalSort() {
        Stack<GraphNodeAdjacencyList> stack = new Stack<>();

        for (GraphNodeAdjacencyList node : nodeList) {
            if (!node.isVisited) {
                topologicalVisit(node, stack);
            }
        }

        while(!stack.isEmpty()) {
            System.out.print(stack.pop().name + " ");
        }
    }



    ////////////////////////////////////////////////////
    /////  FOR SSSPP Single Source Shortest Path  //////
    ////////////////////////////////////////////////////



    public void addUndirectedEdgeSSSPP(int i, int j) {
        GraphNodeSSSPP first = nodeList2.get(i);
        GraphNodeSSSPP second = nodeList2.get(j);

        first.neighbors.add(second);
        second.neighbors.add(first);

    }

    public static void pathPrint(GraphNodeSSSPP node) {
        if (node.parent != null) {
            pathPrint(node.parent);
        }
        System.out.print(node.name + " ");
    }

    public void BFSForSSSPP(GraphNodeSSSPP node) {
        LinkedList<GraphNodeSSSPP> queue = new LinkedList<>();
        queue.add(node);

        while (!queue.isEmpty()) {
            GraphNodeSSSPP currentNode = queue.remove(0);
            currentNode.isVisited = true;
            System.out.print("Printing path for node " + currentNode.name + ": ");
            pathPrint(currentNode);
            System.out.println();

            for (GraphNodeSSSPP neighbor : currentNode.neighbors) {
                if(!neighbor.isVisited) {   // if adjacent nodes are not visited, add to queue
                    queue.add(neighbor);
                    neighbor.isVisited = true;
                    neighbor.parent = currentNode;  // set adjacent nodes' parent to current node
                }
            }
        }
    }

}
