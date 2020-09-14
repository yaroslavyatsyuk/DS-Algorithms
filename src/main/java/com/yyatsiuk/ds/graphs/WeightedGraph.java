package com.yyatsiuk.ds.graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.PriorityQueue;

public class WeightedGraph<T> {
    private final Map<T, List<Edge<T>>> adjacencyList;

    public WeightedGraph() {
        this.adjacencyList = new HashMap<>();
    }

    public void addVertex(T vertex) {
        adjacencyList.put(vertex, new ArrayList<>());
    }

    public void addEdge(T v1, T v2, double weight) {
        addDirectedEdgeHelper(v1, v2, weight);
    }

    private void addDirectedEdgeHelper(T from, T to, double weight) {
        Edge<T> nEdge = new Edge<>(from, to, weight);
        List<Edge<T>> edges = adjacencyList.get(from);
        if (edges == null) {
            edges = new LinkedList<>();
            edges.add(nEdge);
            adjacencyList.put(from, edges);
        } else {
            edges.add(nEdge);
        }
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("WeightedGraph{\n");
        adjacencyList.forEach((key, value) -> {
            sb.append(key.toString()).append(" -> ").append("[");
            if (value != null) {
                for (Edge<T> edge : value) {
                    sb.append(" ").append(edge.to()).append(" ");
                }
            }
            sb.append("]\n");
        });
        sb.append('}');
        return sb.toString();
    }

    public Iterator<Edge<T>> adj(T v) {
        return adjacencyList.getOrDefault(v, new ArrayList<>()).iterator();
    }

    public int V() {
        return this.adjacencyList.size();
    }

    private static class Edge<T> {
        private final T v;
        private final T w;
        private double weight;

        Edge(T v, T w, double weight) {
            this.v = v;
            this.w = w;
            this.weight = weight;
        }

        T from() {
            return this.v;
        }

        T to() {
            return this.w;
        }

        public double getWeight() {
            return weight;
        }

        public void setWeight(double weight) {
            this.weight = weight;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Edge<?> edge = (Edge<?>) o;
            return Objects.equals(v, edge.v) &&
                    Objects.equals(w, edge.w);
        }

        @Override
        public int hashCode() {
            return Objects.hash(v, w);
        }
    }

    private static class Node<T> implements Comparable<Node<T>> {
        private final T vertex;
        private double weight;

        public Node(T vertex, double weight) {
            this.vertex = vertex;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node<T> o) {
            return Double.compare(this.weight, o.weight);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node<?> node = (Node<?>) o;
            return Double.compare(node.weight, weight) == 0;
        }

        @Override
        public int hashCode() {
            return Objects.hash(weight);
        }
    }

    private static class DijkstraResult<T> {
        private Map<T, Double> distance;
        private Map<T, T> prev;
    }

    public DijkstraResult<T> dijkstra(T s) {
        final Map<T, Double> distance = new HashMap<>();
        final Map<T, T> prev = new HashMap<>();
        initializePrevAndDistances(distance, prev);
        distance.put(s, 0.0d);

        PriorityQueue<Node<T>> queue = new PriorityQueue<>();
        queue.add(new Node<>(s, 0.0d));

        while (!queue.isEmpty()) {
            Node<T> node = queue.poll();
            List<Edge<T>> edges = adjacencyList.get(node.vertex);
            for (Edge<T> edge : edges) {
                T v = edge.from();
                T w = edge.to();
                Double dist = distance.get(w);
                if (dist > (distance.get(v) + edge.getWeight())) {
                    distance.put(w, distance.get(v) + edge.getWeight());
                    prev.put(w, v);
                    changePriority(queue, w, distance.get(w));
                }
            }
        }

        DijkstraResult<T> dijkstraResult = new DijkstraResult<>();
        dijkstraResult.distance = distance;
        dijkstraResult.prev = prev;
        return dijkstraResult;
    }

    private void initializePrevAndDistances(Map<T, Double> distance, Map<T, T> prev) {
        for (T u : adjacencyList.keySet()) {
            distance.put(u, Double.POSITIVE_INFINITY);
            prev.put(u, null);
        }
    }

    private void changePriority(PriorityQueue<Node<T>> queue, T vertex, Double newWeight) {
        for (Node<T> node : queue) {
            if (node.vertex.equals(vertex)) {
                node.weight = newWeight;
                return;
            }
        }

        queue.add(new Node<>(vertex, newWeight));
    }

    public static void main(String[] args) {
        WeightedGraph<String> weightedGraph = new WeightedGraph<>();
    }
}
