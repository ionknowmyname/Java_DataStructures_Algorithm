package com.faithfulolaleru.base;

import java.util.ArrayList;

public class GraphNodeSSSPP extends GraphNode {

    public GraphNodeSSSPP parent;


    // this neighbors array is only for adjacency list, for adjacency matrix, we use neighbors in its class
    public ArrayList<GraphNodeSSSPP> neighbors = new ArrayList<GraphNodeSSSPP>();



    public GraphNodeSSSPP(String name, int index) {
        super(name, index);
    }
}
