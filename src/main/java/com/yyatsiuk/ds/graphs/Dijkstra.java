package com.yyatsiuk.ds.graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Dijkstra {

    private static final int INF = Integer.MAX_VALUE;

    public static class Node implements Comparable<Node> {
        int index;
        long distance;

        public Node(int index, long distance) {
            this.index = index;
            this.distance = distance;
        }

        @Override
        public int compareTo(Node o) {
            return Long.compare(this.distance, o.distance);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return distance == node.distance;
        }

        @Override
        public int hashCode() {
            return Objects.hash(distance);
        }
    }

    private static int distance(ArrayList<Integer>[] adj, ArrayList<Integer>[] cost, int s, int t) {
        int[] dist = new int[adj.length];
        Arrays.fill(dist, INF);

        dist[s] = 0;
        PriorityQueue<Node> queue = new PriorityQueue<Node>();
        queue.add(new Node(s, dist[s]));
        while (!queue.isEmpty()) {
            Node u = queue.remove();
            int vertex = u.index;
            for (int v : adj[vertex]) {
                int w = adj[vertex].indexOf(v);
                if (dist[v] > dist[vertex] + cost[vertex].get(w)) {
                    dist[v] = dist[vertex] + cost[vertex].get(w);
                    queue.add(new Node(v, dist[v]));
                }
            }
        }
        if (dist[t] == INF)
            return -1;
        return dist[t];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        ArrayList<Integer>[] adj = (ArrayList<Integer>[]) new ArrayList[n];
        ArrayList<Integer>[] cost = (ArrayList<Integer>[]) new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
            cost[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            int x, y, w;
            x = scanner.nextInt();
            y = scanner.nextInt();
            w = scanner.nextInt();
            adj[x - 1].add(y - 1);
            cost[x - 1].add(w);
        }
        int x = scanner.nextInt() - 1;
        int y = scanner.nextInt() - 1;
        System.out.println(distance(adj, cost, x, y));
    }
}
