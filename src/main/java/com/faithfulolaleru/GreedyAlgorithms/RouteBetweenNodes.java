package com.faithfulolaleru.GreedyAlgorithms;

import com.faithfulolaleru.Graphs.GraphWithAdjacencyList;
import com.faithfulolaleru.base.GraphNodeRBN;
import com.faithfulolaleru.base.GraphNodeSSSPP;
import com.faithfulolaleru.base.WeightedNode;

import java.util.*;

public class RouteBetweenNodes {

    /*
    *   QUESTION: Given a directed graph & 2 nodes (S,E), find out if there is route from S to E
    *
    *   SOLUTION: create a queue and enqueue S node, find all neighbors of enqueued node & enqueue them too
    *   until all nodes in graph are enqueued; if along the way we encounter E node (destination node), we return true;
    *   on the way, we mark visited nodes as visited
    *
    *
    * */


    // use Graph Node
    static List<GraphNodeRBN> nodeList = new ArrayList<>();

    public enum State { Unvisited, Visited, Visiting }


    public RouteBetweenNodes(List<GraphNodeRBN> nodeList) {
        this.nodeList = nodeList;
    }



    public static void main(String[] args) {
        List<GraphNodeRBN> nodeList = new ArrayList<>();
        nodeList.add(new GraphNodeRBN("A", 0));
        nodeList.add(new GraphNodeRBN("B", 1));
        nodeList.add(new GraphNodeRBN("C", 2));
        nodeList.add(new GraphNodeRBN("D", 3));
        nodeList.add(new GraphNodeRBN("E", 4));
        nodeList.add(new GraphNodeRBN("F", 5));
        nodeList.add(new GraphNodeRBN("G", 6));
        nodeList.add(new GraphNodeRBN("H", 7));
        nodeList.add(new GraphNodeRBN("I", 8));
        nodeList.add(new GraphNodeRBN("J", 9));

        RouteBetweenNodes r = new RouteBetweenNodes(nodeList);
        r.addDirectedEdge(0, 1);  // A -> B
        r.addDirectedEdge(0, 2);  // A -> C
        r.addDirectedEdge(0, 3);  // A -> D
        r.addDirectedEdge(1, 9);  // B -> J
        r.addDirectedEdge(2, 6);  // C -> G
        r.addDirectedEdge(4, 0);  // E -> A
        r.addDirectedEdge(4, 5);  // E -> F
        r.addDirectedEdge(5, 8);  // F -> I
        r.addDirectedEdge(6, 7);  // G -> H
        r.addDirectedEdge(6, 3);  // G -> D

        boolean result = routeExist(nodeList.get(0), nodeList.get(4)); // btw A & E
        boolean result2 = routeExist(nodeList.get(4), nodeList.get(0)); // btw E & A
        System.out.println(result);  // should return false
        System.out.println(result2);  // should return true
    }



    public void addDirectedEdge(int i, int j) {
        GraphNodeRBN first = nodeList.get(i);
        GraphNodeRBN second = nodeList.get(j);

        first.neighbors.add(second);
    }


    public static boolean routeExist(GraphNodeRBN start, GraphNodeRBN end) {
        LinkedList<GraphNodeRBN> queue = new LinkedList<>();

        // set all nodes state to unvisited
        for(GraphNodeRBN node : nodeList) {
            node.state = State.Unvisited;
        }

        start.state = State.Visiting;
        queue.add(start);

        GraphNodeRBN currentNode;
        while(!queue.isEmpty()) {
            currentNode = queue.removeFirst();   // remove first acts like dequeue in linked list
            if(currentNode != null) {  // then loop thru all neighbors
                for(GraphNodeRBN neighbor : currentNode.neighbors) {
                    if(neighbor.state == State.Unvisited) {
                        if(neighbor == end) {  // found end node, so route exists
                            return true;
                        } else {   // add neighbor to queue & keep moving on
                            neighbor.state = State.Visiting;
                            queue.add(neighbor);
                        }
                    }
                }
            }

            currentNode.state = State.Visited;
        }

        return false;
    }



    // my 1st solution
    public static boolean routeExist(WeightedNode start, WeightedNode end) {
        Queue<WeightedNode> queue = new PriorityQueue<>();

        queue.add(start);
        start.isVisited = true;

        while(!queue.isEmpty()) {
            WeightedNode currentNode = queue.remove();

            for(WeightedNode neighbor : currentNode.neighbors) {
                if(neighbor == end) return true;

                queue.add(neighbor);
                neighbor.isVisited = true;
            }
        }

        return false;
    }
}
