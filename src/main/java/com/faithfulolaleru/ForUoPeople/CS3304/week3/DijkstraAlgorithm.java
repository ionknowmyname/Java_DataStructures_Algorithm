package com.faithfulolaleru.ForUoPeople.CS3304.week3;

import java.util.*;

public class DijkstraAlgorithm {
    // Represents a weighted road connection
    static class Edge {
        int destination, weight;
        Edge(int destination, int weight) {
            this.destination = destination;
            this.weight = weight;
        }
    }

    // Entry for the min-heap priority queue
    static class NodeEntry implements Comparable<NodeEntry> {
        int node, distance;
        NodeEntry(int node, int distance) {
            this.node = node;
            this.distance = distance;
        }
        @Override
        public int compareTo(NodeEntry other) {
            return Integer.compare(this.distance, other.distance);
        }
    }

    public static int[] dijkstra(List<List<Edge>> graph, int source, int numNodes) {
        // Edge case: empty graph
        if (graph == null || graph.isEmpty() || numNodes == 0) {
            System.out.println("Warning: Empty graph provided.");
            return new int[0];
        }

        int[] dist = new int[numNodes];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[source] = 0;

        boolean[] visited = new boolean[numNodes];
        PriorityQueue<NodeEntry> pq = new PriorityQueue<>();
        pq.offer(new NodeEntry(source, 0));

        while (!pq.isEmpty()) {
            NodeEntry current = pq.poll();
            int u = current.node;

            // Skip stale entries — handles duplicate insertions
            if (visited[u]) continue;
            visited[u] = true;

            for (Edge edge : graph.get(u)) {
                int v = edge.destination;
                int weight = edge.weight;

                // Relax edge u -> v
                if (!visited[v] && dist[u] != Integer.MAX_VALUE
                        && dist[u] + weight < dist[v]) {
                    dist[v] = dist[u] + weight;
                    pq.offer(new NodeEntry(v, dist[v]));
                }
            }
        }
        return dist;
    }

    public static void printResults(int[] dist, int source) {
        System.out.println("Shortest distances from node " + source + ":");
        for (int i = 0; i < dist.length; i++) {
            String d = (dist[i] == Integer.MAX_VALUE)
                    ? "INFINITY (unreachable)" : String.valueOf(dist[i]);
            System.out.println("  Node " + i + " -> " + d);
        }
    }

    public static void main(String[] args) {

        // Demo 1: Standard connected graph (6-node road network)
        System.out.println("=== Demo 1: Connected Road Network ===");
        int n1 = 6;
        List<List<Edge>> graph1 = new ArrayList<>();
        for (int i = 0; i < n1; i++) graph1.add(new ArrayList<>());
        graph1.get(0).add(new Edge(1, 7));
        graph1.get(0).add(new Edge(2, 9));
        graph1.get(0).add(new Edge(5, 14));
        graph1.get(1).add(new Edge(2, 10));
        graph1.get(1).add(new Edge(3, 15));
        graph1.get(2).add(new Edge(3, 11));
        graph1.get(2).add(new Edge(5, 2));
        graph1.get(3).add(new Edge(4, 6));
        graph1.get(4).add(new Edge(5, 9));
        printResults(dijkstra(graph1, 0, n1), 0);

        // Demo 2: Disconnected graph — nodes 2 and 3 are isolated
        System.out.println("\n=== Demo 2: Disconnected Graph ===");
        int n2 = 4;
        List<List<Edge>> graph2 = new ArrayList<>();
        for (int i = 0; i < n2; i++) graph2.add(new ArrayList<>());
        graph2.get(0).add(new Edge(1, 5));
        printResults(dijkstra(graph2, 0, n2), 0);

        // Demo 3: Graph with cycles — algorithm must not loop infinitely
        System.out.println("\n=== Demo 3: Graph with Cycles ===");
        int n3 = 4;
        List<List<Edge>> graph3 = new ArrayList<>();
        for (int i = 0; i < n3; i++) graph3.add(new ArrayList<>());
        graph3.get(0).add(new Edge(1, 1));
        graph3.get(1).add(new Edge(2, 2));
        graph3.get(2).add(new Edge(0, 1)); // cycle back to 0
        graph3.get(2).add(new Edge(3, 4));
        printResults(dijkstra(graph3, 0, n3), 0);

        // Demo 4: Empty graph
        System.out.println("\n=== Demo 4: Empty Graph ===");
        dijkstra(new ArrayList<>(), 0, 0);
        System.out.println("Empty graph handled gracefully.");


        // ADD THIS — runs the performance benchmark
        performanceTest();
    }

    public static List<List<Edge>> buildRandomGraph(int numNodes, int edgeDensity, Random rand) {
        List<List<Edge>> graph = new ArrayList<>();
        for (int i = 0; i < numNodes; i++) graph.add(new ArrayList<>());
        for (int i = 0; i < numNodes; i++) {
            for (int j = 0; j < edgeDensity; j++) {
                int dest = rand.nextInt(numNodes);
                int weight = rand.nextInt(100) + 1;
                if (dest != i) graph.get(i).add(new Edge(dest, weight));
            }
        }
        return graph;
    }

    public static void performanceTest() {
        int[] sizes = {10, 50, 100, 200};
        Random rand = new Random(42);
        System.out.printf("%-10s %-15s%n", "Nodes", "Time (ms)");
        for (int n : sizes) {
            List<List<Edge>> graph = buildRandomGraph(n, 5, rand);
            long start = System.nanoTime();
            dijkstra(graph, 0, n);
            long end = System.nanoTime();
            System.out.printf("%-10d %-15.4f%n", n, (end - start) / 1_000_000.0);
        }
    }
}
