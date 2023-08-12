package com.faithfulolaleru.LEETCODE;

import java.util.*;

public class CloneGraph {

    static class Node {
        public int val;
        public List<Node> neighbors;
        public Node() {
            val = 0;
            neighbors = new ArrayList<>();
        }
        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<>();
        }
        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }



    // 133. Clone Graph
    public Node cloneGraph(Node node) {
        if(node == null) return null; // if no node, return null

        Node[] visited = new Node[101]; // in question, max no. of nodes in graph was 100
        Arrays.fill(visited , null); // initialize to null

        Node copy = new Node(node.val); // create a new node, with same value as the root node

        // copy node has the value of main node, now add its neighbors for the copy as well then return
        dfs(node, copy, visited);

        return copy;
    }

    public void dfs(Node node, Node copy, Node[] visited) {
        // start by saving the copy node in visited, save it at the index of its value, coz a node's value & index are
        // the same for this question; so if you query visited array by its index & its not null, it means a node with
        // that value has been visited
        visited[copy.val] = copy;

        // create all node neighbors & add to be copy's neighbors as well
        for(Node n : node.neighbors) {

            // if node hasnt been visited, it'll be null in visited array
            if(visited[n.val] == null) {
                Node newNode = new Node(n.val);
                copy.neighbors.add(newNode);

                // call recursively to check for the original node's neighbors' neighbors
                dfs(n, newNode, visited);
            } else {  // node already exists & has been visited before, just add to copy's neighbors
                copy.neighbors.add(visited[n.val]);
            }
        }
    }

    // hash map solution
    // time limit exceeded  // bad solution
    public Node cloneGraph2(Node node) {
        if(node == null) return null;

        Map<Node, Node> map = new HashMap<>();
        Node copy = new Node(node.val);
        map.put(node, copy);

        for(Node n : node.neighbors) {
            if(map.containsKey(n))
                copy.neighbors.add(map.get(n));
            else
                copy.neighbors.add(cloneGraph2(n));  // call recursively to add neighbors of neighbors
        }

        return copy;
    }

    // hash map solution working
    public Node cloneGraph3(Node node) {
        return (node == null) ? null : cloneHelper(node, new HashMap<>());
    }

    private Node cloneHelper(Node node, Map<Integer, Node> nodeByVal) {
        if (nodeByVal.containsKey(node.val)) {   // base case for recursive
            return nodeByVal.get(node.val);
            // node is stored using its value as key, so if map contains value of original node as key,
            // then return that node, coz its the cloned node
        }

        // if node.val not present, it'll create a new node with node.val, which is the clone to return
        // this is also what would create new nodes for neighbors
        var clone = nodeByVal.computeIfAbsent(node.val, k -> new Node(node.val));

        for (var neighbor : node.neighbors)
            clone.neighbors.add(cloneHelper(neighbor, nodeByVal));
            // add neighbors recursively so you can add neighbors of neighbors

        return clone;
    }

    // hashmap and queue solution
    public Node cloneGraph4(Node node) {
        if (node == null) return null;
        Map<Node, Node> map = new HashMap<>();
        Queue<Node> q = new LinkedList<>();

        q.add(node);
        map.put(node, new Node(node.val, new ArrayList<>()));  // map original node to clone

        while (!q.isEmpty()) {
            Node n = q.peek();
            q.poll();

            for (Node e : n.neighbors) {
                if (!map.containsKey(e)) { // map doesnt contain neighbor
                    map.put(e, new Node(e.val, new ArrayList<>()));  // create clone neighbor and map to original neighbor
                    q.add(e); // this adds all neighbors to queue
                    // instead of doing any recursion, add neighbor to queue, so when you poll, it'll run the process again
                }

                map.get(n).neighbors.add(map.get(e));
                // map.get(n) gets the clone of the original node, coz from 1st iteration, n is the original node
                // so this is basically the line adding cloned neighbors to the original clone
                // coz map.get(e) gets the clone of the neighbor that was created
                // and since its doing it like a tree, so its also the same line adding neighbors of neighbor clones to
                // the neighbor clone; and down & down like that.
            }
        }

        return map.get(node);
    }
}
