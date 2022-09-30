package com.faithfulolaleru.Graphs;

import com.faithfulolaleru.base.GraphNodeDijkstra;
import com.faithfulolaleru.base.GraphNodeSSSPP;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class GraphForDijkstraSSSPP {

    ArrayList<GraphNodeDijkstra> nodeList = new ArrayList<>();


    public GraphForDijkstraSSSPP( ArrayList<GraphNodeDijkstra> nodeList) {
        this.nodeList = nodeList;
    }




    public static void main(String[] args) {
        ArrayList<GraphNodeDijkstra> nodeList = new ArrayList<>();
        nodeList.add(new GraphNodeDijkstra("A", 0));
        nodeList.add(new GraphNodeDijkstra("B", 1));
        nodeList.add(new GraphNodeDijkstra("C", 2));
        nodeList.add(new GraphNodeDijkstra("D", 3));
        nodeList.add(new GraphNodeDijkstra("E", 4));
        nodeList.add(new GraphNodeDijkstra("F", 5));
        nodeList.add(new GraphNodeDijkstra("G", 6));

        GraphForDijkstraSSSPP g = new GraphForDijkstraSSSPP(nodeList);
        g.addWeighedEdge(0, 1, 2);
        g.addWeighedEdge(0, 2, 5);
        g.addWeighedEdge(1, 2, 6);
        g.addWeighedEdge(1, 3, 1);
        g.addWeighedEdge(1, 4, 3);
        g.addWeighedEdge(2, 5, 8);
        g.addWeighedEdge(3, 4, 4);
        g.addWeighedEdge(4, 6, 9);
        g.addWeighedEdge(5, 6, 7);
        System.out.println("Printing Dijkstra from source: A");
        g.dijkstra(nodeList.get(0));
    }




    void dijkstra(GraphNodeDijkstra node) {
        PriorityQueue<GraphNodeDijkstra> queue = new PriorityQueue<>();
        node.distance = 0;   // set starting node distance to 0
        queue.addAll(nodeList);    // add all elements of list to queue

        while(!queue.isEmpty()) {
            GraphNodeDijkstra currentNode = queue.remove();
            for(GraphNodeDijkstra neighbor : currentNode.neighbors) {
                // if neighbor is in queue, it means neighbor has not been visited; coz when we visit an element in queue, we remove it
                if(queue.contains(neighbor)) {
                    // if neighbor distance is greater than the new distance we calculating
                    if(neighbor.distance > currentNode.distance + currentNode.weightMap.get(neighbor)) {
                        neighbor.distance = (currentNode.distance + currentNode.weightMap.get(neighbor));
                        neighbor.parent = currentNode;
                        // refresh queue by removing neighbor and adding again
                        queue.remove(neighbor);
                        queue.add(neighbor);
                    }
                }
            }
        }

        for(GraphNodeDijkstra nodeToCheck : nodeList) {
            System.out.print("Node " + nodeToCheck + ", distance: " + nodeToCheck.distance + ", Path: ");
            pathPrint(nodeToCheck);
            System.out.println();
        }
    }

    public static void pathPrint(GraphNodeDijkstra node) {
        if (node.parent != null) {
            pathPrint(node.parent);
        }
        System.out.print(node.name + " ");
    }

    public void addWeighedEdge(int i, int j, int d) {   // d is the distance
        GraphNodeDijkstra first  = nodeList.get(i);
        GraphNodeDijkstra second = nodeList.get(j);
        first.neighbors.add(second);
        first.weightMap.put(second, d);
    }
}
