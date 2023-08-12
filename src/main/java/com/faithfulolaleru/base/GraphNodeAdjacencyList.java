package com.faithfulolaleru.base;

import java.util.ArrayList;

public class GraphNodeAdjacencyList extends GraphNode {

    public ArrayList<GraphNodeAdjacencyList> neighbors = new ArrayList<GraphNodeAdjacencyList>();


    public GraphNodeAdjacencyList(String name, int index) {
        super(name, index);
    }
}
