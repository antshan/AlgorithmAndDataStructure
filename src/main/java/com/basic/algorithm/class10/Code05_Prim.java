package com.basic.algorithm.class10;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class Code05_Prim {

    public static Set<Edge> prim(Graph graph) {
        if (graph == null) {
            return null;
        }
        Set<Node> nodeSet = new HashSet<>();
        Set<Edge> edgeSet = new HashSet<>();
        PriorityQueue<Edge> queue = new PriorityQueue<>(new Code04_Kruskal.EdgeComparator());
        for (Node node : graph.nodes.values()) {
            if (!nodeSet.contains(node)) {
                nodeSet.add(node);
                for (Edge edge : node.edges) {
                    queue.add(edge);
                }
                while (!queue.isEmpty()) {
                    Edge cur = queue.poll();
                    Node toNode = cur.to;
                    if (!nodeSet.contains(toNode)) {
                        edgeSet.add(cur);
                        nodeSet.add(toNode);
                        for (Edge edge : toNode.edges) {
                            queue.add(edge);
                        }
                    }
                }
            }
        }
        return edgeSet;
    }
}
