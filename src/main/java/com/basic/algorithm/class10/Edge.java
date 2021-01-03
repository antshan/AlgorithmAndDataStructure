package com.basic.algorithm.class10;

public class Edge {

    int weight;
    Node from;
    Node to;

    public Edge(int weight, Node from, Node to) {
        this.weight = weight;
        this.from = from;
        this.to = to;
    }
}
