package com.faithfulolaleru.HACKERRANK;

public class ConstructArray {

    // Construct the array
    // https://www.hackerrank.com/challenges/construct-the-array/problem?isFullScreen=true

    public static long countArray(int n, int k, int x) {
        long max = 1000000007;
        long[] f = new long[n + 1]; //[1,...,1]
        long[] g = new long[n + 1]; //[1,...,2]
        f[3] = k - 1;
        g[2] = 1;
        g[3] = k - 2;

        for (int i = 4; i <= n; i++) {
            f[i] = (k - 1) * g[i - 1] % max;
            g[i] = (f[i - 1] + (k - 2) * g[i - 1]) % max;
        }

        return x == 1 ? f[n] : g[n];

    }
}
