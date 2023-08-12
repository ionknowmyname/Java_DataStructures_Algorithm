package com.faithfulolaleru.GreedyAlgorithms;

import com.faithfulolaleru.base.WeightedNode;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class PrimAlgorithm {

    // Prim's Algorithm for Minimum Spanning Tree

    List<WeightedNode> nodeList = new ArrayList<>();

    public PrimAlgorithm(List<WeightedNode> nodeList) {
        this.nodeList = nodeList;
    }


    public static void main(String[] args) {
        List<WeightedNode> nodeList = new ArrayList<>();

        nodeList.add(new WeightedNode("A"));
        nodeList.add(new WeightedNode("B"));
        nodeList.add(new WeightedNode("C"));
        nodeList.add(new WeightedNode("D"));
        nodeList.add(new WeightedNode("E"));

        PrimAlgorithm graph = new PrimAlgorithm(nodeList);

        graph.addWeightedUndirectedEdge(0, 1, 5); // A-B, weight 5
        graph.addWeightedUndirectedEdge(1, 3, 8); // B-D, weight 8
        graph.addWeightedUndirectedEdge(0, 2, 13); // A-C, weight 13
        graph.addWeightedUndirectedEdge(0, 4, 15); // A-E, weight 15
        graph.addWeightedUndirectedEdge(1, 2, 10); // B-C, weight 10
        graph.addWeightedUndirectedEdge(2, 3, 6); // C-D, weight 6
        graph.addWeightedUndirectedEdge(2, 4, 20); // C-E, weight 20

        graph.mainPrims(nodeList.get(4));  // for E; total cost should be 34
    }



    public void addWeightedUndirectedEdge(int i, int j, int d) {

        WeightedNode first = nodeList.get(i);
        WeightedNode second = nodeList.get(j);

        // add 1st node to 2nd node neighbors & add 2nd node to 1st node neighbors
        first.neighbors.add(second);
        second.neighbors.add(first);

        // add weights from 2nd to 1st & from 1st to 2nd
        first.weightMap.put(second, d);
        second.weightMap.put(first, d);
    }


    void mainPrims(WeightedNode node) {  // starting node that'll have distance 0
        // set dist of all nodes to infinity
        for (int i = 0; i < nodeList.size(); i++) {
            nodeList.get(i).distance = Integer.MAX_VALUE;
        }

        node.distance = 0;  // set 1st node distance to 0

        // use priority queue, the vertex with the smallest distance would come out first
        // default priority queue is from min to max
        PriorityQueue<WeightedNode> queue = new PriorityQueue<>();
        queue.addAll(nodeList);

        while(!queue.isEmpty()) {
            WeightedNode currentNode = queue.remove();  // each time removes node with smallest dist

            // loop thru neighbors of current node that aren't visited & update their distance & parents
            // if they are in the queue still, it means they aren't visited, coz we popping out of queue while visiting
            for(WeightedNode neighbor : currentNode.neighbors) {
                if(queue.contains(neighbor)) {
                    // unprocessed neighbor, if queue doesn't contain neighbor, then it means its visited

                    if(neighbor.distance > currentNode.weightMap.get(neighbor)) {
                        // weightMap dist was put while creating the edge with neighbor, which is the real dist
                        // neighbor.distance is the infinity we set all node distances to

                        // set the neighbor's dist to the edge's distance
                        neighbor.distance = currentNode.weightMap.get(neighbor);
                        neighbor.parent = currentNode;

                        // refresh queue
                        queue.remove(neighbor);
                        queue.add(neighbor);

                        // add them back to queue coz we've not visited them, we only visited their parents
                        // also remove them first, so when you add them, they come back into queue with updated
                        // distances & parents
                    }
                }
            }
        }

        // calculate cost
        int cost = 0;
        for (WeightedNode nodeToCheck : nodeList) {
            System.out.println("Node " + nodeToCheck + ", distance " + nodeToCheck.distance + ", Parent: " + nodeToCheck.parent);
            cost = cost + nodeToCheck.distance;
        }

        System.out.println("\n Total cost of MST --> " + cost);
    }

}
