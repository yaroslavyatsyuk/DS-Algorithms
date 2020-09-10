package com.yyatsiuk.ds.graphs;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class StronglyConnected {
    private static int postCounter = 0;

    private static int numberOfStronglyConnectedComponents(ArrayList<Integer>[] adj) {
        List<Integer>[] rG = getReversedGraph(adj);
        Map<Integer, Integer> postOrder = dfsGetPostOrderValues(rG);
        postOrder = postOrder.entrySet()
                .stream()
                .sorted((c1, c2) -> c2.getValue().compareTo(c1.getValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

        boolean[] visited = new boolean[adj.length];
        int result = 0;
        for (Integer v : postOrder.keySet()) {
            if (!visited[v]) {
                explore(adj, v, visited);
                result++;
            }
        }

        return result;
    }

    private static List<Integer> getSCC(boolean[] visited2) {
        List<Integer> scc = new ArrayList<>();
        for (int i = 0; i < visited2.length; i++) {
            if (visited2[i]) {
                scc.add(i);
            }
        }

        return scc;
    }


    private static List<Integer>[] getReversedGraph(List<Integer>[] adj) {
        List<Integer>[] reversedGraph = (ArrayList<Integer>[]) new ArrayList[adj.length];

        for (int v = 0; v < adj.length; v++) {
            List<Integer> edges = adj[v];
            for (Integer edge : edges) {
                List<Integer> reversedEdgeList = reversedGraph[edge];
                if (reversedEdgeList == null) {
                    reversedEdgeList = new ArrayList<>();
                }
                reversedEdgeList.add(v);
                reversedGraph[edge] = reversedEdgeList;
            }
        }

        return reversedGraph;
    }

    private static Map<Integer, Integer> dfsGetPostOrderValues(List<Integer>[] adj) {
        boolean[] visited = new boolean[adj.length];
        Map<Integer, Integer> postOrder = new HashMap<>();
        int clock = 1;

        for (int v = 0; v < adj.length; v++) {
            if (!visited[v]) {
                explore(adj, v, visited, postOrder);
            }
        }

        return postOrder;
    }

    private static void explore(List<Integer>[] adj, int vertex, boolean[] visited) {
        visited[vertex] = true;
        List<Integer> edges = adj[vertex];
        if (edges != null) {
            for (Integer edge : edges) {
                if (!visited[edge]) {
                    explore(adj, edge, visited);
                }
            }
        }
    }

    private static void explore(List<Integer>[] adj, int vertex, boolean[] visited, Map<Integer, Integer> postOrder) {
        visited[vertex] = true;
        List<Integer> edges = adj[vertex];
        if (edges != null) {
            for (Integer edge : edges) {
                if (!visited[edge]) {
                    explore(adj, edge, visited, postOrder);
                }
            }
        }

        postOrder.put(vertex, postCounter++);
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
        }
        System.out.println(numberOfStronglyConnectedComponents(adj));
    }

}
