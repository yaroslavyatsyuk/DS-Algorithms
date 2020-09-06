package com.yyatsiuk.ds.graphs;

import java.util.ArrayList;
import java.util.Scanner;

public class ConnectedComponents {
    private static int numberOfComponents(ArrayList<Integer>[] adj) {
        int result = 0;
        boolean[] visited = new boolean[adj.length];
        for (int vertex = 0; vertex < adj.length; vertex++) {
            if (!visited[vertex]) {
                numberOfComponentsHelper(adj, vertex, visited);
                result++;
            }
        }

        return result;
    }

    private static void numberOfComponentsHelper(ArrayList<Integer>[] adj, int vertex, boolean[] visited) {
        visited[vertex] = true;
        for (Integer edge : adj[vertex]) {
            if (!visited[edge]) {
                numberOfComponentsHelper(adj, edge, visited);
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
            int x, y;
            x = scanner.nextInt();
            y = scanner.nextInt();
            adj[x - 1].add(y - 1);
            adj[y - 1].add(x - 1);
        }
        System.out.println(numberOfComponents(adj));
    }
}
