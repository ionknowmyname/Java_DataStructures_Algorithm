package com.faithfulolaleru;

import com.faithfulolaleru.base.GraphNode;
import com.faithfulolaleru.base.GraphNodeAdjacencyList;

import java.util.ArrayList;

public class GraphWithAdjacencyList {


    ArrayList<GraphNodeAdjacencyList> nodeList = new ArrayList<GraphNodeAdjacencyList>();



    public GraphWithAdjacencyList(ArrayList<GraphNodeAdjacencyList> nodeList) {
        this.nodeList = nodeList;
    }


    public static void main(String[] args) {
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
}
