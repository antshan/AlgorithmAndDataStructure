package com.basic.algorithm.class10;

import java.util.*;

public class Code04_Kruskal {

    public static class UnionFind{

        Map<Node,Node> parentMap = new HashMap<>();
        Map<Node,Integer> sizeMap = new HashMap<>();

        public UnionFind() {
            this.parentMap = new HashMap<>();
            this.sizeMap = new HashMap<>();
        }

        public void makeSets(Collection<Node> nodes){
            parentMap.clear();
            sizeMap.clear();
            for (Node node : nodes) {
                parentMap.put(node, node);
                sizeMap.put(node, 1);
            }
        }

        public Node findFather(Node node){
            if (node == null){
                return null;
            }
            Queue<Node> queue = new LinkedList<>();
            while (node != parentMap.get(node)){
                queue.add(node);
                node = parentMap.get(node);
            }
            while (!queue.isEmpty()){
                parentMap.put(queue.poll(), node);
            }
            return node;
        }

        public boolean isSameSet(Node n1, Node n2){
            return findFather(n1) == findFather(n2);
        }

        public void union(Node n1, Node n2){
            if (n1 == null || n2 == null){
                return;
            }
            Node f1 = findFather(n1);
            Node f2 = findFather(n2);
            int s1 = sizeMap.get(f1);
            int s2 = sizeMap.get(f2);
            Node larger = s1 > s2 ? f1 : f2;
            Node smaller = larger == f1 ? f2 : f1;
            parentMap.put(smaller, larger);
            sizeMap.put(larger, s1 + s2);
            sizeMap.remove(smaller);
        }
    }

    public static class EdgeComparator implements Comparator<Edge>{
        @Override
        public int compare(Edge o1, Edge o2) {
            return o1.weight - o2.weight;
        }
    }

    public static Set<Edge> kruskalMST(Graph graph){
        UnionFind unionFind = new UnionFind();
        unionFind.makeSets(graph.nodes.values());

        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(new EdgeComparator());
        priorityQueue.addAll(graph.edges);

        Set<Edge> edges = new HashSet<>();
        while (!priorityQueue.isEmpty()){
            Edge cur = priorityQueue.poll();
            if (!unionFind.isSameSet(cur.from, cur.to)){
                unionFind.union(cur.from, cur.to);
                edges.add(cur);
            }
        }
        return edges;

    }
}
