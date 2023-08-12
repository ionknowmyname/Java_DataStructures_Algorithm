package com.faithfulolaleru.DisjointSet;

import com.faithfulolaleru.base.WeightedNode;

import java.util.ArrayList;
import java.util.List;

public class DisjointSet {

    private List<WeightedNode> nodeList = new ArrayList<>();

    public static void main(String[] args) {
        List<WeightedNode> nodeList = new ArrayList<>();

        nodeList.add(new WeightedNode("A"));
        nodeList.add(new WeightedNode("B"));
        nodeList.add(new WeightedNode("C"));
        nodeList.add(new WeightedNode("D"));

        DisjointSet.makeSet(nodeList);
        WeightedNode firstNode = nodeList.get(0);
        WeightedNode secondNode = nodeList.get(1);

        DisjointSet output = DisjointSet.findSet(secondNode);
        output.printAllNodesofThisSet();

        DisjointSet.union(firstNode, secondNode);
        output = DisjointSet.findSet(secondNode);
        output.printAllNodesofThisSet();

    }


    public static void makeSet(List<WeightedNode> nodeList) {
        // create disjoint set for each weighted node, attach the weighted node to the set,
        // set/associate the disjoint set with the node
        for (WeightedNode node : nodeList) {
            DisjointSet set = new DisjointSet();
            set.nodeList.add(node);
            node.set = set;
        }
    }

    public static DisjointSet findSet(WeightedNode node) {
        return node.set;
    }

    public static DisjointSet union(WeightedNode node1, WeightedNode node2) {
        if (node1.set.equals(node2.set)) {
            return null;  // both nodes already in the same set, do nothing
        } else {
            DisjointSet set1 = node1.set;
            DisjointSet set2 = node2.set;

            // when trying to merge, add the set with the lesser nodes to the one with more nodes
            if (set1.nodeList.size() > set2.nodeList.size()) {
                // adding set2 to set1
                List<WeightedNode> nodeSet2 = set2.nodeList;
                for (WeightedNode node : nodeSet2) {
                    node.set = set1;   // associate each node from set2 to belong to set1
                    set1.nodeList.add(node);   // let set1 recognize those set2 nodes as now belonging to him
                }

                return set1;
            } else {
                // adding set1 to set2
                List<WeightedNode> nodeSet1 = set1.nodeList;
                for (WeightedNode node : nodeSet1) {
                    node.set = set2;
                    set2.nodeList.add(node);
                }

                return set2;
            }
        }
    }

    public void printAllNodesofThisSet() {
        System.out.println("Printing all nodes of the set: ");
        for (WeightedNode node : nodeList ) {
            System.out.print(node + " ");
        }
        System.out.println();
    }
}
