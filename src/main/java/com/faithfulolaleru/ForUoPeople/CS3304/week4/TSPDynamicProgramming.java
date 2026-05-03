package com.faithfulolaleru.ForUoPeople.CS3304.week4;

import java.util.Arrays;
import java.util.Random;

public class TSPDynamicProgramming {

    // Travelling Sales Person

    static final int INF = Integer.MAX_VALUE / 2;

    /**
     * Solves TSP using Held-Karp dynamic programming algorithm.
     * @param dist  n x n distance matrix between offices
     * @return      minimum total distance to visit all offices and return to start
     */
    public static int solveTSP(int[][] dist) {
        int n = dist.length;
        int FULL_MASK = (1 << n) - 1;

        // dp[mask][i] = min cost to reach city i having visited cities in mask
        int[][] dp = new int[1 << n][n];
        for (int[] row : dp) Arrays.fill(row, INF);

        dp[1][0] = 0; // Start at office 0, only office 0 visited

        for (int mask = 1; mask <= FULL_MASK; mask++) {
            for (int u = 0; u < n; u++) {
                if ((mask & (1 << u)) == 0) continue; // u not in current path
                if (dp[mask][u] == INF) continue;

                for (int v = 0; v < n; v++) {
                    if ((mask & (1 << v)) != 0) continue; // v already visited
                    int nextMask = mask | (1 << v);
                    int newCost = dp[mask][u] + dist[u][v];
                    if (newCost < dp[nextMask][v]) {
                        dp[nextMask][v] = newCost;
                    }
                }
            }
        }

        // Find minimum cost to return to start from any last office
        int minCost = INF;
        for (int u = 1; u < n; u++) {
            if (dp[FULL_MASK][u] != INF) {
                int total = dp[FULL_MASK][u] + dist[u][0];
                if (total < minCost) minCost = total;
            }
        }
        return minCost;
    }

    /**
     * Generates a random symmetric distance matrix for n offices.
     */
    public static int[][] generateDistanceMatrix(int n, long seed) {
        Random rand = new Random(seed);
        int[][] dist = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int d = 50 + rand.nextInt(951); // distances between 50 and 1000 km
                dist[i][j] = d;
                dist[j][i] = d;
            }
        }
        return dist;
    }

    public static void main(String[] args) {
        System.out.println("=== TSP Dynamic Programming — Scalability Test ===\n");
        System.out.printf("%-10s %-20s %-15s%n", "Offices", "Min Distance (km)", "Time (ms)");
        System.out.println("-".repeat(47));

        for (int n = 5; n <= 15; n++) {
            int[][] dist = generateDistanceMatrix(n, 42L);
            long start = System.currentTimeMillis();
            int result = solveTSP(dist);
            long elapsed = System.currentTimeMillis() - start;
            System.out.printf("%-10d %-20d %-15d%n", n, result, elapsed);
        }
    }
}
