package com.yyatsiuk.ds.graphs;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Given an undirected graph and two distinct vertices u and v, check if there is a path between U and V
 */
public class Reachability {
    private static int reach(ArrayList<Integer>[] adj, int x, int y) {
        int[] visited = new int[adj.length];
        reachHelper(adj, visited, x);
        return visited[y];
    }

    private static void reachHelper(ArrayList<Integer>[] adj, int[] visited, int x) {
        visited[x] = 1;
        for (Integer v : adj[x]) {
            if (visited[v] == 0) {
                reachHelper(adj, visited, v);
            }
        }
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<Integer>[] adj = (ArrayList<Integer>[]) new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            int x;
            int y;
            x = scanner.nextInt();
            y = scanner.nextInt();
            adj[x - 1].add(y - 1);
            adj[y - 1].add(x - 1);
        }
        int x = scanner.nextInt() - 1;
        int y = scanner.nextInt() - 1;
        System.out.println(reach(adj, x, y));
    }
}
