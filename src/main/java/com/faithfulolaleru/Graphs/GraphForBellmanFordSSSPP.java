package com.faithfulolaleru.Graphs;

import com.faithfulolaleru.base.GraphNodeDijkstra;

import java.util.ArrayList;

public class GraphForBellmanFordSSSPP {

    // Bellman Ford uses same Graph node as Dijkstra, just created a seperate class for it coz
    // even while similar to Dijkstra, its its own algorithm

    ArrayList<GraphNodeDijkstra> nodeList = new ArrayList<>();


    public GraphForBellmanFordSSSPP(ArrayList<GraphNodeDijkstra> nodeList) {
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

        GraphForBellmanFordSSSPP g = new GraphForBellmanFordSSSPP(nodeList);
        g.addWeighedEdge(0, 1, 2);
        g.addWeighedEdge(0, 2, 5);
        g.addWeighedEdge(1, 2, 6);
        g.addWeighedEdge(1, 3, 1);
        g.addWeighedEdge(1, 4, 3);
        g.addWeighedEdge(2, 5, 8);
        g.addWeighedEdge(3, 4, 4);
        g.addWeighedEdge(4, 6, 9);
        g.addWeighedEdge(5, 6, 7);
        System.out.println("Printing BellmanFord from source: A");
        g.bellmanFord(nodeList.get(0));
    }





    void bellmanFord(GraphNodeDijkstra sourceNode) {
        sourceNode.distance = 0;

        for (int i = 0; i < nodeList.size(); i++) {   // for V-1 times
            for(GraphNodeDijkstra currentNode : nodeList) {
                for(GraphNodeDijkstra neighbor : currentNode.neighbors) {
                    if (neighbor.distance > currentNode.distance + currentNode.weightMap.get(neighbor)) {
                        neighbor.distance = (currentNode.distance + currentNode.weightMap.get(neighbor));
                        neighbor.parent = currentNode;
                    }
                }
            }
        }

        System.out.println("Checking for Negative Cycle..");
        // on 1 more iteration, which is the Vth time, if dist. of neighbor is greater than the new dist. of neighbor,
        // then we have -ve cycle

        for (int i = 0; i < nodeList.size(); i++) {   // for V-1 times
            for(GraphNodeDijkstra currentNode : nodeList) {
                for(GraphNodeDijkstra neighbor : currentNode.neighbors) {
                    if (neighbor.distance > currentNode.distance + currentNode.weightMap.get(neighbor)) {
                        System.out.println("Negative cycle found: \n");
                        System.out.println("Vertex name: " + neighbor.name);
                        System.out.println("Old distance: " + neighbor.distance);

                        int newDistance = (currentNode.distance + currentNode.weightMap.get(neighbor));
                        System.out.println("New cost: " + newDistance);
                        return;
                    }
                }
            }
        }

        System.out.println("Negative Cycle not found");

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
