package com.yyatsiuk.ds.graphs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;

public class BFS {
    private static int distance(ArrayList<Integer>[] adj, int s, int t) {
        boolean[] visited = new boolean[adj.length];
        Map<Integer, Integer> prev = new HashMap<>();
        for (int i = 0; i < adj.length; i++) {
            prev.put(i, null);
        }
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(s);
        visited[s] = true;

        while (!queue.isEmpty()) {
            Integer u = queue.poll();
            for (Integer v : adj[u]) {
                if (!visited[v]) {
                    queue.add(v);
                    prev.put(v, u);
                    visited[v] = true;
                }
            }
        }

        List<Integer> shortestPath = getShortestPath(prev, s, t);
        return shortestPath.isEmpty() ? -1 : shortestPath.size();
    }

    private static List<Integer> getShortestPath(Map<Integer, Integer> prev, Integer s, Integer t) {
        List<Integer> path = new ArrayList<>();
        while (!t.equals(s)) {
            path.add(0, t);
            t = prev.get(t);
            if (t == null) return new ArrayList<>();
        }

        return path;
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
        int x = scanner.nextInt() - 1;
        int y = scanner.nextInt() - 1;
        System.out.println(distance(adj, x, y));
    }
}
