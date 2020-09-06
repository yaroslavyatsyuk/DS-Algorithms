package com.yyatsiuk.ds.graphs;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class Graph<T> {
    private final Map<T, Set<T>> adjacencyList = new HashMap<>();

    public void addVertex(T vertex) {
        adjacencyList.putIfAbsent(vertex, new LinkedHashSet<>());
    }

    public void addEdges(T vertex, Set<T> vertexes) {
        Set<T> v1 = adjacencyList.get(vertex);
        if (v1 != null) {
            v1.addAll(vertexes);
        } else {
            Set<T> edgeList = new LinkedHashSet<>(vertexes);
            adjacencyList.put(vertex, edgeList);
        }

        for (T v : vertexes) {
            addEdgeHelper(v, vertex);
        }
    }

    public void addEdge(T vertex1, T vertex2) {
        addEdgeHelper(vertex1, vertex2);
        addEdgeHelper(vertex2, vertex1);
    }

    public void addDirectedEdge(T vertex, T vertex2) {
        addEdgeHelper(vertex, vertex2);
    }

    private void addEdgeHelper(T v, T edge) {
        Set<T> v1 = adjacencyList.get(v);
        if (v1 != null) {
            v1.add(edge);
        } else {
            Set<T> edgeList = new LinkedHashSet<>();
            edgeList.add(edge);
            adjacencyList.put(v, edgeList);
        }
    }

    public Map<T, Set<T>> getAdjacencyList() {
        return adjacencyList;
    }

    public void explore(T vertex) {
        Map<T, Boolean> visited = new HashMap<>();
        exploreHelper(vertex, visited);
    }

    private void exploreHelper(T vertex, Map<T, Boolean> visited) {
        visited.put(vertex, true);
        System.out.println(vertex);
        for (T w : adjacencyList.get(vertex)) {
            Boolean isVisited = visited.get(w);
            if (isVisited == null || !isVisited) {
                exploreHelper(w, visited);
            }
        }
    }

    public void linearOrder() {
        Map<T, Set<T>> graph = new ConcurrentHashMap<>(adjacencyList);
        linearOrderHelper(graph);

    }

    private void linearOrderHelper(Map<T, Set<T>> graph) {
        Map<T, Boolean> visited = new HashMap<>();
        for (Map.Entry<T, Set<T>> entry : graph.entrySet()) {
            if (visited.get(entry.getKey()) == null) {
                exploreHelper(entry.getKey(), visited);
            }
        }
    }

    private T findSink(T vertex, Map<T, Set<T>> graph) {
        Set<T> edges = graph.get(vertex);
        if (edges == null || edges.isEmpty()) {
            return vertex;
        }

        T sink = null;
        for (T v : edges) {
            sink = findSink(v, graph);
        }

        return sink;
    }

    public static void main(String[] args) {
        Graph<Integer> graph = new Graph<>();

        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addVertex(4);
        graph.addVertex(5);
        graph.addDirectedEdge(1, 2);
        graph.addDirectedEdge(1, 3);
        graph.addDirectedEdge(2, 4);
        graph.addDirectedEdge(1, 5);


//        System.out.println(graph.getAdjacencyList());

        graph.linearOrder();
    }
}
