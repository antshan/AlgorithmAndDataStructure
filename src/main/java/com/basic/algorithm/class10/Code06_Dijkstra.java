package com.basic.algorithm.class10;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Code06_Dijkstra {

    public static Map<Node, Integer> dijkstra1(Node from) {
        Map<Node, Integer> distanceMap = new HashMap<>();
        distanceMap.put(from, 0);
        Set<Node> selectedNodes = new HashSet<>();

        Node minNode = getMinDistanceAndUnselectedNodes(distanceMap, selectedNodes);
        while (minNode != null) {
            for (Edge edge : minNode.edges) {
                Node toNode = edge.to;
                int distance = distanceMap.get(minNode);
                if (!distanceMap.containsKey(toNode)) {
                    distanceMap.put(toNode, distance + edge.weight);
                } else {
                    distanceMap.put(toNode, Math.min((distance + edge.weight), distanceMap.get(toNode)));
                }
            }
            selectedNodes.add(minNode);
            minNode = getMinDistanceAndUnselectedNodes(distanceMap, selectedNodes);
        }

        return distanceMap;
    }

    private static Node getMinDistanceAndUnselectedNodes(Map<Node, Integer> distanceMap, Set<Node> selectedNodes) {
        Node minNode = null;
        int minDistance = Integer.MAX_VALUE;
        for (Map.Entry<Node, Integer> entry : distanceMap.entrySet()) {
            Node temp = entry.getKey();
            int distance = entry.getValue();
            if (!selectedNodes.contains(temp) && distance < minDistance) {
                minNode = temp;
                minDistance = distance;
            }
        }
        return minNode;
    }

    public static class NodeRecord {

        Node node;
        int distance;

        public NodeRecord(Node node, int distance) {
            this.node = node;
            this.distance = distance;
        }
    }

    public static class NodeHeap {

        Node[] nodes;
        Map<Node, Integer> heapIndexMap;
        Map<Node, Integer> distanceMap;
        int size;

        public NodeHeap(int size) {
            nodes = new Node[size];
            heapIndexMap = new HashMap<>();
            distanceMap = new HashMap<>();
            size = 0;
        }

        public boolean isEntered(Node node) {
            return heapIndexMap.containsKey(node);
        }

        public boolean inHeap(Node node) {
            return isEntered(node) && heapIndexMap.get(node) != -1;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public void swap(int i, int j) {
            heapIndexMap.put(nodes[i], j);
            heapIndexMap.put(nodes[j], i);
            Node temp = nodes[i];
            nodes[i] = nodes[j];
            nodes[j] = temp;
        }

        private void heapInsert(Node node, int index) {
            while (distanceMap.get(nodes[index]) < distanceMap.get(nodes[(index - 1) / 2])) {
                swap(index, (index - 1) / 2);
                index = (index - 1) / 2;
            }
        }

        private void heapify(int index, int size) {
            int left = index * 2 + 1;
            while (left < size) {
                int smallest = (left + 1 < size && distanceMap.get(nodes[left + 1]) < distanceMap.get(nodes[left]))
                        ? left + 1 : left;
                smallest = distanceMap.get(nodes[index]) < distanceMap.get(nodes[smallest]) ? index : smallest;

                if (smallest == index) {
                    break;
                }
                swap(index, smallest);
                index = smallest;
                left = index * 2 + 1;
            }
        }

        public void addOrUpdateOrIgnore(Node node,int distance){
            if (inHeap(node)){
                distanceMap.put(node, Math.min(distanceMap.get(node), distance));
                heapInsert(node, distanceMap.get(node));
            }
            if (!isEntered(node)){
                nodes[size] = node;
                distanceMap.put(node, distance);
                heapIndexMap.put(node, size);
                heapInsert(node, size++);
            }
        }

        public NodeRecord pop(){
            NodeRecord nodeRecord = new NodeRecord(nodes[0], distanceMap.get(nodes[0]));
            swap(0, size - 1);
            heapIndexMap.put(nodes[size - 1], -1);
            distanceMap.remove(nodes[size - 1]);
            nodes[size -1] = null;
            heapify(0, --size);
            return nodeRecord;
        }
    }

    public static Map<Node, Integer> dijkstra2(Node from, int size){
        NodeHeap nodeHeap = new NodeHeap(size);
        nodeHeap.addOrUpdateOrIgnore(from, 0);
        Map<Node, Integer> res = new HashMap<>();
        while (!nodeHeap.isEmpty()){
            NodeRecord cur = nodeHeap.pop();
            Node node = cur.node;
            int distance = cur.distance;
            for (Edge edge : node.edges) {
                nodeHeap.addOrUpdateOrIgnore(edge.to, edge.weight + distance);
            }
            res.put(node, distance);
        }
        return res;
    }

}
