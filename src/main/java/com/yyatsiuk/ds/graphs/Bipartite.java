package com.yyatsiuk.ds.graphs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;

/**
 * Given an undirected graph with 𝑛 vertices and 𝑚 edges, check whether it is bipartite.
 */
public class Bipartite {
    private static final String WHITE = "white";
    private static final String BLACK = "black";

    private static int bipartite(ArrayList<Integer>[] adj) {
        Map<Integer, String> colours = new HashMap<>();

        boolean[] visited = new boolean[adj.length];
        Queue<Integer> queue = new ArrayDeque<>();

        for (int s = 0; s < adj.length; s++) {
            if (isAllEdgesVisited(adj, visited, s)) continue;

            queue.add(s);
            visited[s] = true;
            colours.put(s, getColour(adj, colours, s));

            while (!queue.isEmpty()) {
                Integer u = queue.poll();
                for (Integer v : adj[u]) {
                    if (!visited[v]) {
                        if (colours.get(v) == null) {
                            updateColours(colours, u, v);
                        } else {
                            if (colours.get(u).equals(colours.get(v))) return 0;
                        }
                    }
                }
            }

        }

        return 1;
    }

    private static void updateColours(Map<Integer, String> colours, Integer u, Integer v) {
        if (colours.get(u).equals(WHITE)) {
            colours.put(v, BLACK);
        } else {
            colours.put(v, WHITE);
        }
    }

    private static boolean isAllEdgesVisited(ArrayList<Integer>[] adj, boolean[] visited, int s) {
        ArrayList<Integer> edges = adj[s];
        if (edges == null || edges.isEmpty()) {
            return true;
        } else {
            boolean isVisited = true;
            for (Integer u : edges) {
                isVisited &= visited[u];
            }

            return isVisited;
        }
    }

    private static String getColour(ArrayList<Integer>[] adj, Map<Integer, String> colours, int s) {
        ArrayList<Integer> edges = adj[s];
        if (edges == null || edges.isEmpty() || s == 0) {
            return WHITE;
        } else {
            for (Integer edge : edges) {
                String colour = colours.get(edge);
                if (colour != null && colour.equals(WHITE)) {
                    return BLACK;
                } else if (colour != null && colour.equals(BLACK)) {
                    return WHITE;
                }
            }
        }

        return WHITE;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<Integer>[] adj = (ArrayList<Integer>[]) new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < m; i++) {
            int x, y;
            x = scanner.nextInt();
            y = scanner.nextInt();
            adj[x - 1].add(y - 1);
            adj[y - 1].add(x - 1);
        }
        System.out.println(bipartite(adj));
    }
}

