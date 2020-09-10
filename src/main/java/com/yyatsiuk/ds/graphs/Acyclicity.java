package com.yyatsiuk.ds.graphs;

import java.util.ArrayList;
import java.util.Scanner;

public class Acyclicity {

    private static int acyclic(ArrayList<Integer>[] adj) {
        boolean[] visited = new boolean[adj.length];
        for (int v = 0; v < adj.length; v++) {
            if (!visited[v]) {
                boolean hasCycleTmp = hasCycle(v, adj, visited, new boolean[adj.length]);
                if (hasCycleTmp) {
                    return 1;
                }
            }
        }

        return 0;
    }

    private static boolean hasCycle(Integer vertex, ArrayList<Integer>[] adj, boolean[] visited, boolean[] visitedTmp) {
        ArrayList<Integer> edges = adj[vertex];
        if (edges == null || edges.isEmpty()) {
            return false;
        } else if (visitedTmp[vertex]) {
            return true;
        }

        visited[vertex] = true;
        visitedTmp[vertex] = true;

        for (Integer v : edges) {
            boolean hasCycleTmp = hasCycle(v, adj, visited, visitedTmp);
            if (hasCycleTmp) {
                return true;
            }
        }

        visitedTmp[vertex] = false;
        return false;
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
        }
        System.out.println(acyclic(adj));
    }
}