package com.faithfulolaleru.ForUoPeople.CS3304.week4;

import java.util.Random;

public class TSPBruteForce {

    static int minDistance;
    static int n;
    static int[][] dist;

    public static int solveBruteForce(int[][] distMatrix) {
        n = distMatrix.length;
        dist = distMatrix;
        minDistance = Integer.MAX_VALUE;

        int[] offices = new int[n - 1];
        for (int i = 0; i < n - 1; i++) offices[i] = i + 1; // offices 1..n-1

        permute(offices, 0);
        return minDistance;
    }

    static void permute(int[] arr, int start) {
        if (start == arr.length) {
            int total = dist[0][arr[0]];
            for (int i = 0; i < arr.length - 1; i++) total += dist[arr[i]][arr[i + 1]];
            total += dist[arr[arr.length - 1]][0];
            if (total < minDistance) minDistance = total;
            return;
        }
        for (int i = start; i < arr.length; i++) {
            int tmp = arr[start]; arr[start] = arr[i]; arr[i] = tmp;
            permute(arr, start + 1);
            tmp = arr[start]; arr[start] = arr[i]; arr[i] = tmp;
        }
    }

    /**
     * Generates a random symmetric distance matrix for n offices.
     * Uses the same seed as TSPDynamicProgramming for fair comparison.
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
        System.out.println("=== TSP Brute-Force — Scalability Test ===");
        System.out.println();
        System.out.printf("%-10s %-20s %-15s%n", "Offices", "Min Distance (km)", "Time (ms)");
        System.out.println("-".repeat(47));

        // Brute-force becomes impractical quickly — cap at 12 to be safe
        int maxOffices = 12;
        if (args.length > 0) {
            try {
                maxOffices = Math.min(Integer.parseInt(args[0]), 13); // hard cap at 13
            } catch (NumberFormatException e) {
                System.out.println("Invalid argument, defaulting to max 11 offices.");
            }
        }

        for (int n = 5; n <= maxOffices; n++) {
            int[][] distMatrix = generateDistanceMatrix(n, 42L); // same seed as DP version
            long start = System.currentTimeMillis();
            int result = solveBruteForce(distMatrix);
            long elapsed = System.currentTimeMillis() - start;
            System.out.printf("%-10d %-20d %-15d%n", n, result, elapsed);
        }

        System.out.println();
    }
}
