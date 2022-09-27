package com.faithfulolaleru.base;

import java.util.ArrayList;

public class GraphNodeSSSPP extends GraphNode {

    public GraphNodeSSSPP parent;

    public ArrayList<GraphNodeSSSPP> neighbors = new ArrayList<GraphNodeSSSPP>();



    public GraphNodeSSSPP(String name, int index) {
        super(name, index);
    }
}
