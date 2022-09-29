package com.faithfulolaleru.base;

import java.util.ArrayList;
import java.util.HashMap;

public class GraphNodeDijkstra extends GraphNode implements Comparable<GraphNodeDijkstra> {

    public ArrayList<GraphNodeDijkstra> neighbors = new ArrayList<>();
    public HashMap<GraphNodeDijkstra, Integer> weightMap = new HashMap<>();
    public GraphNodeDijkstra parent;
    public int distance;

    public GraphNodeDijkstra(String name, int index) {
        super(name, index);
        distance = Integer.MAX_VALUE;   // like infinity
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int compareTo(GraphNodeDijkstra o) {
        return this.distance - o.distance;
    }
}
