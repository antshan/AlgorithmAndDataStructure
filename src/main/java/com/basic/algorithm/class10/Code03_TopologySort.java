package com.basic.algorithm.class10;

import java.util.*;

public class Code03_TopologySort {

    public static List<Node> topologySort(Graph graph) {

        Map<Node, Integer> map = new HashMap<>();

        Queue<Node> zeroQueue = new LinkedList<>();

        for (Node node : graph.nodes.values()) {
            map.put(node, node.in);
            if (node.in == 0) {
                zeroQueue.add(node);
            }
        }

        List<Node> result = new ArrayList<>();
        while (!zeroQueue.isEmpty()) {
            Node node = zeroQueue.poll();
            result.add(node);
            for (Node next : node.nexts) {
                map.put(next, map.get(next) - 1);
                if (map.get(next) == 0) {
                    zeroQueue.add(next);
                }
            }

        }
        return result;
    }
}
