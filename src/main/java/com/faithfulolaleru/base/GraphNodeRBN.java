package com.faithfulolaleru.base;

import com.faithfulolaleru.GreedyAlgorithms.RouteBetweenNodes;

import java.util.ArrayList;
import java.util.List;

public class GraphNodeRBN extends GraphNode {

    // RBN is Route Between Nodes question

    public List<GraphNodeRBN> neighbors = new ArrayList<>();

    public RouteBetweenNodes.State state;

    public GraphNodeRBN(String name, int index) {
        super(name, index);
    }
}
