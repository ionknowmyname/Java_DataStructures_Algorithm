package com.faithfulolaleru.GreedyAlgorithms;

import com.faithfulolaleru.DisjointSet.DisjointSet;
import com.faithfulolaleru.base.WeightedNode;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class KruskalAlgorithm {

    // Kruskal's Algorithm for Minimum Spanning Tree

    public static class UndirectedEdge {
        public WeightedNode first;
        public WeightedNode second;
        public int weight;

        public UndirectedEdge(WeightedNode first, WeightedNode second, int weight) {
            this.first = first;
            this.second = second;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "Edge (" + first + ", " + second + "), weight = " + weight;
        }
    }



    List<WeightedNode> nodeList = new ArrayList<>();
    List<UndirectedEdge> edgeList = new ArrayList<>();

    public KruskalAlgorithm(List<WeightedNode> nodeList) {
        this.nodeList = nodeList;
    }



    public static void main(String[] args) {
        List<WeightedNode> nodeList = new ArrayList<>();

        nodeList.add(new WeightedNode("A"));
        nodeList.add(new WeightedNode("B"));
        nodeList.add(new WeightedNode("C"));
        nodeList.add(new WeightedNode("D"));
        nodeList.add(new WeightedNode("E"));

        KruskalAlgorithm graph = new KruskalAlgorithm(nodeList);

        graph.addWeightedUndirectedEdge(0, 1, 5); // A-B, weight 5
        graph.addWeightedUndirectedEdge(1, 3, 8); // B-D, weight 8
        graph.addWeightedUndirectedEdge(0, 2, 13); // A-C, weight 13
        graph.addWeightedUndirectedEdge(0, 4, 15); // A-E, weight 15
        graph.addWeightedUndirectedEdge(1, 2, 10); // B-C, weight 10
        graph.addWeightedUndirectedEdge(2, 3, 6); // C-D, weight 6
        graph.addWeightedUndirectedEdge(2, 4, 20); // C-E, weight 20

        graph.mainKruskal();  // total cost should be 34
    }




    public void addWeightedUndirectedEdge(int firstIndex, int secondIndex, int weight) {
        UndirectedEdge edge = new UndirectedEdge(nodeList.get(firstIndex), nodeList.get(secondIndex), weight);

        // add 1st node to 2nd node neighbors & add 2nd node to 1st node neighbors
        WeightedNode first = edge.first;
        WeightedNode second = edge.second;

        first.neighbors.add(second);
        second.neighbors.add(first);

        // add weights from 2nd to 1st & from 1st to 2nd
        first.weightMap.put(second, weight);
        second.weightMap.put(first, weight);

        // add newly created edge to Kruskal's edgelist
        edgeList.add(edge);
    }

    void mainKruskal() {
        // create set for all nodes in our nodelist like we did in disjoint set
        DisjointSet.makeSet(nodeList);

        // sort edges in ascending order using comparator
        Comparator<UndirectedEdge> comparator = new Comparator<UndirectedEdge>() {
            @Override
            public int compare(UndirectedEdge o1, UndirectedEdge o2) {
                return o1.weight - o2.weight;
            }
        };

        // Comparator<UndirectedEdge> comparator = (o1, o2) -> o1.weight - o2.weight;
        // Comparator<UndirectedEdge> comparator = Comparator.comparingInt(o -> o.weight);

        Collections.sort(edgeList, comparator);

        int cost = 0;
        // if the 2 nodes that make an edge are not in the same set, add them to the same set
        // & increase cost; adding them to the same set adds them to the minimum spanning tree;
        // at the end, all members of the set would form the MST
        for(UndirectedEdge edge : edgeList) {
            WeightedNode first = edge.first;
            WeightedNode second = edge.second;

            if(!DisjointSet.findSet(first).equals(DisjointSet.findSet(second))) {
                DisjointSet.union(first, second);
                cost += edge.weight;
                System.out.println("Taken " + edge);
            }
        }

        System.out.println("\nTotal cost of Minimum Spanning Tree (MST): " + cost);

        /* NOTE: This makes sure there's no cycle in Graph coz if two nodes in edge were in the same set,
        *   it won't union them. E.g Nodes A,B,C,D. Take edge AB, they are not in the same set, so union,
        *   so A-set now consists of A & B; edge BC are in different sets, so union them, now A-set is now ABC,
        *   edge CD also diff sets, so union again, A-set is now A,B,C,D. Now take edge DA, D & A are already
        *   in the same set, so no unioning, therefore the cycle does not complete
        * */
    }
}
