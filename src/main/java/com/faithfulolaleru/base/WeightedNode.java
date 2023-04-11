package com.faithfulolaleru.base;

import com.faithfulolaleru.DisjointSet.DisjointSet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WeightedNode implements Comparable<WeightedNode> {

    public String name;
    public List<WeightedNode> neighbors = new ArrayList<>();
    public Map<WeightedNode, Integer> weightMap = new HashMap<>();
    public boolean isVisited = false;
    public WeightedNode parent;
    public int distance;
    public int index;
    public DisjointSet set;


    public WeightedNode(String name, int index) {
        this.name = name;
        distance = Integer.MAX_VALUE;
        this.index = index;
    }

    public WeightedNode(String name) {
        this.name = name;
        distance = Integer.MAX_VALUE;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int compareTo(WeightedNode o) {
        return this.distance - o.distance;
    }
}
