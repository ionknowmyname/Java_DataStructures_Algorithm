package com.faithfulolaleru;

import com.faithfulolaleru.base.GraphNode;
import com.faithfulolaleru.base.GraphNodeAdjacencyList;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

public class GraphWithAdjacencyList {


    ArrayList<GraphNodeAdjacencyList> nodeList = new ArrayList<GraphNodeAdjacencyList>();



    public GraphWithAdjacencyList(ArrayList<GraphNodeAdjacencyList> nodeList) {
        this.nodeList = nodeList;
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

}
